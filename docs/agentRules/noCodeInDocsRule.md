# Rule: No Code Snippets in Documentation

## The Golden Rule

**DO NOT duplicate code in `.md` files. Link to actual source files instead.**

---

## Why?

| Problem | Solution |
|---------|----------|
| ❌ Code in docs becomes outdated | ✅ Links always point to current code |
| ❌ Not type-checked or compiled | ✅ Actual code is verified by compiler |
| ❌ Maintenance burden (2 places to update) | ✅ Single source of truth |
| ❌ Duplicated content | ✅ Reference, don't duplicate |
| ❌ Docs drift from reality | ✅ Links ensure accuracy |

---

## What's Forbidden ❌

### NO Code Snippets

```kotlin
// ❌ DO NOT PUT THIS IN MARKDOWN
plugins {
    id("project.android.application")
}

// ❌ DO NOT PUT THIS IN MARKDOWN
@Composable
fun MyComponent() { }

// ❌ DO NOT PUT THIS IN MARKDOWN
class MyViewModel @Inject constructor() : ViewModel() { }
```

### NO These Examples

- Plugin configurations
- Component/Composable implementations
- ViewModel/Repository code
- Data class definitions
- Sealed interfaces
- Use cases
- Mapper functions
- Test examples
- Dependency injection setup
- Navigation code
- Any Kotlin/Java/Groovy code

---

## What's Allowed ✅

### YES Shell Commands

```bash
./gradlew build
git commit -m "message"
docker compose up
```

### YES Configuration Files

```yaml
# detekt-config.yml
complexity:
  maxComplexity: 15
```

```toml
# libs.versions.toml
[versions]
kotlin = "2.0.0"
```

### YES Directory Structures

```
project/
├── app/
├── core/
└── feature/
```

### YES File References

- See `app/build.gradle.kts`
- See `feature/home/HomeViewModel.kt`
- See `core/data/ArticleRepository.kt`

---

## Examples

### ❌ WRONG

```markdown
## ViewModel Pattern

Here's how to create a ViewModel:

\`\`\`kotlin
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val uiState: StateFlow<UiState> = repository
        .getData()
        .map { UiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState.Loading
        )
}
\`\`\`
```

### ✅ CORRECT

```markdown
## ViewModel Pattern

Feature modules follow the ViewModel pattern. See:
- `feature/home/HomeViewModel.kt` - Example ViewModel
- `feature/profile/ProfileViewModel.kt` - Another example
- `docs/architecture/patterns.md` - Detailed explanation
```

---

### ❌ WRONG

```markdown
## Build Configuration

Add these plugins to your module:

\`\`\`kotlin
plugins {
    id("project.android.library")
    id("project.android.library.compose")
    id("project.hilt")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(libs.androidx.compose.material3)
}
\`\`\`
```

### ✅ CORRECT

```markdown
## Build Configuration

See actual module build files for configuration:
- App module: `app/build.gradle.kts`
- Feature modules: `feature/*/build.gradle.kts`
- Core modules: `core/*/build.gradle.kts`
```

---

### ❌ WRONG

```markdown
## Component Example

\`\`\`kotlin
@Composable
fun Button(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(text)
    }
}
\`\`\`
```

### ✅ CORRECT

```markdown
## Component Example

See `sacrament/src/main/java/com/sacrament/ui/components/content/SacramentChip.kt` for a component implementation.
```

---

## Quick Reference Card

| Instead of... | Do this... |
|---------------|------------|
| Code snippets in docs | Link to actual files |
| `\`\`\`kotlin` blocks | `See path/to/file.kt` |
| Example implementations | Reference real implementations |
| Copy-paste code | Point to source |
| Duplicated code | Single source of truth |

---

## Enforcement Checklist

Before committing documentation:

- [ ] No `\`\`\`kotlin`, `\`\`\`java`, `\`\`\`gradle` blocks
- [ ] All code examples replaced with file references
- [ ] Links point to actual existing files
- [ ] Shell commands and config files are OK
- [ ] Documentation is concise and navigable

---

## For AI Agents

When writing documentation:

1. **NEVER** include code snippets
2. **ALWAYS** link to actual source files
3. **VERIFY** linked files exist
4. **KEEP** documentation concise
5. **FOCUS** on concepts, not implementation

---

## Benefits Summary

### Before This Rule

- 📚 Documentation is verbose (300-500+ lines)
- 🔄 Code changes require doc updates
- ⚠️ Docs drift from actual code
- 🐛 No compiler checks on doc examples
- 🔴 Maintenance burden

### After This Rule

- 📄 Documentation is concise (100-200 lines)
- ✅ Code changes don't affect docs
- 🎯 Docs always reference current code
- ✔️ Actual code is compiler-verified
- 🟢 Easy maintenance

---

## Implementation

### Step 1: Clean Up Existing Docs

1. Find all `\`\`\`kotlin`, `\`\`\`java`, `\`\`\`gradle` blocks
2. Replace with file references
3. Verify linked files exist
4. Remove redundant content

### Step 2: Establish the Rule

1. Add this rule to documentation guidelines
2. Add to contribution guidelines
3. Add to AI agent instructions
4. Add to code review checklist

### Step 3: Enforce

1. Review all documentation changes
2. Reject PRs with code snippets in docs
3. Suggest file references instead
4. Keep documentation maintainable

---

## Quick Copy-Paste Rule

```markdown
## Documentation Rule: No Code Snippets

❌ DO NOT put code snippets in markdown files
✅ Link to actual source files instead

**Why:** Code in docs becomes outdated. Links stay accurate.

**Examples:**
- ❌ `\`\`\`kotlin class MyClass { }\`\`\``
- ✅ `See src/main/kotlin/MyClass.kt`
```

---

## Share This

Share this document with:

- Your team
- Contributors
- AI assistants
- Other projects
- Code reviewers

Make it part of your:

- Contribution guidelines
- Documentation standards
- Code review process
- Onboarding materials

---

**Remember:** Documentation should guide you to the code, not duplicate it.

**The source code is the source of truth. Documentation should point to it.**
