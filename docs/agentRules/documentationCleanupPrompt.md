# Documentation Cleanup Prompt

Use this prompt to clean up documentation in other projects and establish the "no code snippets"
rule.

---

## Prompt for AI Assistant

```
I need you to clean up the markdown documentation files in this project. The documentation currently has several issues that make it hard to maintain:

1. **Code snippets duplicated in markdown files** - When the actual code changes, the documentation becomes outdated
2. **"Getting started" template language** - This is not a template project, it's an actual project
3. **Extensive command examples** - Making docs verbose and hard to navigate
4. **Redundant information** - Same concepts explained multiple times with code examples

## Task: Clean Up All Markdown Files

Please do the following:

### 1. Remove Code Snippets and Replace with References

**Search for and remove:**
- All ```kotlin, ```java, ```gradle code blocks
- Configuration examples in markdown files
- Plugin usage examples with code
- ViewModel/Repository/Component implementation examples
- Data class definitions
- Sealed interface examples
- Mapper function examples
- Test code examples

**Replace with:**
- References to actual source files (e.g., "See `app/build.gradle.kts`")
- Links to actual implementations (e.g., "See `feature/home/HomeViewModel.kt`")
- Directory references for multiple examples (e.g., "See `lockerKit/src/main/kotlin/components/`")

**Exception:** Keep shell commands (bash/zsh), configuration file content (YAML, TOML, properties), and directory structure diagrams.

### 2. Remove Template Language

**Remove phrases like:**
- "If you find this template helpful..."
- "This project can be used as a template for..."
- "Getting Started" sections with step-by-step code examples
- Extensive "Prerequisites" sections
- Detailed "Setup" instructions with code snippets

**Replace with:**
- Concise setup instructions without code
- Links to relevant documentation
- Brief bullet points

### 3. Simplify Command Examples

**Instead of:**
```bash
# Run all tests
./gradlew test

# Run tests for specific module
./gradlew :app:test
./gradlew :lockerKit:test

# Clean build
./gradlew clean
```

**Use:**

```
Run tests with `./gradlew test` or module-specific tests with `./gradlew :moduleName:test`
```

### 4. Consolidate Redundant Information

If the same concept is explained in multiple files with code examples:

- Keep one high-level explanation in the main file
- Link to actual implementations for details
- Remove duplicate explanations

### 5. Files to Clean (in priority order)

1. **Root level:** `README.md`, `architecture.md`, `contributing.md`
2. **Build docs:** `buildLogic/README.md`, `buildLogic/*.md`
3. **Main docs:** `docs/*.md`
4. **Agent rules:** `Agents.md` or similar
5. **Module READMEs:** Any `*/README.md` files

### 6. Create the Rule Document

After cleaning up, create or update `docs/agentRules/documentationRules.md` with this critical rule:

**Rule 7: No Code Snippets in Markdown - Link to Actual Code Instead**

This rule should specify:

- ❌ NO Kotlin/Java/Gradle code snippets in .md files
- ✅ Link to actual source files instead
- ✅ Allowed: shell commands, config file content, directory structures
- Why: Code in markdown becomes outdated, isn't type-checked, and duplicates the source of truth

Include examples of what NOT to do and what to do instead.

## Expected Results

After cleanup, documentation should:

- ✅ Be 30-50% shorter
- ✅ Have no duplicated code snippets
- ✅ Reference actual source files for code examples
- ✅ Be maintainable (when code changes, docs don't need updates)
- ✅ Focus on concepts and links, not implementation details
- ✅ Read like a guide/index to the codebase, not a duplication of it

## Verification

After cleanup, verify:

- [ ] No ```kotlin, ```java, ```gradle blocks remain (except in rule documentation showing what NOT
  to do)
- [ ] All code references point to actual files
- [ ] Template language is removed
- [ ] Command examples are concise
- [ ] Documentation rule is established
- [ ] Main files (README.md, CONTRIBUTING.md) are significantly shorter

## Report Format

After completing the cleanup, provide:

1. List of files modified with before/after line counts
2. Summary of changes made
3. The new rule document location
4. Verification checklist confirmation

```

---

## How to Use This Prompt

### For Your Other Projects:

1. **Copy the entire prompt above** (from "I need you to clean up..." to the end)
2. **Paste it into your AI assistant** (Claude, ChatGPT, etc.)
3. **Let it scan your project** and apply the cleanup
4. **Review the changes** before committing
5. **Establish the rule** in your project's agent guidelines

### Quick Adaptation:

If your project has different documentation structure, modify the "Files to Clean" section to match your project's layout.

### Post-Cleanup:

1. Add the rule to your project's contribution guidelines
2. Add it to any AI agent instruction files
3. Include a pre-commit check if possible
4. Update your documentation index to reflect the new structure

---

## Rule Template for Your Projects

Copy this rule into your project's documentation guidelines:

```markdown
## Rule: No Code Snippets in Documentation

**CRITICAL:** Do NOT duplicate code in `.md` files. Instead, reference actual source files.

### Why This Rule Exists

- Code examples in markdown become outdated when the real code changes
- They aren't type-checked by the compiler
- They create maintenance burden (two places to update)
- They duplicate the source of truth
- Linking to actual code ensures documentation stays accurate

### What's Forbidden

❌ Kotlin/Java code examples
❌ Gradle configuration examples  
❌ Plugin usage with code blocks
❌ Component/ViewModel/Repository implementations
❌ Data class definitions
❌ Test examples

### What's Allowed

✅ Shell commands (`./gradlew build`)
✅ Config file content (YAML, TOML, properties)
✅ Directory structure diagrams
✅ File path references
✅ Links to actual code

### Examples

**❌ Wrong:**
```markdown
## Example

\`\`\`kotlin
class MyViewModel @Inject constructor() : ViewModel() {
    // Implementation
}
\`\`\`
```

**✅ Correct:**

```markdown
## Example

See `feature/home/HomeViewModel.kt` for ViewModel implementation pattern.
```

### Enforcement

Before committing documentation:

- [ ] No code snippets in .md files
- [ ] All examples reference actual source files
- [ ] Links point to existing files

```