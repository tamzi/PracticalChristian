---
name: practicalchristian-commit
description: Apply PracticalChristian commit rules. Use when the user asks to commit, review staged changes, split commits, or prepare commit messages.
version: 1.0.0
autoTrigger: true
projectTypes: [android, kotlin]
---

# PracticalChristian Commit

## Use this when

- The user asks to commit changes.
- Reviewing staged or unstaged changes before a commit.
- Deciding whether changes should be split into multiple commits.
- Preparing a commit message.

## Required sources

- Follow `docs/agentRules/commitRules.md` exactly.
- Check `docs/agentRules/documentationRules.md` for documentation changes.
- Use `Agents.md` for project-wide constraints.

## Operating rules

- Never push unless the user explicitly asks, and never force-push protected branches.
- Run git status and inspect diffs before staging.
- Verify every changed file before committing.
- Keep commits atomic by layer, dependency order, and documentation file.
- Do not bypass hooks or suggest bypassing hooks.
- Use the repository commit style and the required AI attribution when operating in Firebender.
