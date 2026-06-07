# Shared AI Agents

Canonical tool-agnostic specialist agents for PracticalChristian live here.

## Stable structure

- `.ai/agents/` is the shared source for specialist personas.
- `firebender.json` registers these files directly for Firebender agents and callable subagents.
- `docs/agentRules/` remains the canonical long-form rulebook and rationale.
- Tool-specific adapters should point here rather than duplicate agent content.

## Usage

- Firebender loads these through `firebender.json`.
- Other tools can point to or copy from this directory only when their agent systems require it.
- Keep instructions focused and link to `Agents.md`, `docs/agentRules/`, and product or technical docs instead of duplicating full rules.
- Do not move `docs/agentRules/` until every `.ai`, documentation, and git-hook reference is migrated deliberately.

## Current agents

- `planner`
- `android-implementer`
- `sacrament-reviewer`
- `test-writer`
- `verifier`
- `security-privacy-reviewer`
- `product-researcher`
