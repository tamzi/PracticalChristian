# Development Setup Guide

## Pre-requisites
- Android Studio Hedgehog or later
- JDK 21
- Android SDK 35
- Git

## Setup Steps
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run `./gradlew detektBaseline` to create baseline
5. Build and run the app

## Project Structure
```
daily/
├── app/                    # Main application module
├── core/                   # Core modules
│   ├── data/              # Data layer implementation
│   ├── domain/            # Domain layer (business logic)
│   ├── localdatasource/   # Local data sources (Room, DataStore)
│   └── remotedatasource/  # Remote data sources (API)
├── sacrament/             # UI components and theming
├── feature/               # Feature modules
│   └── presentation/      # UI layer
└── buildLogic/           # Gradle convention plugins
```

## Build Commands
- `./gradlew build` - Build all modules
- `./gradlew detekt` - Run static analysis
- `./gradlew test` - Run unit tests
- `./gradlew connectedAndroidTest` - Run instrumented tests
