---
name: android-quality-review
description: Review Android Kotlin and Compose changes for architecture, quality, accessibility, performance, and security issues. Use before completing non-trivial code changes.
version: 1.0.0
autoTrigger: true
projectTypes: [android, kotlin]
---

# Android Quality Review

## Use this when

- Reviewing feature, core, app, content, sync, or design-system changes.
- Checking Compose performance and accessibility.
- Looking for architecture or module-boundary violations.
- Preparing to mark implementation work complete.

## Required sources

- Use `docs/agentRules/codingStandards.md`.
- Use `docs/agentRules/architectureRules.md`.
- Use `docs/agentRules/featureDevelopmentRules.md` for feature modules.
- Use `docs/agentRules/testingRules.md` for verification expectations.

## Operating rules

- Check Clean Architecture layer boundaries and dependency direction.
- Prefer immutable state and stable Compose parameters.
- Avoid unnecessary recomposition and hardcoded values.
- Check error handling, logging, and privacy-sensitive flows.
- Confirm tests, previews, and documentation match the change scope.
