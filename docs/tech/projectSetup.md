# Development Setup Guide

## Pre-requisites
- Android Studio Hedgehog or later
- JDK 21 (must be configured in Android Studio: Settings > Build Tools > Gradle > Gradle JDK)
- Android SDK 36 (Compile SDK)
- Min SDK: 33 (Android 13)
- Target SDK: 36 (Android 15)
- Git

## Setup Steps
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run `./gradlew detektBaseline` to create baseline
5. Build and run the app

## Project Structure
```
PracticalChristian/
├── app/                    # Main application module
├── sacrament/              # Sacrament design system library
├── sacrament-demo/         # Sacrament component showcase app
├── buildLogic/             # Gradle convention plugins
│   ├── convention/         # Convention plugin implementations
│   └── docs/               # Build system documentation
├── sync/
│   └── work/               # Background sync and WorkManager
├── core/                   # Core foundational modules
│   ├── common/             # Common utilities and extensions
│   ├── model/              # Domain models and entities
│   ├── datastore/          # DataStore preferences
│   ├── database/           # Room database
│   ├── network/            # Retrofit and network
│   ├── analytics/          # Analytics tracking
│   ├── notifications/      # Notification handling
│   ├── media/              # Media player functionality
│   ├── performance/        # Performance monitoring
│   ├── i18n/               # Internationalization
│   ├── testing/            # Testing utilities
│   ├── ui/                 # Shared UI utilities
│   ├── datasource/
│   │   ├── local/          # Local data source implementations
│   │   └── remote/         # Remote data source implementations
│   ├── data/               # Repository implementations
│   └── domain/             # Use cases and business logic
├── content/                # App content modules (data-only)
│   ├── books/              # Bible books content
│   ├── meditations/        # Meditation content
│   ├── plans/              # Reading plans content
│   ├── prayers/            # Prayer content
│   ├── themes/             # Theme content
│   └── audio/              # Audio content
├── feature/                # Feature modules (UI + presentation)
│   ├── auth/               # Authentication
│   ├── landing/            # Landing screen
│   ├── setup/              # App setup flow
│   ├── onboarding/         # User onboarding
│   ├── home/               # Home screen
│   ├── meditation/         # Meditation feature
│   ├── books/              # Bible books feature
│   ├── plans/              # Reading plans feature
│   ├── schedules/          # Schedules management
│   ├── tags/               # Tags management
│   ├── notes/              # Notes feature
│   ├── journal/            # Journal feature
│   ├── prayer/             # Prayer feature
│   ├── audio/              # Audio player feature
│   ├── streaks/            # Streaks tracking
│   ├── notifications/      # Notifications settings
│   ├── profile/            # User profile
│   ├── settings/           # App settings
│   ├── search/             # Search functionality
│   └── bookmarks/          # Bookmarks feature
├── docs/                   # Documentation
│   ├── agentRules/         # AI agent guidelines
│   ├── product/            # Product docs
│   └── tech/               # Technical docs
└── scripts/                # Git hooks and automation
```

## Build Commands
- `./gradlew build` - Build all modules
- `./gradlew detekt` - Run static analysis
- `./gradlew test` - Run unit tests
- `./gradlew connectedAndroidTest` - Run instrumented tests
