#!/usr/bin/env node
/**
 * Extract Unresolved Comments from Closed PRs
 * 
 * This script extracts all unresolved review comments from closed PRs
 * to create a working list of bugfixes and improvements.
 */

const fs = require('fs');
const path = require('path');

// Manually collected unresolved comments from GitHub API
const unresolvedComments = {
  "PR #14": [
    {
      title: "Remove inaccurate Firestore/Auth configuration claim",
      severity: "P2",
      file: "docs/tech/technicalArchitecture.md",
      line: 81,
      author: "chatgpt-codex-connector",
      description: "This section states that Firestore is already configured for cloud sync and that Firebase Auth handles user accounts, but the codebase only declares Firebase Analytics/Crashlytics/Performance (see `app/build.gradle.kts`) and has no Firestore/Auth dependencies. That makes the architecture doc misleading for anyone planning sync/auth work based on current implementation. Consider rephrasing as future/planned, or adding the missing dependencies/config before claiming it's configured.",
      url: "https://github.com/tamzi/PracticalChristian/pull/14#discussion_r2730155254"
    }
  ],
  "PR #8": [
    {
      title: "Incorrect text replacement in preview",
      severity: "Minor",
      file: "feature/tags/src/main/java/com/practicalchristian/app/feature/tags/TagsScreen.kt",
      line: 454,
      author: "copilot-pull-request-reviewer",
      description: "The text \"Sample SacramentIconTag\" appears to be an incorrect replacement. This is user-facing content in a preview function, and should remain as \"Sample Tag\". The icon naming changes should not affect string literals that represent user data or UI labels.",
      url: "https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751296"
    },
    {
      title: "Incorrect preview name replacement",
      severity: "Minor",
      file: "feature/home/src/main/java/com/practicalchristian/app/feature/home/home/HomeScreen.kt",
      line: 895,
      author: "copilot-pull-request-reviewer",
      description: "The preview name \"SacramentIconHome Screen - Full\" appears to be an incorrect replacement. This should remain as \"Home Screen - Full\" since it's describing the screen being previewed, not an icon.",
      url: "https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751315"
    },
    {
      title: "Incorrect preview name in BooksScreen",
      severity: "Minor",
      file: "feature/books/src/main/java/com/practicalchristian/app/feature/books/list/BooksScreen.kt",
      line: 294,
      author: "copilot-pull-request-reviewer",
      description: "The preview name \"BooksScreen - SacramentIconList View with Books\" appears to be an incorrect replacement. This should remain as \"BooksScreen - List View with Books\".",
      url: "https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751341"
    },
    {
      title: "Missing error handling in SacramentImage",
      severity: "P2",
      file: "sacrament/src/main/java/com/sacrament/ui/primitives/SacramentImage.kt",
      line: 54,
      author: "copilot-pull-request-reviewer",
      description: "The `SacramentImage` component doesn't handle image loading errors. When an AsyncImage fails to load, it will simply display nothing, leaving the user with a blank space. Consider adding an error placeholder parameter or using the `error` parameter of AsyncImage to display the placeholder icon when image loading fails.",
      url: "https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751379"
    },
    {
      title: "Show placeholder on load errors or blank URLs",
      severity: "P2",
      file: "sacrament/src/main/java/com/sacrament/ui/primitives/SacramentImage.kt",
      line: 50,
      author: "chatgpt-codex-connector",
      description: "The new `SacramentImage` only switches to the placeholder when `imageUrl` is `null`; any empty string or invalid URL still runs `AsyncImage` with no `error`/`fallback` painter. Consider treating `isNullOrBlank()` as missing and/or wiring Coil's `error`/`fallback`/`placeholder` slots so failures still render the placeholder.",
      url: "https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705758175"
    },
    {
      title: "Preserve accessibility text for placeholders",
      severity: "P2",
      file: "sacrament/src/main/java/com/sacrament/ui/primitives/SacramentImage.kt",
      line: 92,
      author: "chatgpt-codex-connector",
      description: "When `imageUrl` is null, `SacramentImagePlaceholder` hardcodes `contentDescription = null`, so any caller-supplied `contentDescription` is effectively dropped. This is an accessibility regression; consider passing the description through to the placeholder.",
      url: "https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705758179"
    }
  ],
  "PR #4": [
    {
      title: "Inconsistent indentation in NotificationsHubScreen",
      severity: "Minor",
      file: "feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubScreen.kt",
      line: 298,
      author: "copilot-pull-request-reviewer",
      description: "The indentation for the `iconData` parameter and its closing parenthesis is inconsistent. The properties of `NotificationIconData` have extra indentation (8 spaces instead of the expected alignment).",
      url: "https://github.com/tamzi/PracticalChristian/pull/4#discussion_r2680299693"
    },
    {
      title: "Inconsistent indentation (second occurrence)",
      severity: "Minor",
      file: "feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubScreen.kt",
      line: 318,
      author: "copilot-pull-request-reviewer",
      description: "The indentation for the `iconData` parameter and its closing parenthesis is inconsistent, similar to the issue on lines 291-298.",
      url: "https://github.com/tamzi/PracticalChristian/pull/4#discussion_r2680299701"
    }
  ],
  "PR #2": [
    {
      title: "Update listState when marking a notification read",
      severity: "P2",
      file: "feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubViewModel.kt",
      line: 109,
      author: "chatgpt-codex-connector",
      description: "The UI renders the list from `state.listState`, but `markAsRead` only updates `sections` and `unreadCount`. Because `listState` still holds the old list, tapping a notification will not clear the unread dot in the list until a full refresh. Consider updating `listState` with the new `updatedSections`.",
      url: "https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675653870"
    },
    {
      title: "Keep listState in sync when marking all read",
      severity: "P2",
      file: "feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubViewModel.kt",
      line: 132,
      author: "chatgpt-codex-connector",
      description: "Similar to `markAsRead`, `markAllAsRead` updates `sections` and `unreadCount` but leaves `listState` unchanged. Since the screen uses `state.listState` to render the list, unread indicators will remain visible even though the badge count hits 0.",
      url: "https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675653871"
    },
    {
      title: "Missing Flow properties for notification preferences",
      severity: "Minor",
      file: "core/datasource/local/src/main/java/com/practicalchristian/app/core/localdatasource/preferences/user/UserPreferences.kt",
      line: 16,
      author: "copilot-pull-request-reviewer",
      description: "The new notification permission methods are missing corresponding Flow properties to read the values. The existing pattern provides both Flow getters and suspend setters. The notification permission state should follow this same pattern.",
      url: "https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656735"
    },
    {
      title: "Path exclusion pattern won't work correctly",
      severity: "Medium",
      file: "scripts/check-design-system-usage.sh",
      line: 96,
      author: "copilot-pull-request-reviewer",
      description: "The path exclusion pattern uses a glob pattern that won't work correctly in bash's conditional test. In bash, glob patterns need to be used with `case` statements or the `=~` operator with proper escaping.",
      url: "https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656743"
    }
  ],
  "PR #1": [
    {
      title: "Shadow standard Compose testTag function",
      severity: "Medium",
      file: "sacrament/src/main/java/com/sacrament/ui/testing/TestTags.kt",
      line: 103,
      author: "copilot-pull-request-reviewer",
      description: "This extension function shadows the standard Compose testTag function. The parameter names match but the implementation differs - this version combines testTag with contentDescription, while the standard one only sets the testTag. This creates potential confusion.",
      url: "https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947914"
    },
    {
      title: "Duplicate constant MIN_TOUCH_TARGET_SIZE_DP",
      severity: "Minor",
      file: "sacrament/src/main/java/com/sacrament/ui/testing/AccessibilityDefaults.kt",
      line: 18,
      author: "copilot-pull-request-reviewer",
      description: "There's duplication between MIN_TOUCH_TARGET_SIZE_DP (Float constant) and MIN_TOUCH_TARGET_SIZE (Dp value). Both represent the same value (48) in different units. The Float constant appears unused.",
      url: "https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947915"
    },
    {
      title: "Redundant commit filtering logic",
      severity: "Minor",
      file: "scripts/pre-push.sh",
      line: 136,
      author: "copilot-pull-request-reviewer",
      description: "The logic for filtering commits has redundancy. Lines 117-122 filter commits to exclude those on origin/main when getting the commit list, and then lines 130-135 skip those same commits again in the loop. This double-filtering is unnecessary.",
      url: "https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947917"
    },
    {
      title: "Unused import statements in TestTags.kt",
      severity: "Minor",
      file: "sacrament/src/main/java/com/sacrament/ui/testing/TestTags.kt",
      line: 3,
      author: "copilot-pull-request-reviewer",
      description: "The import statement for SemanticsProperties on line 3 is unused. None of the SemanticsProperties members are directly accessed in this file.",
      url: "https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947928"
    },
    {
      title: "Unused AccessibilityDefaults.Roles object",
      severity: "Minor",
      file: "sacrament/src/main/java/com/sacrament/ui/testing/AccessibilityDefaults.kt",
      line: 75,
      author: "copilot-pull-request-reviewer",
      description: "The AccessibilityDefaults.Roles object defines string constants for roles, but these appear to be unused. In Compose, semantic roles are set using the Role enum, not string literals.",
      url: "https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947929"
    }
  ]
};

function generateMarkdownReport() {
  const reportLines = [];
  
  reportLines.push("# Unresolved Comments from Closed PRs");
  reportLines.push("");
  reportLines.push(`**Repository:** tamzi/PracticalChristian`);
  reportLines.push(`**Generated:** ${new Date().toISOString().split('T')[0]}`);
  reportLines.push("");
  reportLines.push("This report contains all unresolved review comments from closed pull requests.");
  reportLines.push("Use this list to track potential bugfixes and improvements.");
  reportLines.push("");
  reportLines.push("---");
  reportLines.push("");
  
  // Count total issues
  let totalIssues = 0;
  let p1Count = 0;
  let p2Count = 0;
  let mediumCount = 0;
  let minorCount = 0;
  
  Object.values(unresolvedComments).forEach(comments => {
    comments.forEach(comment => {
      totalIssues++;
      if (comment.severity === "P1") p1Count++;
      else if (comment.severity === "P2") p2Count++;
      else if (comment.severity === "Medium") mediumCount++;
      else minorCount++;
    });
  });
  
  reportLines.push("## Summary");
  reportLines.push("");
  reportLines.push(`- **Total Unresolved Comments:** ${totalIssues}`);
  reportLines.push(`- **P1 (High Priority):** ${p1Count}`);
  reportLines.push(`- **P2 (Medium Priority):** ${p2Count}`);
  reportLines.push(`- **Medium Severity:** ${mediumCount}`);
  reportLines.push(`- **Minor Issues:** ${minorCount}`);
  reportLines.push(`- **PRs with Unresolved Comments:** ${Object.keys(unresolvedComments).length}`);
  reportLines.push("");
  reportLines.push("---");
  reportLines.push("");
  
  // Group by severity
  reportLines.push("## Issues by Priority");
  reportLines.push("");
  
  // P2 Issues
  const p2Issues = [];
  Object.entries(unresolvedComments).forEach(([pr, comments]) => {
    comments.filter(c => c.severity === "P2").forEach(comment => {
      p2Issues.push({ pr, ...comment });
    });
  });
  
  if (p2Issues.length > 0) {
    reportLines.push("### 🟡 P2 - Medium Priority");
    reportLines.push("");
    p2Issues.forEach((issue, idx) => {
      reportLines.push(`#### ${idx + 1}. ${issue.title} (${issue.pr})`);
      reportLines.push("");
      reportLines.push(`**File:** \`${issue.file}\` (Line ${issue.line})`);
      reportLines.push(`**Author:** @${issue.author}`);
      reportLines.push("");
      reportLines.push(issue.description);
      reportLines.push("");
      reportLines.push(`[View Comment](${issue.url})`);
      reportLines.push("");
    });
  }
  
  // Medium Issues
  const mediumIssues = [];
  Object.entries(unresolvedComments).forEach(([pr, comments]) => {
    comments.filter(c => c.severity === "Medium").forEach(comment => {
      mediumIssues.push({ pr, ...comment });
    });
  });
  
  if (mediumIssues.length > 0) {
    reportLines.push("### 🟠 Medium Severity");
    reportLines.push("");
    mediumIssues.forEach((issue, idx) => {
      reportLines.push(`#### ${idx + 1}. ${issue.title} (${issue.pr})`);
      reportLines.push("");
      reportLines.push(`**File:** \`${issue.file}\` (Line ${issue.line})`);
      reportLines.push(`**Author:** @${issue.author}`);
      reportLines.push("");
      reportLines.push(issue.description);
      reportLines.push("");
      reportLines.push(`[View Comment](${issue.url})`);
      reportLines.push("");
    });
  }
  
  // Minor Issues
  const minorIssues = [];
  Object.entries(unresolvedComments).forEach(([pr, comments]) => {
    comments.filter(c => c.severity === "Minor").forEach(comment => {
      minorIssues.push({ pr, ...comment });
    });
  });
  
  if (minorIssues.length > 0) {
    reportLines.push("### ⚪ Minor Issues");
    reportLines.push("");
    minorIssues.forEach((issue, idx) => {
      reportLines.push(`#### ${idx + 1}. ${issue.title} (${issue.pr})`);
      reportLines.push("");
      reportLines.push(`**File:** \`${issue.file}\` (Line ${issue.line})`);
      reportLines.push(`**Author:** @${issue.author}`);
      reportLines.push("");
      reportLines.push(issue.description);
      reportLines.push("");
      reportLines.push(`[View Comment](${issue.url})`);
      reportLines.push("");
    });
  }
  
  // Issues by file
  reportLines.push("---");
  reportLines.push("");
  reportLines.push("## Issues by File");
  reportLines.push("");
  
  const byFile = {};
  Object.entries(unresolvedComments).forEach(([pr, comments]) => {
    comments.forEach(comment => {
      if (!byFile[comment.file]) {
        byFile[comment.file] = [];
      }
      byFile[comment.file].push({ pr, ...comment });
    });
  });
  
  Object.entries(byFile).sort().forEach(([file, issues]) => {
    reportLines.push(`### \`${file}\``);
    reportLines.push("");
    issues.forEach((issue, idx) => {
      reportLines.push(`${idx + 1}. **${issue.title}** (${issue.pr}) - ${issue.severity}`);
      reportLines.push(`   - Line ${issue.line}`);
      reportLines.push(`   - [View Comment](${issue.url})`);
      reportLines.push("");
    });
  });
  
  return reportLines.join('\n');
}

// Generate and save report
const report = generateMarkdownReport();
const outputPath = path.join(__dirname, 'UNRESOLVED_COMMENTS.md');
fs.writeFileSync(outputPath, report);
console.log(`✅ Report generated: ${outputPath}`);
console.log(`📊 Found ${Object.values(unresolvedComments).flat().length} unresolved comments across ${Object.keys(unresolvedComments).length} closed PRs`);
