#!/usr/bin/env node
/**
 * Extract Unresolved Comments from Closed PRs — with Codebase Relevance Analysis
 *
 * Phase 1: Queries GitHub GraphQL API for unresolved review threads
 * Phase 2: Checks each comment against the current codebase to determine relevance
 * Phase 3: Classifies severity from comment content
 * Phase 4: Generates a prioritized markdown report
 *
 * Requirements:
 *   - Node.js 18+ (uses native fetch)
 *   - GITHUB_TOKEN env variable with repo read access
 *   - Must be run from within the git repository
 *
 * Usage:
 *   GITHUB_TOKEN=$(gh auth token) node extract_unresolved_comments.js
 */

const fs = require('fs');
const path = require('path');
const { execSync } = require('child_process');

// --- Configuration ---

const OWNER = 'tamzi';
const REPO = 'PracticalChristian';
const GRAPHQL_URL = 'https://api.github.com/graphql';
const OUTPUT_FILE = path.join(__dirname, '..', 'docs', 'tasks', 'unresolvedComments.md');

// Repo root (resolved via git so the script works from any subdirectory)
const REPO_ROOT = execSync('git rev-parse --show-toplevel', { encoding: 'utf-8' }).trim();

// --- Severity keywords ---
// Each pattern maps to a severity. First match wins, so order matters.

const SEVERITY_RULES = [
  { severity: 'high', patterns: [
    /\bcrash/i, /\bsecurity/i, /\bvulnerab/i, /\bdata loss/i, /\bregression/i,
    /\baccessibility\s+(regression|issue|bug)/i, /\bstate\s+(sync|synchroni)/i,
    /\bwon't work/i, /\bdoesn't (work|handle)/i, /\bmissing\s+error\s+handling/i,
    /\bbug\b/i, /\bbroken\b/i, /\bincorrect\s+(behavior|behaviour)/i,
    /\bP[12]\b/,
  ]},
  { severity: 'medium', patterns: [
    /\bshadow/i, /\bincorrect/i, /\bwrong/i, /\bmisleading/i, /\binaccurate/i,
    /\bmissing\b/i, /\binconsistent\s+behavi/i, /\bconfusion/i, /\bpattern\s+match/i,
    /\bplaceholder/i, /\bfallback/i,
  ]},
  { severity: 'low', patterns: [
    /\bindent/i, /\bformat/i, /\bunused/i, /\bduplicat/i, /\bnaming/i,
    /\btypo/i, /\bstyle/i, /\bredundant/i, /\bimport/i, /\bcleanup/i,
    /\bcosmetic/i, /\bminor/i, /\bnit\b/i,
  ]},
];

// --- Relevance status values ---
// still-relevant : file + code area still looks like the issue described
// likely-resolved: file was modified after the comment and the code area changed
// file-removed   : the referenced file no longer exists in the repo
// needs-review   : couldn't determine automatically — human should look
// not-applicable : comment has no file reference (general/PR-level comment)

// --- GraphQL ---

const PR_QUERY = `
query($owner: String!, $repo: String!, $cursor: String) {
  repository(owner: $owner, name: $repo) {
    pullRequests(states: [CLOSED, MERGED], first: 30, after: $cursor, orderBy: {field: UPDATED_AT, direction: DESC}) {
      pageInfo {
        hasNextPage
        endCursor
      }
      nodes {
        number
        title
        url
        state
        mergedAt
        closedAt
        reviewThreads(first: 100) {
          nodes {
            isResolved
            comments(first: 10) {
              nodes {
                body
                url
                path
                line
                createdAt
                author {
                  login
                }
              }
            }
          }
        }
      }
    }
  }
}
`;

// --- Structured logging ---
// Outputs JSON lines (NDJSON) in CI / piped contexts for machine parsing,
// and clean human-readable text in interactive terminals.

const IS_TTY = process.stdout.isTTY === true;

/**
 * Emit a structured log entry.
 *
 * In TTY mode the human-readable `text` is printed to the appropriate stream.
 * In non-TTY mode a single JSON line is written to stdout (info/debug) or
 * stderr (warn/error) so automated tooling can ingest it.
 *
 * @param {'info'|'warn'|'error'|'debug'} level - Log severity
 * @param {string} event - Machine-friendly event name (e.g. 'fetch_prs')
 * @param {string} text  - Human-readable message (TTY output)
 * @param {Object} [data] - Optional structured payload for the JSON line
 */
function log(level, event, text, data = {}) {
  const stream = (level === 'error' || level === 'warn')
    ? process.stderr
    : process.stdout;

  if (IS_TTY) {
    stream.write(text + '\n');
    return;
  }

  const entry = {
    timestamp: new Date().toISOString(),
    level,
    event,
    message: text.replace(/[\u2700-\u27BF\u{1F300}-\u{1F9FF}]/gu, '').trim(),
    ...data,
  };
  stream.write(JSON.stringify(entry) + '\n');
}

// --- Error sanitization ---
// Prevents leaking sensitive data (tokens, absolute paths) in error output.

/**
 * Sanitize an error message for safe console output.
 *
 * Strips:
 *   - GITHUB_TOKEN value if it appears in the text
 *   - Absolute filesystem paths (replaced with repo-relative paths)
 *   - Overly long messages (truncated to a safe length)
 *
 * @param {string} message - Raw error message
 * @param {number} [maxLength=400] - Maximum output length
 * @returns {string} Sanitized message safe for stderr
 */
function sanitizeError(message, maxLength = 400) {
  if (typeof message !== 'string') return 'Unknown error';

  let safe = message;

  // Redact the GitHub token if it leaked into an error message
  const token = process.env.GITHUB_TOKEN;
  if (token && token.length > 4) {
    safe = safe.replaceAll(token, '[REDACTED]');
  }

  // Replace absolute repo paths with relative ones
  if (REPO_ROOT) {
    safe = safe.replaceAll(REPO_ROOT + '/', '');
    safe = safe.replaceAll(REPO_ROOT, '<repo>');
  }

  // Replace home directory references
  const home = process.env.HOME || process.env.USERPROFILE;
  if (home) {
    safe = safe.replaceAll(home, '~');
  }

  // Truncate overly verbose messages
  if (safe.length > maxLength) {
    safe = safe.substring(0, maxLength) + '... [truncated]';
  }

  return safe;
}

/**
 * Sanitize a stack trace, keeping only the first few frames and redacting paths.
 *
 * @param {string} stack - Raw Error.stack string
 * @param {number} [frameCount=3] - Number of stack frames to keep
 * @returns {string} Sanitized stack excerpt
 */
function sanitizeStack(stack, frameCount = 3) {
  if (typeof stack !== 'string') return '';
  const frames = stack.split('\n').slice(1, frameCount + 1);
  return sanitizeError(frames.join('\n'), 600);
}

// --- API helpers ---

function getToken() {
  const token = process.env.GITHUB_TOKEN;
  if (!token) {
    log('error', 'missing_token',
      'Error: GITHUB_TOKEN environment variable is required.\n' +
      'Set it with: export GITHUB_TOKEN=ghp_xxx\n' +
      'Or use:      GITHUB_TOKEN=$(gh auth token) node extract_unresolved_comments.js');
    process.exit(1);
  }
  return token;
}

async function graphql(query, variables) {
  const token = getToken();

  const response = await fetch(GRAPHQL_URL, {
    method: 'POST',
    headers: {
      'Authorization': `bearer ${token}`,
      'Content-Type': 'application/json',
      'User-Agent': 'PracticalChristian-UnresolvedComments/1.0',
    },
    body: JSON.stringify({ query, variables }),
  });

  if (!response.ok) {
    const text = await response.text();
    throw new Error(
      `GitHub API error (${response.status}): ${sanitizeError(text)}`
    );
  }

  const json = await response.json();
  if (json.errors) {
    // Only surface error messages, not full server details
    const messages = json.errors
      .map(e => e.message || 'unknown error')
      .join('; ');
    throw new Error(`GraphQL errors: ${sanitizeError(messages)}`);
  }

  return json.data;
}

// --- Phase 1: Fetch from GitHub ---

async function fetchAllClosedPRs() {
  const allPRs = [];
  let cursor = null;
  let page = 1;

  log('info', 'fetch_prs_start', `Fetching closed PRs from ${OWNER}/${REPO}...`, { owner: OWNER, repo: REPO });

  while (true) {
    const data = await graphql(PR_QUERY, { owner: OWNER, repo: REPO, cursor });
    const prs = data.repository.pullRequests;

    allPRs.push(...prs.nodes);
    log('info', 'fetch_prs_page', `  Page ${page}: fetched ${prs.nodes.length} PRs (total: ${allPRs.length})`, { page, pageFetched: prs.nodes.length, totalFetched: allPRs.length });

    if (!prs.pageInfo.hasNextPage) break;
    cursor = prs.pageInfo.endCursor;
    page++;
  }

  return allPRs;
}

function extractUnresolvedComments(prs) {
  const unresolvedByPR = {};

  for (const pr of prs) {
    const unresolvedThreads = pr.reviewThreads.nodes.filter(thread => !thread.isResolved);
    if (unresolvedThreads.length === 0) continue;

    const comments = unresolvedThreads.map(thread => {
      const firstComment = thread.comments.nodes[0];
      if (!firstComment) return null;

      const bodyLines = (firstComment.body || '').split('\n').filter(l => l.trim());
      // Strip markdown formatting (headers, bold, badge images) to get a clean title
      const cleanLines = bodyLines.map(l =>
        l.replace(/^#+\s*/, '')
         .replace(/!\[.*?\]\(.*?\)/g, '')      // strip markdown images/badges
         .replace(/\*\*<sub>.*?<\/sub>\*\*\s*/g, '') // strip badge-wrapped bold
         .replace(/^\*\*\s*(.*?)\s*\*\*$/g, '$1')   // unwrap bold text
         .replace(/<sub>|<\/sub>/g, '')               // strip remaining sub tags
         .trim()
      ).filter(l => l.length > 0);

      const title = cleanLines[0]
        ? cleanLines[0].substring(0, 120)
        : 'Untitled comment';

      return {
        title,
        file: firstComment.path || null,
        line: firstComment.line || null,
        author: firstComment.author?.login || 'unknown',
        description: firstComment.body || '',
        url: firstComment.url,
        createdAt: firstComment.createdAt,
      };
    }).filter(Boolean);

    if (comments.length > 0) {
      unresolvedByPR[`PR #${pr.number}`] = {
        prTitle: pr.title,
        prUrl: pr.url,
        prState: pr.mergedAt ? 'merged' : 'closed',
        comments,
      };
    }
  }

  return unresolvedByPR;
}

// --- Phase 2: Codebase relevance analysis ---

/**
 * Read file lines around a target line number.
 * Returns { exists, lines, content } where lines is an array of the surrounding lines.
 */
function readFileContext(filePath, targetLine, contextRadius = 15) {
  const absPath = path.join(REPO_ROOT, filePath);

  if (!fs.existsSync(absPath)) {
    return { exists: false, lines: [], content: '' };
  }

  try {
    const fullContent = fs.readFileSync(absPath, 'utf-8');
    const allLines = fullContent.split('\n');

    if (!targetLine) {
      // No specific line — return first 40 lines as context
      return { exists: true, lines: allLines.slice(0, 40), content: fullContent };
    }

    const start = Math.max(0, targetLine - contextRadius - 1);
    const end = Math.min(allLines.length, targetLine + contextRadius);
    return { exists: true, lines: allLines.slice(start, end), content: fullContent };
  } catch {
    return { exists: true, lines: [], content: '' };
  }
}

/**
 * Check if a file was modified after a given date using git log.
 */
function fileModifiedAfter(filePath, afterDate) {
  try {
    const absPath = path.join(REPO_ROOT, filePath);
    if (!fs.existsSync(absPath)) return false;

    const result = execSync(
      `git log --oneline --after="${afterDate}" -- "${filePath}"`,
      { cwd: REPO_ROOT, encoding: 'utf-8', timeout: 5000 }
    ).trim();

    return result.length > 0;
  } catch {
    return false;
  }
}

/**
 * Get the last commit date for a file.
 */
function getFileLastModified(filePath) {
  try {
    return execSync(
      `git log -1 --format="%aI" -- "${filePath}"`,
      { cwd: REPO_ROOT, encoding: 'utf-8', timeout: 5000 }
    ).trim();
  } catch {
    return null;
  }
}

/**
 * Extract key terms from a comment body for matching against current code.
 * Looks for function names, variable names, class names, and specific patterns.
 */
function extractSearchTerms(body) {
  const terms = [];

  // Match backtick-quoted identifiers (e.g., `markAsRead`, `SacramentImage`)
  const backtickMatches = body.match(/`([A-Za-z_][\w.]*)`/g) || [];
  for (const m of backtickMatches) {
    terms.push(m.replace(/`/g, ''));
  }

  // Match camelCase/PascalCase identifiers that look like code
  const identifiers = body.match(/\b[A-Z][a-zA-Z]{2,}\b/g) || [];
  // Filter out common English words
  const skipWords = new Set([
    'The', 'This', 'That', 'These', 'Those', 'When', 'Where', 'Which', 'What',
    'Consider', 'Instead', 'Because', 'However', 'Since', 'Similar', 'Should',
    'Could', 'Would', 'Both', 'Only', 'Also', 'Even', 'Still', 'Any', 'All',
    'Note', 'See', 'Use', 'Add', 'Set', 'Get', 'Has', 'Not', 'But', 'For',
    'May', 'Can', 'Are', 'Was', 'Its', 'Being', 'Based', 'File', 'Line',
    'Code', 'Here', 'With', 'From', 'Into', 'More', 'Some', 'Each',
    'Ensure', 'Avoid', 'Make', 'Keep', 'Check', 'Remove', 'Update',
    'According', 'Accor', 'Currently', 'Already', 'Between',
  ]);
  for (const id of identifiers) {
    if (!skipWords.has(id) && id.length > 3) {
      terms.push(id);
    }
  }

  return [...new Set(terms)];
}

/**
 * Determine whether the issue described in a comment is still present in the code.
 *
 * Returns: { relevance, reason, evidence }
 *   relevance: 'still-relevant' | 'likely-resolved' | 'file-removed' | 'needs-review' | 'not-applicable'
 */
function analyzeRelevance(comment) {
  // No file reference — can't check against codebase
  if (!comment.file) {
    return {
      relevance: 'not-applicable',
      reason: 'Comment is a general PR-level comment with no file reference.',
      evidence: null,
    };
  }

  const absPath = path.join(REPO_ROOT, comment.file);

  // File no longer exists
  if (!fs.existsSync(absPath)) {
    return {
      relevance: 'file-removed',
      reason: `File \`${comment.file}\` no longer exists in the repository.`,
      evidence: null,
    };
  }

  const { lines, content } = readFileContext(comment.file, comment.line);
  const wasModified = fileModifiedAfter(comment.file, comment.createdAt);
  const lastModified = getFileLastModified(comment.file);
  const searchTerms = extractSearchTerms(comment.description);
  const contextText = lines.join('\n');
  const lowerBody = comment.description.toLowerCase();

  // --- Heuristic checks ---

  // Check 1: If comment mentions missing error handling, check if error/catch/fallback was added
  if (lowerBody.includes('error handling') || lowerBody.includes('error placeholder') || lowerBody.includes('fallback')) {
    const hasErrorHandling = /\berror\s*[=:({]/i.test(contextText) ||
      /\bfallback\s*[=:({]/i.test(contextText) ||
      /\bcatch\s*[({]/i.test(contextText) ||
      /\.error\b/i.test(contextText) ||
      /onError/i.test(contextText);

    if (hasErrorHandling && wasModified) {
      return {
        relevance: 'likely-resolved',
        reason: `File was modified after the comment, and error/fallback handling now appears in the code around line ${comment.line || 'N/A'}.`,
        evidence: 'Found error/fallback/catch patterns in current code context.',
      };
    }
    if (!hasErrorHandling) {
      return {
        relevance: 'still-relevant',
        reason: `No error handling, fallback, or catch patterns found near line ${comment.line || 'N/A'}.`,
        evidence: `Searched for error/fallback/catch patterns in ${comment.file}.`,
      };
    }
  }

  // Check 2: If comment mentions unused import/variable/constant, check if it's still there
  if (lowerBody.includes('unused') || lowerBody.includes('duplicate constant') || lowerBody.includes('unused import')) {
    const matchedTerms = searchTerms.filter(term => content.includes(term));
    if (matchedTerms.length === 0) {
      return {
        relevance: 'likely-resolved',
        reason: `Referenced identifiers no longer found in the file.`,
        evidence: `Searched for: ${searchTerms.slice(0, 5).join(', ')}`,
      };
    }
    if (wasModified) {
      return {
        relevance: 'needs-review',
        reason: `File was modified, but referenced identifiers still exist: ${matchedTerms.join(', ')}. May or may not be resolved.`,
        evidence: `Found: ${matchedTerms.join(', ')} in current code.`,
      };
    }
    return {
      relevance: 'still-relevant',
      reason: `Referenced identifiers still present and file was not modified since comment.`,
      evidence: `Found: ${matchedTerms.join(', ')}`,
    };
  }

  // Check 3: If comment mentions accessibility (contentDescription, semantics)
  if (lowerBody.includes('accessibility') || lowerBody.includes('contentdescription') || lowerBody.includes('content description')) {
    const hasA11yFix = /contentDescription\s*=\s*(?!null)/i.test(contextText) ||
      /semantics/i.test(contextText);

    if (hasA11yFix && wasModified) {
      return {
        relevance: 'likely-resolved',
        reason: 'File modified and contentDescription/semantics patterns found in current code.',
        evidence: 'Found non-null contentDescription or semantics usage.',
      };
    }
    if (!hasA11yFix) {
      return {
        relevance: 'still-relevant',
        reason: 'Accessibility pattern (contentDescription) still appears missing or set to null.',
        evidence: `Checked code around line ${comment.line || 'N/A'}.`,
      };
    }
  }

  // Check 4: If comment is about state synchronization (listState, markAsRead, etc.)
  if (lowerBody.includes('liststate') || lowerBody.includes('state.') || lowerBody.includes('state sync')) {
    const stateTerms = searchTerms.filter(t =>
      t.toLowerCase().includes('state') || t.toLowerCase().includes('list')
    );
    const matchedInCode = stateTerms.filter(term => contextText.includes(term));

    if (wasModified && matchedInCode.length > 0) {
      // Check if the specific update pattern exists
      const hasStateUpdate = /listState\s*=/.test(contextText) || /copy\(.*listState/.test(contextText);
      if (hasStateUpdate) {
        return {
          relevance: 'likely-resolved',
          reason: 'File was modified and listState appears to be updated in the relevant code.',
          evidence: 'Found listState assignment in current code context.',
        };
      }
      return {
        relevance: 'still-relevant',
        reason: 'State identifiers found but no evidence of the synchronization fix.',
        evidence: `Found ${matchedInCode.join(', ')} but no listState update pattern.`,
      };
    }
  }

  // Check 5: If comment is about indentation/formatting
  if (lowerBody.includes('indent') || lowerBody.includes('formatting') || lowerBody.includes('spacing')) {
    if (wasModified) {
      return {
        relevance: 'likely-resolved',
        reason: 'File was modified after the comment. Formatting issues are often fixed incidentally.',
        evidence: `File last modified: ${lastModified || 'unknown'}.`,
      };
    }
    return {
      relevance: 'still-relevant',
      reason: 'File has not been modified since the comment was posted.',
      evidence: `Comment date: ${comment.createdAt}, last modified: ${lastModified || 'unknown'}.`,
    };
  }

  // Check 6: If comment mentions incorrect text/naming in preview
  if (lowerBody.includes('preview name') || lowerBody.includes('incorrect replacement') || lowerBody.includes('text replacement')) {
    const badTerms = searchTerms.filter(term =>
      term.includes('SacramentIcon') || term.includes('Sample Sacrament')
    );
    const stillBad = badTerms.filter(term => content.includes(term));

    if (stillBad.length > 0) {
      return {
        relevance: 'still-relevant',
        reason: `Incorrect text still present in file: ${stillBad.join(', ')}`,
        evidence: `Found in current ${comment.file}.`,
      };
    }
    if (wasModified) {
      return {
        relevance: 'likely-resolved',
        reason: 'File was modified and problematic text patterns not found.',
        evidence: `Searched for: ${badTerms.join(', ') || 'SacramentIcon patterns'}`,
      };
    }
  }

  // Check 7: Documentation accuracy (Firestore/Auth claims, SDK versions, etc.)
  if (comment.file.endsWith('.md') || comment.file.includes('docs/')) {
    const docTerms = searchTerms.filter(t => t.length > 4);
    const foundTerms = docTerms.filter(term => content.includes(term));

    if (wasModified && foundTerms.length === 0) {
      return {
        relevance: 'likely-resolved',
        reason: 'Documentation file was modified and referenced terms no longer found.',
        evidence: `Searched for: ${docTerms.slice(0, 5).join(', ')}`,
      };
    }
    if (foundTerms.length > 0) {
      return {
        relevance: 'still-relevant',
        reason: `Referenced documentation terms still present: ${foundTerms.slice(0, 3).join(', ')}`,
        evidence: `Found in current ${comment.file}.`,
      };
    }
  }

  // Check 8: Script/shell issues
  if (comment.file.endsWith('.sh')) {
    if (wasModified) {
      return {
        relevance: 'needs-review',
        reason: 'Script was modified after comment but needs manual verification.',
        evidence: `File last modified: ${lastModified || 'unknown'}.`,
      };
    }
    return {
      relevance: 'still-relevant',
      reason: 'Script has not been modified since the comment was posted.',
      evidence: `Comment: ${comment.createdAt}, last modified: ${lastModified || 'unknown'}.`,
    };
  }

  // Fallback: generic heuristic based on whether file changed + search terms
  if (wasModified) {
    const matchedTerms = searchTerms.filter(term => content.includes(term));
    if (matchedTerms.length === 0) {
      return {
        relevance: 'likely-resolved',
        reason: 'File was modified and referenced code identifiers no longer found in context.',
        evidence: `Searched for: ${searchTerms.slice(0, 5).join(', ')}`,
      };
    }
    return {
      relevance: 'needs-review',
      reason: `File was modified but referenced identifiers still exist. Needs manual check.`,
      evidence: `Still found: ${matchedTerms.slice(0, 5).join(', ')}`,
    };
  }

  // File not modified since comment was posted — issue likely still relevant
  return {
    relevance: 'still-relevant',
    reason: `File has not been modified since the comment (${comment.createdAt}).`,
    evidence: `Last modified: ${lastModified || 'unknown'}.`,
  };
}

// --- Phase 3: Severity classification ---

function classifySeverity(comment) {
  const text = comment.description + ' ' + comment.title;

  for (const rule of SEVERITY_RULES) {
    for (const pattern of rule.patterns) {
      if (pattern.test(text)) {
        return rule.severity;
      }
    }
  }

  return 'low'; // default
}

// --- Phase 4: Post-process all comments ---

function postProcess(unresolvedByPR) {
  log('info', 'analysis_start', 'Analyzing relevance against current codebase...');
  let analyzed = 0;

  for (const [prKey, prData] of Object.entries(unresolvedByPR)) {
    for (const comment of prData.comments) {
      comment.severity = classifySeverity(comment);
      const analysis = analyzeRelevance(comment);
      comment.relevance = analysis.relevance;
      comment.relevanceReason = analysis.reason;
      comment.relevanceEvidence = analysis.evidence;
      analyzed++;
    }
  }

  // Emit analysis summary
  const allComments = Object.values(unresolvedByPR).flatMap(pr => pr.comments);
  const bySeverity = { high: 0, medium: 0, low: 0 };
  const byRelevance = {};

  for (const c of allComments) {
    switch (c.severity) {
      case 'high':   bySeverity.high++;   break;
      case 'medium': bySeverity.medium++; break;
      case 'low':    bySeverity.low++;    break;
      default:
        log('warn', 'unknown_severity',
          `Unknown severity "${c.severity}" on comment "${c.title}". This issue will not be categorized.`,
          { severity: c.severity, title: c.title, url: c.url });
    }
    byRelevance[c.relevance] = (byRelevance[c.relevance] || 0) + 1;
  }

  log('info', 'analysis_complete',
    `  Analyzed ${analyzed} comments.\n` +
    `  Severity:  ${bySeverity.high} high, ${bySeverity.medium} medium, ${bySeverity.low} low\n` +
    `  Relevance: ${Object.entries(byRelevance).map(([k, v]) => `${v} ${k}`).join(', ')}`,
    { analyzed, severity: bySeverity, relevance: byRelevance });
}

// --- Report generation ---

const RELEVANCE_ICONS = {
  'still-relevant': '🔴',
  'likely-resolved': '🟢',
  'file-removed': '⚫',
  'needs-review': '🟡',
  'not-applicable': '⚪',
};

const SEVERITY_ICONS = {
  'high': '🔴 High',
  'medium': '🟠 Medium',
  'low': '⚪ Low',
};

function generateMarkdownReport(unresolvedByPR) {
  const lines = [];
  const allComments = [];

  for (const [prKey, prData] of Object.entries(unresolvedByPR)) {
    for (const comment of prData.comments) {
      allComments.push({ prKey, prData, ...comment });
    }
  }

  // --- Header ---
  lines.push('# Unresolved Comments from Closed PRs');
  lines.push('');
  lines.push(`**Repository:** ${OWNER}/${REPO}`);
  lines.push(`**Generated:** ${new Date().toISOString().split('T')[0]}`);
  lines.push(`**Method:** Automated extraction + codebase relevance analysis`);
  lines.push('');
  lines.push('---');
  lines.push('');

  // --- Summary stats ---
  const totalComments = allComments.length;
  const totalPRs = Object.keys(unresolvedByPR).length;
  const bySeverity = { high: 0, medium: 0, low: 0 };
  const byRelevance = {};

  for (const c of allComments) {
    switch (c.severity) {
      case 'high':   bySeverity.high++;   break;
      case 'medium': bySeverity.medium++; break;
      case 'low':    bySeverity.low++;    break;
      default: break; // Already warned during postProcess
    }
    byRelevance[c.relevance] = (byRelevance[c.relevance] || 0) + 1;
  }

  lines.push('## Summary');
  lines.push('');
  lines.push(`- **Total Unresolved Threads:** ${totalComments}`);
  lines.push(`- **PRs with Unresolved Comments:** ${totalPRs}`);
  lines.push('');
  lines.push('### Severity Breakdown');
  lines.push('');
  lines.push(`| Severity | Count |`);
  lines.push(`|----------|-------|`);
  lines.push(`| 🔴 High | ${bySeverity.high} |`);
  lines.push(`| 🟠 Medium | ${bySeverity.medium} |`);
  lines.push(`| ⚪ Low | ${bySeverity.low} |`);
  lines.push('');
  lines.push('### Relevance Breakdown');
  lines.push('');
  lines.push('| Status | Meaning | Count |');
  lines.push('|--------|---------|-------|');
  lines.push(`| 🔴 Still Relevant | Issue still present in code | ${byRelevance['still-relevant'] || 0} |`);
  lines.push(`| 🟡 Needs Review | File changed, unclear if fixed | ${byRelevance['needs-review'] || 0} |`);
  lines.push(`| 🟢 Likely Resolved | Code appears to address the issue | ${byRelevance['likely-resolved'] || 0} |`);
  lines.push(`| ⚫ File Removed | Referenced file no longer exists | ${byRelevance['file-removed'] || 0} |`);
  lines.push(`| ⚪ Not Applicable | General comment, no file reference | ${byRelevance['not-applicable'] || 0} |`);
  lines.push('');
  lines.push('---');
  lines.push('');

  // --- Actionable items first: still-relevant, sorted by severity ---
  lines.push('## Actionable Issues (Still Relevant)');
  lines.push('');

  const actionable = allComments
    .filter(c => c.relevance === 'still-relevant')
    .sort((a, b) => {
      const order = { high: 0, medium: 1, low: 2 };
      return (order[a.severity] ?? 3) - (order[b.severity] ?? 3);
    });

  if (actionable.length === 0) {
    lines.push('*No issues confirmed as still relevant. Nice!*');
    lines.push('');
  } else {
    lines.push(`**${actionable.length} issues** confirmed still present in the codebase.`);
    lines.push('');

    for (const [idx, comment] of actionable.entries()) {
      lines.push(`### ${idx + 1}. ${comment.title}`);
      lines.push('');
      lines.push(`| | |`);
      lines.push(`|---|---|`);
      lines.push(`| **Severity** | ${SEVERITY_ICONS[comment.severity]} |`);
      lines.push(`| **File** | \`${comment.file || 'N/A'}\`${comment.line ? ` (Line ${comment.line})` : ''} |`);
      lines.push(`| **PR** | ${comment.prKey} |`);
      lines.push(`| **Author** | @${comment.author} |`);
      lines.push(`| **Why still relevant** | ${comment.relevanceReason} |`);
      lines.push('');
      const desc = comment.description.length > 600
        ? comment.description.substring(0, 600) + '...'
        : comment.description;
      lines.push(desc);
      lines.push('');
      lines.push(`[View Comment](${comment.url})`);
      lines.push('');
    }
  }

  // --- Needs review ---
  lines.push('---');
  lines.push('');
  lines.push('## Needs Manual Review');
  lines.push('');

  const needsReview = allComments.filter(c => c.relevance === 'needs-review');

  if (needsReview.length === 0) {
    lines.push('*No items need manual review.*');
    lines.push('');
  } else {
    lines.push(`**${needsReview.length} issues** where the file changed but resolution is unclear.`);
    lines.push('');

    for (const [idx, comment] of needsReview.entries()) {
      lines.push(`${idx + 1}. **${comment.title}** — ${SEVERITY_ICONS[comment.severity]}`);
      lines.push(`   - \`${comment.file || 'N/A'}\`${comment.line ? ` (Line ${comment.line})` : ''}`);
      lines.push(`   - ${comment.relevanceReason}`);
      lines.push(`   - [View Comment](${comment.url})`);
      lines.push('');
    }
  }

  // --- Likely resolved ---
  lines.push('---');
  lines.push('');
  lines.push('## Likely Resolved');
  lines.push('');

  const resolved = allComments.filter(c => c.relevance === 'likely-resolved' || c.relevance === 'file-removed');

  if (resolved.length === 0) {
    lines.push('*None detected.*');
    lines.push('');
  } else {
    lines.push(`**${resolved.length} issues** that appear to have been addressed or are no longer applicable.`);
    lines.push('');

    for (const [idx, comment] of resolved.entries()) {
      const icon = RELEVANCE_ICONS[comment.relevance];
      lines.push(`${idx + 1}. ${icon} **${comment.title}** (${comment.prKey})`);
      lines.push(`   - \`${comment.file || 'N/A'}\` — ${comment.relevanceReason}`);
      lines.push(`   - [View Comment](${comment.url})`);
      lines.push('');
    }
  }

  // --- Full list by PR (for reference) ---
  lines.push('---');
  lines.push('');
  lines.push('## Full List by PR');
  lines.push('');

  for (const [prKey, prData] of Object.entries(unresolvedByPR)) {
    lines.push(`### ${prKey}: ${prData.prTitle}`);
    lines.push('');
    lines.push(`**State:** ${prData.prState} | **Threads:** ${prData.comments.length} | [View PR](${prData.prUrl})`);
    lines.push('');

    lines.push('| # | Title | Severity | Relevance | File |');
    lines.push('|---|-------|----------|-----------|------|');

    prData.comments.forEach((comment, idx) => {
      const relIcon = RELEVANCE_ICONS[comment.relevance] || '❓';
      const sevLabel = SEVERITY_ICONS[comment.severity] || comment.severity;
      const shortTitle = comment.title.substring(0, 70) + (comment.title.length > 70 ? '...' : '');
      const fileShort = comment.file
        ? comment.file.split('/').slice(-2).join('/')
        : 'N/A';
      lines.push(`| ${idx + 1} | [${shortTitle}](${comment.url}) | ${sevLabel} | ${relIcon} ${comment.relevance} | \`${fileShort}\` |`);
    });

    lines.push('');
  }

  // --- Issues by file ---
  lines.push('---');
  lines.push('');
  lines.push('## Issues by File');
  lines.push('');

  const byFile = {};
  for (const comment of allComments) {
    const file = comment.file || 'N/A';
    if (!byFile[file]) byFile[file] = [];
    byFile[file].push(comment);
  }

  for (const [file, issues] of Object.entries(byFile).sort()) {
    const activeCount = issues.filter(i => i.relevance === 'still-relevant').length;
    const indicator = activeCount > 0 ? ` — **${activeCount} active**` : '';
    lines.push(`### \`${file}\`${indicator}`);
    lines.push('');
    for (const [idx, issue] of issues.entries()) {
      const relIcon = RELEVANCE_ICONS[issue.relevance] || '❓';
      lines.push(`${idx + 1}. ${relIcon} **${issue.title}** (${issue.prKey}) — ${SEVERITY_ICONS[issue.severity]}`);
      lines.push(`   - [View Comment](${issue.url})`);
      lines.push('');
    }
  }

  return lines.join('\n');
}

// --- Main ---

async function main() {
  try {
    const prs = await fetchAllClosedPRs();
    log('info', 'fetch_prs_done', `Found ${prs.length} closed/merged PRs total.`, { prCount: prs.length });

    const unresolvedByPR = extractUnresolvedComments(prs);
    const totalUnresolved = Object.values(unresolvedByPR)
      .reduce((sum, pr) => sum + pr.comments.length, 0);
    const prWithComments = Object.keys(unresolvedByPR).length;

    log('info', 'extract_done', `Found ${totalUnresolved} unresolved comment threads across ${prWithComments} PRs.`, { totalUnresolved, prsWithComments: prWithComments });

    if (totalUnresolved === 0) {
      log('info', 'no_comments', 'No unresolved comments found. No report generated.');
      return;
    }

    // Post-process: check relevance + classify severity
    postProcess(unresolvedByPR);

    const report = generateMarkdownReport(unresolvedByPR);

    // Ensure the output directory exists before writing
    const outputDir = path.dirname(OUTPUT_FILE);
    try {
      fs.mkdirSync(outputDir, { recursive: true });
    } catch (mkdirErr) {
      const reason = sanitizeError(mkdirErr.message);
      const fix = (mkdirErr.code === 'EACCES' || mkdirErr.code === 'EPERM')
        ? 'Check write permissions on the output directory.'
        : undefined;
      log('error', 'mkdir_failed',
        `Failed to create output directory.\n   Reason: ${reason}` +
        (fix ? `\n   Fix: ${fix}` : ''),
        { code: mkdirErr.code || 'unknown', reason, fix });
      process.exit(1);
    }

    try {
      fs.writeFileSync(OUTPUT_FILE, report);
    } catch (writeErr) {
      const reason = sanitizeError(writeErr.message);
      let fix;
      if (writeErr.code === 'EACCES' || writeErr.code === 'EPERM') {
        fix = 'Check file/directory permissions for the output location.';
      } else if (writeErr.code === 'ENOSPC') {
        fix = 'Disk is full. Free up space and try again.';
      } else if (writeErr.code === 'EROFS') {
        fix = 'Filesystem is read-only. Remount with write access or choose a different output path.';
      }
      log('error', 'write_failed',
        `Failed to write report file.\n   Reason: ${reason}` +
        (fix ? `\n   Fix: ${fix}` : `\n   Error code: ${writeErr.code || 'unknown'}`),
        { code: writeErr.code || 'unknown', reason, fix });
      process.exit(1);
    }

    log('info', 'report_written', `Report written to: ${OUTPUT_FILE}`, { outputFile: OUTPUT_FILE });
  } catch (err) {
    log('error', 'fatal',
      `Fatal error: ${sanitizeError(err.message)}` +
      (err.stack ? `\n   Stack trace: ${sanitizeStack(err.stack)}` : ''),
      { error: sanitizeError(err.message) });
    process.exit(1);
  }
}

main();
