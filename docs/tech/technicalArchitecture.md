# Technical Architecture & Transition Plan (Android to KMP)

## Project Overview & Vision

We are building PracticalChristian, a Bible meditations mobile app, with an initial focus on
Android and a roadmap to later support iOS via Kotlin Multiplatform. The goal is
to deliver a modern, engaging Android app that can evolve into a cross-platform
solution without duplicating core logic. By leveraging Kotlin Multiplatform
(KMP), we intend to reuse the app's core code across Android (and eventually iOS)
to maintain feature parity and accelerate multi-platform support. In line with
the Product Requirements Document (PRD) vision, the app will combine daily
devotionals, guided meditations, journaling, and community features in one
seamless experience.

### Key Objectives

- **Modern Android App:** A fully functional Android app using Kotlin and
  Jetpack Compose UI, following best practices (similar to Google's
  NowinAndroid sample which is "a fully functional Android app built entirely
  with Kotlin and Jetpack Compose"). The app implements core features
  (daily devotional feed, Bible reader, journaling, verse memory, etc.) for 
  a single-user experience.
- **Robust Architecture:** Multi-module clean architecture (MVVM) with
  dependency injection (Hilt) and offline-first design, ensuring
  maintainability and scalability. The codebase is organized into
  logical modules (core layers and feature modules) to enforce separation of
  concerns and enable future multiplatform support.
- **Kotlin Multiplatform Readiness:** Core business logic is structured as pure
  Kotlin modules that can potentially be shared with an iOS app in the future.
  The architecture allows for future migration to KMP-ready libraries 
  (SQLDelight, Ktor) without fundamental restructuring.

The following sections detail the technology stack and module architecture.

## Technology Stack & Architectural Patterns

- **Programming Language:** Kotlin is used for all development. Kotlin offers
  first-class support on Android and is the basis for KMP, enabling code sharing
  with iOS in the future. All business logic will be written in Kotlin and
  placed in modules that can be made multiplatform.
- **UI Framework:** Jetpack Compose is used for the Android UI layer, enabling
  declarative UI and rapid development. Compose is Kotlin-friendly and aligns
  with modern Android best practices (NowinAndroid is built entirely with
  Compose). Using Compose also positions us to potentially use Compose
  Multiplatform for iOS in the future, although our current plan is to use
  native SwiftUI on iOS with shared logic.
- **Architecture Pattern:** We adopt a Clean Architecture approach with MVVM
  (Model-View-ViewModel) for presentation. The ViewModel layer (in Kotlin) will
  hold UI state and business logic for each screen, exposing state flows that
  the Compose UI observes. This separation allows us to reuse ViewModel logic on
  iOS by writing expect/actual or using KMP-compatible state management (for
  example, StateFlow observed from SwiftUI). The data layer uses repository
  patterns, and the domain layer contains use-cases and business rules,
  decoupling UI from data sources. This layered approach makes the core logic
  platform-agnostic and easily testable.
- **Dependency Injection:** We will use Dagger Hilt for DI on Android to manage
  object creation and scope (for example, providing singletons for repositories,
  database, etc.). Hilt will inject ViewModels and other dependencies in
  Compose. For multiplatform, we will design DI such that the shared code
  expects certain implementations to be injected (for example, platform-specific
  implementations for network or storage if needed). Hilt is Android-specific;
  on iOS we may use manual DI or a service locator for the Kotlin components.
  The DI setup ensures loose coupling between modules.
- **Networking:** Currently using Retrofit with OkHttp for Android. Retrofit 
  provides a robust, well-tested solution for REST API calls with excellent 
  Kotlin coroutines support. For future KMP migration (Phase 2+), we may 
  evaluate Ktor Client as a multiplatform alternative. Because our MVP is 
  largely offline-first, network calls are mostly for sync and content updates.
- **Local Data & Offline:** Adopting an offline-first strategy means the app
  will rely on local storage for primary data access, syncing in background. We
  currently use **Room** database (Android's recommended persistence library) for
  local storage, providing type-safe database access via DAOs. Bible text, 
  devotional plans, schedules, notes, and tags are stored locally so the app is 
  fully usable offline. Room provides excellent Kotlin coroutines and Flow support, 
  making it ideal for reactive UI updates. For **future KMP migration** (Phase 2+), 
  we may evaluate SQLDelight as a multiplatform alternative that can generate Kotlin 
  code for both Android and iOS. Data synchronization (uploading journal to cloud, 
  downloading new devotional content) will be handled via simple cloud endpoints or 
  Firebase. Firebase Cloud Firestore is configured for cloud sync of user data and 
  real-time updates (it has built-in offline caching). Firebase Auth handles user 
  accounts and authentication tokens, with Google sign-in support on Android 
  (and Apple sign-in planned for iOS).
- **Testing:** Emphasis on unit testing for shared logic (for example,
  verifying the verse memorization SRS algorithm, or journal encryption logic)
  on the JVM, since the core will be in Kotlin. ViewModels and use cases can be
  tested without Android dependencies. Instrumentation tests on Android for
  Compose UI and database integration will be set up (possibly using Robolectric
  or Jetpack Compose testing). Continuous Integration (for example, GitHub
  Actions) will run the test suites to maintain quality.
- **Reference Implementation:** The architecture is inspired by Android's Now in
  Android app which follows a multi-module clean architecture with Compose UI,
  Hilt, and modern practices. NowinAndroid organizes code into core modules (for
  data, model, UI theming, etc.) and feature modules for each screen/feature,
  using Compose Navigation to navigate between feature modules. We will emulate
  this structure to create a scalable codebase.

## Build + Release Notes

- **R8/Proguard obfuscation:** Release builds use R8. We provide a custom
  obfuscation dictionary at `app/proguard-dictionary.txt`, referenced from
  `app/proguard-rules.pro` via `-obfuscationdictionary`,
  `-classobfuscationdictionary`, and `-packageobfuscationdictionary`. The file
  must exist for release builds to succeed; keep it as a simple word list to
  control obfuscated name output.

## Modular Architecture (Modules and Layers)

To enforce separation of concerns and enable parallel development, the project
is structured into multiple Gradle modules. This aligns with the plan from our
prior research on feature modularization. The high-level module categories are:

### App Module

- `app`: The main Android app module (entry point). This depends on all feature
  modules and orchestrates navigation. It also includes the Hilt application
  class for DI and overall app configuration.

### Core Modules

Core modules provide foundational logic, models, and services used across
features. These will eventually become KMP shared modules:

- `core:common`: Common utilities, extensions, and constants used app-wide.
- `core:model`: Data models (Kotlin data classes) shared across app layers
  (for example, data transfer objects for Devotional, Plan, JournalEntry, User).
- `core:domain`: Business domain layer, containing use case classes and
  interfaces for repositories (the abstraction of data layer). This defines
  operations like `GetDailyDevotionalUseCase`, `AddJournalEntryUseCase`, which
  the app can use without knowing data details.
- `core:data`: Data layer implementations of repositories, coordinating data
  from local and remote sources. For example, `DevotionalRepositoryImpl` fetches
  devotionals from a local database or network API as needed. This module will
  depend on `core:model` and platform-specific data sources.
- `core:datasource:local`: Handles local storage concerns such as the database
  (SQLDelight/Room) and preferences. It defines DAOs or database interfaces for
  other modules to use.
- `core:datasource:remote`: Handles network API calls (REST clients, Firebase
  integration). It will include service definitions (for example, Retrofit
  interfaces or Ktor clients) and data mappers from network to model.
- Optional core modules as needed (for example, `core:analytics` if we separate
  analytics, or `core:encryption` for crypto utilities).

### Feature Modules

Each major user-facing feature or screen is encapsulated in its own module.
Feature modules contain the UI (Compose screens), their own ViewModels, and any
feature-specific logic. They depend on the core modules for data and domain. The
planned feature modules include:

- `feature:landing`: Onboarding/landing screens (welcome, sign-in/up flow).
- `feature:authentication`: User auth flows (register, login, password reset).
- `feature:home`: Main dashboard after login, which likely shows the daily
  devotional feed and navigation to other sections.
- `feature:devotional`: If needed, module for the daily devotional reading UI
  and related logic.
- `feature:bible`: Bible reader feature (scripture text view, search,
  highlight).
- `feature:journal`: Encrypted journal UI (list of entries, editor screen).
- `feature:memory`: Verse memorization feature (list of memory verses, quiz UI).
  We might rename `quotes` to `memory` to clarify purpose.
- `feature:reading-plans`: Plans library and plan detail screens.
- `feature:prayer`: Prayer list/tracker (if included separately from journal).
- `feature:community`: Social feed or Circles (small groups) feature.
- `feature:circles`: Possibly separate from general community, handles group
  chats and posts.
- `feature:church`: Church channels integration (joining a church, viewing
  church-provided plans).
- `feature:settings`: Settings UI (profile, preferences, notification settings).
- Additional modules from previous outline: account/profile, AI (if AI chatbot
  is planned), meditation (if separate from devotionals), etc. Many of these
  are future or optional; for MVP some will not be implemented but placeholders
  can exist.

Each feature module contains its Compose screens, ViewModel(s), and any
feature-specific data logic. They only interact with data via the core
domain/repository interfaces, not directly to DB or network. This ensures
replaceability and facilitates using the core logic on iOS. For example,
`feature:journal` will use a `JournalViewModel` which calls
`AddJournalEntryUseCase` (from `core:domain`). The use case then uses the
repository implemented in `core:data` to save to DB and sync, without the UI
needing to know details.

#### Feature Module Package Structure

Feature modules are organized by screen scope. Use simple, predictable folders
and keep shared UI pieces in a local `ui/` folder (do not add design system
components inside feature modules).

Example:

```
feature/notes/
  src/main/java/.../feature/notes/
    list/      // list screen + viewmodel + ui state
    detail/    // detail screen + viewmodel + ui state
    edit/      // edit screen + viewmodel + ui state
    navigation/
```

#### Core Package Naming Conventions

Use consistent package naming across core modules:

- `core:domain`: `model/`, `repository/`, `usecases/` (optionally nested by
  feature, e.g. `usecases/notes/`). Use cases stay in `core:domain`, not in
  feature modules.
- `core:data`: `repository/`, `mappers/`, `di/`.
- `core:datasource:remote`: `api/`, `dto/`, `mapper/`.
- `core:datasource:local`: `dao/`, `entity/`, `mapper/` (or `database/` when
  using Room/SQLDelight wrappers).

### Design System Module

- `sacrament`: A Material-free Compose design system module with common UI components, theming,
  and styles used across the app. This includes colors, typography, reusable
  widgets (buttons, loading indicators, cards, etc.), built entirely on Compose
  Foundation (no Material3 dependencies). The design system enforces consistency
  through tokens → primitives → components → patterns, while keeping pages/screens
  in feature modules. All feature modules must use Sacrament components exclusively;
  Material3 components and hardcoded colors are prohibited outside the `sacrament`
  module. See `docs/tech/sacrament/designSystem.md` for complete guidelines.

### Content Modules

Content modules provide structured app content as data-only modules (no UI):

- `content:books`: Bible books and biblical content data
- `content:meditations`: Meditation and devotional content
- `content:plans`: Reading plans and schedules
- `content:prayers`: Prayer content and templates
- `content:themes`: Thematic content collections
- `content:audio`: Audio content metadata and references

These modules contain raw content data (JSON, embedded resources) that is consumed
by feature modules and stored via the data layer. Separating content from features
enables independent content updates and makes testing easier.

### Module Dependencies

Each feature module can depend on core modules and `sacrament` (for UI
components). They should not depend on each other directly to keep features
modular. Navigation between features is handled through the app module (using a
navigation graph or Compose Navigation routes that the app composes together).
This decoupling allows us to include or exclude features easily and helps with
parallel development. The app module ties everything together: it sets up the
dependency injection graph (providing instances of core components), configures
navigation, and includes the entry Compose UI (for example, `NavHost`).

All core modules will be packaged so we can include them in a Kotlin
Multiplatform shared library. Concretely:

- `core:model`, `core:domain`, `core:common` are pure Kotlin and are
  straightforward to mark as `commonMain` sources for KMP.
- `core:data` can be mostly common Kotlin, with expect/actual for platform
  specifics if needed (for example, actual implementations using SQLDelight or
  Firebase).
- `core:datasource:local` and `core:datasource:remote` likely have platform
  specifics (SQLDelight uses Kotlin/Native for iOS, Firebase SDK is not
  KMP-friendly directly). We may structure these as part of `core:data` but with
  expect/actual or separate them into platform-specific source sets.
  Alternatively, we can use multiplatform libraries (SQLDelight, Ktor) that
  abstract away differences so that even these modules compile for iOS. For
  Firebase (if heavily used), we might call it on Android side only and use a
  different approach on iOS (or integrate via KMP wrappers).

### Legacy Modules

The old architecture reference included a `feature:presentation` legacy module.
This will be refactored away by moving code into the above feature modules.
We'll incrementally migrate any old code (if we started from an existing
codebase) into this new structure. In summary, our modular setup mirrors the
structure outlined in the sample technical doc, providing clarity and
enforceable boundaries. It ensures each feature is self-contained and the core
logic is centralized for sharing.

## Current Implementation

The application implements a multi-module Android architecture with the following characteristics:

### Core Architecture

- **Multi-module structure**: 40+ modules organized by concern (app, core, feature, content, design system)
- **Clean Architecture**: Domain → Data → Presentation layers with clear boundaries
- **Result type pattern**: `Outcome<T>` for domain operations, `ItemState<T>` for UI state
- **Dependency Injection**: Hilt throughout all layers
- **Navigation**: Navigation Compose 2.9.x with type-safe navigation

### Data Layer

- **Local persistence**: Room database for offline-first functionality
- **Network**: Retrofit + OkHttp for API communication
- **Preferences**: DataStore for user settings and preferences
- **Timezone strategy**: UTC storage with timezone-aware display

### UI Layer

- **Framework**: Jetpack Compose (declarative UI)
- **Design system**: Sacrament (Material-free, custom design tokens)
- **State management**: Unidirectional Data Flow with StateFlow
- **Theme**: Light/dark mode support via Sacrament color system

### Infrastructure

- **Firebase**: Analytics, Crashlytics, Performance monitoring
- **Build**: Convention plugins for consistent module configuration
- **Testing**: JUnit, Truth, Turbine for unit/integration tests
- **Quality**: Detekt for static analysis, JaCoCo for coverage

### Feature Modules

Core features implemented:

- **Home**: Bible reading schedules and daily devotional feed
- **Books**: Bible reader with offline content
- **Notes/Journal**: Note-taking with tagging capabilities  
- **Schedules**: Reading plan management and tracking
- **Tags**: Content organization and filtering
- **Profile**: User settings and preferences
- **Auth**: Authentication and onboarding flows
- **Search**: Content search capabilities

### Extensibility

The architecture supports future enhancements:

- **Multiplatform potential**: Core modules use pure Kotlin, enabling future KMP migration
- **Modular features**: New features can be added as independent modules
- **Design system**: Sacrament provides consistent UI foundation for all features
- **Content modules**: Structured content can be updated independently from features
