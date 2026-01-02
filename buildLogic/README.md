# Build Logic

This directory contains the build logic and convention plugins for the PracticalChristian Android project, following the [Grapla pattern](https://github.com/tamzi/Grapla) for better organization and maintainability.

## Structure

```
buildLogic/
├── src/main/kotlin/
│   ├── AndroidConvention.kt          # Plugin ID constants
│   ├── AndroidModules.kt             # Module path constants
│   ├── AndroidSdk.kt                 # SDK version constants
│   ├── convention/                   # Convention plugins package (Grapla pattern)
│   │   ├── ApplicationConventionPlugin.kt # Application plugin
│   │   ├── LibraryConventionPlugin.kt    # Library plugin
│   │   ├── ModuleConventionPlugin.kt     # Module plugin
│   │   ├── FeatureConventionPlugin.kt    # Feature plugin
│   │   ├── HiltConventionPlugin.kt       # Hilt plugin
│   │   └── ComposeConventionPlugin.kt    # Compose plugins
│   ├── helpers/
│   │   └── KotlinConfiguration.kt    # Kotlin configuration helpers
│   └── extensions/
│       ├── CommonExtensions.kt       # Common extension functions
│       ├── DependencyHandlerScopeExtensions.kt # Dependency extensions
│       ├── ProjectExtensions.kt      # Project extensions
│       └── VersionCatalogsExtensions.kt # Version catalog extensions
├── build.gradle.kts                  # Build logic configuration
├── settings.gradle.kts               # Build logic settings
└── README.md                         # This file
```

## Convention Plugins

### Core Plugins
- **Application Convention**: Configures Android application modules
- **Library Convention**: Configures Android library modules
- **Module Convention**: Configures core modules (data, domain, etc.)
- **Feature Convention**: Configures feature modules with UI components

### Specialized Plugins
- **Hilt Convention**: Configures dependency injection
- **Compose Convention**: Configures Jetpack Compose
- **Detekt Convention**: Configures static code analysis for Kotlin

## Usage

Apply convention plugins in your module's `build.gradle.kts`:

```kotlin
plugins {
    id("practicalchristian.android.application")  // For app module
    id("practicalchristian.android.feature")      // For feature modules
    id("practicalchristian.android.library")      // For core modules
    id("io.gitlab.arturbosch.detekt")             // For code analysis (auto-applied to all modules)
}
```

### Running Detekt

Run static code analysis across all modules:
```bash
./gradlew detekt
```

Create a baseline to suppress existing issues:
```bash
./gradlew detektBaseline
```

View reports in `build/reports/detekt/` for each module.

## Configuration

### SDK Versions
- **Compile SDK**: 35
- **Target SDK**: 34
- **Minimum SDK**: 24

### Java/Kotlin
- **Java Version**: 21
- **Kotlin JVM Target**: 21

### Compose
- Compose compiler version is managed by the BOM
- See `../compose_compiler_config.conf` for configuration details

## Benefits

1. **Consistency**: All modules use the same configuration
2. **Maintainability**: Changes in one place affect all modules
3. **Type Safety**: Compile-time checking of plugin IDs and module paths
4. **Documentation**: Inline documentation for better understanding
5. **Modern Structure**: Following current Android development best practices

## References

- [Grapla Repository](https://github.com/tamzi/Grapla) - Inspiration for this structure
- [Gradle Convention Plugins](https://docs.gradle.org/current/userguide/implementing_gradle_plugins.html)
- [Android Gradle Plugin](https://developer.android.com/studio/build)
