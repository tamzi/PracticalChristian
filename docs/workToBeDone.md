# Work To Be Done

Single source of truth for project tasks, priorities, and status.

## How to Use

- Add tasks as short, action-focused items.
- Keep tasks in the smallest section they fit.
- Move items as they progress (Current -> Next -> Backlog -> Done).
- Prefer checkboxes for clarity.
- Use `<details>` with a `<summary>` for each epic so it can collapse; nest `STORY:` and `TASK:` lists under the epic.
- Follow the rules laid out in the agentRules folder.

## Current Focus

<details>
<summary>EPIC: Theme switcher and settings integration</summary>

- Plan: `docs/darkLightThemePlan.md`

- [ ] STORY: Switcher component readiness
  - [ ] TASK: Review `sacrament/src/main/java/com/sacrament/ui/components/input/` switcher defaults.
  - [ ] TASK: Ensure light/dark (off/on) states map to theme tokens.
  - [ ] TASK: Confirm sizing, padding, and icon placement match the reference.
- [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

- [ ] STORY: Settings screen integration
  - [ ] TASK: Add the switcher in `feature/settings/src/main/java/com/practicalchristian/app/feature/settings/SettingsScreen.kt`.
  - [ ] TASK: Ensure switcher uses Sacrament component and icons from the reference.
  - [ ] TASK: Confirm accessibility labels and touch targets.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.


</details>

<details>
<summary>EPIC: Theme preference and persistence</summary>

- Plan: `docs/darkLightThemePlan.md`

- [ ] STORY: User preference storage
  - [ ] TASK: Review preference storage in `core/datasource/local/src/main/java/com/practicalchristian/app/core/localdatasource/preferences/user/`.
  - [ ] TASK: Confirm preference is light/dark only and persists across launches.
- [ ] STORY: App theme application
  - [ ] TASK: Confirm theme selection in `app/src/main/java/...` uses stored preference.
  - [ ] TASK: Validate runtime theme changes from the switcher.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>

<details>
<summary>EPIC: Demo, previews, and QA</summary>

- Plan: `docs/darkLightThemePlan.md`

- [ ] STORY: Sacrament demo coverage
  - [ ] TASK: Add the updated switcher to the Sacrament demo app.
  - [ ] TASK: Verify the demo showcases light and dark variants.
- [ ] STORY: Previews and visual QA
  - [ ] TASK: Add/verify previews for light and dark variants where needed.
  - [ ] TASK: Validate bottom bar spotlight alignment in dark mode.
  - [ ] TASK: Check contrast and system bar appearance in both themes.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>

<details>
<summary>EPIC: Design system setup + enforcement</summary>
- [ ] STORY: Enforcement (last)
  - [ ] TASK: enforce using theme colours from the design system.
  - [ ] TASK: Do a thorough deep code review of this epic to ensure it is complete and correct. Fix any issues found and commit the changes. Remember to follow the commit rules as laid out in the commitRules.md file.

</details>

## Next Up

<details>
<summary>EPIC: MVP product decisions</summary>

- [ ] STORY: Translation + licensing constraints
  - [ ] TASK: Decide MVP translations + licensing budget (confirm offline rights requirements).
- [ ] STORY: Onboarding preferences
  - [ ] TASK: Define required onboarding preferences (time, session length, Sabbath, tradition profile).
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>

## Backlog

<details>
<summary>EPIC: Daily Devotional Feed MVP</summary>

- [ ] STORY: Define MVP scope
  - [ ] TASK: Specify 5/10/20 min variants, citations, offline cache, gentle notifications.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>

<details>
<summary>EPIC: Church membership + preaching feed MVP</summary>

- [ ] STORY: Define MVP scope
  - [ ] TASK: Scope invite/search and general channel experiences.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>

<details>
<summary>EPIC: Small Circles MVP</summary>

- [ ] STORY: Define MVP scope
  - [ ] TASK: Define private plans, prayer updates, weekly check-ins.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>

<details>
<summary>EPIC: Encrypted journal export + biometric lock</summary>

- [ ] STORY: Define MVP scope
  - [ ] TASK: Plan encrypted journal export + biometric lock.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>

<details>
<summary>EPIC: SRS memorization MVP</summary>

- [ ] STORY: Define MVP scope
  - [ ] TASK: Plan Leitner queue + tie to highlights.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>

<details>
<summary>EPIC: Licensing track</summary>

- [ ] STORY: Translation shortlist + cost/rights matrix
  - [ ] TASK: Build licensing track (translation shortlist + cost/rights matrix).
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>

<details>
<summary>EPIC: Audio strategy and roadmap</summary>

- [ ] STORY: Define options
  - [ ] TASK: Decide audio strategy and roadmap (TTS vs licensed).
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>

<details>
<summary>EPIC: Research pastoral content formats</summary>

- [ ] STORY: Discovery research
  - [ ] TASK: Run research on pastoral content formats, multi-church behavior, privacy language, and opt-in signals for MTS/W.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>

<details>
<summary>EPIC: Comprehensive Audit Trails</summary>

- [ ] STORY: Define audit trail scope and criteria
  - [ ] TASK: Define a critical action catalog by feature.
  - [ ] TASK: Document architecture and event schema in `docs/tech/auditTrails.md`.
- [ ] STORY: Feature instrumentation
  - [ ] TASK: Add audit events for notification permission request and skip outcomes in `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/reminder/NotificationReminderViewModel.kt`.
- [ ] STORY: Local persistence and retention
  - [ ] TASK: Add audit event storage using Room with encryption at rest.
  - [ ] TASK: Enforce 90-day retention with periodic cleanup.
- [ ] STORY: Background sync
  - [ ] TASK: Add local queue and upload worker with retry/backoff.
  - [ ] TASK: Add a stub backend interface and ingestion contract.
- [ ] STORY: Compliance access
  - [ ] TASK: Define backend export format and access flow.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>

<details>
<summary>EPIC: Secure error handling</summary>

- [ ] STORY: App-wide policy and guidance
  - [ ] TASK: Define user-facing error copy catalog and severity categories.
  - [ ] TASK: Define error code taxonomy and mapping ownership by layer.
  - [ ] TASK: Document secure error handling guidelines in `docs/tech/errorHandling.md`.
- [ ] STORY: UI sanitization
  - [ ] TASK: Replace direct rendering of raw error messages in feature UIs (notifications, schedules, tags, books, notes).
  - [ ] TASK: Route user-facing copy through a single mapping utility per feature or shared helper.
- [ ] STORY: Logging and telemetry
  - [ ] TASK: Add redaction and correlation IDs for error logs and crash reports.
  - [ ] TASK: Verify analytics payloads exclude secrets and PII.
- [ ] STORY: Edge case management
  - [ ] TASK: Gate POST_NOTIFICATIONS permission requests to Android 13+ in `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/reminder/NotificationReminderScreen.kt`.
  - [ ] TASK: Define handling for permission denial and "don't ask again" states in notifications onboarding.
  - [ ] TASK: Add error handling guidance for API-level behavior in `docs/tech/errorHandling.md`.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>

<details>
<summary>EPIC: Accessibility compliance and inclusive design</summary>

- [ ] STORY: Accessibility audit and baseline
  - [ ] TASK: Run accessibility scanner on all screens and document current state.
  - [ ] TASK: Test with TalkBack and Switch Access to identify navigation issues.
  - [ ] TASK: Audit color contrast ratios across all design system tokens against WCAG AA standards.
  - [ ] TASK: Create accessibility compliance checklist and document in `docs/tech/accessibility.md`.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

- [ ] STORY: Semantic content descriptions
  - [ ] TASK: Add meaningful contentDescription to all icons, images, and interactive elements.
  - [ ] TASK: Ensure decorative elements have null contentDescription to avoid screen reader clutter.
  - [ ] TASK: Review and improve semantic roles for custom components (buttons, lists, headers).
  - [ ] TASK: Add state descriptions for toggles, checkboxes, and progress indicators.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

- [ ] STORY: Keyboard and focus navigation
  - [ ] TASK: Ensure all interactive elements are keyboard/D-pad navigable.
  - [ ] TASK: Verify logical focus order across all screens.
  - [ ] TASK: Add visual focus indicators for keyboard navigation.
  - [ ] TASK: Test and fix focus traps in modals and bottom sheets.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

- [ ] STORY: Touch target sizing
  - [ ] TASK: Audit all interactive elements to ensure minimum 48dp touch targets.
  - [ ] TASK: Add spacing tokens to design system for accessible touch targets.
  - [ ] TASK: Fix small touch targets in list items, icon buttons, and chips.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

- [ ] STORY: Text accessibility
  - [ ] TASK: Ensure all text scales properly with system font size settings.
  - [ ] TASK: Test layouts with 200% font scaling and fix overflow/truncation issues.
  - [ ] TASK: Add proper heading semantics using Modifier.semantics for screen reader navigation.
  - [ ] TASK: Ensure minimum 16sp font size for body text across all features.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

- [ ] STORY: Dynamic content and live regions
  - [ ] TASK: Add live region announcements for dynamic content updates (notifications, loading states).
  - [ ] TASK: Ensure error messages are announced to screen readers.
  - [ ] TASK: Add progress announcements for long-running operations.
  - [ ] TASK: Test and improve announcements for navigation changes.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

- [ ] STORY: Accessibility testing infrastructure
  - [ ] TASK: Add automated accessibility tests using Compose testing semantics.
  - [ ] TASK: Create accessibility test suite for critical user flows.
  - [ ] TASK: Add CI check for common accessibility issues (missing contentDescription, small touch targets).
  - [ ] TASK: Document accessibility testing guidelines for feature development.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

- [ ] STORY: Alternative input methods
  - [ ] TASK: Test and optimize for Switch Access navigation.
  - [ ] TASK: Ensure voice input works properly in all text fields.
  - [ ] TASK: Add support for external keyboard shortcuts for common actions.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

- [ ] STORY: Accessibility documentation
  - [ ] TASK: Create accessibility guidelines for component development in `sacrament` module.
  - [ ] TASK: Document accessibility requirements in feature development rules.
  - [ ] TASK: Add accessibility section to component documentation with examples.
  - [ ] TASK: Create accessibility checklist for PR reviews.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>

<details>
<summary>EPIC: Internationalization and localization</summary>

- [ ] STORY: i18n infrastructure setup
  - [ ] TASK: Audit current string usage and identify hardcoded strings across all features.
  - [ ] TASK: Set up string resources architecture using Android strings.xml.
  - [ ] TASK: Define naming conventions and organization structure for string resources.
  - [ ] TASK: Document i18n architecture and guidelines in `docs/tech/internationalization.md`.
- [ ] STORY: String extraction and migration
  - [ ] TASK: Extract all hardcoded UI strings in `feature/auth` to string resources.
  - [ ] TASK: Extract all hardcoded UI strings in `feature/home` to string resources.
  - [ ] TASK: Extract all hardcoded UI strings in `feature/notes` to string resources.
  - [ ] TASK: Extract all hardcoded UI strings in `feature/books` to string resources.
  - [ ] TASK: Extract all hardcoded UI strings in `feature/schedules` to string resources.
  - [ ] TASK: Extract all hardcoded UI strings in `feature/tags` to string resources.
  - [ ] TASK: Extract all hardcoded UI strings in `feature/settings` to string resources.
  - [ ] TASK: Extract all hardcoded UI strings in `feature/profile` to string resources.
  - [ ] TASK: Extract all hardcoded UI strings in `feature/onboarding` to string resources.
  - [ ] TASK: Extract all hardcoded UI strings in `sacrament` design system components.
- [ ] STORY: Pluralization and formatting
  - [ ] TASK: Identify and implement proper plural handling using quantity strings.
  - [ ] TASK: Add support for formatted strings with parameters (dates, numbers, names).
  - [ ] TASK: Implement proper date and time formatting with locale support.
  - [ ] TASK: Add currency and number formatting where applicable.
- [ ] STORY: RTL (Right-to-Left) support
  - [ ] TASK: Enable RTL layout support in manifest and test with Arabic/Hebrew locales.
  - [ ] TASK: Audit and fix layout issues in RTL mode across all screens.
  - [ ] TASK: Ensure proper text alignment and icon mirroring in RTL layouts.
  - [ ] TASK: Test navigation and gestures in RTL mode.
  - [ ] TASK: Document RTL considerations in component development guidelines.
- [ ] STORY: Locale-specific content
  - [ ] TASK: Define strategy for locale-specific images and assets.
  - [ ] TASK: Implement locale-aware content delivery for devotionals and biblical texts.
  - [ ] TASK: Add support for locale-specific date formats and calendar systems.
  - [ ] TASK: Handle locale-specific sorting and collation for lists.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

- [ ] STORY: Translation workflow and tooling
  - [ ] TASK: Set up translation management system or workflow (e.g., Crowdin, Lokalise).
  - [ ] TASK: Create translation guidelines and context documentation for translators.
  - [ ] TASK: Define string context and comments for ambiguous translations.
  - [ ] TASK: Add translation validation checks to catch missing or outdated strings.
  - [ ] TASK: Create process for translation updates and review cycles.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

- [ ] STORY: Initial language support
  - [ ] TASK: Prioritize and select initial set of target languages for MVP.
  - [ ] TASK: Complete translations for selected languages.
  - [ ] TASK: Add language selector in settings with proper locale switching.
  - [ ] TASK: Test all features in each supported language.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

- [ ] STORY: Testing and quality assurance
  - [ ] TASK: Add automated tests for string resource completeness across locales.
  - [ ] TASK: Test with pseudo-localization to identify layout and truncation issues.
  - [ ] TASK: Add CI check to prevent new hardcoded strings from being merged.
  - [ ] TASK: Create manual testing checklist for each new language.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

- [ ] STORY: Dynamic language switching
  - [ ] TASK: Implement in-app language switching without app restart.
  - [ ] TASK: Persist user's language preference across sessions.
  - [ ] TASK: Handle language changes for cached and offline content.
  - [ ] TASK: Test language switching across all app states.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

- [ ] STORY: Documentation and maintenance
  - [ ] TASK: Document string resource conventions in feature development rules.
  - [ ] TASK: Create guidelines for adding new strings and handling edge cases.
  - [ ] TASK: Add i18n checklist to PR review template.
  - [ ] TASK: Document translation maintenance process and ownership.
  - [ ] TASK: DO a code review and ensure that changes made make sense and are correct. If not, fix the issues and commit the changes, remember to follow the commit rules as laid out in the commitRules.md file.

</details>
