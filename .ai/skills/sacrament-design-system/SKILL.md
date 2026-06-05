---
name: sacrament-design-system
description: Apply Sacrament design system rules. Use when working in sacrament, sacrament-demo, Compose components, theme tokens, previews, or design-system enforcement.
version: 1.0.0
autoTrigger: true
projectTypes: [android, kotlin]
---

# Sacrament Design System

## Use this when

- Creating or changing Sacrament components.
- Updating theme tokens, colors, typography, spacing, or design primitives.
- Working in `sacrament` or `sacrament-demo`.
- Reviewing UI for design-system compliance.

## Required sources

- Use `docs/tech/sacrament/designSystem.md`.
- Use `docs/tech/sacrament/packageReference.md`.
- Check existing components under `sacrament/src/main/java/com/sacrament/ui/`.
- Follow `Agents.md` design-system boundaries.

## Operating rules

- Keep `sacrament` independent from app-specific code.
- Use Sacrament theme values instead of hardcoded colors or dimensions.
- Avoid Material components inside the design system unless the project rules explicitly allow them.
- Public composables need KDoc and previews.
- Validate accessibility basics, including semantics and touch targets.
