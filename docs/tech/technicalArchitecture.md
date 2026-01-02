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

- **Modern Android MVP:** Launch a fully functional Android app using Kotlin and
  Jetpack Compose UI, following best practices (similar to Google's
  NowinAndroid sample which is "a fully functional Android app built entirely
  with Kotlin and Jetpack Compose"). The app will implement core features
  outlined in the PRD (daily devotional feed, Bible reader, journaling, verse
  memory, etc.) for a single-user experience initially.
- **Robust Architecture:** Use a multi-module clean architecture (MVVM) with
  dependency injection (Hilt) and an offline-first design, ensuring
  maintainability and scalability. The app's codebase will be organized into
  logical modules (core layers and feature modules) to enforce separation of
  concerns and prepare for multiplatform support.
- **Kotlin Multiplatform Readiness:** Structure core business logic as pure
  Kotlin modules from the start so they can be shared with an iOS app. We plan a
  phased transition to KMP where after the Android MVP, the core logic modules
  are made KMM (Kotlin Multiplatform Mobile) compatible (e.g., using
  multiplatform libraries for database and networking). This will allow
  developing an iOS client that reuses these Kotlin libraries for data, domain,
  and logic.
- **Timeline & Roadmap:** We outline three phases of implementation, aiming for
  an iOS release (or beta) by june 2026 (about 6 to 8 months from now).
  - **Phase 1 (MVP):** Android app with core features, delivered in about
    3 to 4 months (targeting April 2026).
  - **Phase 2:** Feature enhancements on Android and groundwork for KMP,
    about 2 to 3 months after MVP (target Mar 2026). By the end of this phase,
    the app remains Android-only but the codebase is KMP-ready.
  - **Phase 3:** Kotlin Multiplatform integration and iOS app development,
    along with a major UI/UX redesign to coincide with the multi-platform
    launch (targeting Apr 2026 for initial iOS rollout). This phase will
    deliver an iOS client (leveraging shared Kotlin logic) and refresh the
    app's design for both platforms.

Throughout these phases, we prioritize a privacy-first, offline-capable user
experience as detailed in the PRD. The following sections detail the technology
stack, module architecture, and each implementation phase.

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
- **Networking:** Use a multiplatform-friendly HTTP client such as Ktor Client
  or Retrofit (with OkHttp for Android). Ktor Client is KMP-ready and can be
  used in shared code for API calls. We'll start with simple REST endpoints (or
  Firebase services) for features like sync and content fetch. Because our MVP
  is largely offline-first, network calls are mostly for sync and content
  updates.
- **Local Data & Offline:** Adopting an offline-first strategy means the app
  will rely on local storage for primary data access, syncing in background. We
  will use a database (SQLite) via a multiplatform library SQLDelight for
  persistence, as it can generate Kotlin code for both Android and iOS. For
  instance, Bible text, devotional plans, and journal entries will be stored
  locally so the app is fully usable offline. The PRD emphasizes that core
  features (reading, journaling, plans, verse memory) work without internet.
  SQLDelight will reside in a shared data module. On Android, we might start
  with Room for familiarity, but we plan to migrate to SQLDelight (or use it
  from the start) to ease multiplatform transition. Data synchronization
  (uploading journal to cloud, downloading new devotional content) will be
  handled via simple cloud endpoints or Firebase. We'll likely use Firebase
  Cloud Firestore for cloud sync of user data and real-time updates (it has
  built-in offline caching). Firebase Auth will handle user accounts and
  authentication tokens on both platforms, with Google sign-in support on
  Android (and Apple sign-in on iOS).
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

### Design System Module

- `sacrament`: A UI toolkit module with common Compose UI components, theming
  and styles used across the app. This includes colors, typography, reusable
  widgets (buttons, loading indicators), etc. Following NowinAndroid's
  approach, `sacrament` provides a consistent look and feel. It will allow
  easy global theme changes, which is important for the planned redesign in
  Phase 3 (changes concentrate in the design system). Both Android and iOS will
  follow the same design guidelines: on iOS we'll create analogous components in
  SwiftUI or use Compose Multiplatform if we go that route.

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

## Phase 1: Android MVP Implementation

### Timeline and Goal

- **Timeline:** About 3 to 4 months (October 2025 to January 2026).
- **Goal:** Deliver a functional Android app (APK) covering the core individual
  experience as defined in the PRD's MVP (v1.0) scope. This version targets
  Android only, providing value to users and a foundation for future expansion.
  We focus on single-user features and offline functionality, deferring complex
  social and cloud features to later phases.

### Scope of MVP (v1.0)

- **Daily Devotional Feed:** Implement the home screen showing a daily
  devotional (scripture + reflection). For MVP we can include a starter set of
  devotionals (for example, a built-in 30-day reading plan) stored locally. The
  5/10/20 minute length toggle might be simplified at first (possibly start
  with a single default length devotional to reduce initial content needs). The
  feed shows today's devotion and perhaps recent or upcoming ones. Marking a
  devotion as completed and tracking streaks should be included (basic habit
  formation metric). This is the centerpiece of daily use.
- **Bible Reader:** Integrate a Bible reading interface to allow reading full
  chapters and quick verse lookup. Include one translation (KJV, which is
  public domain) fully offline in the app. Users can navigate by book/chapter
  and search for verses (basic text search). We'll implement a simple UI with
  chapter selection and scrolling text. Advanced features like parallel
  translation or extensive footnotes are not MVP (can show just reference links
  or none). Ensure the text is stored in the local DB and loaded quickly. Also
  allow copying verses or simple highlighting (optional in MVP).
- **Encrypted Journaling:** Provide a Journal screen where users can create,
  view, and delete journal entries. Enforce client-side encryption for entries
  (for example, derive an encryption key from the user's password or a
  device-keystore key if using guest mode). For MVP, a simple encryption (we can
  use Android's Jetpack Security Crypto or SQLCipher library) will be
  implemented to prove the concept. The journal entries are stored locally
  (SQLite) and synced to cloud only in encrypted form if the user is logged in.
  Basic features: add entry (with timestamp, optional title), list entries,
  view entry. Search within journal could be rudimentary or omitted in MVP
  (given encryption makes searching tricky; maybe allow filtering by date or
  tags if implemented). The focus is privacy: we will highlight that the journal
  is secure and personal. If possible, allow an app-level PIN/biometric lock for
  the journal section (nice-to-have if time permits, otherwise Phase 2).
- **Verse Memorization (Memory Verses):** Implement the ability for a user to
  save verses to a "memory list" and practice them with a basic spaced
  repetition mechanism. MVP might keep it simple: the user can mark a verse to
  memorize (from Bible reader or devotion screen). Those verses appear in a
  "Memory Verses" list. The review mechanism can be basic flashcards in MVP:
  show the verse text with hidden parts or just prompt the user to recall and
  then show the answer. We will schedule review notifications using a fixed
  interval (for example, every day for a week, then weekly) as a placeholder
  SRS algorithm. Tracking which verses are due for review each day and showing
  a count or notification ("You have 3 verses to review today") is a stretch
  goal in MVP; at minimum, the user can manually review their saved verses.
  This feature differentiates us even in MVP, so we will include the bare
  essentials for it.
- **Audio - Text-to-Speech:** Provide an option to listen to content via TTS for
  both scripture and devotionals. On Android, we'll use the built-in Google
  Text-to-Speech engine. MVP UI: a play/pause button on the devotional and
  Bible screens that reads the text aloud. We do not need complex audio
  controls beyond play/pause and maybe a speed adjuster. Ensure it works
  offline: instruct users to download the voice data for offline TTS (we can
  detect if no TTS engine or data is present and prompt accordingly). This
  aligns with the PRD goal of having all content available in audio form from
  day one. Background playback (audio continuing when app is in background) can
  be supported via Android media APIs, but if time is short, we can allow audio
  only when app is in foreground for MVP. Ideally, we'll integrate Android's
  MediaSession so playback can continue with screen off and show a notification
  player.
- **Offline-First Functionality:** Core features must work fully offline from
  MVP launch. Concretely:
  - All content needed for MVP (the default devotional plan, KJV Bible text,
    etc.) will ship with the app or be downloadable on first use. The local
    database will be pre-populated with devotionals and Bible text (or included
    as assets).
  - Any user data (journal entries, memory verses, etc.) is stored locally
    first, and if the user is online and logged in, sync it to cloud in
    background. If offline, queue the sync for later.
  - The app should not block or show "no connection" for core usage. For
    example, if it's offline and it's a new day, the app should show the
    devotional for that day if it was preloaded or show yesterday's with a
    notice if a new one cannot be fetched.
  - We will implement a simple sync mechanism: possibly using Firebase
    Firestore for journal and settings backup (since Firestore automatically
    syncs when connection is back). Alternatively, for MVP, we could even skip
    cloud sync entirely and just allow an optional backup (for example, export
    journal to Google Drive manually) to avoid server work, but having at least
    basic Firebase sync will enhance the experience. The PRD suggests even a
    simple encrypted file backup to Drive or iCloud could suffice for MVP.
  - Testing offline scenarios will be part of QA.
- **Accounts & Authentication:** Implement basic email/password signup and
  login (using Firebase Auth or a simple custom auth). Guest mode will be
  allowed so a user can use the app without creating an account. However,
  having an account enables data sync across devices and will be required for
  future social features. For MVP, we likely allow skipping login entirely,
  letting the app generate a local profile (with a unique ID) so the user can
  use all features offline. We'll provide an option in settings or onboarding
  to create an account to enable cloud backup. If using Firebase Auth, it's
  straightforward to integrate Google Sign-In as well for convenience (on
  Android). The app must handle an upgrade from guest to logged-in (merging
  local data to cloud).
- **Onboarding Flow:** On first launch, after a welcome screen (feature
  highlights), prompt to sign up or continue as guest. Then ask for initial
  preferences: preferred devotion length (5/10/20 min, etc.), daily reminder
  time, and optionally their church or a general interest selection. However,
  to keep MVP tight, we can shorten onboarding: maybe just set reminder time
  and let them skip other steps. The daily reminder notification for devotion
  is a key habit-forming feature, so likely in onboarding we will ask permission
  to schedule notifications at the user's chosen time (for example, 7 AM).
- **Basic Navigation & UI:** Use a single-activity architecture with Compose
  Navigation or a manual navigation system. Likely a bottom navigation bar
  (tabs) for main sections: Home, Bible, Journal, Plans, More. The Home tab
  shows devotionals, Bible tab opens reader, Journal tab opens journal, Plans
  tab can list reading plans, More tab for settings/profile. If too many
  features, we might opt for a Navigation Drawer or just show fewer tabs in
  MVP (maybe Home, Bible, Journal, More; and within More we include Settings,
  etc.). Navigation between Compose screens will be handled in the app module
  via `NavHost`. Each feature module provides composable screens that register
  routes.
- **UI/UX Design:** For MVP, we'll implement a simple, clean UI using our design
  system components. It should look modern but we won't spend too much time on
  fancy custom graphics. We'll follow Material Design 3 guidelines, using
  Compose Material components themed appropriately. We have dark mode support
  out-of-box with Compose Material. We'll ensure font sizes and contrast are
  adequate (consider accessibility). The design will likely evolve, but MVP's
  focus is functionality and clarity. We can incorporate imagery like a subtle
  background image on devotionals or a splash screen illustration if readily
  available, but it's optional. A consistent color scheme (possibly a soothing
  blue/teal palette to give a calm, spiritual feel) will be applied via
  `sacrament`.
- **Quality & Monitoring:** Before release, we'll do internal testing (and
  possibly a closed beta). We will integrate Crashlytics for crash reporting so
  we catch any runtime issues early (particularly important for offline
  scenarios and encryption). We'll also instrument basic analytics (Firebase
  Analytics) to measure engagement (this will help verify metrics like daily
  active users, but user privacy is considered; we track generic events, not
  content of journal, etc.).
- **Deferring to Later (Post-MVP):** We intentionally exclude or simplify
  certain things in MVP to meet the timeline:
  - Small Group Circles and Church features: no social or group chat in MVP;
    it's single-player only. We won't implement Circles, friend invites, or
    church channels yet. This avoids needing a complex backend and moderation.
    We will lay groundwork in data models to avoid dead ends (for example,
    design the data schemas with potential user IDs and group IDs), but UI is
    hidden.
  - Content variety and volume: MVP will use mostly static content (the initial
    devotionals and KJV Bible). We avoid dependency on external content APIs or
    licensing issues initially. All included content is either original or
    public domain (no need to license NIV yet, etc.).
  - Payments/Monetization: no in-app purchases or subscriptions in MVP. The app
    is free at start; we won't integrate Google Play Billing until Phase 3.
  - Advanced security features: while we implement core encryption for journal,
    we might not implement features like 2FA or complex encryption passphrases
    in MVP due to time. The basics (secure storage, encryption at rest) will be
    covered, but the user might not be given the option to set a custom
    encryption password in MVP.
  - Secondary features: verse sharing (to social media), verse art images,
    comment threads on devotionals, advanced search with fuzzy matching, custom
    plan creation, etc., are beyond MVP scope. If the basic set is done early,
    we can consider one or two small extras (maybe allow sharing a verse or
    devotional via the Android share sheet as text image), but the plan is to
    focus on core use cases.
  - iPad/Tablet or Web: not applicable in MVP. The Compose UI will naturally
    scale to different screen sizes, but we'll optimize for phones in portrait.
    Tablet UI can be refined later. No web app in MVP (though content might be
    managed manually via Firebase or JSON).

### Phase 1 Outcomes

By the end of Phase 1, we expect to have:

- The PracticalChristian app running on Android, fulfilling the primary spiritual use cases
  (daily reading, journaling, memorization) offline.
- A codebase structured into modules. The KMP aspect may not be fully
  implemented yet, but the separation is there. We will likely keep all code in
  the Android project during MVP, but plan the refactor to KMP by ensuring pure
  Kotlin logic is not tied to Android frameworks. For instance, ViewModels might
  use `androidx.lifecycle.ViewModel` in MVP; when going KMP, we might replace
  those with KMP ViewModel alternatives or keep them in common code via
  libraries like KMPViewModel. We remain mindful of this but may not actually
  pull the trigger on multiplatform until Phase 2.
- Preliminary analytics on usage and feedback from early users to inform Phase 2
  improvements.

MVP success criteria (as per PRD) include launching to a small user group and
seeing that they engage daily and that offline use is smooth. Technically,
success means no major crashes, data correctly saved offline, and positive
reception that the app is easy to use and beneficial.

## Phase 2: Enhancements & Kotlin Multiplatform Preparation

### Timeline and Goal

Timeline: about 2 to 3 months post-MVP (February to March 2026). Goal: improve
and expand the Android app's features based on MVP feedback, and refactor the
codebase to be fully Kotlin Multiplatform-enabled in preparation for iOS
development. By the end of Phase 2 (v1.1 of the app), the Android app should
feel more polished and content-rich, and the core logic should be running in a
shared module that can be consumed by an iOS project. The app is still
Android-only during this phase, but with KMP in place "under the hood" (the iOS
client will come in Phase 3).

### Scope of Phase 2 (v1.1) - "Enhanced Content & Social Foundations"

#### Broader Content & Features

- **Add More Biblical Content:** Include an additional Bible translation (or
  two) based on user feedback. For example, if many found KJV hard to read, add
  the World English Bible (WEB) which is modern English and public domain. We
  can make WEB available as an option in settings or even default to it for new
  users. This addresses an immediate user need without licensing cost. We may
  also integrate a free API for a modern version (like ESV via Crossway API) for
  online use as a stopgap. If technically simple, the app could fetch verses
  from ESV API when online (and not store offline to respect terms), giving an
  alternative translation experience.
- **Expanded Devotional Library:** Provide more variety in devotionals. By
  v1.1, we can introduce a Plans Library section. This feature will list
  multiple reading plans (topical or biblical). For instance, alongside the
  daily default plan, include a few curated plans: a 7-day "Beginner's Prayer
  Journey", a 14-day "Forgiveness Study", or an imported public-domain
  devotional (like Spurgeon's Morning & Evening for a month). Users can browse
  plans, start one, and track their progress in a new "Plans" screen. This
  requires implementing UI for plan lists and plan detail (with day-by-day
  content). Under the hood, we likely add a `PlanRepository` and related data
  classes. We may reuse the existing daily devotional logic to display plan
  entries. The key new function is allowing multiple concurrent plans and
  tracking each separately (maybe a simple progress percentage or day X of Y
  shown).

Note: The PRD suggests enabling multiple ongoing plans and friend sharing by
v1.1. For now, we'll allow multiple plans per user (no restrictions), but not
yet implement friend sharing (we'll prepare the data model so a plan can have an
"invite code" or similar in the future).

#### Notifications & Reminders

In MVP we had a fixed daily devotion reminder. In Phase 2, make notifications
more configurable and comprehensive. Add a settings UI where users can turn
on/off reminders or set a different time. Introduce plan reminders (if a user
has not completed today's reading, send an evening reminder), streak
celebrations, and memory verse review reminders. Also, since we might quietly
start building backend features, integrate Firebase Cloud Messaging for any
server-push notifications if needed (for example, later social notifications).
For now, most notifications can be scheduled locally. This enhancement keeps
users engaged and lets them personalize their habit triggers.

#### UI/UX Polish

Address usability issues from MVP feedback. Possibly implement an intro tutorial
or tooltips for new users (for example, highlight "Tap the star to save a memory
verse!"). Ensure the Settings screen is fully built out with toggles for
notifications, account management (change password, etc.), and an Export Data
option (allow user to export their journal to a text file or backup, showing our
transparency). Also ensure accessibility improvements (larger text support,
etc.) are in place by now.

#### Social Foundations

While full social features (groups, etc.) come in Phase 3, Phase 2 can lay
groundwork:

- Create username/display name fields in the user profile so later, friends or
  group members have an identifier beyond email. If the user is logged in,
  allow them to set a username (and maybe auto-generate a random one if not).
  This prepares us for friend invites in v2.
- Possibly implement a hidden feature to share a reading plan link: a user can
  tap "Share this plan" and the app generates a link or code that, if another
  user opens, simply opens that plan in their app (or invites them to it). This
  is an intermediate social step to test content sharing without building full
  friend lists. It mimics "read the same plan with a friend" by manual
  coordination. We won't yet have an in-app friends list or group chat for it,
  but we can record internally if two users joined via the same link (maybe a
  proto "shared plan" concept).
- On the backend side, start designing the data model for Circles (small
  groups), for example a Firestore collection or a table structure. We might
  not expose any UI, but having an initial model means that if we have any
  enthusiastic test group, we could manually create a small closed beta group
  and test posting. However, likely we keep it dark in UI until v2.
- We will enforce login for cloud sync now (in MVP it was optional). In v1.1,
  if users want their data across devices, encourage them strongly to create an
  account. We may prompt guest users after some time to sign up ("Create an
  account to save your progress to the cloud"). This solidifies our user
  accounts in preparation for social features which will require accounts.

#### Technical Enhancements

- **Refactor to Kotlin Multiplatform:** Phase 2's crucial development task is
  to modularize and convert core code to a shared KMP library. We will create a
  new Gradle KMM module (for example, `shared`) or repurpose our core modules
  as KMP. Likely approach:
  - Combine or mark `core:model`, `core:domain`, `core:data` as Kotlin
    Multiplatform modules with common code.
  - Move any Android-specific implementations (Room, Firebase) to the Android
    source set, and create iOS stubs or expect/actual as needed. For instance,
    use SQLDelight so the database logic is common, with platform drivers
    injected at runtime.
  - The shared module will produce an Android library for use in the Android
    app and an iOS framework for the future iOS app.
- **Networking:** Replace Retrofit (if used) with Ktor Client in shared code so
  that networking works on iOS. Or use Retrofit on Android and plan to use a
  different approach on iOS by abstraction. Ideally, adopt one strategy: Ktor
  client in common code with OkHttp engine on Android and URLSession engine on
  iOS, so our RemoteDataSource can be multiplatform.
- **Data Sync & Firebase:** Firebase SDK is not multiplatform. We have options:
  keep Firebase calls on Android side only for now, integrate a Firebase KMP
  SDK (community libraries exist), or use REST APIs for Firebase. A simpler
  path: for Phase 2, we might limit cloud operations to Android and not require
  them on iOS until launch. But since by Phase 3 iOS should sync too, we plan
  ahead. Perhaps use a lightweight custom REST backend for critical sync (for
  example, implement a small Ktor server for journal sync) which both platforms
  can call. Or use Firebase Auth's REST API to sign in on iOS side. This
  requires evaluation. Given time, we might decide to initially not include
  full Firebase in shared code. We could have expect/actual in
  `core:datasource:remote` where Android actual uses Firebase SDK and iOS
  actual uses a placeholder or minimal implementation (like hitting Firestore
  via REST or deferring sync until we find a solution). The PRD suggests using
  multiplatform-ready solutions like SQLDelight for DB and treating sync as
  secondary, so one approach is to rely on the local DB as source of truth on
  iOS too and do periodic sync via simple HTTP calls.
- **ViewModels:** Consider how to share state with iOS. Options: (a) use KMP
  libraries like Kotlin MVVM or mokoMVVM that provide multiplatform ViewModel
  and state flow that SwiftUI can observe; or (b) maintain separate ViewModel on
  iOS side that calls shared use-cases. A middle ground: keep business logic
  (use cases, repositories) in shared, and let Android have Android ViewModels
  wrapping those, while iOS has SwiftUI ViewModels (ObservableObjects) that call
  into shared logic. For Phase 2, we might choose to simply share use-case
  classes and repository implementations, and not literally share ViewModel
  classes. That means minimal changes to the Android code and still leaves
  iOS open. The PRD mentions an approach: use StateFlows in shared ViewModels
  and have SwiftUI observe them. We might attempt that for completeness using
  Kotlinx.coroutines flows and a FlowWrapper for SwiftUI.
- **Testing KMP:** Once refactored, ensure all unit tests still pass now running
  on the common code. We might add iOS unit tests (Kotlin/Native tests) for core
  logic as well at this stage.

By end of Phase 2, "core logic is KMP ready for iOS", meaning one could drop the
shared library into an Xcode project and have all data/domain functionality
working.

#### Performance & Polish

Address any performance issues (maybe preloading data on background threads to
avoid UI jank). If large lists (plans library, etc.), use Compose `LazyColumn`
efficiently. Also implement missing error handling from MVP (for example, show
a user-friendly error if a sync fails or if content not found). Possibly
incorporate a loading indicator for network ops or when switching plans.

#### Backend/Foundation Work

If not already done, Phase 2 is a good time to set up some simple backend
services needed for Phase 3:

- If we plan on implementing friend invitations or sharing by Phase 3, set up a
  minimal cloud function or service to handle those (or decide to piggyback
  entirely on Firebase dynamic links).
- Prepare push notification setup for iOS (APNs credentials in Firebase, etc.)
  so that by iOS launch we can send notifications cross-platform.
- If any content management improvements: create a script or simple admin
  interface to upload new devotional content to Firebase so we can update the
  app's content remotely without app updates. This is not user-facing but helps
  us manage content growth (for example, adding new plans or devotionals
  easily).

#### Redesign Kick-off

Start planning the UI redesign while implementing new features. Gather feedback
on current UI/UX from MVP users. By end of Phase 2, we should have a design
prototype for a refreshed look that we will implement in Phase 3. This redesign
may align with adopting Material You styling, new color themes, updated
logo/branding, etc. Implementation will mostly happen in Phase 3, but Phase 2
can begin gradually (for example, update the app icon, or incorporate any quick
wins like better typography from Material3).

#### Outcome of Phase 2

The Android app (v1.1) will feel more mature: more content (multiple plans,
another translation), more user control (notifications, settings), and slightly
stepping into social (usernames, plan sharing). Importantly, under the hood, we
will now have the shared Kotlin code solidified. The core logic can run on iOS,
and we might even start an experimental iOS project to verify integration (for
example, build a simple SwiftUI list of devotionals using the shared module).
The PRD expects by v1.1 that we are "still Android-only, but core logic is KMP
ready for iOS", which is exactly our deliverable. This sets the stage for Phase
3, where we will build out the iOS app UI and further expand features like full
community groups and monetization.

## Phase 3: Kotlin Multiplatform Integration, iOS Launch & Redesign

### Timeline and Goal

Timeline: about 3 to 4 months after Phase 2 (April 2026 and beyond). Goal:
transform the project into a true multi-platform product by launching an iOS
version of the app using the shared Kotlin code, and implement a comprehensive
UI/UX redesign to coincide with the iOS release (and the introduction of
community features and monetization). Phase 3 corresponds to a major version
bump (v2.0), where the app evolves from an "Android MVP" to a cross-platform app
with social/community functionality and the beginning of the business model
(subscriptions).

### Scope of Phase 3 (v2.0) - "Community & Growth Expansion"

#### iOS Application Development

Start development of the iOS client in Swift (SwiftUI for UI). Thanks to the
KMP-shared modules (from Phase 2), the iOS app can call into the same ViewModel
logic or use case classes to get data, ensuring consistent behavior and feature
set.

- **Architecture on iOS:** Likely use SwiftUI + Combine (or KMP's Flow via
  Combine interop) for reactive UI. We will create SwiftUI views for each
  feature (DevotionalView, BibleReaderView, JournalView, etc.) analogous to
  Compose screens. For each, the underlying data comes from the Kotlin shared
  layer. We may instantiate shared ViewModels (if we made them multiplatform
  using state flows) and observe their StateFlow via ObservableObject wrapper.
  Alternatively, we use the repository use-cases directly in SwiftUI and manage
  state in SwiftUI. There are examples of KMP apps where the shared layer
  provides a Flow and SwiftUI subscribes to it.
- **UI Design on iOS:** We will aim for a native iOS feel while keeping
  consistent branding. Colors, icons, and general aesthetics remain the same as
  Android (especially after the redesign). But we will adhere to iOS platform
  conventions for navigation and controls. For example, use a TabView for bottom
  tabs, NavigationStack for in-page navigation, iOS-style toggles in settings,
  etc. If Compose Multiplatform for iOS is stable by this time, we could
  consider using it to share UI code as well, but likely we stick to SwiftUI for
  a truly native experience unless resource constraints push us to reuse Compose
  UI. The PRD suggests possibly using SwiftUI or Compose Multiplatform for iOS
  UI; we'll evaluate stability. As of 2026, Compose Multiplatform may be viable
  but is newer, so safer to do SwiftUI.
- **Feature Parity:** Ensure all features available on Android (post-Phase 2)
  are available on iOS. This includes the daily devotion feed, Bible reader,
  journal entries (with encryption; we might need to implement encryption on
  iOS via Kotlin code or use CommonCrypto), memory verses, plan library,
  notifications for iOS, etc. We will leverage as much shared logic as possible:
  - The encryption, SRS algorithm, etc., all come from shared Kotlin, so it's
    consistent.
  - For local storage on iOS, SQLDelight will use an iOS SQLite database for
    the same schema, so it "just works" given correct initialization.
  - Networking calls from shared code will use Ktor with an iOS HTTP client
    engine. Or if we integrated Firebase, we might need to use alternative means
    on iOS. Possibly we skip full Firebase login on iOS MVP by requiring users
    to use email/password (which KMP can handle via REST to Firebase Auth
    endpoints). This may require writing some platform-specific code in Kotlin
    (actual for iOS using Firebase REST API or using a KMP library like
    FirebaseKMP).
- **Push Notifications:** Set up APNs via Firebase Messaging or native iOS
  notifications for daily reminders, etc. The iOS app will prompt for
  notification permission and schedule local notifications just like Android
  (for daily devotions, plan reminders). For push (for example, if we send a
  notification from server), integrate Firebase Cloud Messaging's APNs
  configuration. The shared code can define the message content, but actual
  registration for push and handling must be done in iOS code. We might need to
  implement a small iOS-specific push handler that calls shared logic when a
  notification is tapped (to navigate to appropriate content).
- **Testing iOS:** We will do a TestFlight beta perhaps with the same pilot
  users to get feedback. Focus on ensuring the shared code works well under
  Kotlin/Native (fix any memory management issues, threading, etc., that differ
  from JVM). Also ensure that encryption works on iOS (key handling in Keychain
  if needed).

According to plan, by end of this phase or shortly after, we aim to release an
iOS version (even if beta). This effectively doubles our reach and caters to
mixed-platform communities.

#### Community Features (Social Expansion)

- **Launch Small Groups (Circles):** Fully enable the Circles feature for
  social interaction. Users can create or join circles (invite-only groups)
  within the app.
  - **UI:** A new "Community" or "Groups" section. For example, a tab or a
    screen listing the user's Circles. From there, they can open a Circle
    (group chat feed). Provide a "Create Circle" flow (name the group, get an
    invite code or link to share). Also a way to join an existing circle via
    code or link.
  - **Messaging:** Implement basic group chat within a circle. Likely use
    Firebase Firestore or Realtime DB for messaging to avoid building entire
    infra. We'll integrate something like Firestore where each circle has a
    collection of messages. Use listeners to live-update new messages. The
    shared Kotlin code can provide a ChatRepository that wraps Firebase calls
    (on iOS, possibly use a lightweight KMP Firebase wrapper or call via REST
    API or just limit realtime features on iOS initial launch by requiring
    manual refresh; preferably find a way to integrate Firebase realtime on iOS
    too, perhaps through multiplatform libraries or by writing minimal Swift
    code that calls Firestore SDK and passes data to Kotlin).
  - **Features:** Text posts and maybe emoji reactions. We'll skip advanced
    things (no images or voice messages in v2, to minimize complexity). Ensure
    notifications for new messages (using FCM/APNs).
  - **Moderation:** The circle creator can remove members or delete messages if
    needed. Basic UI for that (long-press message to delete if owner). Possibly
    a "Report" button to admin (which might just send us an email for now).
  - **Integration:** Circles tie into existing features. If members of a circle
    are on the same reading plan, show a discussion thread for that plan (the
    PRD described comments on plan days visible within the circle). For v2, a
    simpler approach: after reading a devotion, user can go to the circle and
    manually post about it. Automated threading can be a future improvement.
    We might indicate what plan day a post is about if the user chooses. Verse
    sharing to circle: allow user to share a verse or journal snippet to their
    circle directly from those screens. Circle members can see each other's
    progress or streaks optionally (this may be a minor feature; maybe a toggle
    "share my activity to circle" - if enabled, show "Alice completed Day 5 of
    Plan X" in the circle feed or such).
  - **Impact:** We expect that by adding Circles, we significantly boost
    engagement and retention (community effect). We will monitor metrics like
    number of messages and circles created.
- **Introduce Church Channels (Beta):** As per plan, onboard a few pilot
  churches to test the Church Channel feature.
  - Provide an interface (possibly a simple web portal or even manual JSON) for
    a church admin to create a channel, upload devotional plans or
    announcements, and generate a join code. On the app side, add UI for users
    to search or enter code to join a church channel.
  - Once joined, the church channel content appears in the app: for example, a
    Church feed separate from personal feed, or integrated in the home feed
    (maybe a section "From Your Church"). For simplicity, we may give channels
    a section in the app (like how YouVersion has "My Church"). But a minimal
    approach: the channel basically behaves like a special circle run by the
    church (with potentially unlimited members). The church can post reading
    plans (which appear like recommended plans to channel members) and
    announcements (which could appear as notifications or in a channel feed).
  - Implement minimal admin features: likely not building an in-app admin UI in
    mobile for church leaders. Instead, maybe a basic web admin or simply
    instruct pilot churches to send us content that we upload to Firestore.
    However, since we plan to scale it, we might implement a very basic web
    form or reuse Firebase console for content storage.
  - The analytics for church (if any in v2) might not be fully built, but we
    can promise to send them some usage stats manually.
  - This feature is beta and mainly to prove out the concept and gather
    requirements. So we might work closely with 1 to 3 churches. It's also a
    selling point for B2B model, which we plan to monetize (likely in future
    phases or late v2).

#### Monetization Launch (Freemium Model)

By v2, we plan to introduce the Premium subscription tier to start generating
revenue. The timing is appropriate because by now we have a robust set of
features and content. The monetization strategy from PRD will be implemented as
follows:

- **Implement Subscriptions:** Integrate in-app purchases on Android (Google
  Play Billing) and iOS (StoreKit) for a Premium subscription (monthly/yearly).
  Use the same product IDs across platforms where possible. Possibly use
  RevenueCat or Firebase Extensions to unify subscription handling, or do
  platform-specific logic and have our backend verify receipts.
- **Paywall & Upsell:** Identify features and content to put behind the Premium
  paywall. As per earlier discussion and PRD:
  - Additional Bible translations (licensed ones like NIV, ESV). By v2 we aim
    to have at least one such license, for example NIV. These will be
    Premium-only content to justify subscription (because we incur licensing
    cost). Implementation: in app, free users see NIV in list but tapping it
    shows a modal "Subscribe to access NIV and other modern translations".
  - Premium audio content (for example, human-narrated audio if we manage to
    license or produce any by then) or higher-quality TTS/ambient sounds as
    add-ons. Possibly by v2 we include one professional audio (maybe an audio
    Bible for New Testament). Make that premium.
  - Exclusive devotionals/plans: if we have content partnerships by then (or
    even classic content curated nicely), mark some plans as Premium. For
    example, a 30-day study from a well-known author might be premium-only.
  - Social/community perks: possibly allow free users to join only a limited
    number of circles (say one circle) whereas Premium can join/create
    unlimited circles. This encourages heavy community users to subscribe but
    still lets casual users participate a bit. We must be careful not to
    cripple core community, but a limit like one free circle might be
    acceptable as PRD suggests.
  - Device sync: if in MVP we allowed free sync, we might decide to require
    login plus Premium for multi-device sync. But basic backup should probably
    remain free to not risk data loss. We could differentiate: free account
    syncs journal and basics; Premium syncs across unlimited devices plus maybe
    web access if we ever have web.
  - UI customization: not critical, but perhaps Premium users get extra themes
    or icons (small perk).
- **Core Content Stays Free:** We will not put core daily devotion or Bible
  reading behind paywall. Those remain free to keep the ministry ethos. The
  idea is Premium adds convenience and depth (versions, audio, exclusive
  content), while free covers basic spiritual needs.
- **Build Paywall Screen:** When user hits a premium feature, show a screen
  highlighting benefits of Premium (perhaps a "Free vs Premium" comparison).
  Include pricing and a CTA to subscribe. Also possibly offer a free trial for
  7 days (both stores support trial periods).
- **Backend for Subscriptions:** Implement verification (like using Play Store
  purchase tokens and App Store receipts). Possibly use Firebase Functions to
  verify receipts server-side to prevent fraud. Or simpler: trust client for
  now but plan to secure later. We'll also implement logic to handle
  cancellation (maybe grace period access).
- **Promotion:** We might give early bird pricing or founding member discounts
  as PRD discussed, but those are marketing details. We ensure the app
  gracefully downgrades if sub expires (no data loss, just lock premium content
  again).
- **Mission Note:** As noted in PRD, communicate transparently that Premium
  supports the app's mission and not just greed. Possibly include a note or
  onboarding slide about this to keep user trust (for example, "Your
  subscription helps us license more translations and create more content to
  bless others").

#### B2B Monetization (Church Plan)

Not necessarily fully launched in v2, but we explore it:

- If our pilot churches loved the channel feature, we can prepare a Church Plan
  offering. Maybe we decide on pricing like $X per month for a church (depending
  on size). We likely won't automate payments for this in v2; instead, we might
  sign one church on a contract or free trial and plan to scale it in future.
- The concept: a church pays, and all its members get Premium for free
  (subsidized). We can implement that by flagging users in that church's channel
  as premium-entitled. For now, since only a couple churches, we might manually
  upgrade those users or use a simple mechanism (for example, a "churchCode"
  that unlocks Premium).
- We'll refine how to integrate this in the app UI (perhaps show "Provided by
  Your Church" if a user's church has a subscription).
- No robust payment portal for churches in v2; that would come later with more
  dev resources or when scaling.

#### UI/UX Redesign Implementation

We coordinate a major redesign in Phase 3 to refresh the app's look and address
any UX shortcomings observed. Typically, after getting MVP out and initial
feedback, it's ideal to refine the design for broader release (especially to
make a splash on iOS launch).

Redesign elements may include:

- Visual theme update: possibly adopt Material You on Android (dynamic theming,
  rounded shapes, etc.) and analogous styling on iOS. Update color scheme and
  typography for a more modern, distinctive look. Ensure a consistent brand
  identity across platforms (maybe a new logo or refined iconography).
- Navigation reorganization: based on usage, adjust what is easily accessible.
  For example, if Journal is used less, perhaps demote it from tab bar; if
  community is key, give it a prominent tab. If adding Church, incorporate that
  prominently.
- Higher fidelity UI components: more custom illustrations or backgrounds to
  make the app feel polished (for example, a nice graphic on the welcome screen
  or subtle background gradients).
- Better onboarding: improved onboarding flow with nicer graphics, or an in-app
  tutorial for new social features.
- Consistent design language: because now we have two platforms, define a
  design language that works on both (color, fonts, icon style). Android and
  iOS will each use native components but we ensure the overall feel is the same
  app. For instance, use the same color codes and similar layout concepts (tab
  bar on iOS vs bottom nav on Android).
- Accessibility review: incorporate changes to make the app more accessible
  (color contrast, button sizes, etc.).

Implementation will largely occur in the `sacrament` module for Android (for
example, replacing theme definitions with Material3, updating component styles)
and similarly adjusting SwiftUI view styles on iOS. We should time the redesign
so both platforms launch with the new look (to avoid one looking outdated).
Possibly engage a UI/UX designer during this phase if we have not already, given
this is a crucial public launch stage.

#### Outcome of Phase 3

By April 2026, we aim to release PracticalChristian App 2.0:

- **Android 2.0:** With community features, refined UI, and subscription model
  active.
- **iOS 1.0:** The first iOS release, feature-equivalent to Android and with
  the same redesign, likely marked as version 2.0 as well for parity.

We will likely label it a major update and do a marketing push (perhaps around
Easter 2026, which is in April, a good time for a faith app launch). The
ecosystem now covers both major mobile OS, enabling friends and church groups
regardless of device to participate (which was crucial for growth, as noted:
"many Christian communities have mixed device usage"). We'll also begin
measuring revenue and seeing how users respond to Premium. The app's success
metrics (engagement, retention, MTS/W as PRD calls it) should see an uptick due
to social and new content, and we'll track conversion to Premium.

#### Beyond Phase 3

Beyond April 2026, further phases (v3+) would focus on scaling and optimizing:

- Expanding internationally (add more languages/translations).
- Advanced features like AI-driven recommendations or integration with
  wearables.
- Possibly a web companion or more church admin tools.
- Continuous improvement based on analytics (for example, if memory feature is
  underused, revamp it; if community thrives, invest more there).

For now, we have a clear path to deliver a high-quality Android app and then
transition into a multiplatform product, all while adhering to the product's
core vision and values (ensuring technology serves the spiritual purpose, not
the other way around).
