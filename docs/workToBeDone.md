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


- [x] STORY: Patterns + previews + test hooks
  - [x] TASK: Implement patterns (ScreenScaffold, EmptyState, ErrorState, LoadingState).
  - [x] TASK: Add preview infrastructure (PreviewTheme, SampleModels, PreviewParameterProviders).
  - [x] TASK: Standardize component API order + slot APIs; co-locate Defaults/Tokens where needed.
  - [x] TASK: Add TestTags + semantics defaults + accessibility minimum touch targets.

- [x] STORY: Design system catalog app + docs
  - [x] TASK: Align design system module naming/structure (`sacrament`) and update settings + docs + dependencies accordingly.
    - [x] TASK: Update task reference from `:core:designsystem` to `sacrament` in workToBeDone.md (module is correctly named `sacrament`).
  - [x] TASK: Add `:sacrament-demo` app to showcase the design system.
  - [x] TASK: Build component catalog screens with variants/sizes/intents.
  - [x] TASK: Update `sacrament/README.md` to link to design system guide and document how to add components (README exists but may need enhancement).
  - [x] TASK: Add a "Definition of Done" checklist for components in `CONTRIBUTING.md` (previews + tests + a11y).
    - [x] TASK: Create `CONTRIBUTING.md` at project root if it doesn't exist.

- [ ] STORY: Enforce design system boundaries
  - [x] TASK: Remove `androidx.compose.material3.*` usage inside the design system module.
  - [ ] TASK: Replace remaining hardcoded styles with design system tokens across `app`, `core`, and `feature` modules.
  - [ ] TASK: Replace Material3 UI components in `app`, `core`, and `feature` modules with design system primitives/components.
    - [ ] TASK: Replace Material3 components in `feature/tags` (heavy usage: Button, Card, Scaffold, TextField, etc.).
    - [ ] TASK: Replace Material3 components in `feature/schedules` (Button, Card, Scaffold, TopAppBar, etc.).
    - [ ] TASK: Replace Material3 components in `feature/notes` (Scaffold, TextField, TopAppBar, etc.).
    - [ ] TASK: Replace Material3 components in `feature/books` (Card, Scaffold, TopAppBar, etc.).
    - [ ] TASK: Replace Material3 components in `feature/auth`, `feature/home`, `feature/settings`, `feature/setup`, `feature/profile`, `feature/onboarding`.
    - [ ] TASK: Replace Material3 Scaffold in `sacrament` module (currently used internally by `SacramentScreenScaffold`).
    - [ ] TASK: Remove Material3 dependency from `app/build.gradle.kts`.
    - [ ] TASK: Remove Material3 dependency from `core/ui/build.gradle.kts`.
  - [x] TASK: Migrate remaining `PracticalChristianPalette` usages to `PracticalChristianTokens` across feature modules.
  - [ ] TASK: Migrate feature previews to use the design system theme for consistent visuals.
    - [ ] TASK: Update previews in `feature/tags` to use `SacramentTheme`.
    - [ ] TASK: Update previews in `feature/schedules` to use `SacramentTheme`.
    - [ ] TASK: Verify all feature previews use `SacramentTheme` (landing, home, notes, profile already migrated).
  - [ ] TASK: Add lint/detekt guardrails to block `androidx.compose.material3.*` and `Color(0x...)` outside `sacrament`.
  - [ ] TASK: Remove Material3 dependency from feature convention plugin and module build files once migrations are complete.
    - [ ] TASK: Remove Material3 from `buildLogic/convention/src/main/kotlin/com/practicalchristian/app/convention/feature/AndroidFeatureConventionPlugin.kt`.
  - [ ] TASK: Add CI check to enforce design system rules.
  - [ ] TASK: Document usage in `docs/tech/technicalArchitecture.md` and `docs/agentRules/featureDevelopmentRules.md`.

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
