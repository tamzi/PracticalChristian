---
name: practicalchristian-testing
description: Apply PracticalChristian testing and verification standards. Use when adding tests, fixing failing tests, validating features, or deciding which Gradle checks to run.
version: 1.0.0
autoTrigger: true
projectTypes: [android, kotlin]
---

# PracticalChristian Testing

## Use this when

- Adding or updating tests.
- Fixing failing builds, lint, Detekt, or unit tests.
- Verifying feature work before completion.
- Deciding appropriate local checks.

## Required sources

- Follow `docs/agentRules/testingRules.md`.
- Use `docs/agentRules/workflowRules.md` for verification expectations.
- Check module build files for actual configured test dependencies.

## Operating rules

- Prefer targeted checks while iterating, then broader checks when work is complete.
- Add tests for new behavior when feasible.
- Use existing test utilities and patterns before adding new ones.
- For Compose UI, include previews for component work.
- Report verification honestly, including checks not run.
