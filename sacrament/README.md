# Sacrament Design System

Material-free Compose design system for PracticalChristian.

## Documentation

- **[Design System Guide](docs/tech/sacrament/designSystem.md)**: Complete guide to building and organizing the design system
- **[Package Reference](docs/tech/sacrament/packageReference.md)**: Detailed package structure and boundaries

## Quick Start

### Using Components

```kotlin
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.patterns.SacramentScreenScaffold

SacramentScreenScaffold(
    topBar = { SacramentTopAppBar(...) }
) { paddingValues ->
    // Screen content
}
```

### Theme Setup

```kotlin
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.Bar

SacramentTheme(
    navigationBar = Bar.SURFACE,
    statusBar = Bar.BACKGROUND
) {
    // App content
}
```

## Structure

- **`foundation/`**: Tokens, theme, and design decisions
- **`primitives/`**: Thin wrappers enforcing tokens
- **`components/`**: Reusable UI building blocks
- **`patterns/`**: Screen scaffolds and standard compositions
- **`preview/`**: Preview utilities and sample data
- **`testing/`**: Test tags and accessibility defaults

## Catalog App

The design system includes a catalog app (`:sacrament-demo`) that showcases all components, variants, sizes, and intents.

### Running the Catalog

```bash
./gradlew :sacrament-demo:installDebug
```

Or run from Android Studio:
1. Select the `sacrament-demo` run configuration
2. Run on a device or emulator

The catalog app provides:
- Interactive component gallery
- All variants, sizes, and tones
- Live examples of component usage
- Pattern demonstrations

## Adding a Component

1. Follow parameter order: required content → callbacks → variant → modifier last
2. Use slot APIs for composition flexibility
3. Co-locate Defaults/Tokens files
4. Add previews using `PreviewTheme` and `SampleModels`
5. Include test tags via `TestTags`
6. Ensure minimum 48dp touch targets
7. Add component to catalog app (`:sacrament-demo`)

See [Design System Guide](docs/tech/sacrament/designSystem.md) for details.

