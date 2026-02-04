# Unresolved Comments from Closed PRs

**Repository:** tamzi/PracticalChristian
**Generated:** 2026-02-04

This report contains all unresolved review comments from closed pull requests.
Use this list to track potential bugfixes and improvements.

---

## Summary

- **Total Unresolved Comments:** 18
- **P1 (High Priority):** 0
- **P2 (Medium Priority):** 6
- **Medium Severity:** 2
- **Minor Issues:** 10
- **PRs with Unresolved Comments:** 5

---

## Issues by Priority

### 🟡 P2 - Medium Priority

#### 1. Remove inaccurate Firestore/Auth configuration claim (PR #14)

**File:** `docs/tech/technicalArchitecture.md` (Line 81)
**Author:** @chatgpt-codex-connector

This section states that Firestore is already configured for cloud sync and that Firebase Auth handles user accounts, but the codebase only declares Firebase Analytics/Crashlytics/Performance (see `app/build.gradle.kts`) and has no Firestore/Auth dependencies. That makes the architecture doc misleading for anyone planning sync/auth work based on current implementation. Consider rephrasing as future/planned, or adding the missing dependencies/config before claiming it's configured.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/14#discussion_r2730155254)

#### 2. Missing error handling in SacramentImage (PR #8)

**File:** `sacrament/src/main/java/com/sacrament/ui/primitives/SacramentImage.kt` (Line 54)
**Author:** @copilot-pull-request-reviewer

The `SacramentImage` component doesn't handle image loading errors. When an AsyncImage fails to load, it will simply display nothing, leaving the user with a blank space. Consider adding an error placeholder parameter or using the `error` parameter of AsyncImage to display the placeholder icon when image loading fails.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751379)

#### 3. Show placeholder on load errors or blank URLs (PR #8)

**File:** `sacrament/src/main/java/com/sacrament/ui/primitives/SacramentImage.kt` (Line 50)
**Author:** @chatgpt-codex-connector

The new `SacramentImage` only switches to the placeholder when `imageUrl` is `null`; any empty string or invalid URL still runs `AsyncImage` with no `error`/`fallback` painter. Consider treating `isNullOrBlank()` as missing and/or wiring Coil's `error`/`fallback`/`placeholder` slots so failures still render the placeholder.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705758175)

#### 4. Preserve accessibility text for placeholders (PR #8)

**File:** `sacrament/src/main/java/com/sacrament/ui/primitives/SacramentImage.kt` (Line 92)
**Author:** @chatgpt-codex-connector

When `imageUrl` is null, `SacramentImagePlaceholder` hardcodes `contentDescription = null`, so any caller-supplied `contentDescription` is effectively dropped. This is an accessibility regression; consider passing the description through to the placeholder.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705758179)

#### 5. Update listState when marking a notification read (PR #2)

**File:** `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubViewModel.kt` (Line 109)
**Author:** @chatgpt-codex-connector

The UI renders the list from `state.listState`, but `markAsRead` only updates `sections` and `unreadCount`. Because `listState` still holds the old list, tapping a notification will not clear the unread dot in the list until a full refresh. Consider updating `listState` with the new `updatedSections`.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675653870)

#### 6. Keep listState in sync when marking all read (PR #2)

**File:** `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubViewModel.kt` (Line 132)
**Author:** @chatgpt-codex-connector

Similar to `markAsRead`, `markAllAsRead` updates `sections` and `unreadCount` but leaves `listState` unchanged. Since the screen uses `state.listState` to render the list, unread indicators will remain visible even though the badge count hits 0.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675653871)

### 🟠 Medium Severity

#### 1. Path exclusion pattern won't work correctly (PR #2)

**File:** `scripts/check-design-system-usage.sh` (Line 96)
**Author:** @copilot-pull-request-reviewer

The path exclusion pattern uses a glob pattern that won't work correctly in bash's conditional test. In bash, glob patterns need to be used with `case` statements or the `=~` operator with proper escaping.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656743)

#### 2. Shadow standard Compose testTag function (PR #1)

**File:** `sacrament/src/main/java/com/sacrament/ui/testing/TestTags.kt` (Line 103)
**Author:** @copilot-pull-request-reviewer

This extension function shadows the standard Compose testTag function. The parameter names match but the implementation differs - this version combines testTag with contentDescription, while the standard one only sets the testTag. This creates potential confusion.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947914)

### ⚪ Minor Issues

#### 1. Incorrect text replacement in preview (PR #8)

**File:** `feature/tags/src/main/java/com/practicalchristian/app/feature/tags/TagsScreen.kt` (Line 454)
**Author:** @copilot-pull-request-reviewer

The text "Sample SacramentIconTag" appears to be an incorrect replacement. This is user-facing content in a preview function, and should remain as "Sample Tag". The icon naming changes should not affect string literals that represent user data or UI labels.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751296)

#### 2. Incorrect preview name replacement (PR #8)

**File:** `feature/home/src/main/java/com/practicalchristian/app/feature/home/home/HomeScreen.kt` (Line 895)
**Author:** @copilot-pull-request-reviewer

The preview name "SacramentIconHome Screen - Full" appears to be an incorrect replacement. This should remain as "Home Screen - Full" since it's describing the screen being previewed, not an icon.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751315)

#### 3. Incorrect preview name in BooksScreen (PR #8)

**File:** `feature/books/src/main/java/com/practicalchristian/app/feature/books/list/BooksScreen.kt` (Line 294)
**Author:** @copilot-pull-request-reviewer

The preview name "BooksScreen - SacramentIconList View with Books" appears to be an incorrect replacement. This should remain as "BooksScreen - List View with Books".

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751341)

#### 4. Inconsistent indentation in NotificationsHubScreen (PR #4)

**File:** `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubScreen.kt` (Line 298)
**Author:** @copilot-pull-request-reviewer

The indentation for the `iconData` parameter and its closing parenthesis is inconsistent. The properties of `NotificationIconData` have extra indentation (8 spaces instead of the expected alignment).

[View Comment](https://github.com/tamzi/PracticalChristian/pull/4#discussion_r2680299693)

#### 5. Inconsistent indentation (second occurrence) (PR #4)

**File:** `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubScreen.kt` (Line 318)
**Author:** @copilot-pull-request-reviewer

The indentation for the `iconData` parameter and its closing parenthesis is inconsistent, similar to the issue on lines 291-298.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/4#discussion_r2680299701)

#### 6. Missing Flow properties for notification preferences (PR #2)

**File:** `core/datasource/local/src/main/java/com/practicalchristian/app/core/localdatasource/preferences/user/UserPreferences.kt` (Line 16)
**Author:** @copilot-pull-request-reviewer

The new notification permission methods are missing corresponding Flow properties to read the values. The existing pattern provides both Flow getters and suspend setters. The notification permission state should follow this same pattern.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656735)

#### 7. Duplicate constant MIN_TOUCH_TARGET_SIZE_DP (PR #1)

**File:** `sacrament/src/main/java/com/sacrament/ui/testing/AccessibilityDefaults.kt` (Line 18)
**Author:** @copilot-pull-request-reviewer

There's duplication between MIN_TOUCH_TARGET_SIZE_DP (Float constant) and MIN_TOUCH_TARGET_SIZE (Dp value). Both represent the same value (48) in different units. The Float constant appears unused.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947915)

#### 8. Redundant commit filtering logic (PR #1)

**File:** `scripts/pre-push.sh` (Line 136)
**Author:** @copilot-pull-request-reviewer

The logic for filtering commits has redundancy. Lines 117-122 filter commits to exclude those on origin/main when getting the commit list, and then lines 130-135 skip those same commits again in the loop. This double-filtering is unnecessary.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947917)

#### 9. Unused import statements in TestTags.kt (PR #1)

**File:** `sacrament/src/main/java/com/sacrament/ui/testing/TestTags.kt` (Line 3)
**Author:** @copilot-pull-request-reviewer

The import statement for SemanticsProperties on line 3 is unused. None of the SemanticsProperties members are directly accessed in this file.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947928)

#### 10. Unused AccessibilityDefaults.Roles object (PR #1)

**File:** `sacrament/src/main/java/com/sacrament/ui/testing/AccessibilityDefaults.kt` (Line 75)
**Author:** @copilot-pull-request-reviewer

The AccessibilityDefaults.Roles object defines string constants for roles, but these appear to be unused. In Compose, semantic roles are set using the Role enum, not string literals.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947929)

---

## Issues by File

### `core/datasource/local/src/main/java/com/practicalchristian/app/core/localdatasource/preferences/user/UserPreferences.kt`

1. **Missing Flow properties for notification preferences** (PR #2) - Minor
   - Line 16
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656735)

### `docs/tech/technicalArchitecture.md`

1. **Remove inaccurate Firestore/Auth configuration claim** (PR #14) - P2
   - Line 81
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/14#discussion_r2730155254)

### `feature/books/src/main/java/com/practicalchristian/app/feature/books/list/BooksScreen.kt`

1. **Incorrect preview name in BooksScreen** (PR #8) - Minor
   - Line 294
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751341)

### `feature/home/src/main/java/com/practicalchristian/app/feature/home/home/HomeScreen.kt`

1. **Incorrect preview name replacement** (PR #8) - Minor
   - Line 895
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751315)

### `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubScreen.kt`

1. **Inconsistent indentation in NotificationsHubScreen** (PR #4) - Minor
   - Line 298
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/4#discussion_r2680299693)

2. **Inconsistent indentation (second occurrence)** (PR #4) - Minor
   - Line 318
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/4#discussion_r2680299701)

### `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubViewModel.kt`

1. **Update listState when marking a notification read** (PR #2) - P2
   - Line 109
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675653870)

2. **Keep listState in sync when marking all read** (PR #2) - P2
   - Line 132
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675653871)

### `feature/tags/src/main/java/com/practicalchristian/app/feature/tags/TagsScreen.kt`

1. **Incorrect text replacement in preview** (PR #8) - Minor
   - Line 454
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751296)

### `sacrament/src/main/java/com/sacrament/ui/primitives/SacramentImage.kt`

1. **Missing error handling in SacramentImage** (PR #8) - P2
   - Line 54
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751379)

2. **Show placeholder on load errors or blank URLs** (PR #8) - P2
   - Line 50
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705758175)

3. **Preserve accessibility text for placeholders** (PR #8) - P2
   - Line 92
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705758179)

### `sacrament/src/main/java/com/sacrament/ui/testing/AccessibilityDefaults.kt`

1. **Duplicate constant MIN_TOUCH_TARGET_SIZE_DP** (PR #1) - Minor
   - Line 18
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947915)

2. **Unused AccessibilityDefaults.Roles object** (PR #1) - Minor
   - Line 75
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947929)

### `sacrament/src/main/java/com/sacrament/ui/testing/TestTags.kt`

1. **Shadow standard Compose testTag function** (PR #1) - Medium
   - Line 103
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947914)

2. **Unused import statements in TestTags.kt** (PR #1) - Minor
   - Line 3
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947928)

### `scripts/check-design-system-usage.sh`

1. **Path exclusion pattern won't work correctly** (PR #2) - Medium
   - Line 96
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656743)

### `scripts/pre-push.sh`

1. **Redundant commit filtering logic** (PR #1) - Minor
   - Line 136
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947917)
