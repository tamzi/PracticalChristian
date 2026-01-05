# Contributing to PracticalChristian

Thank you for contributing to PracticalChristian! This guide will help you understand our development process and standards.

## Design System Components

When adding or modifying components in the `sacrament` design system module, follow this Definition of Done checklist:

### Definition of Done for Design System Components

- [ ] **Component Implementation**
  - [ ] Follows parameter order: required content → callbacks → variant → modifier last
  - [ ] Uses slot APIs for composition flexibility (when appropriate)
  - [ ] Co-locates Defaults/Tokens files with component
  - [ ] All variants, sizes, and intents are implemented

- [ ] **Previews**
  - [ ] Preview functions added using `PreviewTheme`
  - [ ] All variants shown in previews
  - [ ] Light and dark theme previews included
  - [ ] Uses `SampleModels` and `PreviewParameterProviders` for data

- [ ] **Testing**
  - [ ] Test tags added via `TestTags` constants
  - [ ] Unit tests for component logic (if applicable)
  - [ ] UI tests for interactive behavior (if applicable)

- [ ] **Accessibility**
  - [ ] Minimum 48dp touch targets (or documented exceptions)
  - [ ] Content descriptions for icons (or explicitly marked decorative)
  - [ ] Semantic roles where appropriate (button, switch, heading, etc.)
  - [ ] Focus order/keyboard navigation for complex widgets

- [ ] **Documentation**
  - [ ] KDoc comments with usage examples
  - [ ] Component added to catalog app (`:sacrament-demo`)
  - [ ] All variants/sizes/intents demonstrated in catalog

- [ ] **Code Quality**
  - [ ] Passes detekt checks
  - [ ] No Material3 dependencies (design system is Material-free)
  - [ ] Uses design system tokens (colors, spacing, typography, etc.)
  - [ ] Follows naming conventions (Sacrament prefix for public APIs)

## General Contribution Guidelines

1. **Commit Messages**: Follow the project's commit message format (see `docs/agentRules/commitRules.md`)
2. **Code Style**: Follow Kotlin style guide and project conventions
3. **Testing**: Add tests for new features and bug fixes
4. **Documentation**: Update relevant documentation when adding features

## Getting Started

1. Fork the repository
2. Create a feature branch
3. Make your changes following the guidelines above
4. Run `./gradlew detekt test` to ensure code quality
5. Submit a pull request

For more details, see:
- [Design System Guide](docs/tech/sacrament/designSystem.md)
- [Architecture Documentation](docs/architecture.md)
- [Coding Standards](docs/agentRules/codingStandards.md)

