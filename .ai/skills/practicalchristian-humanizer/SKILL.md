---
name: practicalchristian-humanizer
description: Humanize PracticalChristian docs and PR messages. Use when writing, rewriting, or reviewing documentation, pull request titles, PR bodies, summaries, changelogs, or release notes for natural, clear, non-generic wording.
version: 1.0.0
autoTrigger: true
projectTypes: [android, kotlin]
---

# PracticalChristian Humanizer

## Use this when

- Writing or rewriting documentation.
- Drafting pull request titles or bodies.
- Improving implementation summaries, changelogs, or release notes.
- Removing robotic, generic, inflated, or overly polished wording.
- Making technical writing sound clear, direct, and human.

## Required sources

- Follow `docs/agentRules/documentationRules.md` for documentation changes.
- Follow `docs/agentRules/workflowRules.md` for implementation summaries.
- Follow `docs/agentRules/commitRules.md` when PR or commit language depends on repository history.
- Use `Agents.md` for the project-wide AI setup and writing expectations.

## Voice rules

- Sound like a thoughtful teammate, not a marketing page or AI assistant.
- Be specific about what changed, why it matters, and what was verified.
- Prefer plain language over buzzwords.
- Keep confidence calibrated; do not overclaim tests, behavior, performance, or security.
- Preserve technical accuracy, filenames, module names, and project terminology.
- Keep spiritual or product language respectful, grounded, and non-performative.

## Editing rules

- Cut filler such as "seamlessly," "robust," "comprehensive," "leverage," and "delve" unless it is genuinely the clearest word.
- Replace vague claims with concrete outcomes.
- Prefer active voice and short sentences.
- Keep bullets parallel and scannable.
- Do not add code snippets to markdown docs; link to source files instead.
- Do not invent user impact, metrics, tests, screenshots, or approvals.

## PR message shape

- Title: concise, action-oriented, and specific.
- Summary: one to three bullets focused on user or developer value.
- Test plan: list checks actually run, or state when checks were not run.
- Risks or follow-ups: include only meaningful caveats.

## Documentation shape

- Start with the reader's practical question or decision.
- Explain the smallest useful amount of context.
- Prefer durable guidance over implementation narration.
- Link to canonical rule or source files instead of duplicating long explanations.
- Keep docs crisp enough that a human would actually read them.
