# Sacrament Design System — Package reference (module `:sacrament`)

This section explains **what each package is responsible for**, what it may contain, and what it must *not* contain. The goal is a clean, Material‑free, Compose‑first DS with predictable boundaries.

> **See also:** [Design System Guide](designSystem.md) for the complete guide to building and organizing the design system.

---

## `foundation/`

**Purpose:** The “source of truth” for design decisions. Defines tokens and theme plumbing.

**Contains:**

* Raw palette values and semantic token models (colors, type, spacing, radii, elevation, motion…)
* Mappings (e.g., light/dark token sets)
* CompositionLocals and theme entry point (`SacramentTheme`)

**Does not contain:** UI components like `Button`, `TextField`, screen scaffolds, or feature copy/models.

### `foundation/color/`

**Purpose:** Everything color‑related—from raw colors to semantic roles.

**Contains:**

* `Palette.kt`: raw brand colors + neutral ramp + utility ramps
* `SacramentColorTokens.kt`: semantic role models (e.g., `text.strong`, `surface.sunken`, `semantic.success`)
* `LightColors.kt` / `DarkColors.kt`: map palette → semantic roles
* Optional role resolution helpers (e.g., `ColorAccessors.kt`)

**Rules:**

* Components should **not** import raw palette values directly; they should read semantic roles from `SacramentTheme.colors.*`.

### `foundation/icon/`

**Purpose:** Tokenize icon sizing and optionally provide typed icon access.

**Contains:**

* Icon size tokens (e.g., `IconTokens.sizeSm/Md/Lg`)
* (Optional) typed icon registry wrappers like `SacramentIcons.*` (if you choose to centralize asset access)

### `foundation/typography/`

**Purpose:** Typography tokens and text styles (Material‑free).

**Contains:**

* Font families, weights, line heights
* Semantic text styles (e.g., `heading.lg`, `body.md`, `label.sm`)
* Scaling strategy and defaults

**Rule:** expose DS types (your own models), not Material `Typography`.

### `foundation/shape/`

**Purpose:** Shape/radius tokens.

**Contains:**

* Radii tokens (e.g., `radii.sm/md/lg`)
* Standard shapes per component category (if needed)

### `foundation/layout/`

**Purpose:** Spacing + layout tokens.

**Contains:**

* Spacing scale (`spacing.2/4/8/12/16...`)
* Standard paddings/margins
* Grid/breakpoint helpers (optional)

### `foundation/elevation/`

**Purpose:** Elevation tokens and shadow specs.

**Contains:**

* Elevation scale (`elevation.none/low/medium/high`)
* Shadow parameters (blur, offset) if you render custom shadows

### `foundation/motion/`

**Purpose:** Animation and motion standards.

**Contains:**

* Durations, easings, spring specs
* Standard transitions (optional) as tokens/specs (not full components)

### `foundation/Theme.kt`

**Purpose:** The DS theme entry point and CompositionLocals.

**Contains:**

* `SacramentTheme` composable
* `LocalColors`, `LocalTypography`, `LocalSpacing`, `LocalRadii`, `LocalElevation`, `LocalMotion`
* Default token sets and switching logic (light/dark)

**Rules:**

* No `MaterialTheme`
* Primitives/components read from locals (e.g., `SacramentTheme.colors.text.strong`).

---

## `primitives/`

**Purpose:** Thin wrappers over Compose UI/Foundation that enforce tokens, defaults, and semantics.

Think of primitives as “guardrails”: they make the right thing easy and consistent.

**Contains:**

* `SacramentText`: wraps `Text`, applies DS typography + default color + semantics
* `SacramentSurface`: wraps a `Box`/`Layout` with DS background/border/elevation behavior
* `SacramentIcon`: enforces DS icon sizes/tint defaults
* `SacramentDivider`: uses DS spacing/color thickness rules

**Does not contain:**

* Rich interaction patterns (dialogs, sheets, nav bars)
* Feature-aware layouts

**Design goal:** A primitive should feel like a slightly opinionated `foundation`/`ui` call—not a full component.

---

## `components/`

**Purpose:** Reusable UI building blocks built from primitives + tokens. These are what most product code uses.

**Contains:**

* Buttons, inputs, navigation widgets, surfaces, content units, feedback widgets
* Consistent API shape (state/slots/modifier last)
* Defaults + tokens co-located (optionally `*Defaults.kt`, `*Tokens.kt`)

**Does not contain:**

* Feature copy, domain models, navigation logic
* Full screen implementations

### `components/action/`

**Purpose:** Action initiators.

**Contains:**

* `SacramentButton`, `SacramentIconButton`, `SacramentFab`
* Press/disabled/focus states + semantics

### `components/input/`

**Purpose:** Interactive input controls.

**Contains:**

* `SacramentTextField`, `SacramentSearchField`
* `SacramentCheckbox`, `SacramentRadio`, `SacramentSwitch`
* State hoisting APIs + keyboard/focus behaviors

### `components/navigation/`

**Purpose:** Navigation chrome and selection controls.

**Contains:**

* `SacramentTopAppBar`, `SacramentBottomBar`, `SacramentTabRow`, `SacramentNavigationRail`
* Selection/active indicators based on tokens

### `components/surface/`

**Purpose:** Containment and overlays.

**Contains:**

* `SacramentCard` (clickable/non-clickable), `SacramentSheet`, `SacramentDialog`
* Border/elevation/shape standards

### `components/content/`

**Purpose:** Content atoms that appear frequently in lists and layouts.

**Contains:**

* `SacramentListItem`, `SacramentAvatar`, `SacramentChip`, `SacramentBadge`, `SacramentTag`

### `components/feedback/`

**Purpose:** Feedback to the user.

**Contains:**

* `SacramentSnackbar`, `SacramentToastHost`, `SacramentProgressIndicator`, `SacramentInlineMessage`
* A11y semantics for status/announcements

### `components/sections/`

**Purpose:** Larger “section-level” compositions used across screens.

**Contains:**

* `SacramentSectionHeader`, `SacramentSectionCard`, `SacramentSectionList`
* “Widget-level” compositions like `SacramentWidgetProfileHeader`

**Rule:** Still DS‑generic. If it mentions a feature or domain type, it belongs in a feature module.

---

## `patterns/`

**Purpose:** Screen scaffolds and standard compositions (templates). These define *layout conventions* without becoming feature screens.

**Contains:**

* `SacramentScreenScaffold`: app chrome + slots for top bar/bottom bar/content
* `SacramentEmptyState`, `SacramentErrorState`, `SacramentLoadingState`: standard states used by many features

**Does not contain:**

* Navigation graph, routes, feature-specific flows
* Domain logic, API calls, view models

**Heuristic:** A pattern can be dropped into multiple features unchanged.

---

## `preview/`

**Purpose:** Preview-only utilities so previews are consistent and don’t leak fake data into production.

**Contains:**

* `PreviewTheme.kt`: wraps composables in `SacramentTheme` and sets preview configs (light/dark, font scale, etc.)
* `SampleModels.kt`: preview-only data objects and sample text
* `PreviewParameterProviders.kt`: providers for variants/sizes/intents

**Rules:**

* Preview data stays in `preview/`.
* Production components should not depend on `preview/`.

---

## Quick “where does this go?” cheat sheet

* **Raw values** (hex colors, dp sizes, durations) → `foundation/*`
* **Semantic roles** (text.strong, surface.base, spacing.md) → `foundation/*`
* **CompositionLocals + Theme** → `foundation/Theme.kt`
* **Thin wrappers around Compose** (Text/Icon/Surface) → `primitives/`
* **Reusable UI blocks** used directly by features → `components/*`
* **Screen scaffolds + standard states** used by features → `patterns/`
* **Preview-only setup + sample data** → `preview/`

---

## Boundaries that keep this healthy

* No `androidx.compose.material3.*` imports anywhere in `:sacrament`.
* No domain models, business logic, navigation routes, or feature copy in DS.
* Components read **semantic tokens** via `SacramentTheme.*`, not raw palette values.
* Keep public surface small; helpers/internal layouts stay `internal`.
