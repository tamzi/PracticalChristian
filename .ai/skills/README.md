# Shared AI Skills

Canonical tool-agnostic skills for PracticalChristian live here.

## Stable structure

- `.ai/skills/` is the shared source for lightweight, task-triggerable wrappers.
- `docs/agentRules/` remains the canonical long-form rulebook and rationale.
- `.firebender/skills/` contains symlink adapters so Firebender can load these as team skills.
- Tool-specific adapters should point here rather than duplicate skill content.

## Usage

- Firebender loads these through `.firebender/skills` adapters.
- Other tools can point to or copy from this directory only when their skill systems require it.
- Keep each `SKILL.md` focused and link to `Agents.md` or `docs/agentRules/` instead of duplicating full rules.
- Do not move `docs/agentRules/` until every `.ai`, documentation, and git-hook reference is migrated deliberately.

## Current skills

- `android-quality-review`
- `practicalchristian-architecture`
- `practicalchristian-coding-standards`
- `practicalchristian-commit`
- `practicalchristian-doc-cleanup`
- `practicalchristian-docs`
- `practicalchristian-feature-development`
- `practicalchristian-humanizer`
- `practicalchristian-project-context`
- `practicalchristian-testing`
- `practicalchristian-thoughtful-collaboration`
- `practicalchristian-workflow`
- `sacrament-design-system`
