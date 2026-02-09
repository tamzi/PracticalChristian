# Development Setup Guide

## Pre-requisites
- Android Studio Ladybug or later
- JDK 21
- Android SDK 36
- Git

## Setup Steps
1. Clone the repository
2. Open in Android Studio
3. Set Gradle JDK to 21 (Settings > Build > Gradle > Gradle JDK)
4. Sync Gradle files
5. Run `./gradlew detektBaseline` to create baseline
6. Build and run the app

## Project Structure
```
PracticalChristian/
├── app/                        # Main application entry point
├── core/                       # Shared domain/data layers and infrastructure
│   ├── analytics/              # Analytics tracking
│   ├── common/                 # Shared utilities
│   ├── data/                   # Data layer (repositories)
│   ├── database/               # Room database
│   ├── datasource/             # Data source abstractions
│   │   ├── local/              # Local data sources (Room, DataStore)
│   │   └── remote/             # Remote data sources (API)
│   ├── datastore/              # Preferences DataStore
│   ├── domain/                 # Domain layer (use cases, business logic)
│   ├── i18n/                   # Internationalization resources
│   ├── media/                  # Media playback
│   ├── model/                  # Shared data models
│   ├── network/                # Networking (Retrofit, OkHttp)
│   ├── notifications/          # Notification handling
│   ├── performance/            # Performance monitoring
│   ├── testing/                # Shared test utilities
│   └── ui/                     # Shared Compose UI utilities
├── content/                    # App content modules (data-only)
│   ├── audio/                  # Audio content
│   ├── books/                  # Bible book data
│   ├── meditations/            # Meditation content
│   ├── plans/                  # Reading plan data
│   ├── prayers/                # Prayer content
│   └── themes/                 # Theme/topic data
├── feature/                    # Feature modules (Compose UI + presentation)
│   ├── audio/                  # Audio player feature
│   ├── auth/                   # Authentication
│   ├── bookmarks/              # Bookmarks
│   ├── books/                  # Bible books browser
│   ├── home/                   # Home screen
│   ├── journal/                # Journaling
│   ├── landing/                # Landing / welcome screen
│   ├── meditation/             # Meditation experience
│   ├── notes/                  # Notes
│   ├── notifications/          # Notification settings
│   ├── onboarding/             # Onboarding flow
│   ├── plans/                  # Reading plans
│   ├── prayer/                 # Prayer feature
│   ├── profile/                # User profile
│   ├── schedules/              # Schedules
│   ├── search/                 # Search
│   ├── settings/               # App settings
│   ├── setup/                  # Initial setup
│   ├── streaks/                # Streaks / habit tracking
│   └── tags/                   # Tags management
├── sacrament/                  # Sacrament design system library
├── sacrament-demo/             # Design system showcase application
├── sync/                       # Background sync tooling
│   └── work/                   # WorkManager sync tasks
├── buildLogic/                 # Gradle convention plugins
│   └── convention/             # Convention plugin implementations
├── scripts/                    # Git hooks and automation scripts
└── docs/                       # Documentation
    ├── agentRules/             # AI agent rules
    ├── product/                # Product requirements
    └── tech/                   # Technical documentation
```

## Build Commands
- `./gradlew build` - Build all modules
- `./gradlew detekt` - Run static analysis
- `./gradlew detekt --auto-correct` - Run static analysis with auto-fix
- `./gradlew test` - Run unit tests
- `./gradlew connectedAndroidTest` - Run instrumented tests
