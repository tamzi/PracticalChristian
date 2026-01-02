# buildLogic Directory Structure (Grapla Pattern)

This document outlines the buildLogic directory structure following the [Grapla pattern](https://github.com/tamzi/Grapla).

## Structure Overview

```
buildLogic/
├── build.gradle.kts                  # Build configuration
├── settings.gradle.kts               # Settings for buildLogic module
├── README.md                         # Documentation
├── docs/structure.md                 # This file
└── src/main/kotlin/
    ├── AndroidConvention.kt          # Plugin ID constants
    ├── AndroidModules.kt             # Module path constants
    ├── AndroidSdk.kt                 # SDK version constants
    ├── convention/                   # ✅ Convention plugins (Grapla pattern)
    │   ├── ApplicationConventionPlugin.kt
    │   ├── ComposeConventionPlugin.kt
    │   ├── DetektConventionPlugin.kt
    │   ├── FeatureConventionPlugin.kt
    │   ├── HiltConventionPlugin.kt
    │   ├── LibraryConventionPlugin.kt
    │   └── ModuleConventionPlugin.kt
    ├── extensions/                   # Extension functions
    │   ├── CommonExtensions.kt
    │   ├── DependencyHandlerScopeExtensions.kt
    │   ├── ProjectExtensions.kt
    │   └── VersionCatalogsExtensions.kt
    └── helpers/                      # Helper utilities
        └── KotlinConfiguration.kt
```

## Key Features

### 1. Convention Package
All convention plugins are organized in the `convention/` package, following the Grapla pattern:
- ✅ Clear separation of convention plugins from other code
- ✅ Proper package structure (`package convention`)
- ✅ Easy to locate and maintain

### 2. Plugin Organization
- **Core Plugins**: Application, Library, Module
- **Feature Plugin**: For feature modules with UI components
- **Specialized Plugins**: Hilt, Compose, Detekt
- **Configuration**: AndroidSdk, AndroidConvention, AndroidModules
- **Helpers**: Kotlin configuration utilities
- **Extensions**: Reusable extension functions

### 3. Comparison with Grapla

| Aspect | Grapla | This Project |
|--------|--------|--------------|
| Convention plugins location | `src/main/kotlin/convention/` | ✅ `src/main/kotlin/convention/` |
| Package declaration | `package convention` | ✅ `package convention` |
| Helper functions | Separate package | ✅ `helpers/` and `extensions/` |
| Constants | Top-level files | ✅ `AndroidSdk.kt`, `AndroidConvention.kt`, `AndroidModules.kt` |
| Documentation | README | ✅ README.md + docs/structure.md |

## Benefits

1. **Follows Grapla Pattern**: Exact match with the Grapla repository structure
2. **Better Organization**: Clear separation between plugins, helpers, and extensions
3. **Maintainability**: Easy to find and modify convention plugins
4. **Scalability**: Simple to add new convention plugins
5. **Documentation**: Comprehensive documentation for the structure

## References

- [Grapla Repository](https://github.com/tamzi/Grapla)
- [Gradle Convention Plugins](https://docs.gradle.org/current/userguide/implementing_gradle_plugins.html)
- [Android Gradle Plugin](https://developer.android.com/studio/build)

