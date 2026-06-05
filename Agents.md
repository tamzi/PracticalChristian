# AI Agent Rules & Guidelines for PracticalChristian

> **Purpose:** Centralized rules and guidelines for AI agents working on the PracticalChristian project

---

## ⚠️ CRITICAL: Read the Detailed Rules

**This document is a QUICK REFERENCE ONLY.**

## AI Setup Structure

Use this stable split for project AI guidance:

- **`Agents.md`**: Quick project-wide AI entry point.
- **`.ai/skills/`**: Tool-agnostic, task-triggerable skill wrappers.
- **`.ai/agents/`**: Tool-agnostic specialist agent and subagent personas.
- **`.firebender/skills/`**: Firebender team-skill adapters that point to `.ai/skills/`.
- **`firebender.json`**: Firebender agent registration for `.ai/agents/`.
- **`docs/agentRules/`**: Canonical long-form rulebook and rationale.

Do **not** delete or move `docs/agentRules/` unless doing a deliberate migration that updates all
references in `.ai/`, project docs, and git hook scripts.

For comprehensive, enforceable rules that you **MUST** follow, see:

**[`docs/agentRules/`](docs/agentRules/README.md)** - Detailed agent rules directory

**Start here:**

- **[docs/agentRules/README.md](docs/agentRules/README.md)** - Complete index and navigation
- **[docs/agentRules/commitRules.md](docs/agentRules/commitRules.md)** - MANDATORY pre-commit
  checklist
- **[docs/agentRules/documentationRules.md](docs/agentRules/documentationRules.md)** - **CRITICAL:**
  Documentation requirements including **NO CODE SNIPPETS IN MARKDOWN** (Rule 7)

**The detailed rules include:**

- ✅ Specific checklists you MUST follow before every commit
- ✅ Architecture patterns (links to actual code, not duplicated examples)
- ✅ Testing requirements and utilities
- ✅ Common mistakes and how to avoid them
- ✅ Enforcement criteria for all standards
- ⚠️ **Rule 7: NO code snippets in .md files - link to actual code instead**

---

## Overview

This document provides essential context, conventions, and guidelines for AI agents (like Codex, Claude,
GitHub Copilot, etc.) contributing to the PracticalChristian codebase. PracticalChristian is a Bible
meditations app for Android, designed to help users build consistent, Scripture-centered habits.

## Project Context

### What is PracticalChristian?

PracticalChristian is a Bible meditations app for Android focused on daily Scripture engagement and
spiritual growth. It blends guided devotionals, reading plans, prayer, journaling, and community
features to help users build consistent habits around the Bible with a privacy-first mindset.

**App Concept:**
- Daily devotionals and Scripture meditation content
- Reading plans, schedules, and reminders
- Notes, journaling, and tags for reflection
- Prayer and community features (groups/circles)
- Audio and offline-first access to core content

**Technical Characteristics:**

- Multi-module architecture with convention plugins
- Clean Architecture with core and feature modules
- Sacrament design system library (`sacrament`)
- Sacrament demo application (`sacrament-demo`) showcasing components
- Modern Android development with Compose UI + Foundation and a Material-free design system
- Built with Gradle Kotlin DSL and convention plugins

### Tech Stack

| Category         | Technology          | Notes                       |
|------------------|---------------------|-----------------------------|
| **Language**     | Kotlin              | Primary language            |
| **UI Framework** | Jetpack Compose     | Foundation                  |
| **Build System** | Gradle (Kotlin DSL) | With convention plugins     |
| **DI**           | Hilt (Dagger)       | Dependency injection        |
| **Database**     | Room                | Offline-first persistence   |
| **Networking**   | Retrofit + OkHttp   | Remote API integration      |
| **Async**        | Coroutines + Flow   | Async and reactive streams  |
| **Quality**      | Detekt              | Code quality and formatting |
| **Testing**      | JUnit, Roborazzi    | Unit and screenshot tests   |

### SDK Versions

| Configuration   | Version | Android Version |
|-----------------|---------|-----------------|
| **Min SDK**     | 33      | Android 13      |
| **Target SDK**  | 36      | Android 15      |
| **Compile SDK** | 36      | Android 15      |
| **Java**        | 21      | Java 21         |
| **Kotlin JVM**  | 21      | Target JVM      |

**Important:** Uses Java 21 - must be configured in Android Studio:

- Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK > 21

## Module Structure

```
PracticalChristian/
├── app/                    # Main application entry point
├── core/                   # Shared domain/data layers and infrastructure
├── feature/                # Feature modules (Compose UI + presentation)
├── content/                # App content modules (data-only)
├── sacrament/              # Sacrament design system library
├── sacrament-demo/         # Sacrament design system showcase application
├── buildLogic/             # Convention plugins for build configuration
│   └── convention/         # Convention plugin implementations
├── sync/                   # Work manager / sync tooling
└── docs/                   # Documentation
    └── agentRules/         # Detailed AI agent rules
```

### Module Responsibilities

**app:**

- Main application entry point
- Integrates core, feature, and content modules
- Single Activity architecture with Compose
- Uses Hilt for dependency injection

**core:**

- Clean Architecture foundations (data, domain, common, and infra)
- Shared utilities, data sources, and repositories

**feature:**

- Feature-specific UI and presentation logic
- Compose screens, ViewModels, and navigation

**content:**

- Data-only modules for Bible books, meditations, plans, prayers, themes, and audio

**sacrament (design system):**

- Core design system library
- Shared Compose components, patterns, and primitives
- Theme, typography, and tokens
- Exported components for consumption by apps

**sacrament-demo:**

- Component catalog and showcase
- Interactive component explorer
- Design system documentation
- Testing ground for new components

**buildLogic:**

- Convention plugins for consistent build configuration
- Shared build logic across modules
- Gradle configuration reusability

**sync:**

- Background work and synchronization tooling

## Architecture Principles

### 1. Clean Architecture + MVVM

- Domain, data, and presentation layers are separated
- Feature modules own their UI and presentation logic
- Core modules provide shared infrastructure

### 2. Compose-First

- All UI built with Jetpack Compose
- No XML layouts
- Composable functions for all components
- Preview annotations for design-time rendering

### 3. Convention Plugins

Build configuration is managed through convention plugins:

- `PracticalChristian.android.application` - Application module setup
- `PracticalChristian.android.library` - Library module setup
- `PracticalChristian.android.library.compose` - Compose library configuration
- `PracticalChristian.hilt` - Hilt dependency injection setup
- `PracticalChristian.android.lint` - Lint configuration

### 4. Design System Boundaries

- Design system (`sacrament`) is independent and reusable
- Demo app depends on design system, not vice versa
- Theme and styling centralized in sacrament
- Clear module boundaries

## Development Guidelines

UI and design-system rules live in `docs/tech/sacrament/designSystem.md` and
`docs/tech/sacrament/packageReference.md`.

### 1. Coding Standards

**Kotlin Style:**

- Follow official Kotlin coding conventions
- Use Detekt for code analysis and formatting (configured in project)
- Write clear, concise code comments
- Prefer immutable data structures

**Naming Conventions:**

- Composables: PascalCase (e.g., `ButtonPrimary`, `CardHeader`)
- Functions: camelCase (e.g., `calculateSpacing`, `formatText`)
- Constants: UPPER_SNAKE_CASE (e.g., `MAX_ITEMS`, `DEFAULT_TIMEOUT`)
- Files: Match primary type name (e.g., `ButtonPrimary.kt`)

**Documentation:**

- Public APIs must include KDoc
- Refer to existing components for KDoc style, for example:
  `sacrament/src/main/java/com/sacrament/ui/components/action/SacramentButton.kt`

### 2. Component Development

**Creating New Components:**

1. **Determine scope:** Primitive, component, or pattern
2. **Place in correct directory:**
   `sacrament/src/main/java/com/sacrament/ui/{primitives|components|patterns}/`
3. **Follow naming pattern:** Descriptive + Sacrament prefix (e.g., `SacramentButton`)
4. **Include previews:** Add `@Preview` annotations for design-time rendering
5. **Document parameters:** Use KDoc for all public composables
6. **Make configurable:** Use `Modifier` parameter for flexibility

See existing components in `sacrament/` for implementation patterns.

### 3. Theme and Styling

**Theme Usage:**

- All components should use the Sacrament theme and tokens

**Consistent Styling:**

- Don't hardcode colors - use theme colors
- Don't hardcode dimensions - use theme spacing

### 4. Testing

- Place unit tests in `src/test/java`
- Every component needs `@Preview` annotations
- Include multiple previews for different states

See `docs/agentRules/testingRules.md` for comprehensive testing guidelines.

## Commit Guidelines

See `docs/agentRules/commitRules.md` for comprehensive commit message format and rules.

## Quality Standards

### Code Quality

- **Detekt:** Static code analysis must pass
- **Detekt:** Code formatting must be consistent
- **No warnings:** Address all compiler warnings
- **No hardcoded values:** Use constants, theme values, or resources

### Performance

- **Compose efficiency:** Avoid unnecessary recomposition
- **Use remember:** Cache computed values appropriately
- **Stable parameters:** Mark data classes with `@Immutable` or `@Stable` when appropriate
- **Lazy composition:** Use `LazyColumn`, `LazyRow` for lists

### Accessibility

- **Content descriptions:** Provide for all interactive elements
- **Semantic properties:** Use appropriate semantics modifiers
- **Touch targets:** Minimum 48dp for interactive elements
- **Color contrast:** Ensure sufficient contrast ratios

## Project-Specific Patterns

See existing components in `sacrament/` for:

- Theme access patterns
- Modifier parameter usage
- Preview patterns
- State management

Refer to `docs/agentRules/codingStandards.md` for comprehensive coding patterns.

## Documentation Standards

### 1. Code Documentation

- **Public APIs:** Must have KDoc comments
- **Complex logic:** Inline comments explaining why, not what
- **TODOs:** Include issue reference or explanation

### 2. Component Documentation

Each component should document:

- Purpose and use case
- Parameters and their effects
- Any special considerations
- Example usage in KDoc (if complex)

### 3. Markdown Documentation

- Use camelCase for markdown files (e.g., `agentRules.md`)
- Include table of contents for long documents
- Use code blocks with language specification for allowed blocks (commands/config)
- Keep line length reasonable (80-120 chars)

## Important Files and Directories

```
PracticalChristian/
├── gradle/libs.versions.toml    # Version catalog for dependencies
├── build.gradle.kts              # Root build configuration
├── settings.gradle.kts           # Module and repository configuration
├── buildLogic/                   # Convention plugin source code
├── docs/agentRules/              # Detailed agent rules (reference this!)
│   ├── projectOverview.md        # Detailed project context
│   ├── codingStandards.md        # Comprehensive coding standards
│   ├── commitRules.md            # Git commit guidelines
│   └── ...                       # Other specific rule files
└── scripts/                      # Git hooks and automation scripts
```

## Related Documentation

⚠️ **IMPORTANT:** This document provides a high-level overview. For detailed, comprehensive rules
that **MUST** be followed, see the `docs/agentRules/` directory:

### Essential Reading (READ THESE FIRST!)

- **[README.md](docs/agentRules/README.md)** - Index and quick reference for all agent rules
- **[workflowRules.md](docs/agentRules/workflowRules.md)** - **CRITICAL:** Work planning,
  self-review, implementation summaries, library versions, and documentation preferences
- **[commitRules.md](docs/agentRules/commitRules.md)** - **CRITICAL:** Git commit message
  guidelines, pre-commit checklist, and atomic commit rules
- **[documentationRules.md](docs/agentRules/documentationRules.md)** - **CRITICAL:** Documentation
  location, size limits, and naming conventions

### Product & Design System

- **[docs/product/prd.md](docs/product/prd.md)** - Product requirements and vision
- **[docs/product/featureList.md](docs/product/featureList.md)** - Feature inventory
- **[docs/tech/sacrament/designSystem.md](docs/tech/sacrament/designSystem.md)** - Sacrament design system guide
- **[docs/tech/sacrament/packageReference.md](docs/tech/sacrament/packageReference.md)** - Sacrament package reference

### Architecture & Design

- **[projectOverview.md](docs/agentRules/projectOverview.md)** - Detailed project information, tech
  stack, and SDK versions
- **[architectureRules.md](docs/agentRules/architectureRules.md)** - Clean Architecture layers,
  module dependencies, and data flow patterns

### Development Standards

- **[codingStandards.md](docs/agentRules/codingStandards.md)** - Comprehensive Kotlin code style
  guide, naming conventions, and documentation requirements
- **[testingRules.md](docs/agentRules/testingRules.md)** - Testing frameworks, patterns, coverage
  requirements (70%+), and test utilities
- **[featureDevelopmentRules.md](docs/agentRules/featureDevelopmentRules.md)** - Feature module
  structure, ViewModel patterns, navigation, and state management

### Why These Rules Matter

These detailed rules provide:

1. **Specific enforcement criteria** - Clear do's and don'ts with examples
2. **Pre-commit checklists** - Mandatory steps before creating any commit
3. **Architecture patterns** - Proven patterns for Clean Architecture implementation
4. **Testing strategies** - Comprehensive testing approaches with code examples
5. **Common mistakes** - Anti-patterns to avoid with corrections

**Before making any commit, you MUST:**

- Review the relevant sections in `docs/agentRules/`
- Follow the pre-commit checklist in `commitRules.md`
- Verify documentation compliance per `documentationRules.md`
- Ensure code quality per `codingStandards.md`
- Check test coverage per `testingRules.md`

## Quick Reference Checklist

Before completing any task, verify:

### Work Planning & Review (see workflowRules.md)

- [ ] Discussion before implementation (for questions/suggestions)
- [ ] Work planned before execution
- [ ] Self-review checklist completed
- [ ] Implementation summary provided
- [ ] Only stable library versions (no alpha/beta)
- [ ] Inline documentation preferred over README code snippets

### Code Quality

- [ ] Code follows Kotlin coding conventions
- [ ] Detekt formatting is applied (`./gradlew detekt --auto-correct`)
- [ ] Detekt checks pass (`./gradlew detekt`)
- [ ] Tests are written and passing (`./gradlew test`)
- [ ] Components have `@Preview` annotations
- [ ] Public APIs have KDoc documentation
- [ ] Theme values used instead of hardcoded values

### Commit Standards
- [ ] Commit message follows conventional format
- [ ] Changes are in correct module (sacrament vs app vs sacrament-demo)
- [ ] No TODO comments without explanation or issue reference

## Getting Help

When uncertain about:

- **Architecture decisions:** Check `docs/agentRules/architectureRules.md`
- **Code style:** Check `docs/agentRules/codingStandards.md`
- **Component patterns:** Look at existing components in `sacrament/`
- **Build setup:** Check convention plugins in `buildLogic/`
- **Testing:** Check `docs/agentRules/testingRules.md`

## Project Philosophy

1. **Reusability First** - Components should be flexible and reusable
2. **Consistency** - Follow established patterns and conventions
3. **Quality** - Never compromise on code quality or testing
4. **Documentation** - Well-documented code is maintainable code
5. **Simplicity** - Prefer simple, clear solutions over clever ones
6. **Performance** - Write efficient Compose code
7. **Accessibility** - Build inclusive user interfaces

## Notes for AI Agents

- This project uses **convention plugins** extensively - check `buildLogic/` before adding plugins
- The design system (`sacrament`) should remain **independent** - no app-specific code
- Use Sacrament primitives/components; avoid Material components in `sacrament`
- Follow Sacrament component organization patterns
- **Preview every component** - previews are essential for development
- **Document public APIs** - sacrament is a library, documentation is critical
- When in doubt, **look at existing code** for patterns and conventions

---

**Repository:** [PracticalChristian on GitHub](https://github.com/tamzi/PracticalChristian)
