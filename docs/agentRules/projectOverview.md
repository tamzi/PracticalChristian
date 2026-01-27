# PracticalChristian Project Overview

> **For AI Agents:** This file provides essential context about the PracticalChristian project

## What is PracticalChristian?

PracticalChristian is a modern Android application for daily Bible meditations and spiritual growth. It provides structured Bible reading schedules, note-taking capabilities, and tag-based organization to help users engage with Scripture consistently and meaningfully.

## Tech Stack

| Category | Technology | Version |
|----------|-----------|---------|
| **Language** | Kotlin | 2.3.0 |
| **UI Framework** | Jetpack Compose | 2025.12.01 BOM |
| **Build System** | Gradle (Kotlin DSL) | 8.13.2 |
| **DI** | Hilt (Dagger) | 2.57.2 |
| **Async** | Coroutines + Flow | 1.10.2 |
| **Network** | Retrofit + OkHttp | 3.0.0 / 5.3.2 |
| **Database** | Room | 2.8.4 |
| **Preferences** | DataStore | 1.2.0 |
| **Navigation** | Navigation Compose | 2.9.6 |
| **Testing** | JUnit 4, Truth, Turbine | 4.13.2 / 1.4.5 / 1.2.1 |
| **Coverage** | JaCoCo | 0.8.7 |
| **Quality** | Detekt | 1.23.8 |

## Architecture

### Clean Architecture with MVVM

- **UI:** Built entirely with Jetpack Compose with Sacrament design system (Material-free)
- **State Management:** Unidirectional Data Flow (UDF) using Coroutines and `Flow`s
- **ViewModel Pattern:** Standard Hilt ViewModels with `StateFlow`
- **Dependency Injection:** Hilt throughout the app
- **Navigation:** Navigation Compose 2.9.x for type-safe navigation
- **Data Layer:** Repository pattern with Room + DataStore
    - **Room Database:** Notes, schedules, tags, meditation history
    - **DataStore:** User preferences, settings, reading progress
- **Domain Layer:** Use cases for business logic, `Outcome<T>` for results
- **Design System:** `sacrament` module with shared UI components and themes

## SDK Versions

| Configuration   | Version | Android Version |
|-----------------|---------|-----------------|
| **Min SDK**     | 26      | Android 8       |
| **Target SDK**  | 35      | Android 15      |
| **Compile SDK** | 35      | Android 15      |
| **Java**        | 21      | -               |
| **Kotlin JVM**  | 21      | -               |

**Market Coverage:** Android 8+ provides broad device compatibility

## Module Structure

### Current Modules

The project uses a multi-module architecture with clear separation of concerns:

#### App & Design System (2 modules)

- **app** - Main application entry point
- **sacrament** - Design System (shared UI components, theme, typography)

#### Core Infrastructure (4 modules)

**Foundation Layer:**
- **core:common** - Utilities, extensions, result types
- **core:domain** - Domain models, use cases, `Outcome<T>` type
- **core:data** - Repository pattern implementation, data coordination
- **core:datasource:local** - Room database, DAOs, entities
- **core:datasource:remote** - API clients, network data sources

#### Feature Modules (1 module)

**Presentation Layer:**
- **feature:presentation** - All UI screens and ViewModels (Hilt ViewModels)
    - Home screen
    - Schedules screen
    - Notes/Journal screen
    - Settings screen

## Build Commands

### Build

```bash
./gradlew build                          # Build all
./gradlew clean build --refresh-dependencies  # Clean build with dependency refresh
./gradlew assembleDebug                  # Build debug APK
./gradlew assembleRelease                # Build release APK
```

### Test

```bash
./gradlew test                           # All unit tests
./gradlew connectedAndroidTest           # All instrumented tests
./gradlew :module:test                   # Module-specific tests
```

### Code Coverage

```bash
./gradlew jacocoTestReport              # Unit test coverage (JaCoCo)
./gradlew jacocoRootReport              # Combined coverage report
```

**Always run coverage after code changes!**

### Quality Checks

```bash
./gradlew lint                           # Android Lint
./gradlew detekt                         # Detekt static analysis
./gradlew detektBaseline                 # Generate detekt baseline
```

## Repository Information

- **URL:** (PracticalChristian repository URL)
- **Main Branch:** `main`
- **Workflow:** Feature branches → Pull Requests → Main
- **Package:** `com.practicalchristian.app`

## Convention Plugins

PracticalChristian uses convention plugins for centralized build configuration:

### Key Plugins

- `practicalchristian.android.application` - Base app setup
- `practicalchristian.android.library` - Base library setup
- `practicalchristian.android.feature` - Feature module conventions
- `practicalchristian.hilt` - Hilt dependency injection
- `practicalchristian.android.test` - Unit/instrumented test setup
- `practicalchristian.android.lint` - Lint configuration
- `practicalchristian.jacoco` - JaCoCo coverage

**Full list:** See `buildLogic/README.md`

## Documentation Structure

```
docs/
├── agentRules/                         # AI agent rules
│   ├── README.md                       # Rules index
│   ├── projectOverview.md              # Project context (you are here!)
│   ├── testingRules.md                 # Testing standards
│   ├── featureDevelopmentRules.md      # Feature development
│   └── ...
├── architecture.md                     # Clean Architecture with Outcome<T>
├── timezoneHandling.md                 # UTC storage strategy
└── logs/                               # Historical development logs
```

## Current Status

**Phase:** Active development - Core features implemented, ongoing improvements

**Module Status:**

- ✅ Core infrastructure modules created (4 modules)
- ✅ Design system established (`sacrament`)
- ✅ Feature presentation module structure defined
- ✅ Clean Architecture with `Outcome<T>` implemented
- ✅ UTC timezone handling strategy implemented
- ✅ Navigation Compose 2.9.x migration completed

## Key Principles

1. **Clean Architecture** - Domain → Data → Presentation
2. **Offline-First** - Local data with background sync
3. **Type Safety** - Kotlin, sealed interfaces, `Outcome<T>`
4. **Testing** - 70%+ coverage target
5. **Modularization** - Clear layer boundaries
6. **Convention Over Configuration** - Convention plugins for consistency
7. **UTC Storage** - All timestamps stored in UTC for consistency

## Domain Result Types

PracticalChristian uses a Clean Architecture approach with distinct result types per layer:

### Domain Layer (core:domain)

**Business-level result types. See:**

- Outcome type: `core/domain/src/.../Outcome.kt`
- DomainError: `core/domain/src/.../DomainError.kt`

### Data Layer (core:data)

**Infrastructure result types. See:**

- LocalResult: `core/localdatasource/src/.../LocalResult.kt`
- Mapping functions: `core/data/src/.../mappers/` directory

### Presentation Layer (feature:presentation)

**UI state types. See:**

- ItemState: `feature/presentation/src/.../ItemState.kt`

**See:** `docs/architecture.md` for complete details

## Dependencies Quick Reference

### Always Available (from convention plugins)

- Kotlin stdlib
- Coroutines
- Compose (if using compose plugins)
- Hilt (if using hilt plugin)
- Testing libraries (if using test plugins)

### Add Explicitly When Needed

- Room - Add room dependencies
- DataStore - Add datastore dependency
- Navigation Compose - Add navigation-compose dependency
- Coil - For image loading
- Firebase - For analytics, crashlytics

## Important Files

- **SDK Config:** `buildLogic/convention/src/main/kotlin/util/BuildLogicConstants.kt`
- **Version Catalog:** `gradle/libs.versions.toml`
- **Root Build:** `build.gradle.kts`
- **Settings:** `settings.gradle.kts`
- **Detekt Config:** `detekt.yml`
