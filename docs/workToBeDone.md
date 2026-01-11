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
- [ ] STORY: Feature migrations - auth
  - [ ] TASK: Replace Material3 components in `feature/auth`.
  - [ ] TASK: Replace hardcoded styles with design system tokens in `feature/auth`.
  - [ ] TASK: Update previews in `feature/auth` to use `SacramentTheme`.
- [ ] STORY: Feature migrations - home
  - [ ] TASK: Replace Material3 components in `feature/home`.
  - [ ] TASK: Replace hardcoded styles with design system tokens in `feature/home`.
  - [ ] TASK: Update previews in `feature/home` to use `SacramentTheme`.
- [ ] STORY: Feature migrations - settings
  - [ ] TASK: Replace Material3 components in `feature/settings`.
  - [ ] TASK: Replace hardcoded styles with design system tokens in `feature/settings`.
  - [ ] TASK: Update previews in `feature/settings` to use `SacramentTheme`.
- [ ] STORY: Feature migrations - setup
  - [ ] TASK: Replace Material3 components in `feature/setup`.
  - [ ] TASK: Replace hardcoded styles with design system tokens in `feature/setup`.
  - [ ] TASK: Update previews in `feature/setup` to use `SacramentTheme`.
- [ ] STORY: Feature migrations - profile
  - [ ] TASK: Replace Material3 components in `feature/profile`.
  - [ ] TASK: Replace hardcoded styles with design system tokens in `feature/profile`.
  - [ ] TASK: Update previews in `feature/profile` to use `SacramentTheme`.
- [ ] STORY: Feature migrations - onboarding
  - [ ] TASK: Replace Material3 components in `feature/onboarding`.
  - [ ] TASK: Replace hardcoded styles with design system tokens in `feature/onboarding`.
  - [ ] TASK: Update previews in `feature/onboarding` to use `SacramentTheme`.
- [ ] STORY: Core + app migration
  - [ ] TASK: Replace Material3 UI components in `app` and `core` with design system primitives/components.
  - [ ] TASK: Replace remaining hardcoded styles with design system tokens across `app` and `core`.
  - [ ] TASK: Remove Material3 dependency from `app/build.gradle.kts`.
  - [ ] TASK: Remove Material3 dependency from `core/ui/build.gradle.kts`.
- [ ] STORY: Sacrament internal cleanup
  - [ ] TASK: Replace Material3 Scaffold in `sacrament` (used by `SacramentScreenScaffold`).
  - [ ] TASK: Verify `sacrament-demo` renders correctly after removal.
- [ ] STORY: Previews + theme consistency
  - [ ] TASK: Verify all feature previews use `SacramentTheme` (landing, home, notes, profile already migrated).
- [ ] STORY: Documentation
  - [ ] TASK: Document usage in `docs/tech/technicalArchitecture.md` and `docs/agentRules/featureDevelopmentRules.md`.
- [ ] STORY: Enforcement (last)
  - [ ] TASK: Add lint/detekt guardrails to block `androidx.compose.material3.*` and `Color(0x...)` outside `sacrament`.
  - [ ] TASK: Remove Material3 dependency from feature convention plugin and module build files once migrations are complete.
    - [ ] TASK: Remove Material3 from `buildLogic/convention/src/main/kotlin/com/practicalchristian/app/convention/feature/AndroidFeatureConventionPlugin.kt`.
  - [ ] TASK: Add CI check to enforce design system rules.
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
