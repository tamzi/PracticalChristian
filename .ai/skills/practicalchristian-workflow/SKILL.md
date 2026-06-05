---
name: practicalchristian-workflow
description: Follow PracticalChristian planning, thoughtful collaboration, self-review, and implementation summary rules. Use before starting complex work, when requirements are ambiguous, or before reporting completion.
version: 1.0.0
autoTrigger: true
projectTypes: [android, kotlin]
---

# PracticalChristian Workflow

## Use this when

- Planning multi-step work.
- Evaluating ambiguous requirements.
- Reviewing whether work is ready to mark complete.
- Preparing an implementation summary.

## Required sources

- Start with `Agents.md` for project-wide agent behavior.
- Use `docs/agentRules/workflowRules.md` for planning, execution, review, and summaries.
- Use `docs/agentRules/thoughtfulCollaboration.md` before acting on suggestions, refactors, warning fixes, or architectural changes.
- Use `docs/tasks/workToBeDone.md` for current priorities.

## Operating rules

- Discuss before implementing when the request is a question, suggestion, or architectural decision.
- Plan before execution and identify affected modules before editing.
- Prefer the smallest correct change.
- Review actual changed files before reporting completion.
- Summaries must state what changed, why it changed, and what verification was performed.
