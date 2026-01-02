# PracticalChristian - Bible Meditations App

A modern Android application for daily Bible meditations and spiritual growth.

## 📱 Features

- Daily Bible reading schedules
- Note-taking and journaling
- Tag-based organization
- Dark/Light theme support
- Offline-first architecture

## 🏗️ Architecture

- [Architecture Overview](docs/architecture.md) - Clean Architecture principles and domain

- **Clean Architecture** with Domain, Data, and Presentation layers
- **MVVM** pattern with ViewModels and StateFlow
- **Dependency Injection** with Hilt
- **Navigation** with Navigation Compose 2.9.x
- **Database** with Room
- **UI** with Jetpack Compose

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: Clean Architecture + MVVM
- **DI**: Hilt
- **Navigation**: Navigation Compose 2.9.x
- **Database**: Room
- **Networking**: Retrofit + OkHttp
- **Async**: Kotlin Coroutines + Flow
- **Build System**: Gradle with Kotlin DSL

## 🚀 Getting Started

### Technical Guides
- [Setup Guide](docs/tech/projectSetup.md)


### Prerequisites
- Android Studio Hedgehog or later
- JDK 21
- Android SDK 34+

### Build Commands

```bash
# Clean build with dependency refresh
./gradlew clean build --refresh-dependencies

# Run detekt
./gradlew detekt

# Generate detekt baseline
./gradlew detektBaseline

# Run tests
./gradlew test

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease
```

## 📋 Project Structure

```
app/                    # Main application module
core/
├── data/              # Data layer implementation
├── domain/            # Domain layer (business logic)
├── localdatasource/   # Local database and caching
└── remotedatasource/  # Remote API integration
feature/
└── presentation/      # UI layer with Compose screens
sacrament/             # Shared UI components and themes
```

## ✅ Work Tracking

- [Work To Be Done](docs/workToBeDone.md)

## 📄 License

This project is part of the PracticalChristian Bible Meditations application.
