---
name: practicalchristian-docs
description: Apply PracticalChristian documentation rules. Use when creating or editing markdown, docs indexes, task docs, architecture docs, or agent instructions.
version: 1.0.0
autoTrigger: true
projectTypes: [android, kotlin]
---

# PracticalChristian Docs

## Use this when

- Creating or editing markdown documentation.
- Updating task tracking.
- Adding architecture, technical, product, or agent guidance.
- Reviewing documentation for compliance.

## Required sources

- Follow `docs/agentRules/documentationRules.md`.
- Check `docs/README.md` for documentation index updates.
- Use `docs/agentRules/README.md` for agent-rule navigation.

## Operating rules

- Keep documentation crisp and specific.
- Put project documentation under `docs/` unless a tool requires a dedicated config directory.
- Use camelCase for markdown files except `README.md` and `Agents.md`.
- Do not duplicate source code in markdown; link to actual files instead.
- Avoid “Last Updated” dates and maintainer sections.
- Do not create documentation files unless they are needed for the task.
