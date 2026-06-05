---
name: practicalchristian-feature-development
description: Apply PracticalChristian feature-module development rules. Use when building screens, ViewModels, UI state, navigation, Hilt integration, or feature-specific presentation logic.
version: 1.0.0
autoTrigger: true
projectTypes: [android, kotlin]
---

# PracticalChristian Feature Development

## Use this when

- Creating or changing feature modules.
- Adding Compose screens, ViewModels, UI state, or navigation.
- Wiring Hilt dependencies for feature code.
- Reviewing feature-layer architecture.

## Required sources

- Follow `docs/agentRules/featureDevelopmentRules.md`.
- Use `docs/agentRules/architectureRules.md` for layer boundaries.
- Check existing feature modules for patterns before adding new ones.

## Operating rules

- Keep feature modules focused on UI and presentation logic.
- Model screen state explicitly and handle loading, empty, success, and error states.
- Use existing navigation and dependency-injection patterns.
- Prefer state hoisting and unidirectional data flow in Compose.
- Add tests or verification steps for behavior changes.
