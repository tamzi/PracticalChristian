# Work To Be Done

Single source of truth for project tasks, priorities, and status.

## How to Use

- Add tasks as short, action-focused items.
- Keep tasks in the smallest section they fit.
- Move items as they progress (Current -> Next -> Backlog -> Done).
- Prefer checkboxes for clarity.
- Use `<details>` with a `<summary>` for each epic so it can collapse; nest `STORY:` and `TASK:` lists under the epic.

## Current Focus

<details>
<summary>EPIC: Design system setup + enforcement</summary>

- [x] STORY: Inventory + baseline
  - [x] TASK: Audit remaining Material3 usage and hardcoded colors outside `sacrament`.
  - [x] TASK: Run and validate `scripts/check-design-system-usage.sh` output.
- [x] STORY: Feature migrations - tags
  - [x] TASK: Replace Material3 components in `feature/tags` (heavy usage: Button, Card, Scaffold, TextField, etc.).
  - [x] TASK: Replace hardcoded styles with design system tokens in `feature/tags`.
  - [x] TASK: Update previews in `feature/tags` to use `SacramentTheme`.
- [x] STORY: Feature migrations - schedules
  - [x] TASK: Replace Material3 components in `feature/schedules` (Button, Card, Scaffold, TopAppBar, etc.).
  - [x] TASK: Replace hardcoded styles with design system tokens in `feature/schedules`.
  - [x] TASK: Update previews in `feature/schedules` to use `SacramentTheme`.
- [x] STORY: Feature migrations - notes
  - [x] TASK: Replace Material3 components in `feature/notes` (Scaffold, TextField, TopAppBar, etc.).
  - [x] TASK: Replace hardcoded styles with design system tokens in `feature/notes`.
  - [x] TASK: Update previews in `feature/notes` to use `SacramentTheme`.
- [x] STORY: Feature migrations - books
  - [x] TASK: Replace Material3 components in `feature/books` (Card, Scaffold, TopAppBar, etc.).
  - [x] TASK: Replace hardcoded styles with design system tokens in `feature/books`.
  - [x] TASK: Update previews in `feature/books` to use `SacramentTheme`.
- [x] STORY: Feature migrations - auth
  - [x] TASK: Replace Material3 components in `feature/auth`.
  - [x] TASK: Replace hardcoded styles with design system tokens in `feature/auth`.
  - [x] TASK: Update previews in `feature/auth` to use `SacramentTheme`.
- [x] STORY: Feature migrations - home
  - [x] TASK: Replace Material3 components in `feature/home`.
  - [x] TASK: Replace hardcoded styles with design system tokens in `feature/home`.
  - [x] TASK: Update previews in `feature/home` to use `SacramentTheme`.
- [x] STORY: Feature migrations - settings
  - [x] TASK: Replace Material3 components in `feature/settings`.
  - [x] TASK: Replace hardcoded styles with design system tokens in `feature/settings`.
  - [x] TASK: Update previews in `feature/settings` to use `SacramentTheme`.
- [x] STORY: Feature migrations - setup
  - [x] TASK: Replace Material3 components in `feature/setup`.
  - [x] TASK: Replace hardcoded styles with design system tokens in `feature/setup`.
  - [x] TASK: Update previews in `feature/setup` to use `SacramentTheme`.
- [x] STORY: Feature migrations - profile
  - [x] TASK: Replace Material3 components in `feature/profile`.
  - [x] TASK: Replace hardcoded styles with design system tokens in `feature/profile`.
  - [x] TASK: Update previews in `feature/profile` to use `SacramentTheme`.
- [x] STORY: Feature migrations - onboarding
  - [x] TASK: Replace Material3 components in `feature/onboarding`.
  - [x] TASK: Replace hardcoded styles with design system tokens in `feature/onboarding`.
  - [x] TASK: Update previews in `feature/onboarding` to use `SacramentTheme`.
- [x] STORY: Core + app migration
  - [x] TASK: Replace Material3 UI components in `app` and `core` with design system primitives/components.
  - [x] TASK: Replace remaining hardcoded styles with design system tokens across `app` and `core`.
  - [x] TASK: Remove Material3 dependency from `app/build.gradle.kts`.
  - [x] TASK: Remove Material3 dependency from `core/ui/build.gradle.kts`.
- [x] STORY: Sacrament internal cleanup
  - [x] TASK: Replace Material3 Scaffold in `sacrament` (used by `SacramentScreenScaffold`).
  - [x] TASK: Verify `sacrament-demo` renders correctly after removal.
- [x] STORY: Icon centralization
  - [x] TASK: Create centralized `SacramentIcons` registry in `sacrament` module.
  - [x] TASK: Update all feature modules to use `SacramentIcons` instead of Material icons directly.
  - [x] TASK: Update `core/ui` to use `SacramentIcons`.
  - [x] TASK: Update enforcement script to allow Material icons only in `sacrament` module.
  - [x] TASK: Update documentation to clarify icon usage rules.
- [x] STORY: Previews + theme consistency
  - [x] TASK: Verify all feature previews use `SacramentTheme` (landing, home, notes, profile already migrated).
- [x] STORY: Documentation
  - [x] TASK: Document usage in `docs/tech/technicalArchitecture.md` and `docs/agentRules/featureDevelopmentRules.md`.
- [x] STORY: Enforcement (last)
  - [x] TASK: Add lint/detekt guardrails to block `androidx.compose.material3.*` and `Color(0x...)` outside `sacrament`.
  - [x] TASK: Remove Material3 dependency from feature convention plugin and module build files once migrations are complete.
    - [x] TASK: Remove Material3 from `buildLogic/convention/src/main/kotlin/com/practicalchristian/app/convention/feature/AndroidFeatureConventionPlugin.kt`.
  - [x] TASK: Add CI check to enforce design system rules.
  - [ ] TASK: enforce using theme colours from the design system.
  - [ ] TASK: Do a thorough deep code review of this epic to ensure it is complete and correct. Fix any issues found and commit the changes.

</details>

## Next Up

<details>
<summary>EPIC: MVP product decisions</summary>

- [ ] STORY: Translation + licensing constraints
  - [ ] TASK: Decide MVP translations + licensing budget (confirm offline rights requirements).
- [ ] STORY: Onboarding preferences
  - [ ] TASK: Define required onboarding preferences (time, session length, Sabbath, tradition profile).

</details>

## Backlog

<details>
<summary>EPIC: Daily Devotional Feed MVP</summary>

- [ ] STORY: Define MVP scope
  - [ ] TASK: Specify 5/10/20 min variants, citations, offline cache, gentle notifications.

</details>

<details>
<summary>EPIC: Church membership + preaching feed MVP</summary>

- [ ] STORY: Define MVP scope
  - [ ] TASK: Scope invite/search and general channel experiences.

</details>

<details>
<summary>EPIC: Small Circles MVP</summary>

- [ ] STORY: Define MVP scope
  - [ ] TASK: Define private plans, prayer updates, weekly check-ins.

</details>

<details>
<summary>EPIC: Encrypted journal export + biometric lock</summary>

- [ ] STORY: Define MVP scope
  - [ ] TASK: Plan encrypted journal export + biometric lock.

</details>

<details>
<summary>EPIC: SRS memorization MVP</summary>

- [ ] STORY: Define MVP scope
  - [ ] TASK: Plan Leitner queue + tie to highlights.

</details>

<details>
<summary>EPIC: Licensing track</summary>

- [ ] STORY: Translation shortlist + cost/rights matrix
  - [ ] TASK: Build licensing track (translation shortlist + cost/rights matrix).

</details>

<details>
<summary>EPIC: Audio strategy and roadmap</summary>

- [ ] STORY: Define options
  - [ ] TASK: Decide audio strategy and roadmap (TTS vs licensed).

</details>

<details>
<summary>EPIC: Research pastoral content formats</summary>

- [ ] STORY: Discovery research
  - [ ] TASK: Run research on pastoral content formats, multi-church behavior, privacy language, and opt-in signals for MTS/W.

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

</details>

<details>
<summary>EPIC: Accessibility compliance and inclusive design</summary>

- [ ] STORY: Accessibility audit and baseline
  - [ ] TASK: Run accessibility scanner on all screens and document current state.
  - [ ] TASK: Test with TalkBack and Switch Access to identify navigation issues.
  - [ ] TASK: Audit color contrast ratios across all design system tokens against WCAG AA standards.
  - [ ] TASK: Create accessibility compliance checklist and document in `docs/tech/accessibility.md`.
- [ ] STORY: Semantic content descriptions
  - [ ] TASK: Add meaningful contentDescription to all icons, images, and interactive elements.
  - [ ] TASK: Ensure decorative elements have null contentDescription to avoid screen reader clutter.
  - [ ] TASK: Review and improve semantic roles for custom components (buttons, lists, headers).
  - [ ] TASK: Add state descriptions for toggles, checkboxes, and progress indicators.
- [ ] STORY: Keyboard and focus navigation
  - [ ] TASK: Ensure all interactive elements are keyboard/D-pad navigable.
  - [ ] TASK: Verify logical focus order across all screens.
  - [ ] TASK: Add visual focus indicators for keyboard navigation.
  - [ ] TASK: Test and fix focus traps in modals and bottom sheets.
- [ ] STORY: Touch target sizing
  - [ ] TASK: Audit all interactive elements to ensure minimum 48dp touch targets.
  - [ ] TASK: Add spacing tokens to design system for accessible touch targets.
  - [ ] TASK: Fix small touch targets in list items, icon buttons, and chips.
- [ ] STORY: Text accessibility
  - [ ] TASK: Ensure all text scales properly with system font size settings.
  - [ ] TASK: Test layouts with 200% font scaling and fix overflow/truncation issues.
  - [ ] TASK: Add proper heading semantics using Modifier.semantics for screen reader navigation.
  - [ ] TASK: Ensure minimum 16sp font size for body text across all features.
- [ ] STORY: Dynamic content and live regions
  - [ ] TASK: Add live region announcements for dynamic content updates (notifications, loading states).
  - [ ] TASK: Ensure error messages are announced to screen readers.
  - [ ] TASK: Add progress announcements for long-running operations.
  - [ ] TASK: Test and improve announcements for navigation changes.
- [ ] STORY: Accessibility testing infrastructure
  - [ ] TASK: Add automated accessibility tests using Compose testing semantics.
  - [ ] TASK: Create accessibility test suite for critical user flows.
  - [ ] TASK: Add CI check for common accessibility issues (missing contentDescription, small touch targets).
  - [ ] TASK: Document accessibility testing guidelines for feature development.
- [ ] STORY: Alternative input methods
  - [ ] TASK: Test and optimize for Switch Access navigation.
  - [ ] TASK: Ensure voice input works properly in all text fields.
  - [ ] TASK: Add support for external keyboard shortcuts for common actions.
- [ ] STORY: Accessibility documentation
  - [ ] TASK: Create accessibility guidelines for component development in `sacrament` module.
  - [ ] TASK: Document accessibility requirements in feature development rules.
  - [ ] TASK: Add accessibility section to component documentation with examples.
  - [ ] TASK: Create accessibility checklist for PR reviews.

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
- [ ] STORY: Translation workflow and tooling
  - [ ] TASK: Set up translation management system or workflow (e.g., Crowdin, Lokalise).
  - [ ] TASK: Create translation guidelines and context documentation for translators.
  - [ ] TASK: Define string context and comments for ambiguous translations.
  - [ ] TASK: Add translation validation checks to catch missing or outdated strings.
  - [ ] TASK: Create process for translation updates and review cycles.
- [ ] STORY: Initial language support
  - [ ] TASK: Prioritize and select initial set of target languages for MVP.
  - [ ] TASK: Complete translations for selected languages.
  - [ ] TASK: Add language selector in settings with proper locale switching.
  - [ ] TASK: Test all features in each supported language.
- [ ] STORY: Testing and quality assurance
  - [ ] TASK: Add automated tests for string resource completeness across locales.
  - [ ] TASK: Test with pseudo-localization to identify layout and truncation issues.
  - [ ] TASK: Add CI check to prevent new hardcoded strings from being merged.
  - [ ] TASK: Create manual testing checklist for each new language.
- [ ] STORY: Dynamic language switching
  - [ ] TASK: Implement in-app language switching without app restart.
  - [ ] TASK: Persist user's language preference across sessions.
  - [ ] TASK: Handle language changes for cached and offline content.
  - [ ] TASK: Test language switching across all app states.
- [ ] STORY: Documentation and maintenance
  - [ ] TASK: Document string resource conventions in feature development rules.
  - [ ] TASK: Create guidelines for adding new strings and handling edge cases.
  - [ ] TASK: Add i18n checklist to PR review template.
  - [ ] TASK: Document translation maintenance process and ownership.

</details>
