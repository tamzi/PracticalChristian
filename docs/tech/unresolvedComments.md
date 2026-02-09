# Unresolved Comments Extraction Tool

Automated tool for extracting unresolved PR review comments, analyzing their relevance against the
current codebase, and generating a prioritized technical debt report.

## Purpose

Review comments on closed/merged PRs that were never resolved represent hidden technical debt. This
tool surfaces them, checks whether the issues are still present in the code, and produces an
actionable report sorted by severity and relevance.

## How It Works

The script runs a four-phase pipeline:

### Phase 1: Fetch

Queries the GitHub GraphQL API for all closed and merged PRs. Uses the `PullRequestReviewThread`
type which exposes `isResolved` — the only place GitHub tracks thread resolution status. Paginates
automatically to cover the full PR history.

### Phase 2: Codebase Relevance Analysis

For each unresolved comment that references a file, the script checks the current state of the repo:

- **File existence** — if the file was deleted, the issue is marked `file-removed`
- **Git history** — checks via `git log` whether the file was modified after the comment date
- **Code inspection** — reads the code around the referenced line and runs heuristic checks:
  - Error handling patterns (error/fallback/catch) for comments about missing error handling
  - Accessibility attributes (contentDescription) for accessibility-related comments
  - State update patterns (listState assignments) for state synchronization comments
  - Identifier presence for unused import/constant comments
  - Text pattern matching for incorrect naming/replacement comments
  - Documentation term matching for docs accuracy comments

Each comment gets a relevance status:

| Status | Meaning |
|--------|---------|
| `still-relevant` | Issue confirmed still present in the code |
| `likely-resolved` | Code appears to address the issue |
| `needs-review` | File changed but resolution unclear — human should verify |
| `file-removed` | Referenced file no longer exists |
| `not-applicable` | General comment with no file reference |

### Phase 3: Severity Classification

Keyword-based scan of comment text assigns a severity level:

| Severity | Trigger keywords |
|----------|-----------------|
| **High** | crash, security, regression, accessibility bug, state sync, missing error handling, broken |
| **Medium** | incorrect, misleading, missing, shadow, placeholder, fallback |
| **Low** | indent, formatting, unused, duplicate, naming, typo, redundant |

### Phase 4: Report Generation

Generates a markdown report at `docs/tasks/unresolvedComments.md` with sections ordered by
actionability:

1. **Actionable Issues** — confirmed still-relevant, sorted by severity
2. **Needs Manual Review** — file changed but unclear if fixed
3. **Likely Resolved** — appear addressed or no longer applicable
4. **Full List by PR** — reference table with all threads
5. **Issues by File** — grouped view for planning atomic fix PRs

## Usage

```bash
GITHUB_TOKEN=$(gh auth token) node scripts/extract_unresolved_comments.js
```

### Requirements

- Node.js 18+ (uses native `fetch`, no npm dependencies)
- `GITHUB_TOKEN` environment variable with repo read access
- Must be run from within the git repository (uses `git log` for history checks)

### Token Options

```bash
# Option 1: gh CLI (easiest if already authenticated)
GITHUB_TOKEN=$(gh auth token) node scripts/extract_unresolved_comments.js

# Option 2: Personal access token
GITHUB_TOKEN=ghp_xxx node scripts/extract_unresolved_comments.js
```

## Output

The report is written to `docs/tasks/unresolvedComments.md`. It sits alongside `workToBeDone.md` in
the tasks directory, serving as a complementary backlog of review-identified technical debt.

## Refresh Cadence

Run quarterly or before major releases to keep the backlog current. Since comments are fetched live
from GitHub and checked against the current codebase, re-running the script will automatically
detect issues that have been resolved since the last run.

## Limitations

- **Heuristic-based relevance** — the code inspection uses pattern matching, not full semantic
  analysis. Comments marked `needs-review` require human verification.
- **Severity is keyword-based** — may not perfectly match the actual impact. Treat as a starting
  point for triage, not a final classification.
- **GraphQL pagination** — fetches 30 PRs per page with up to 100 review threads each. Repos with
  very large PR histories may need the page size adjusted in the script configuration.

## Related

- See `scripts/extract_unresolved_comments.js` for the implementation
- See `docs/tasks/unresolvedComments.md` for the generated report
- See `docs/tasks/workToBeDone.md` for the main project task backlog
