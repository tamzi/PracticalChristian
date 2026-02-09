# Unresolved Comments from Closed PRs

**Repository:** tamzi/PracticalChristian
**Generated:** 2026-02-09
**Method:** Automated extraction + codebase relevance analysis

---

## Summary

- **Total Unresolved Threads:** 38
- **PRs with Unresolved Comments:** 6

### Severity Breakdown

| Severity | Count |
|----------|-------|
| 🔴 High | 7 |
| 🟠 Medium | 14 |
| ⚪ Low | 17 |

### Relevance Breakdown

| Status | Meaning | Count |
|--------|---------|-------|
| 🔴 Still Relevant | Issue still present in code | 13 |
| 🟡 Needs Review | File changed, unclear if fixed | 11 |
| 🟢 Likely Resolved | Code appears to address the issue | 13 |
| ⚫ File Removed | Referenced file no longer exists | 1 |
| ⚪ Not Applicable | General comment, no file reference | 0 |

---

## Actionable Issues (Still Relevant)

**13 issues** confirmed still present in the codebase.

### 1. Remove inaccurate Firestore/Auth configuration claim

| | |
|---|---|
| **Severity** | 🔴 High |
| **File** | `docs/tech/technicalArchitecture.md` (Line 81) |
| **PR** | PR #14 |
| **Author** | @chatgpt-codex-connector |
| **Why still relevant** | Referenced documentation terms still present: Firestore, Firebase, Analytics |

**<sub><sub>![P2 Badge](https://img.shields.io/badge/P2-yellow?style=flat)</sub></sub>  Remove inaccurate Firestore/Auth configuration claim**

This section states that Firestore is already configured for cloud sync and that Firebase Auth handles user accounts, but the codebase only declares Firebase Analytics/Crashlytics/Performance (see `app/build.gradle.kts`) and has no Firestore/Auth dependencies. That makes the architecture doc misleading for anyone planning sync/auth work based on current implementation. Consider rephrasing as future/planned, or adding the missing dependencies/config bef...

[View Comment](https://github.com/tamzi/PracticalChristian/pull/14#discussion_r2730155254)

### 2. The `SacramentImage` component doesn't handle image loading errors. When an AsyncImage fails to load, it will simply dis

| | |
|---|---|
| **Severity** | 🔴 High |
| **File** | `sacrament/src/main/java/com/sacrament/ui/primitives/SacramentImage.kt` (Line 54) |
| **PR** | PR #8 |
| **Author** | @copilot-pull-request-reviewer |
| **Why still relevant** | No error handling, fallback, or catch patterns found near line 54. |

The `SacramentImage` component doesn't handle image loading errors. When an AsyncImage fails to load, it will simply display nothing, leaving the user with a blank space. Consider adding an error placeholder parameter or using the `error` parameter of AsyncImage to display the placeholder icon when image loading fails.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751379)

### 3. Show placeholder on load errors or blank URLs

| | |
|---|---|
| **Severity** | 🔴 High |
| **File** | `sacrament/src/main/java/com/sacrament/ui/primitives/SacramentImage.kt` (Line 50) |
| **PR** | PR #8 |
| **Author** | @chatgpt-codex-connector |
| **Why still relevant** | No error handling, fallback, or catch patterns found near line 50. |

**<sub><sub>![P2 Badge](https://img.shields.io/badge/P2-yellow?style=flat)</sub></sub>  Show placeholder on load errors or blank URLs**

The new `SacramentImage` only switches to the placeholder when `imageUrl` is `null`; any empty string or invalid URL still runs `AsyncImage` with no `error`/`fallback` painter. In those cases (e.g., backend returns `""`, offline, or 404), the image area will render empty even though the KDoc says it should show a placeholder on failed loads. Consider treating `isNullOrBlank()` as missing and/or wiring Coil’s `error`/`fallback`/`placeholder` slots so failures ...

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705758175)

### 4. The text "Sample SacramentIconTag" appears to be an incorrect replacement. This is user-facing content in a preview func

| | |
|---|---|
| **Severity** | 🟠 Medium |
| **File** | `feature/tags/src/main/java/com/practicalchristian/app/feature/tags/TagsScreen.kt` (Line 454) |
| **PR** | PR #8 |
| **Author** | @copilot-pull-request-reviewer |
| **Why still relevant** | Incorrect text still present in file: SacramentIconTag |

The text "Sample SacramentIconTag" appears to be an incorrect replacement. This is user-facing content in a preview function, and should remain as "Sample Tag". The icon naming changes should not affect string literals that represent user data or UI labels.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751296)

### 5. The preview name "SacramentIconHome Screen - Full" appears to be an incorrect replacement. This should remain as "Home S

| | |
|---|---|
| **Severity** | 🟠 Medium |
| **File** | `feature/home/src/main/java/com/practicalchristian/app/feature/home/home/HomeScreen.kt` (Line 895) |
| **PR** | PR #8 |
| **Author** | @copilot-pull-request-reviewer |
| **Why still relevant** | Incorrect text still present in file: SacramentIconHome |

The preview name "SacramentIconHome Screen - Full" appears to be an incorrect replacement. This should remain as "Home Screen - Full" since it's describing the screen being previewed, not an icon. The icon naming changes should not affect preview function names or descriptions.
```suggestion
@Preview(showBackground = true, name = "Home Screen - Full")
```

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751315)

### 6. The preview name "BooksScreen - SacramentIconList View with Books" appears to be an incorrect replacement. This should r

| | |
|---|---|
| **Severity** | 🟠 Medium |
| **File** | `feature/books/src/main/java/com/practicalchristian/app/feature/books/list/BooksScreen.kt` (Line 294) |
| **PR** | PR #8 |
| **Author** | @copilot-pull-request-reviewer |
| **Why still relevant** | Incorrect text still present in file: SacramentIconList |

The preview name "BooksScreen - SacramentIconList View with Books" appears to be an incorrect replacement. This should remain as "BooksScreen - List View with Books" since it's describing the view mode being previewed, not an icon. The icon naming changes should not affect preview function names or descriptions.
```suggestion
@Preview(showBackground = true, name = "BooksScreen - List View with Books")
```

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751341)

### 7. The preview name "BookItem - SacramentIconList View" appears to be an incorrect replacement. This should remain as "Book

| | |
|---|---|
| **Severity** | 🟠 Medium |
| **File** | `feature/books/src/main/java/com/practicalchristian/app/feature/books/list/BooksScreen.kt` (Line 376) |
| **PR** | PR #8 |
| **Author** | @copilot-pull-request-reviewer |
| **Why still relevant** | Incorrect text still present in file: SacramentIconList |

The preview name "BookItem - SacramentIconList View" appears to be an incorrect replacement. This should remain as "BookItem - List View" since it's describing the view mode being previewed, not an icon. The icon naming changes should not affect preview function names or descriptions.
```suggestion
@Preview(showBackground = true, name = "BookItem - List View")
```

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751357)

### 8. The text "Existing SacramentIconTag" appears to be an incorrect replacement. This is user-facing content in a preview fu

| | |
|---|---|
| **Severity** | 🟠 Medium |
| **File** | `feature/tags/src/main/java/com/practicalchristian/app/feature/tags/TagsScreen.kt` (Line 488) |
| **PR** | PR #8 |
| **Author** | @copilot-pull-request-reviewer |
| **Why still relevant** | Incorrect text still present in file: SacramentIconTag |

The text "Existing SacramentIconTag" appears to be an incorrect replacement. This is user-facing content in a preview function, and should remain as "Existing Tag". The icon naming changes should not affect string literals that represent user data or UI labels.
```suggestion
            tag = TagDomain(id = 1, name = "Existing Tag", color = "#2196F3"),
```

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751444)

### 9. The text "New SacramentIconTag" appears to be an incorrect replacement. This is user-facing content in a preview functio

| | |
|---|---|
| **Severity** | 🟠 Medium |
| **File** | `feature/notes/src/main/java/com/practicalchristian/app/feature/notes/edit/EditNoteScreen.kt` (Line 481) |
| **PR** | PR #8 |
| **Author** | @copilot-pull-request-reviewer |
| **Why still relevant** | Incorrect text still present in file: SacramentIconTag |

The text "New SacramentIconTag" appears to be an incorrect replacement. This is user-facing content in a preview function, and should remain as "New Tag". The icon naming changes should not affect string literals that represent user data or UI labels.
```suggestion
            name = "New Tag",
```

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751460)

### 10. The preview name "BooksScreen - Single SacramentIconBook" appears to be an incorrect replacement. This should remain as 

| | |
|---|---|
| **Severity** | 🟠 Medium |
| **File** | `feature/books/src/main/java/com/practicalchristian/app/feature/books/list/BooksScreen.kt` (Line 336) |
| **PR** | PR #8 |
| **Author** | @copilot-pull-request-reviewer |
| **Why still relevant** | Incorrect text still present in file: SacramentIconBook |

The preview name "BooksScreen - Single SacramentIconBook" appears to be an incorrect replacement. This should remain as "BooksScreen - Single Book" since it's describing the screen content being previewed, not an icon. The icon naming changes should not affect preview function names or descriptions.
```suggestion
@Preview(showBackground = true, name = "BooksScreen - Single Book")
```

[View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751478)

### 11. The documentation mentions "Target SDK: 36 (Android 15)" but Android SDK 36 is not Android 15. According to the Android 

| | |
|---|---|
| **Severity** | ⚪ Low |
| **File** | `docs/tech/projectSetup.md` (Line 8) |
| **PR** | PR #16 |
| **Author** | @copilot-pull-request-reviewer |
| **Why still relevant** | Referenced documentation terms still present: Android |

The documentation mentions "Target SDK: 36 (Android 15)" but Android SDK 36 is not Android 15. According to the Android SDK versioning:
- Android 14 = API Level 34 (UpsideDownCake)
- Android 15 = API Level 35 (VanillaIceCream)
- Android 16 = API Level 36 (Baklava)

The Target SDK 36 should correspond to Android 16, not Android 15. This discrepancy could mislead developers about which Android version they're targeting.
```suggestion
- Target SDK: 36 (Android 16)
```

[View Comment](https://github.com/tamzi/PracticalChristian/pull/16#discussion_r2766156543)

### 12. There's inconsistent hyphenation of "Material-free" vs "Material‑free" across documentation files. In designSystem.md, i

| | |
|---|---|
| **Severity** | ⚪ Low |
| **File** | `docs/tech/sacrament/packageReference.md` (Line 3) |
| **PR** | PR #1 |
| **Author** | @copilot-pull-request-reviewer |
| **Why still relevant** | Referenced documentation terms still present: Material |

There's inconsistent hyphenation of "Material-free" vs "Material‑free" across documentation files. In designSystem.md, it's hyphenated with a standard hyphen-minus (U+002D), while in packageReference.md it uses a non-breaking hyphen (U+2011). This inconsistency can cause search issues and creates visual inconsistency. Standardize on using the regular hyphen-minus character throughout all documentation.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947921)

### 13. There's inconsistent hyphenation of "Material-free" vs "Material‑free". This line uses a non-breaking hyphen (U+2011) in

| | |
|---|---|
| **Severity** | ⚪ Low |
| **File** | `docs/tech/sacrament/packageReference.md` (Line 47) |
| **PR** | PR #1 |
| **Author** | @copilot-pull-request-reviewer |
| **Why still relevant** | Referenced documentation terms still present: Material |

There's inconsistent hyphenation of "Material-free" vs "Material‑free". This line uses a non-breaking hyphen (U+2011) instead of the standard hyphen-minus (U+002D) that's used in designSystem.md. This inconsistency can cause search issues and creates visual inconsistency. Use the regular hyphen-minus character for consistency.

[View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947936)

---

## Needs Manual Review

**11 issues** where the file changed but resolution is unclear.

1. **The comment text "SacramentIconPerson icon" is redundant and unclear. The word "icon" is repeated, and the "SacramentIco** — ⚪ Low
   - `sacrament/src/main/res/drawable/sacrament_profile_placeholder.xml` (Line 12)
   - File was modified but referenced identifiers still exist. Needs manual check.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751417)

2. **The label "SacramentIconInfo" appears to be a mistaken replacement. This should remain as "Info" to describe the tone ca** — ⚪ Low
   - `sacrament-demo/src/main/java/com/sacrament/demo/action/iconbutton/screens/IconButtonTonesCatalogScreen.kt` (Line 154)
   - File was modified but referenced identifiers still exist. Needs manual check.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751431)

3. **Keep listState in sync when marking all read** — 🔴 High
   - `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubViewModel.kt` (Line 132)
   - File was modified but referenced identifiers still exist. Needs manual check.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675653871)

4. **File renames double-counted causing false commit violations** — 🟠 Medium
   - `scripts/pre-commit-hook.sh` (Line 37)
   - Script was modified after comment but needs manual verification.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656693)

5. **The new notification permission methods are missing corresponding Flow properties to read the values. The existing patte** — 🟠 Medium
   - `core/datasource/local/src/main/java/com/practicalchristian/app/core/localdatasource/preferences/user/UserPreferences.kt` (Line 16)
   - File was modified but referenced identifiers still exist. Needs manual check.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656735)

6. **The path exclusion pattern uses a glob pattern that won't work correctly in bash's conditional test. In bash, glob patte** — 🔴 High
   - `scripts/check-design-system-usage.sh` (Line 96)
   - Script was modified after comment but needs manual verification.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656743)

7. **This when-expression contains significant code duplication across all branches. Each branch repeats the same Scaffold co** — ⚪ Low
   - `sacrament/src/main/java/com/sacrament/ui/patterns/SacramentScreenScaffold.kt`
   - File was modified but referenced identifiers still exist. Needs manual check.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947912)

8. **The logic for filtering commits has redundancy. Lines 117-122 filter commits to exclude those on origin/main when gettin** — ⚪ Low
   - `scripts/pre-push.sh` (Line 136)
   - Script was modified after comment but needs manual verification.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947917)

9. **The comment on line 52 states "Material3 Scaffold requires non-null composables" which is incorrect. Material3's Scaffol** — 🟠 Medium
   - `sacrament/src/main/java/com/sacrament/ui/patterns/SacramentScreenScaffold.kt`
   - File was modified but referenced identifiers still exist. Needs manual check.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947924)

10. **The AccessibilityDefaults.Roles object defines string constants for roles like "Button", "Checkbox", etc., but these app** — 🟠 Medium
   - `sacrament/src/main/java/com/sacrament/ui/testing/AccessibilityDefaults.kt` (Line 75)
   - File was modified, but referenced identifiers still exist: AccessibilityDefaults, Roles, Button, Checkbox, Role. May or may not be resolved.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947929)

11. **The import statement ordering has been changed. The androidx.annotation.DrawableRes import was moved from line 7 to line** — ⚪ Low
   - `feature/onboarding/src/main/java/com/practicalchristian/app/feature/onboarding/OnboardingScreen.kt` (Line 3)
   - File was modified but referenced identifiers still exist. Needs manual check.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947931)

---

## Likely Resolved

**14 issues** that appear to have been addressed or are no longer applicable.

1. 🟢 **The timestamp comment contains a future date: "Mon Mar 31 21:02:21 CEST 2025". The current date is February 4, 2026, so ** (PR #16)
   - `gradle/wrapper/gradle-wrapper.properties` — File was modified and referenced code identifiers no longer found in context.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/16#discussion_r2766156582)

2. 🟢 **Missing space after the comma. The comment should be formatted as `imageUrl = null, // Shows placeholder avatar` with a ** (PR #8)
   - `sacrament/src/main/java/com/sacrament/ui/components/content/list/SacramentListItemLeadingImageAndTrailingIcon.kt` — File was modified after the comment. Formatting issues are often fixed incidentally.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751399)

3. 🟢 **Preserve accessibility text for placeholders** (PR #8)
   - `sacrament/src/main/java/com/sacrament/ui/primitives/SacramentImage.kt` — File modified and contentDescription/semantics patterns found in current code.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705758179)

4. 🟢 **The indentation for the `iconData` parameter and its closing parenthesis is inconsistent. The properties of `Notificatio** (PR #4)
   - `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubScreen.kt` — File was modified after the comment. Formatting issues are often fixed incidentally.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/4#discussion_r2680299693)

5. 🟢 **The indentation for the `iconData` parameter and its closing parenthesis is inconsistent, similar to the issue on lines ** (PR #4)
   - `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubScreen.kt` — File was modified after the comment. Formatting issues are often fixed incidentally.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/4#discussion_r2680299701)

6. 🟢 **Update listState when marking a notification read** (PR #2)
   - `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubViewModel.kt` — File was modified and listState appears to be updated in the relevant code.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675653870)

7. 🟢 **Marking notifications as read doesn't update displayed list** (PR #2)
   - `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubViewModel.kt` — File was modified and listState appears to be updated in the relevant code.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656687)

8. 🟢 **The sample notification messages reference "Crush", "happners", "crossed paths", and "Map" which appear to be from a dat** (PR #2)
   - `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubViewModel.kt` — File was modified and referenced code identifiers no longer found in context.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656703)

9. ⚫ **The notification permission screen refers to "appointment reminders" and "booking" which don't match the app's context. ** (PR #2)
   - `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/reminder/NotificationPermissionScreen.kt` — File `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/reminder/NotificationPermissionScreen.kt` no longer exists in the repository.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656714)

10. 🟢 **The word "happners" appears to be a typo. It should likely be "people" or possibly a brand-specific term that should be ** (PR #2)
   - `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubViewModel.kt` — File was modified and referenced code identifiers no longer found in context.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656723)

11. 🟢 **This extension function shadows the standard Compose testTag function from androidx.compose.ui.semantics. The parameter ** (PR #1)
   - `sacrament/src/main/java/com/sacrament/ui/testing/TestTags.kt` — File modified and contentDescription/semantics patterns found in current code.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947914)

12. 🟢 **There's duplication between MIN_TOUCH_TARGET_SIZE_DP (Float constant) and MIN_TOUCH_TARGET_SIZE (Dp value). Both represe** (PR #1)
   - `sacrament/src/main/java/com/sacrament/ui/testing/AccessibilityDefaults.kt` — Referenced identifiers no longer found in the file.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947915)

13. 🟢 **The import statement for contentDescription on line 4 is unused. The contentDescription used in the semantics block on l** (PR #1)
   - `sacrament/src/main/java/com/sacrament/ui/testing/TestTags.kt` — Referenced identifiers no longer found in the file.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947925)

14. 🟢 **The import statement for SemanticsProperties on line 3 is unused. None of the SemanticsProperties members are directly a** (PR #1)
   - `sacrament/src/main/java/com/sacrament/ui/testing/TestTags.kt` — Referenced identifiers no longer found in the file.
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947928)

---

## Full List by PR

### PR #16: Upgrade dependencies

**State:** closed | **Threads:** 2 | [View PR](https://github.com/tamzi/PracticalChristian/pull/16)

| # | Title | Severity | Relevance | File |
|---|-------|----------|-----------|------|
| 1 | [The documentation mentions "Target SDK: 36 (Android 15)" but Android S...](https://github.com/tamzi/PracticalChristian/pull/16#discussion_r2766156543) | ⚪ Low | 🔴 still-relevant | `tech/projectSetup.md` |
| 2 | [The timestamp comment contains a future date: "Mon Mar 31 21:02:21 CES...](https://github.com/tamzi/PracticalChristian/pull/16#discussion_r2766156582) | 🟠 Medium | 🟢 likely-resolved | `wrapper/gradle-wrapper.properties` |

### PR #14: Architecture updates

**State:** merged | **Threads:** 1 | [View PR](https://github.com/tamzi/PracticalChristian/pull/14)

| # | Title | Severity | Relevance | File |
|---|-------|----------|-----------|------|
| 1 | [Remove inaccurate Firestore/Auth configuration claim](https://github.com/tamzi/PracticalChristian/pull/14#discussion_r2730155254) | 🔴 High | 🔴 still-relevant | `tech/technicalArchitecture.md` |

### PR #8: List item creations

**State:** merged | **Threads:** 13 | [View PR](https://github.com/tamzi/PracticalChristian/pull/8)

| # | Title | Severity | Relevance | File |
|---|-------|----------|-----------|------|
| 1 | [The text "Sample SacramentIconTag" appears to be an incorrect replacem...](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751296) | 🟠 Medium | 🔴 still-relevant | `tags/TagsScreen.kt` |
| 2 | [The preview name "SacramentIconHome Screen - Full" appears to be an in...](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751315) | 🟠 Medium | 🔴 still-relevant | `home/HomeScreen.kt` |
| 3 | [The preview name "BooksScreen - SacramentIconList View with Books" app...](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751341) | 🟠 Medium | 🔴 still-relevant | `list/BooksScreen.kt` |
| 4 | [The preview name "BookItem - SacramentIconList View" appears to be an ...](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751357) | 🟠 Medium | 🔴 still-relevant | `list/BooksScreen.kt` |
| 5 | [The `SacramentImage` component doesn't handle image loading errors. Wh...](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751379) | 🔴 High | 🔴 still-relevant | `primitives/SacramentImage.kt` |
| 6 | [Missing space after the comma. The comment should be formatted as `ima...](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751399) | 🟠 Medium | 🟢 likely-resolved | `list/SacramentListItemLeadingImageAndTrailingIcon.kt` |
| 7 | [The comment text "SacramentIconPerson icon" is redundant and unclear. ...](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751417) | ⚪ Low | 🟡 needs-review | `drawable/sacrament_profile_placeholder.xml` |
| 8 | [The label "SacramentIconInfo" appears to be a mistaken replacement. Th...](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751431) | ⚪ Low | 🟡 needs-review | `screens/IconButtonTonesCatalogScreen.kt` |
| 9 | [The text "Existing SacramentIconTag" appears to be an incorrect replac...](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751444) | 🟠 Medium | 🔴 still-relevant | `tags/TagsScreen.kt` |
| 10 | [The text "New SacramentIconTag" appears to be an incorrect replacement...](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751460) | 🟠 Medium | 🔴 still-relevant | `edit/EditNoteScreen.kt` |
| 11 | [The preview name "BooksScreen - Single SacramentIconBook" appears to b...](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751478) | 🟠 Medium | 🔴 still-relevant | `list/BooksScreen.kt` |
| 12 | [Show placeholder on load errors or blank URLs](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705758175) | 🔴 High | 🔴 still-relevant | `primitives/SacramentImage.kt` |
| 13 | [Preserve accessibility text for placeholders](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705758179) | 🔴 High | 🟢 likely-resolved | `primitives/SacramentImage.kt` |

### PR #4: Scrament enforcement

**State:** merged | **Threads:** 2 | [View PR](https://github.com/tamzi/PracticalChristian/pull/4)

| # | Title | Severity | Relevance | File |
|---|-------|----------|-----------|------|
| 1 | [The indentation for the `iconData` parameter and its closing parenthes...](https://github.com/tamzi/PracticalChristian/pull/4#discussion_r2680299693) | ⚪ Low | 🟢 likely-resolved | `hub/NotificationsHubScreen.kt` |
| 2 | [The indentation for the `iconData` parameter and its closing parenthes...](https://github.com/tamzi/PracticalChristian/pull/4#discussion_r2680299701) | ⚪ Low | 🟢 likely-resolved | `hub/NotificationsHubScreen.kt` |

### PR #2: Notifications

**State:** merged | **Threads:** 9 | [View PR](https://github.com/tamzi/PracticalChristian/pull/2)

| # | Title | Severity | Relevance | File |
|---|-------|----------|-----------|------|
| 1 | [Update listState when marking a notification read](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675653870) | 🔴 High | 🟢 likely-resolved | `hub/NotificationsHubViewModel.kt` |
| 2 | [Keep listState in sync when marking all read](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675653871) | 🔴 High | 🟡 needs-review | `hub/NotificationsHubViewModel.kt` |
| 3 | [Marking notifications as read doesn't update displayed list](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656687) | ⚪ Low | 🟢 likely-resolved | `hub/NotificationsHubViewModel.kt` |
| 4 | [File renames double-counted causing false commit violations](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656693) | 🟠 Medium | 🟡 needs-review | `scripts/pre-commit-hook.sh` |
| 5 | [The sample notification messages reference "Crush", "happners", "cross...](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656703) | ⚪ Low | 🟢 likely-resolved | `hub/NotificationsHubViewModel.kt` |
| 6 | [The notification permission screen refers to "appointment reminders" a...](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656714) | ⚪ Low | ⚫ file-removed | `reminder/NotificationPermissionScreen.kt` |
| 7 | [The word "happners" appears to be a typo. It should likely be "people"...](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656723) | ⚪ Low | 🟢 likely-resolved | `hub/NotificationsHubViewModel.kt` |
| 8 | [The new notification permission methods are missing corresponding Flow...](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656735) | 🟠 Medium | 🟡 needs-review | `user/UserPreferences.kt` |
| 9 | [The path exclusion pattern uses a glob pattern that won't work correct...](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656743) | 🔴 High | 🟡 needs-review | `scripts/check-design-system-usage.sh` |

### PR #1: Design system wireup

**State:** merged | **Threads:** 11 | [View PR](https://github.com/tamzi/PracticalChristian/pull/1)

| # | Title | Severity | Relevance | File |
|---|-------|----------|-----------|------|
| 1 | [This when-expression contains significant code duplication across all ...](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947912) | ⚪ Low | 🟡 needs-review | `patterns/SacramentScreenScaffold.kt` |
| 2 | [This extension function shadows the standard Compose testTag function ...](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947914) | 🟠 Medium | 🟢 likely-resolved | `testing/TestTags.kt` |
| 3 | [There's duplication between MIN_TOUCH_TARGET_SIZE_DP (Float constant) ...](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947915) | ⚪ Low | 🟢 likely-resolved | `testing/AccessibilityDefaults.kt` |
| 4 | [The logic for filtering commits has redundancy. Lines 117-122 filter c...](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947917) | ⚪ Low | 🟡 needs-review | `scripts/pre-push.sh` |
| 5 | [There's inconsistent hyphenation of "Material-free" vs "Material‑free"...](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947921) | ⚪ Low | 🔴 still-relevant | `sacrament/packageReference.md` |
| 6 | [The comment on line 52 states "Material3 Scaffold requires non-null co...](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947924) | 🟠 Medium | 🟡 needs-review | `patterns/SacramentScreenScaffold.kt` |
| 7 | [The import statement for contentDescription on line 4 is unused. The c...](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947925) | ⚪ Low | 🟢 likely-resolved | `testing/TestTags.kt` |
| 8 | [The import statement for SemanticsProperties on line 3 is unused. None...](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947928) | ⚪ Low | 🟢 likely-resolved | `testing/TestTags.kt` |
| 9 | [The AccessibilityDefaults.Roles object defines string constants for ro...](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947929) | 🟠 Medium | 🟡 needs-review | `testing/AccessibilityDefaults.kt` |
| 10 | [The import statement ordering has been changed. The androidx.annotatio...](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947931) | ⚪ Low | 🟡 needs-review | `onboarding/OnboardingScreen.kt` |
| 11 | [There's inconsistent hyphenation of "Material-free" vs "Material‑free"...](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947936) | ⚪ Low | 🔴 still-relevant | `sacrament/packageReference.md` |

---

## Issues by File

### `core/datasource/local/src/main/java/com/practicalchristian/app/core/localdatasource/preferences/user/UserPreferences.kt`

1. 🟡 **The new notification permission methods are missing corresponding Flow properties to read the values. The existing patte** (PR #2) — 🟠 Medium
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656735)

### `docs/tech/projectSetup.md` — **1 active**

1. 🔴 **The documentation mentions "Target SDK: 36 (Android 15)" but Android SDK 36 is not Android 15. According to the Android ** (PR #16) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/16#discussion_r2766156543)

### `docs/tech/sacrament/packageReference.md` — **2 active**

1. 🔴 **There's inconsistent hyphenation of "Material-free" vs "Material‑free" across documentation files. In designSystem.md, i** (PR #1) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947921)

2. 🔴 **There's inconsistent hyphenation of "Material-free" vs "Material‑free". This line uses a non-breaking hyphen (U+2011) in** (PR #1) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947936)

### `docs/tech/technicalArchitecture.md` — **1 active**

1. 🔴 **Remove inaccurate Firestore/Auth configuration claim** (PR #14) — 🔴 High
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/14#discussion_r2730155254)

### `feature/books/src/main/java/com/practicalchristian/app/feature/books/list/BooksScreen.kt` — **3 active**

1. 🔴 **The preview name "BooksScreen - SacramentIconList View with Books" appears to be an incorrect replacement. This should r** (PR #8) — 🟠 Medium
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751341)

2. 🔴 **The preview name "BookItem - SacramentIconList View" appears to be an incorrect replacement. This should remain as "Book** (PR #8) — 🟠 Medium
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751357)

3. 🔴 **The preview name "BooksScreen - Single SacramentIconBook" appears to be an incorrect replacement. This should remain as ** (PR #8) — 🟠 Medium
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751478)

### `feature/home/src/main/java/com/practicalchristian/app/feature/home/home/HomeScreen.kt` — **1 active**

1. 🔴 **The preview name "SacramentIconHome Screen - Full" appears to be an incorrect replacement. This should remain as "Home S** (PR #8) — 🟠 Medium
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751315)

### `feature/notes/src/main/java/com/practicalchristian/app/feature/notes/edit/EditNoteScreen.kt` — **1 active**

1. 🔴 **The text "New SacramentIconTag" appears to be an incorrect replacement. This is user-facing content in a preview functio** (PR #8) — 🟠 Medium
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751460)

### `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubScreen.kt`

1. 🟢 **The indentation for the `iconData` parameter and its closing parenthesis is inconsistent. The properties of `Notificatio** (PR #4) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/4#discussion_r2680299693)

2. 🟢 **The indentation for the `iconData` parameter and its closing parenthesis is inconsistent, similar to the issue on lines ** (PR #4) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/4#discussion_r2680299701)

### `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/hub/NotificationsHubViewModel.kt`

1. 🟢 **Update listState when marking a notification read** (PR #2) — 🔴 High
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675653870)

2. 🟡 **Keep listState in sync when marking all read** (PR #2) — 🔴 High
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675653871)

3. 🟢 **Marking notifications as read doesn't update displayed list** (PR #2) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656687)

4. 🟢 **The sample notification messages reference "Crush", "happners", "crossed paths", and "Map" which appear to be from a dat** (PR #2) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656703)

5. 🟢 **The word "happners" appears to be a typo. It should likely be "people" or possibly a brand-specific term that should be ** (PR #2) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656723)

### `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/reminder/NotificationPermissionScreen.kt`

1. ⚫ **The notification permission screen refers to "appointment reminders" and "booking" which don't match the app's context. ** (PR #2) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656714)

### `feature/onboarding/src/main/java/com/practicalchristian/app/feature/onboarding/OnboardingScreen.kt`

1. 🟡 **The import statement ordering has been changed. The androidx.annotation.DrawableRes import was moved from line 7 to line** (PR #1) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947931)

### `feature/tags/src/main/java/com/practicalchristian/app/feature/tags/TagsScreen.kt` — **2 active**

1. 🔴 **The text "Sample SacramentIconTag" appears to be an incorrect replacement. This is user-facing content in a preview func** (PR #8) — 🟠 Medium
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751296)

2. 🔴 **The text "Existing SacramentIconTag" appears to be an incorrect replacement. This is user-facing content in a preview fu** (PR #8) — 🟠 Medium
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751444)

### `gradle/wrapper/gradle-wrapper.properties`

1. 🟢 **The timestamp comment contains a future date: "Mon Mar 31 21:02:21 CEST 2025". The current date is February 4, 2026, so ** (PR #16) — 🟠 Medium
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/16#discussion_r2766156582)

### `sacrament-demo/src/main/java/com/sacrament/demo/action/iconbutton/screens/IconButtonTonesCatalogScreen.kt`

1. 🟡 **The label "SacramentIconInfo" appears to be a mistaken replacement. This should remain as "Info" to describe the tone ca** (PR #8) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751431)

### `sacrament/src/main/java/com/sacrament/ui/components/content/list/SacramentListItemLeadingImageAndTrailingIcon.kt`

1. 🟢 **Missing space after the comma. The comment should be formatted as `imageUrl = null, // Shows placeholder avatar` with a ** (PR #8) — 🟠 Medium
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751399)

### `sacrament/src/main/java/com/sacrament/ui/patterns/SacramentScreenScaffold.kt`

1. 🟡 **This when-expression contains significant code duplication across all branches. Each branch repeats the same Scaffold co** (PR #1) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947912)

2. 🟡 **The comment on line 52 states "Material3 Scaffold requires non-null composables" which is incorrect. Material3's Scaffol** (PR #1) — 🟠 Medium
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947924)

### `sacrament/src/main/java/com/sacrament/ui/primitives/SacramentImage.kt` — **2 active**

1. 🔴 **The `SacramentImage` component doesn't handle image loading errors. When an AsyncImage fails to load, it will simply dis** (PR #8) — 🔴 High
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751379)

2. 🔴 **Show placeholder on load errors or blank URLs** (PR #8) — 🔴 High
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705758175)

3. 🟢 **Preserve accessibility text for placeholders** (PR #8) — 🔴 High
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705758179)

### `sacrament/src/main/java/com/sacrament/ui/testing/AccessibilityDefaults.kt`

1. 🟢 **There's duplication between MIN_TOUCH_TARGET_SIZE_DP (Float constant) and MIN_TOUCH_TARGET_SIZE (Dp value). Both represe** (PR #1) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947915)

2. 🟡 **The AccessibilityDefaults.Roles object defines string constants for roles like "Button", "Checkbox", etc., but these app** (PR #1) — 🟠 Medium
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947929)

### `sacrament/src/main/java/com/sacrament/ui/testing/TestTags.kt`

1. 🟢 **This extension function shadows the standard Compose testTag function from androidx.compose.ui.semantics. The parameter ** (PR #1) — 🟠 Medium
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947914)

2. 🟢 **The import statement for contentDescription on line 4 is unused. The contentDescription used in the semantics block on l** (PR #1) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947925)

3. 🟢 **The import statement for SemanticsProperties on line 3 is unused. None of the SemanticsProperties members are directly a** (PR #1) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947928)

### `sacrament/src/main/res/drawable/sacrament_profile_placeholder.xml`

1. 🟡 **The comment text "SacramentIconPerson icon" is redundant and unclear. The word "icon" is repeated, and the "SacramentIco** (PR #8) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/8#discussion_r2705751417)

### `scripts/check-design-system-usage.sh`

1. 🟡 **The path exclusion pattern uses a glob pattern that won't work correctly in bash's conditional test. In bash, glob patte** (PR #2) — 🔴 High
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656743)

### `scripts/pre-commit-hook.sh`

1. 🟡 **File renames double-counted causing false commit violations** (PR #2) — 🟠 Medium
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/2#discussion_r2675656693)

### `scripts/pre-push.sh`

1. 🟡 **The logic for filtering commits has redundancy. Lines 117-122 filter commits to exclude those on origin/main when gettin** (PR #1) — ⚪ Low
   - [View Comment](https://github.com/tamzi/PracticalChristian/pull/1#discussion_r2659947917)
