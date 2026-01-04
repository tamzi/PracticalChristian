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
  - [ ] TASK: Standardize component API order + slot APIs; co-locate Defaults/Tokens where needed.
  - [x] TASK: Add TestTags + semantics defaults + accessibility minimum touch targets.

- [ ] STORY: Design system catalog app + docs
  - [ ] TASK: Align design system module naming/structure (`sacrament`) and update settings + docs + dependencies accordingly.
  - [ ] TASK: Add `:sacrament-demo` app to showcase the design system.
  - [ ] TASK: Build component catalog screens with variants/sizes/intents.
  - [ ] TASK: Add `:core:designsystem/README.md` linking to the guide and how to add components.
  - [ ] TASK: Add a "Definition of Done" checklist for components in `CONTRIBUTING.md` (previews + tests + a11y).

- [ ] STORY: Enforce design system boundaries
  - [x] TASK: Remove `androidx.compose.material3.*` usage inside the design system module.
  - [ ] TASK: Replace remaining hardcoded styles with design system tokens across `app`, `core`, and `feature` modules.
  - [ ] TASK: Replace Material3 UI components in `app`, `core`, and `feature` modules with design system primitives/components.
  - [x] TASK: Migrate remaining `PracticalChristianPalette` usages to `PracticalChristianTokens` across feature modules.
  - [ ] TASK: Migrate feature previews to use the design system theme for consistent visuals.
  - [ ] TASK: Add lint/detekt guardrails to block `androidx.compose.material3.*` and `Color(0x...)` outside `sacrament`.
  - [ ] TASK: Remove Material3 dependency from feature convention plugin and module build files once migrations are complete.
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
