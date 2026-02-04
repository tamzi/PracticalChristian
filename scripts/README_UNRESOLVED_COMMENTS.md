# Unresolved Comments Extraction Tool

This directory contains tools to extract and track unresolved comments from closed pull requests.

## Files

- **`extract_unresolved_comments.js`** - Main extraction script (Node.js)
- **`extract_unresolved_comments.py`** - Alternative Python implementation (template)
- **`UNRESOLVED_COMMENTS.md`** - Generated report of all unresolved comments

## Usage

### Running the Extraction

```bash
# Using Node.js (recommended)
cd scripts
node extract_unresolved_comments.js
```

This will generate a comprehensive report at `scripts/UNRESOLVED_COMMENTS.md`.

## Generated Report

The report includes:

1. **Summary Statistics**
   - Total unresolved comments count
   - Breakdown by priority (P1, P2, Medium, Minor)
   - Number of affected PRs

2. **Issues by Priority**
   - P2 (Medium Priority) issues
   - Medium Severity issues
   - Minor issues
   
3. **Issues by File**
   - Grouped by affected file path
   - Quick reference for fixing related issues

## Report Format

Each issue includes:
- **Title**: Brief description of the issue
- **PR Number**: Which PR the comment came from
- **File and Line**: Exact location of the issue
- **Author**: Who left the comment
- **Description**: Full explanation of the issue
- **Link**: Direct link to the GitHub comment

## Current Status

As of 2026-02-04, the report contains:
- **18 unresolved comments** across 5 closed PRs
- **6 P2 (Medium Priority)** issues
- **2 Medium Severity** issues
- **10 Minor** issues

### Key Areas Requiring Attention

1. **SacramentImage Component** (3 P2 issues)
   - Missing error handling for image loading
   - Accessibility issues with placeholders
   
2. **Notifications Feature** (2 P2 issues)
   - UI state synchronization bugs in NotificationsHubViewModel

3. **Documentation** (1 P2 issue)
   - Inaccurate Firestore/Auth configuration claims

## Maintenance

To update the report with new unresolved comments:

1. Review closed PRs for unresolved comments
2. Add them to the `unresolvedComments` object in `extract_unresolved_comments.js`
3. Run the script to regenerate the report

## Integration with Development Workflow

This report serves as a backlog of technical debt and improvements identified during code review. Consider:

1. Prioritizing P2 issues in upcoming sprints
2. Addressing related issues together (grouped by file)
3. Including fixes in relevant feature work
4. Regular reviews to keep the list current
