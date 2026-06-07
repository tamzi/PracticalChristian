---
name: practicalchristian-architecture
description: Apply PracticalChristian architecture and module-boundary rules. Use when changing module dependencies, Clean Architecture layers, repositories, use cases, data flow, or cross-module APIs.
version: 1.0.0
autoTrigger: true
projectTypes: [android, kotlin]
---

# PracticalChristian Architecture

## Use this when

- Changing module dependencies or public APIs.
- Adding repositories, use cases, data sources, or domain models.
- Reviewing Clean Architecture layer boundaries.
- Evaluating whether code belongs in app, core, feature, content, sync, or sacrament.

## Required sources

- Follow `docs/agentRules/architectureRules.md`.
- Use `docs/agentRules/projectOverview.md` for module responsibilities.
- Use `docs/tech/technicalArchitecture.md` and `docs/architecture.md` for broader architecture context.

## Operating rules

- Respect dependency direction and avoid circular dependencies.
- Keep feature UI and presentation logic inside feature modules.
- Keep shared infrastructure in core modules.
- Prefer existing architecture patterns before adding new abstractions.
- Document architectural decisions only when the decision is durable and useful.
