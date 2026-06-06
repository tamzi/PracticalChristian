# Work To Be Done

Single source of truth for project tasks, priorities, and status, organized to match the foundation-first build plan in `docs/product/discoveryV2.md` (Phase 0–6).

## How to Use

- Add tasks as short, action-focused items; keep each in the smallest section it fits.
- Tasks are grouped by build phase. A later phase should not ship ahead of the foundation it depends on (see discoveryV2 §15).
- Use `<details>` with a `<summary>` for each epic so it can collapse; nest `STORY:` and `TASK:` lists under the epic.
- Every story closes with a code review and an atomic commit per `commitRules.md` — this is a standing convention, not a repeated task.
- Move completed epics to **Done**.
- Follow the rules laid out in the agentRules folder.

## Active Now

<details>
<summary>EPIC: Design system enforcement (Phase 1 foundation)</summary>

- [ ] STORY: Theme color enforcement
  - [ ] TASK: Enforce using theme colours from the design system across all features.
  - [ ] TASK: Deep review of the design-system work for completeness and correctness; fix any gaps.

</details>

<details>
<summary>EPIC: Cross-tool agent adapters (tooling)</summary>

- [ ] STORY: Cross-tool adapters
  - [ ] TASK: Map Cursor, Codex, and Claude Code project-level support for skills, agents, and instructions.
  - [ ] TASK: Add tool-specific adapter files only where required, keeping `.ai` as the source of truth.
  - [ ] TASK: Document how each tool should load the shared `.ai` setup without duplicating rule content.

</details>

## Phase 0 — Gating Decisions

> These block the build because each shapes the data model, architecture, or content operations later phases assume. Resolve before Phase 1. See discoveryV2 §17.

<details>
<summary>EPIC: Translations & licensing decision</summary>

- [ ] STORY: Translation shortlist + cost/rights matrix
  - [ ] TASK: Decide launch translations and licensing budget; confirm offline/quotation/audio rights.
  - [ ] TASK: Build a translation shortlist with a cost/rights matrix.

</details>

<details>
<summary>EPIC: Platform scope decision</summary>

- [ ] STORY: Android-only vs iOS/KMP
  - [ ] TASK: Decide whether the shared domain targets iOS/KMP now; capture the impact on module/domain architecture.

</details>

<details>
<summary>EPIC: Tradition profiles depth decision</summary>

- [ ] STORY: Scope tradition profiles
  - [ ] TASK: Decide which traditions launch and how deeply they tune content, commentary sourcing, and the CMS schema.

</details>

<details>
<summary>EPIC: Backend platform decision</summary>

- [ ] STORY: Managed vs self-hosted
  - [ ] TASK: Choose the backend platform for sync, directory, ingestion, scheduling, and AI; document the decision.

</details>

<details>
<summary>EPIC: Audio strategy decision</summary>

- [ ] STORY: Licensed vs TTS
  - [ ] TASK: Decide audio strategy and timing (licensed vs TTS); determine whether ASR/media/CDN infra is needed in Phase 1 or Phase 6.

</details>

<details>
<summary>EPIC: Content operations decision</summary>

- [ ] STORY: Editorial board + throughput
  - [ ] TASK: Define editorial board composition and the authoring throughput that sustains daily content.
  - [ ] TASK: Research pastoral content formats, multi-church behavior, privacy language, and opt-in signals for MTS/W.

</details>

<details>
<summary>EPIC: Church platform scope & pricing decision</summary>

- [ ] STORY: B2B scope + pricing bands
  - [ ] TASK: Define church platform feature scope and pricing bands.

</details>

## Phase 1 — Foundations

> Load-bearing infrastructure; cheap to set up early and costly to retrofit. Everything below depends on these.

<details>
<summary>EPIC: Canonical IDs (OSIS)</summary>

- [ ] STORY: Cross-translation verse identity
  - [ ] TASK: Adopt an OSIS-like canonical ID scheme for verses across translations.
  - [ ] TASK: Wire highlights, bookmarks, search, cross-references, and plan/sermon references to canonical IDs.

</details>

<details>
<summary>EPIC: Backend, identity & offline sync</summary>

- [ ] STORY: Sync foundation
  - [ ] TASK: Implement federated sign-in and per-entity versioning.
  - [ ] TASK: Build resumable, idempotent sync with conflict resolution.

</details>

<details>
<summary>EPIC: Encryption model</summary>

- [ ] STORY: At-rest + optional E2E boundary
  - [ ] TASK: Define at-rest encryption and the optional E2E boundary for journals/prayers before sync and search depend on it.

</details>

<details>
<summary>EPIC: Content pipeline & editorial CMS</summary>

- [ ] STORY: Authoring workflow
  - [ ] TASK: Stand up Author → Editor → Theological Reviewer → L10n → QA → Publish with versioning and scheduling.
  - [ ] TASK: Add a Scripture-reference validator.

</details>

<details>
<summary>EPIC: Analytics & event schema</summary>

- [ ] STORY: MTS/W instrumentation
  - [ ] TASK: Define MTS/W and activation/retention events as a schema.
  - [ ] TASK: Instrument session and reflective-action events against the schema.

</details>

## Phase 2 — Core Devotional Experience

> Depends on Phase 1.

<details>
<summary>EPIC: Onboarding preferences</summary>

- [ ] STORY: Rule-of-Life onboarding
  - [ ] TASK: Define required onboarding preferences (time, session length, Sabbath, tradition profile, goals).
  - [ ] TASK: Wire preferences into recommendations.

</details>

<details>
<summary>EPIC: Multi-translation reader</summary>

- [ ] STORY: Reader core
  - [ ] TASK: Highlight, bookmark, copy/share with attribution; offline packs.

</details>

<details>
<summary>EPIC: Session archetypes</summary>

- [ ] STORY: Meditation sessions
  - [ ] TASK: Implement Lectio Divina, Breath Prayer & Silence, and Gratitude Examen with time-boxed variants.
- [ ] STORY: SRS memorization
  - [ ] TASK: Build the Leitner/SRS queue tied to highlights.

</details>

<details>
<summary>EPIC: Reading plans</summary>

- [ ] STORY: Plans + flexibility
  - [ ] TASK: Bible-in-a-year, canonical, thematic, and challenge plans; custom builder.
  - [ ] TASK: Graceful catch-up and freeze/skip options.

</details>

<details>
<summary>EPIC: Encrypted journal + prayer</summary>

- [ ] STORY: Journal + prayer
  - [ ] TASK: Encrypted journal export (Markdown/PDF) + biometric lock.
  - [ ] TASK: Personal prayer requests with statuses and reminders.

</details>

<details>
<summary>EPIC: Daily Devotional Feed</summary>

- [ ] STORY: Define scope
  - [ ] TASK: Specify 5/10/20-min variants, citations, offline cache, and gentle notifications.

</details>

<details>
<summary>EPIC: Offline FTS search</summary>

- [ ] STORY: Search
  - [ ] TASK: Offline full-text search across Scripture, highlights, and notes; saved searches and collections.

</details>

<details>
<summary>EPIC: Humane habit layer</summary>

- [ ] STORY: Gentle habits
  - [ ] TASK: Streaks with freeze windows, Sabbath Mode, Quiet Hours, and digest delivery.

</details>

## Phase 3 — Intelligence & Personalization

> Depends on Phases 1–2.

<details>
<summary>EPIC: Tradition profiles</summary>

- [ ] STORY: Profile tuning
  - [ ] TASK: Tune commentary tone, reading order, and resources by tradition profile.

</details>

<details>
<summary>EPIC: Cross-reference graph</summary>

- [ ] STORY: Related-verse insights
  - [ ] TASK: Curate/license a cross-reference dataset; surface related verses and topical tags.

</details>

<details>
<summary>EPIC: AI Q&A (RAG with citations)</summary>

- [ ] STORY: Guardrailed RAG
  - [ ] TASK: RAG over licensed texts/commentaries with explicit citations; tradition-aware variants presented neutrally.

</details>

<details>
<summary>EPIC: On-device journal insights</summary>

- [ ] STORY: Private NLP (opt-in)
  - [ ] TASK: On-device or privacy-preserving NLP for opt-in journal insights and sentiment.

</details>

## Phase 4 — Community (Safety-First)

> Depends on Phases 1–2.

<details>
<summary>EPIC: Small Circles</summary>

- [ ] STORY: Private circles
  - [ ] TASK: Private shared plans, prayer updates, and weekly check-ins.
- [ ] STORY: Privacy & moderation
  - [ ] TASK: Privacy levels (private → circle → church → public, opt-in).
  - [ ] TASK: Moderation and steward roles; reporting and escalation; sensitive-content protocols.

</details>

## Phase 5 — Church / B2B Platform

> Depends on Phases 1 and 4.

<details>
<summary>EPIC: Church membership & preaching feed</summary>

- [ ] STORY: Directory & membership
  - [ ] TASK: Church Directory Service; join via invite code, search, QR, or domain verification; multiple memberships with a Primary.
- [ ] STORY: Preaching distribution
  - [ ] TASK: Pastor/series/general channels with a subscription graph and push; general channel for non-members.
- [ ] STORY: Sermon-synced plans
  - [ ] TASK: Attach weekly/seasonal plans + reflection prompts with auto-enroll.

</details>

<details>
<summary>EPIC: Content ingestion pipeline</summary>

- [ ] STORY: Sermon ingestion
  - [ ] TASK: Upload (audio/video/PDF/notes) → media storage → CDN → ASR → AI-assisted summary/tagging with human review → publish; auto-link Scripture references.

</details>

<details>
<summary>EPIC: Church operations</summary>

- [ ] STORY: Dashboard, events & rights
  - [ ] TASK: Pastoral dashboard (aggregate only — no journals or prayer content).
  - [ ] TASK: Local event locator and bulletin; RSVP with private follow-ups.
  - [ ] TASK: Rights/moderation — IP/license grant, takedown/DMCA, steward moderation in church spaces.

</details>

## Phase 6 — Reach & Richness

> Depends on Phase 2; Phase 5 for partner content.

<details>
<summary>EPIC: Audio Bibles</summary>

- [ ] STORY: Narrated/TTS audio
  - [ ] TASK: Audio Bibles (licensed or TTS) with verse-synced captions.

</details>

<details>
<summary>EPIC: Internationalization and localization</summary>

- [ ] STORY: i18n infrastructure setup
  - [ ] TASK: Audit current string usage and identify hardcoded strings across all features.
  - [ ] TASK: Set up string resources architecture using Android strings.xml.
  - [ ] TASK: Define naming conventions and organization structure for string resources.
  - [ ] TASK: Document i18n architecture and guidelines in `docs/tech/internationalization.md`.
- [ ] STORY: String extraction and migration
  - [ ] TASK: Extract hardcoded UI strings to string resources in auth, home, notes, books, schedules, tags, settings, profile, and onboarding.
  - [ ] TASK: Extract hardcoded UI strings in `sacrament` design system components.
- [ ] STORY: Pluralization and formatting
  - [ ] TASK: Implement proper plural handling using quantity strings.
  - [ ] TASK: Add formatted strings with parameters (dates, numbers, names).
  - [ ] TASK: Implement locale-aware date/time, currency, and number formatting.
- [ ] STORY: RTL (Right-to-Left) support
  - [ ] TASK: Enable RTL layout support and test with Arabic/Hebrew locales.
  - [ ] TASK: Audit and fix layout, text alignment, and icon mirroring in RTL mode.
  - [ ] TASK: Test navigation and gestures in RTL; document RTL considerations in component guidelines.
- [ ] STORY: Locale-specific content
  - [ ] TASK: Define a strategy for locale-specific images and assets.
  - [ ] TASK: Implement locale-aware content delivery for devotionals and biblical texts.
  - [ ] TASK: Support locale-specific date formats, calendars, sorting, and collation.
- [ ] STORY: Translation workflow and tooling
  - [ ] TASK: Set up a translation management workflow (e.g., Crowdin, Lokalise).
  - [ ] TASK: Create translation guidelines and string context for translators.
  - [ ] TASK: Add validation checks for missing or outdated strings and a review-cycle process.
- [ ] STORY: Initial language support
  - [ ] TASK: Prioritize and select the initial set of target languages for launch.
  - [ ] TASK: Complete translations and add a language selector with proper locale switching.
  - [ ] TASK: Test all features in each supported language.
- [ ] STORY: Testing and quality assurance
  - [ ] TASK: Add automated tests for string-resource completeness across locales.
  - [ ] TASK: Test with pseudo-localization for layout/truncation issues.
  - [ ] TASK: Add a CI check to prevent new hardcoded strings; create a manual per-language checklist.
- [ ] STORY: Dynamic language switching
  - [ ] TASK: Implement in-app language switching without restart and persist the preference.
  - [ ] TASK: Handle language changes for cached and offline content; test across app states.

</details>

<details>
<summary>EPIC: Web reader</summary>

- [ ] STORY: Web reader
  - [ ] TASK: Browser-based reader sharing the domain layer.

</details>

<details>
<summary>EPIC: Devotional marketplace</summary>

- [ ] STORY: Partner content
  - [ ] TASK: Marketplace for vetted devotional series and partner devotionals.

</details>

## Cross-Cutting (every phase)

> Privacy/security, accessibility, content cadence, and cost/ops are acceptance gates on all phases, not standalone phases.

<details>
<summary>EPIC: Accessibility compliance and inclusive design</summary>

- [ ] STORY: Accessibility audit and baseline
  - [ ] TASK: Run an accessibility scanner on all screens and document the current state.
  - [ ] TASK: Test with TalkBack and Switch Access to identify navigation issues.
  - [ ] TASK: Audit color contrast across design system tokens against WCAG AA.
  - [ ] TASK: Create an accessibility compliance checklist in `docs/tech/accessibility.md`.
- [ ] STORY: Semantic content descriptions
  - [ ] TASK: Add meaningful contentDescription to icons, images, and interactive elements; null for decorative.
  - [ ] TASK: Improve semantic roles for custom components and add state descriptions for toggles/checkboxes/progress.
- [ ] STORY: Keyboard and focus navigation
  - [ ] TASK: Ensure all interactive elements are keyboard/D-pad navigable with a logical focus order.
  - [ ] TASK: Add visual focus indicators and fix focus traps in modals and bottom sheets.
- [ ] STORY: Touch target sizing
  - [ ] TASK: Audit interactive elements for the 48dp minimum and add spacing tokens.
  - [ ] TASK: Fix small touch targets in list items, icon buttons, and chips.
- [ ] STORY: Text accessibility
  - [ ] TASK: Ensure text scales with system font settings; test 200% scaling and fix overflow/truncation.
  - [ ] TASK: Add heading semantics and ensure minimum 16sp body text across features.
- [ ] STORY: Dynamic content and live regions
  - [ ] TASK: Add live region announcements for dynamic updates, errors, and long-running progress.
  - [ ] TASK: Improve announcements for navigation changes.
- [ ] STORY: Accessibility testing infrastructure
  - [ ] TASK: Add automated accessibility tests using Compose testing semantics for critical flows.
  - [ ] TASK: Add a CI check for common issues (missing contentDescription, small touch targets) and document guidelines.
- [ ] STORY: Alternative input methods
  - [ ] TASK: Optimize for Switch Access and voice input in all text fields.
  - [ ] TASK: Add external keyboard shortcuts for common actions.
- [ ] STORY: Accessibility documentation
  - [ ] TASK: Add accessibility guidelines for `sacrament` component development with examples.
  - [ ] TASK: Add accessibility requirements to feature dev rules and a PR review checklist.

</details>

<details>
<summary>EPIC: Secure error handling</summary>

- [ ] STORY: App-wide policy and guidance
  - [ ] TASK: Define a user-facing error copy catalog and severity categories.
  - [ ] TASK: Define an error code taxonomy and mapping ownership by layer.
  - [ ] TASK: Document secure error handling guidelines in `docs/tech/errorHandling.md`.
- [ ] STORY: UI sanitization
  - [ ] TASK: Replace direct rendering of raw error messages in feature UIs (notifications, schedules, tags, books, notes).
  - [ ] TASK: Route user-facing copy through a single mapping utility per feature or a shared helper.
- [ ] STORY: Logging and telemetry
  - [ ] TASK: Add redaction and correlation IDs for error logs and crash reports.
  - [ ] TASK: Verify analytics payloads exclude secrets and PII.
- [ ] STORY: Edge case management
  - [ ] TASK: Gate POST_NOTIFICATIONS permission requests to Android 13+ in `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/reminder/NotificationReminderScreen.kt`.
  - [ ] TASK: Define handling for permission denial and "don't ask again" states in notifications onboarding.
  - [ ] TASK: Add API-level behavior guidance in `docs/tech/errorHandling.md`.

</details>

<details>
<summary>EPIC: Comprehensive audit trails</summary>

- [ ] STORY: Define audit trail scope and criteria
  - [ ] TASK: Define a critical action catalog by feature.
  - [ ] TASK: Document architecture and event schema in `docs/tech/auditTrails.md`.
- [ ] STORY: Feature instrumentation
  - [ ] TASK: Add audit events for notification permission request and skip outcomes in `feature/notifications/src/main/java/com/practicalchristian/app/feature/notifications/reminder/NotificationReminderViewModel.kt`.
- [ ] STORY: Local persistence and retention
  - [ ] TASK: Add audit event storage using Room with encryption at rest.
  - [ ] TASK: Enforce 90-day retention with periodic cleanup.
- [ ] STORY: Background sync
  - [ ] TASK: Add a local queue and upload worker with retry/backoff.
  - [ ] TASK: Add a stub backend interface and ingestion contract.
- [ ] STORY: Compliance access
  - [ ] TASK: Define the backend export format and access flow.

</details>

<details>
<summary>EPIC: Unit test coverage per feature</summary>

- [ ] STORY: Feature unit tests (ViewModels, use cases, state)
  - [ ] TASK: Write unit tests for `feature/audio`.
  - [ ] TASK: Write unit tests for `feature/auth`.
  - [ ] TASK: Write unit tests for `feature/bookmarks`.
  - [ ] TASK: Write unit tests for `feature/books`.
  - [ ] TASK: Write unit tests for `feature/home`.
  - [ ] TASK: Write unit tests for `feature/journal`.
  - [ ] TASK: Write unit tests for `feature/landing`.
  - [ ] TASK: Write unit tests for `feature/meditation`.
  - [ ] TASK: Write unit tests for `feature/notes`.
  - [ ] TASK: Write unit tests for `feature/notifications`.
  - [ ] TASK: Write unit tests for `feature/onboarding`.
  - [ ] TASK: Write unit tests for `feature/plans`.
  - [ ] TASK: Write unit tests for `feature/prayer`.
  - [ ] TASK: Write unit tests for `feature/presentation`.
  - [ ] TASK: Write unit tests for `feature/profile`.
  - [ ] TASK: Write unit tests for `feature/schedules`.
  - [ ] TASK: Write unit tests for `feature/search`.
  - [ ] TASK: Write unit tests for `feature/settings`.
  - [ ] TASK: Write unit tests for `feature/setup`.
  - [ ] TASK: Write unit tests for `feature/streaks`.
  - [ ] TASK: Write unit tests for `feature/tags`.
- [ ] STORY: Test enforcement gate
  - [ ] TASK: Fix the Gradle test config and re-enable the commented-out `test` step in `scripts/pre-push.sh`.
  - [ ] TASK: Add a JaCoCo coverage threshold via `jacocoTestCoverageVerification` and run it in `scripts/pre-push.sh` so pushes are blocked below threshold — new features ship with unit tests by convention.

</details>

## Done

<details>
<summary>EPIC: Bottom Navigation Spotlight Indicator</summary>

- [x] Spotlight indicator with theme-aware coloring, vertical glow, and position animation shipped across `SacramentBottomBar`, the demo navigation, and Compose previews; touch targets, dark-mode-only glow, and theme switching validated and reviewed.

</details>

<details>
<summary>EPIC: AI agent and skills setup</summary>

- [x] Shared workflow/commit/documentation/testing/design-system/quality skills and the planner/implementer/reviewer/test-writer/verifier/security/product agents created under `.ai`, Firebender wired to load them without duplication, and skill resolution / agent registration / symlink tracking validated. (Remaining cross-tool adapter work tracked under Active Now.)

</details>
