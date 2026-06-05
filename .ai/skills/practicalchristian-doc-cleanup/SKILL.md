---
name: practicalchristian-doc-cleanup
description: Clean and consolidate PracticalChristian documentation. Use when removing duplicated docs, enforcing no-code-in-docs rules, or improving docs organization and indexes.
version: 1.0.0
autoTrigger: true
projectTypes: [android, kotlin]
---

# PracticalChristian Documentation Cleanup

## Use this when

- Cleaning duplicated or stale documentation.
- Enforcing no code snippets in markdown.
- Reviewing documentation naming, size, or index coverage.
- Consolidating agent rules or technical docs.

## Required sources

- Follow `docs/agentRules/documentationRules.md`.
- Use `docs/agentRules/noCodeInDocsRule.md` for no-code-in-docs enforcement.
- Use `docs/agentRules/documentationCleanupPrompt.md` for cleanup process guidance.
- Check `docs/README.md` when documentation files are added, moved, or removed.

## Operating rules

- Keep docs crisp and specific.
- Link to actual source files instead of duplicating code.
- Preserve useful decisions and delete repeated boilerplate.
- Keep one source of truth for each topic.
- Avoid broad documentation rewrites unless requested.
