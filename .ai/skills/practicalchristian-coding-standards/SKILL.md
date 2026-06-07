---
name: practicalchristian-coding-standards
description: Apply PracticalChristian Kotlin, Compose, naming, KDoc, comments, and code organization standards. Use when editing code or reviewing implementation quality.
version: 1.0.0
autoTrigger: true
projectTypes: [android, kotlin]
---

# PracticalChristian Coding Standards

## Use this when

- Writing or reviewing Kotlin code.
- Adding public APIs that need KDoc.
- Refactoring names, imports, or file organization.
- Checking comments and implementation clarity.

## Required sources

- Follow `docs/agentRules/codingStandards.md`.
- Use `Agents.md` for project-wide naming and comment expectations.
- Use nearby source files for local style patterns.

## Operating rules

- Prefer immutable state and clear names.
- Keep comments concise and focused on why, not what.
- Public APIs need KDoc when required by project rules.
- Follow existing package and file organization patterns.
- Address Detekt issues at the root cause instead of suppressing them by default.
