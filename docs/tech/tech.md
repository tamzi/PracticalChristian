# Feature Modules for the PracticalChristian App

This document outlines the modular structure for the "PracticalChristian" application, aligning with the established multi-module architecture (`app`, `core:*`, `sacrament`, `feature:*`). Placeholder structures and build files have been created for these modules.

## Core Modules

These modules provide foundational capabilities:

*   `core:common`: Shared utilities, constants, extensions.
*   `core:data`: Implements repositories, abstracting data sources.
*   `core:domain`: Contains use cases, domain models, and business logic interfaces.
*   `core:datasource:local`: Manages local data storage (database, preferences).
*   `core:model`: Shared data transfer objects and entities.
*   `core:datasource:remote`: Handles network API interactions.

## Feature Modules

These modules encapsulate specific user-facing features or business logic domains.

*   **`feature:account`**: Manages user profile details and settings.
*   **`feature:ai`**: Handles AI-driven recommendations and chatbot integration.
*   **`feature:authentication`**: Handles user registration, login, password management.
*   **`feature:bible`**: Provides Bible reading interface, translations, highlighting, bookmarking, search, offline access.
*   **`feature:books`**: Manages listing and viewing "books" (potentially merging with `feature:bible` later).
*   **`feature:community`**: Manages community groups, shared prayer requests, challenges, leaderboards.
*   **`feature:home`**: Acts as the main dashboard or entry point after login.
*   **`feature:journal`**: Provides the personal devotional journal interface, reflection prompts.
*   **`feature:landing`**: Handles the initial screen presented to the user before login/onboarding.
*   **`feature:localization`**: Contains logic related to fetching/displaying region-specific content or handling multi-language support infrastructure.
*   **`feature:meditation`**: Displays daily meditation texts and manages themes.
*   **`feature:monetization`**: Handles features like local church locator, bulletin board, etc.
*   **`feature:notes`**: Handles creation, viewing, and editing of general notes.
*   **`feature:onboarding`**: Manages the initial user setup flow after registration.
*   **`feature:prayer`**: Manages personal prayer points/lists and reminders.
*   **`feature:quotes`**: Fetches and displays daily quotes/verses and handles sharing.
*   **`feature:reading-plans`**: Manages predefined and custom Bible reading plans, tracks progress, handles reminders.
*   **`feature:schedules`**: Manages viewing schedules.
*   **`feature:search`**: Provides global search functionality.
*   **`feature:settings`**: Manages app-wide settings, notification preferences, etc.
*   **`feature:setup`**: Handles specific setup steps, possibly part of onboarding.
*   **`feature:tags`**: Manages tags for organizing content.

*(Legacy Module: `feature:presentation` still exists containing the original presentation code, intended for refactoring into the above feature modules.)*

## Design System Module

*   **`sacrament`**: Contains reusable UI components, themes, typography, and styling definitions used across all feature modules.

Each feature module depends on the necessary `core` modules and the `sacrament`. The main `app` module coordinates these feature modules.
