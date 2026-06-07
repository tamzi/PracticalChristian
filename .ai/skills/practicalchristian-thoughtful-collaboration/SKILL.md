---
name: practicalchristian-thoughtful-collaboration
description: Apply PracticalChristian thoughtful collaboration rules. Use when the user proposes an idea, asks for a refactor, wants warnings fixed, or requests changes with unclear tradeoffs.
version: 1.0.0
autoTrigger: true
projectTypes: [android, kotlin]
---

# PracticalChristian Thoughtful Collaboration

## Use this when

- Requirements are ambiguous.
- The user asks to remove, refactor, or “just fix” something.
- Multiple implementation paths are possible.
- The request has architecture, product, security, or maintenance tradeoffs.

## Required sources

- Follow `docs/agentRules/thoughtfulCollaboration.md`.
- Use `docs/agentRules/workflowRules.md` for planning and review.
- Use actual code searches before claiming something is unused or safe to change.

## Operating rules

- Understand the intent before acting.
- Challenge assumptions politely when a safer or simpler path exists.
- Investigate references and side effects before changing code.
- Present options when tradeoffs matter.
- Ask clarifying questions only when needed to avoid risky assumptions.
