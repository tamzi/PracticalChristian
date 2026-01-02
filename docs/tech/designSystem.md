# Sacrament Design System Guide

This guide defines how we build and organize a Compose-first design system. It is
the source of truth for tokens, primitives, components, patterns, and rules for
keeping feature UI out of the design system.

## 1) What this is

A Compose-first design system that enforces consistency through
tokens -> primitives -> components -> patterns, while keeping pages/screens in
feature modules.

### Layers

- Tokens (Foundation): raw values + semantic roles (colors, typography, spacing,
  radius, elevation, motion, icon sizes)
- Primitives (Atoms): thin wrappers around Compose UI/Foundation that enforce
  tokens + defaults
- Components (Molecules/Organisms): reusable UI building blocks (Button,
  TextField, ListItem, AppBar, etc.)
- Patterns (Templates): screen scaffolds + standard compositions
  (SacramentScreenScaffold, SacramentEmptyState, SacramentErrorState, SacramentAuthLayout)

Rule: pages live in feature modules, not in the design system.

## 2) How this stacks up vs common Android design-system approaches

### What we're choosing

We are building a Material-free Compose design system:

- No MaterialTheme
- No ColorScheme, Typography, Shapes from Material
- No Material components (Button, TextField, etc.)

We do use Compose UI + Foundation (layout, text, gestures, scrolling,
animation) and implement our own primitives/components on top of our tokens.

### A) Typical Material 3 app (MaterialTheme + M3 components)

- Pros: fast start, lots of behavior/polish for free (states, accessibility,
  inputs)
- Cons: hard to fully own visual identity; teams often drift into ad-hoc
  overrides
- How ours compares: we take full ownership of look/feel and API consistency, at
  the cost of more implementation work

### B) Theme-only DS (tokens + custom MaterialTheme)

- Pros: better consistency than A, moderate effort
- Cons: still relies on Material component behavior/styling constraints
- How ours compares: we avoid Material types altogether; our theme is our
  CompositionLocals + our component library

### C) Full bespoke component library (large org style)

- Pros: strongest consistency and brand control; scalable across teams
- Cons: requires governance + ongoing maintenance
- How ours compares: we match this approach structurally (tokens -> primitives ->
  components -> patterns), but keep a hard line: no feature UI in the DS

### What we must own (trade-offs)

Going Material-free means we must implement/standardize:

- component states (pressed/disabled/focused/selected)
- indication/ripple (or custom feedback)
- touch target sizing + semantics/a11y defaults
- input behaviors and styling boundaries (especially text fields)
- consistent layout/spacing + motion specs

Bottom line: this structure is production-grade and gives maximum brand control,
but only works well with solid conventions, a catalog, and tests.

## 3) Recommended module layout

- `sacrament`: tokens + primitives + components + patterns
  (Material-free)
- `sacrament-demo`: gallery/catalog app to browse components
- `:core:testing`: JVM-only test utilities (fast, no Android plugin)
- `:core:ui-testing`: Android/Compose test-only utilities
  (instrumentation + screenshot)

### Clean split (recommended for long-term)

`:core:testing` (JVM-only)

- fakes and builders
- coroutine test rules
- time abstractions (clock) + test clocks
- logging stubs

`:core:ui-testing` (Android/Compose test-only)

- Compose UI test helpers
- Semantics matchers and node helpers
- screenshot infra (if used)
- TestTags

Tiny dependency rules:

- `:core:testing` must not apply the Android Gradle Plugin and must not depend on
  AndroidX UI/Compose artifacts
- `:core:ui-testing` may apply the Android plugin and depend on
  `androidx.compose.ui:ui-test-junit4`, `androidx.test` libraries, and screenshot
  test tooling

Feature modules should depend on only what they need: JVM tests use
`:core:testing`; instrumentation/screenshot tests use `:core:ui-testing`.

Compose-only note: you can skip `androidx.compose.material3:*` entirely. Build
primitives/components with `androidx.compose.ui`, `androidx.compose.foundation`,
and `androidx.compose.animation`.

## 4) Package structure inside :sacrament

```
sacrament/
  foundation/
    color/
      Palette.kt               // raw palette + neutrals + raw brand colors
      SacramentColorTokens.kt      // semantic token models
      LightColors.kt           // LightSacramentColors mapping
      DarkColors.kt            // DarkSacramentColors mapping
      ColorAccessors.kt        // optional helpers for resolving roles

    icon/
      IconTokens.kt

    typography/
    shape/
    layout/
    elevation/
    motion/
    Theme.kt

  primitives/
    SacramentText.kt
    SacramentIcon.kt
    SacramentSurface.kt
    SacramentDivider.kt

  components/
    action/
      SacramentButton.kt
      SacramentIconButton.kt
      SacramentFab.kt

    input/
      SacramentTextField.kt
      SacramentSearchField.kt
      SacramentCheckbox.kt
      SacramentRadio.kt
      SacramentSwitch.kt

    navigation/
      SacramentTopAppBar.kt
      SacramentBottomBar.kt
      SacramentTabRow.kt
      SacramentNavigationRail.kt

    surface/
      SacramentCard.kt
      SacramentSheet.kt
      SacramentDialog.kt

    content/
      SacramentListItem.kt
      SacramentAvatar.kt
      SacramentChip.kt
      SacramentBadge.kt
      SacramentTag.kt

    feedback/
      SacramentSnackbar.kt
      SacramentToastHost.kt
      SacramentProgressIndicator.kt
      SacramentInlineMessage.kt

    sections/
      SacramentSectionHeader.kt
      SacramentSectionCard.kt
      SacramentSectionList.kt
      SacramentWidgetProfileHeader.kt
      SacramentWidgetToolbarWithSearch.kt
      SacramentWidgetSummaryPanel.kt

  patterns/
    SacramentScreenScaffold.kt
    SacramentEmptyState.kt
    SacramentErrorState.kt
    SacramentLoadingState.kt

  preview/
    PreviewTheme.kt
    SampleModels.kt
    PreviewParameterProviders.kt
```

### Example placement of an existing file

Recommended split:

- `foundation/color/SacramentColorTokens.kt`: SacramentBrandColors, SacramentTextColors,
  SacramentSurfaceColors, SacramentSemanticColors, SacramentUtilityColors,
  SacramentOnboardingColors, SacramentColorTokens
- `foundation/color/LightColors.kt`: LightSacramentColors
- `foundation/color/DarkColors.kt`: DarkSacramentColors
- `foundation/color/Palette.kt`: SacramentPalette and raw colors like Neutral50/300,
  ErrorRed*, InfoBlue*, etc.

Also: avoid importing raw colors from a separate `ui.theme` package. Keep raw
palette + neutrals in `foundation/color` so tokens do not get scattered.

## 4.1) Current gaps (pre-migration audit)

- Tokens lived outside `foundation/` and were scattered under theme packages.
- Raw palette values were split across multiple files instead of a single palette source.
- Typography and shapes relied on Material3 types.
- Motion tokens were not defined.
- Theme used `MaterialTheme` and Material color schemes.

### Rule (Material-free)

- No design-system file should import `androidx.compose.material3.*`.
- `foundation/Theme.kt` must provide our own CompositionLocals (e.g., LocalColors,
  LocalTypography, LocalSpacing, LocalRadii, LocalElevation, LocalMotion).
- Components/primitives read from these locals (e.g.
  `SacramentTheme.colors.text.strong`) rather than MaterialTheme.

## 5) Naming conventions

- Small components: SacramentButton, SacramentChip, SacramentTextField
- Section-level: SacramentSectionHeader, SacramentSectionList
- Widget-level (more complex compositions): SacramentWidgetProfileHeader,
  SacramentWidgetToolbarWithSearch

Goal: keep big components discoverable without adding more top-level folders.

## 6) Small add-ons that keep it clean

These conventions stop a design system from turning into a "collection of random
composables".

### 6.1) Co-locate component config (Defaults + Tokens)

Keep the styling and defaults right next to the component so it is easy to
evolve without touching every call site.

```
components/action/
  SacramentButton.kt
  SacramentButtonDefaults.kt
  SacramentButtonTokens.kt   // optional: component-specific semantic tokens
```

When to create `*Tokens.kt`:

- When a component has multiple internal roles beyond global tokens (e.g.,
  button container/content/disabled/pressed)
- When you want consistent mapping across variants without leaking palette
  values

### 6.2) Enforce a consistent public API shape

Use a predictable parameter order and naming across components.

Recommended parameter order:

1. required content/state params (e.g., text, value)
2. callbacks (e.g., onClick, onValueChange)
3. variant/size/intent (e.g., variant, size, intent)
4. enablement + interaction (enabled, interactionSource)
5. appearance hooks (colors, shape, contentPadding) only when needed
6. `modifier: Modifier = Modifier` last

This makes components feel consistent to use.

### 6.3) Always provide slot APIs for composition

Avoid one-off components like `SacramentProfileHeader(name: String, avatarUrl: ...)`
when the layout can be reusable. Prefer slot APIs:

- `title: @Composable () -> Unit`
- `leading: @Composable (() -> Unit)?`
- `trailing: @Composable (() -> Unit)?`
- `content: @Composable () -> Unit`

This keeps components flexible without creating dozens of near-duplicates.

### 6.4) Modifier + semantics conventions

- Every composable takes `modifier: Modifier = Modifier` and it is last.
- Add semantic roles where appropriate (button, switch, heading).
- Never hide essential semantics behind optional flags.

### 6.5) State hoisting by default

For input-like components, prefer hoisted state:

- `value + onValueChange`
- `checked + onCheckedChange`

Only use internal state for truly self-contained widgets (and document it
clearly).

### 6.6) Keep icons/illustrations separate from UI API

Do not mix assets with component implementations.

Good options:

- `foundation/icons` or `resources/icons`
- Provide typed accessors like `SacramentIcons.Add`, `SacramentIcons.Settings` rather than
  scattering resource IDs

### 6.7) Draw a hard line between DS and feature UI

Design system: reusable, cross-feature components and patterns.

Feature modules: anything that knows about domain models, business rules,
feature copy, or navigation.

If a component mentions a feature name or depends on feature models, it does not
belong in the DS.

### 6.8) Public vs internal (API surface control)

- Make only the intended entry points public.
- Keep helpers, internal layout composables, and experimental pieces internal.
- Prefer internal constructors for internal models.

This stops accidental coupling.

### 6.9) Preview hygiene

Centralize preview setup:

- `preview/PreviewTheme.kt` (light/dark, dynamic color on/off if used)
- `PreviewParameterProviders.kt` for variants/sizes/intents
- `SampleModels.kt` for preview-only data

Rule: preview/sample data lives in `preview/` and should not leak into
production APIs.

### 6.10) Testability hooks (small, intentional)

- Central TestTags (or SacramentTestTags) constants
- Provide semantics properties where assertions need stability
- If you do screenshot tests, standardize golden naming by component + variant

### 6.11) Accessibility defaults

Bake in the boring-but-critical stuff:

- minimum touch target (or documented exceptions)
- clear contentDescription for icons (or explicitly mark decorative icons)
- focus order/keyboard navigation for complex widgets

### 6.12) Deprecation + migration strategy

When you replace a component API:

- Use `@Deprecated(message = "...", replaceWith = ...)`
- Keep a short migration note in the changelog

This keeps your DS usable as it evolves.

## 7) Repo-readiness checklist (what mature DS repos include)

- A catalog/demo app (component gallery)
- Preview strategy (light/dark, font scale, RTL, accessibility sizes)
- Snapshot/screenshot tests for key components
- API surface rules (what is public vs internal)
- A short contribution guide (how to add a component)
- A changelog (especially if published as a library)

## 8) Where this lives in the repo

Since this guide will ship with the repository, keep it easy to discover and
hard to rot.

Recommended placement:

- `docs/design-system.md` (or `docs/design-system/guide.md`) as the source of
  truth
- Add a short `sacrament/README.md` that links to the guide and shows:
  - how to run the catalog app
  - how to add a new component
  - how tokens/themes are wired

Recommended supporting files:

- `docs/design-system/decisions/` (optional): ADRs for major DS decisions
- `CONTRIBUTING.md`: includes "Definition of Done for a new component"
  (preview + tests + a11y)
- `CHANGELOG.md`: versioned changes to DS APIs
