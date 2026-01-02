# Coding Standards

> **For AI Agents:** Code style and quality standards for PracticalChristian

## Kotlin Style Guide

Follow the [Official Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
with PracticalChristian-specific additions.

## Naming Conventions

### Classes and Interfaces

| Type | Convention | Example |
|------|-----------|---------|
| **Class** | PascalCase | `ArticleRepository` |
| **Interface** | PascalCase | `NetworkDataSource` |
| **Data Class** | PascalCase | `Article`, `UserData` |
| **Sealed Interface** | PascalCase | `HomeUiState` |
| **Enum** | PascalCase | `ArticleCategory` |
| **Object** | PascalCase | `HomeRoute` |

### Functions and Properties

| Type | Convention | Example |
|------|-----------|---------|
| **Function** | camelCase | `getArticles()` |
| **Property** | camelCase | `val articleList` |
| **Private Property** | camelCase with `_` prefix | `private val _state` |
| **Const** | UPPER_SNAKE_CASE | `const val MAX_ITEMS = 10` |
| **Composable** | PascalCase | `@Composable fun HomeScreen()` |

### Packages

- All lowercase
- Use meaningful names
- Match directory structure

```
com.practicalchristian.app.feature.presentation
com.practicalchristian.app.core.data.repository
com.practicalchristian.app.core.localdatasource.dao
```

### Files

| Type | Convention | Example |
|------|-----------|---------|
| **Single Class** | Match class name | `Article.kt` |
| **Multiple Related** | Descriptive name | `Models.kt`, `Mappers.kt` |
| **Extensions** | `{Type}Extensions.kt` | `StringExtensions.kt` |
| **Constants** | `Constants.kt` | `NetworkConstants.kt` |

### Documentation Files

**All documentation must use camelCase naming, with one exception:**

| File Type               | Convention              | Example                              |
|-------------------------|-------------------------|--------------------------------------|
| **Documentation**       | camelCase with `.md`    | `modularizationPlan.md`              |
| **Implementation Logs** | camelCase               | `phase1CoreModules.md`               |
| **Guides**              | camelCase               | `quickStart.md`, `testingGuide.md`   |
| **README files**        | `README.md` (uppercase) | `README.md` (exception to camelCase) |

**Rules:**

- ✅ Use camelCase for all markdown files
- ✅ Start with lowercase letter
- ✅ Use descriptive names
- ✅ No spaces, use camelCase instead
- ✅ No underscores or hyphens
- ✅ **Exception:** README files must be `README.md` (all capitals)
- ❌ No PascalCase (except README.md)
- ❌ No snake_case
- ❌ No kebab-case
- ❌ **NEVER** use lowercase `readme.md` - always use `README.md`

**Examples:**

```
✅ Good
docs/modularizationPlan.md
docs/agentRules/codingStandards.md
docs/phase1/phase1CoreModules.md
docs/phase1/phase1.1/modelsAndCommon.md
docs/implementationLog/coverageSetup.md
docs/README.md                              ← Capital letters
feature/stories/README.md                   ← Capital letters

❌ Bad
docs/Modularization-Plan.md
docs/phase1Summary.md
docs/phase-1-core-modules.md
docs/PHASE1.md
docs/readme.md                              ← Wrong! Should be README.md
feature/stories/readme.md                   ← Wrong! Should be README.md
```

### Documentation Organization

**⚠️ CRITICAL RULE: All documentation must be in the `docs/` directory, except for module READMEs.**

#### Documentation Location Rules

| Document Type            | Location                      | Size Limit              | Purpose                     |
|--------------------------|-------------------------------|-------------------------|-----------------------------|
| **Module README**        | `{module}/README.md`          | **Small** (< 100 lines) | Quick module overview       |
| **Detailed Module Docs** | `docs/architecture/{module}/` | Any size                | Comprehensive documentation |
| **Architecture Docs**    | `docs/architecture/`          | Any size                | System design, patterns     |
| **Build Docs**           | `docs/buildLogic/`            | Any size                | Build configuration         |
| **Agent Rules**          | `docs/agents/`                | Any size                | AI agent guidelines         |
| **Implementation Logs**  | `docs/implementationLog/`     | Any size                | Historical records          |
| **Modularization**       | `docs/modularization/`        | Any size                | Module planning/tracking    |

#### Module README Rules

**Module READMEs must be SMALL and link to detailed docs:**

```markdown
# Feature: Stories

> Quick overview of the Stories feature module

## Purpose

Displays news articles in a full-screen story format (Instagram/Snapchat style).

## Key Components

- `StoriesHeadlinesScreen` - Main UI
- `StoriesHeadlinesViewModel` - State management
- `StoriesConfig` - Configuration

## Architecture

See [docs/architecture/stories/](../../docs/architecture/stories/) for:
- Detailed implementation
- Architecture decisions
- Testing strategy
- Performance considerations

## Quick Start

See `feature/presentation/src/.../navigation/` for navigation examples.

## Dependencies

- `core:model` - Data models
- `core:ui` - Common UI components
- `core:data` - Data layer

---


```

**Rules for Module READMEs:**

- ✅ **Keep under 100 lines** - Brief overview only
- ✅ **Link to detailed docs** in `docs/architecture/{module}/`
- ✅ **Quick start examples** - Show basic usage
- ✅ **Key components list** - What's in this module
- ✅ **Dependencies** - What does it depend on
- ❌ **NO detailed implementation** - Move to `docs/architecture/`
- ❌ **NO architecture diagrams** - Move to `docs/architecture/`
- ❌ **NO testing guides** - Move to `docs/architecture/`
- ❌ **NO long explanations** - Keep it concise

#### Detailed Documentation Structure

**When module README exceeds 100 lines, create:**

```
docs/architecture/{moduleName}/
├── README.md # Module architecture overview
├── implementation.md # Detailed implementation
├── testing.md # Testing strategy
├── apiReference.md # Public API documentation
└── decisions.md # Architecture decisions (ADRs)
```

**Note:** API documentation should be in KDoc comments in code, not in separate files.

**Example: Stories Feature Documentation**

```
feature/stories/
└── README.md # Small (< 100 lines) - Quick overview

docs/architecture/stories/
├── README.md # Architecture overview
├── storiesImplementation.md # Full implementation details
├── storiesTesting.md # Testing guide
├── storiesUiDesign.md # UI/UX design decisions
├── bannersIntegration.md # Banners integration details
└── performanceOptimization.md # Performance considerations
```

#### Directory Structure Rules

**Correct structure:**

```
project/
├── feature/
│   ├── stories/
│   │   ├── src/...
│   │   └── README.md ← SMALL (< 100 lines)
│   └── home/
│       ├── src/...
│       └── README.md ← SMALL (< 100 lines)
│
├── core/
│   ├── model/
│   │   ├── src/...
│   │   └── README.md ← SMALL (< 100 lines)
│   └── data/
│       ├── src/...
│       └── README.md ← SMALL (< 100 lines)
│
└── docs/
    ├── README.md ← Documentation index
    ├── architecture/
    │   ├── stories/ ← Detailed stories docs
    │   │   ├── README.md
    │   │   ├── implementation.md
    │   │   └── testing.md
    │   ├── home/ ← Detailed home docs
    │   │   ├── README.md
    │   │   └── implementation.md
    │   ├── model/ ← Detailed model docs
    │   │   └── README.md
    │   └── data/ ← Detailed data layer docs
    │       └── README.md
    ├── agentRules/
    ├── buildLogic/
    └── modularization/
```

**Wrong structure (DO NOT DO THIS):**

```
❌ feature/stories/
├── src/...
├── README.md
├── architecture.md ← Wrong! Move to docs/architecture/stories/
├── implementation.md ← Wrong! Move to docs/architecture/stories/
├── testingGuide.md ← Wrong! Move to docs/architecture/stories/
└── docs/ ← Wrong! All docs in root docs/
    └── design.md
```

#### When to Split Module README

**Split when README contains any of these:**

- ✅ Detailed implementation explanations (> 50 lines)
- ✅ Architecture diagrams or ASCII diagrams
- ✅ Complete testing guides
- ✅ Performance optimization details
- ✅ Migration guides
- ✅ Troubleshooting sections (> 20 lines)

**Note:** API reference documentation should use KDoc in code files, not separate markdown files.

**Keep in module README:**

- ✅ One-paragraph module purpose
- ✅ List of key components (5-10 items max)
- ✅ Quick start code snippet (< 20 lines)
- ✅ Dependencies list
- ✅ Link to detailed docs

#### Migration Process for Oversized READMEs

If a module README is too large:

1. **Create docs directory:**
   ```bash
   mkdir -p docs/architecture/{moduleName}
   ```

2. **Create new files in docs/architecture:**
    - `README.md` - Architecture overview
    - `implementation.md` - Move detailed implementation
    - `testing.md` - Move testing details
    - **Note:** API docs should be KDoc in code, not separate files

3. **Update module README:**
    - Keep only quick overview (< 100 lines)
    - Add links to detailed docs
    - Remove moved content

4. **Update cross-references:**
    - Update all links pointing to old content
    - Update docs/README.md index

**Example Migration:**

```markdown
<!-- Before: feature/stories/README.md (450 lines) -->
# Feature: Stories
[450 lines of detailed content...]

<!-- After: feature/stories/README.md (85 lines) -->
# Feature: Stories

Quick overview with links to:
- [Architecture](../../docs/architecture/stories/README.md)
- [Implementation](../../docs/architecture/stories/implementation.md)
- [Testing](../../docs/architecture/stories/testing.md)

<!-- New: docs/architecture/stories/implementation.md -->
# Stories Implementation

[Detailed implementation content moved here...]
```

### Directory Structure

- Directories should use camelCase (e.g., `agentRules/`, `buildLogic/`)
- Use lowercase for phase directories with numbers: `phase1/`, `phase2/`
- Use camelCase for subdirectories: `phase1.1/`, `phase1.2/`

**Example Structure:**

```
docs/
├── README.md                               ← Capital letters (exception)
├── modularizationPlan.md                  ← camelCase
├── architecture.md                         ← camelCase
├── agentRules/                             ← camelCase directory
│   ├── README.md                          ← Capital letters (exception)
│   ├── codingStandards.md                 ← camelCase
│   └── testingRules.md                    ← camelCase
└── phase1/                                 ← lowercase with number
    ├── phase1CoreModules.md               ← camelCase
    ├── phase1.1/                          ← camelCase subdir
    │   ├── modelsAndCommon.md            ← camelCase
    │   └── qualityReview.md              ← camelCase
    └── phase1.2/                          ← camelCase subdir
        └── networkLayer.md                ← camelCase
```

### Code Organization

### File Structure Order

```kotlin
// 1. Package declaration
package com.tamzi.changingroom.feature.home

// 2. Imports (grouped and sorted)
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

// 3. Constants (if any)
private const val MAX_ITEMS = 10

// 4. Public API
class HomeViewModel @Inject constructor() : ViewModel() {
    // Public properties first
    val uiState: StateFlow<HomeUiState> = ...
    
    // Public functions
    fun onAction(action: HomeAction) { }
    
    // Private properties
    private val _state = MutableStateFlow()
    
    // Private functions
    private fun handleClick() { }
}
```

### Import Ordering

1. Android/AndroidX imports
2. Third-party libraries
3. Kotlin stdlib
4. Project imports

```kotlin
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.StateFlow

import com.tamzi.changingroom.core.model.Article
import javax.inject.Inject
```

## Documentation

### KDoc Requirements

#### Public APIs

All public classes, functions, and properties **must** have KDoc:

```kotlin
/**
 * Repository for managing schedule data with offline-first approach.
 *
 * This repository coordinates between local and remote data sources,
 * ensuring data is always available offline and synced in the background.
 */
class OfflineFirstScheduleRepository @Inject constructor(
    private val localDataSource: ScheduleLocalDataSource,
    private val remoteDataSource: ScheduleRemoteDataSource,
) : ScheduleRepository {
    
    /**
     * Returns a flow of all schedules, filtered by optional date range.
     *
     * @param startDate Optional start date filter. If null, returns all schedules.
     * @param endDate Optional end date filter. If null, returns all schedules.
     * @return Flow of schedule list wrapped in Outcome, updates when data changes.
     */
    override fun getSchedules(
        startDate: Instant? = null,
        endDate: Instant? = null
    ): Flow<Outcome<List<Schedule>>> {
        // Implementation
    }
}
```

#### Internal/Private APIs

Private functions need comments only if logic is complex:

```kotlin
// Simple, self-explanatory - no comment needed
private fun clearCache() {
    cache.clear()
}

// Complex logic - add comment
/**
 * Merges network and cached articles, preferring network data
 * and falling back to cache if network request fails.
 */
private suspend fun mergeArticles(
    networkArticles: List<NetworkArticle>,
    cachedArticles: List<Article>,
): List<Article> {
    // Complex implementation
}
```

### Comment Style

```kotlin
// Single-line comment for brief explanations

/*
 * Multi-line comment for longer explanations
 * that span multiple lines.
 */

/**
 * KDoc comment for public APIs.
 *
 * @param parameter Description
 * @return Description
 */
```

## Code Quality

### Prefer Expressions Over Statements

```kotlin
// ✅ Do this
fun getStatus() = when {
    isLoading -> "Loading"
    hasError -> "Error"
    else -> "Success"
}

// ❌ Don't do this
fun getStatus(): String {
    if (isLoading) {
        return "Loading"
    } else if (hasError) {
        return "Error"
    } else {
        return "Success"
    }
}
```

### Use Meaningful Names

```kotlin
// ✅ Clear and descriptive
val scheduleRepository = OfflineFirstScheduleRepository()
fun calculateReadingProgress(chapters: List<Chapter>): Double

// ❌ Unclear or abbreviated
val repo = OfflineFirstScheduleRepository()
fun calc(l: List<Chapter>): Double
```

### Keep Functions Small

```kotlin
// ✅ Single responsibility
fun validateEmail(email: String): Boolean {
    return email.contains("@") && email.contains(".")
}

fun sendEmail(email: String, message: String) {
    if (validateEmail(email)) {
        // Send email
    }
}

// ❌ Doing too much
fun sendEmailWithValidation(email: String, message: String) {
    val isValid = email.contains("@") && email.contains(".")
    if (isValid) {
        // Send email
        // Log result
        // Update UI
        // Notify user
    }
}
```

### Avoid Nested Conditions

```kotlin
// ✅ Early returns
fun processNote(note: Note?): Outcome<Note> {
    if (note == null) return Outcome.Failure(DomainError.Validation("Note is null"))
    if (note.title.isEmpty()) return Outcome.Failure(DomainError.Validation("Title is empty"))
    if (note.content.isEmpty()) return Outcome.Failure(DomainError.Validation("Content is empty"))
    
    return Outcome.Success(note)
}

// ❌ Nested if statements
fun processNote(note: Note?): Outcome<Note> {
    if (note != null) {
        if (note.title.isNotEmpty()) {
            if (note.content.isNotEmpty()) {
                return Outcome.Success(note)
            } else {
                return Outcome.Failure(DomainError.Validation("Content is empty"))
            }
        } else {
            return Outcome.Failure(DomainError.Validation("Title is empty"))
        }
    } else {
        return Outcome.Failure(DomainError.Validation("Note is null"))
    }
}
```

## Kotlin Features

### Use Data Classes

```kotlin
// ✅ Immutable data with copy
data class Note(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Instant,
)

// ❌ Manual class with mutable properties
class Note(
    var id: String,
    var title: String,
    var content: String,
    var createdAt: Instant,
)
```

### Prefer Sealed Interfaces

```kotlin
// ✅ Exhaustive when expressions
sealed interface Outcome<out T> {
    data class Success<T>(val value: T) : Outcome<T>
    data class Failure(val error: DomainError) : Outcome<Nothing>
}

when (outcome) {
    is Outcome.Success -> // Handle success with outcome.value
    is Outcome.Failure -> // Handle error with outcome.error
}
```

### Use Extension Functions

```kotlin
// ✅ Extend existing types
fun String.isValidEmail(): Boolean {
    return contains("@") && contains(".")
}

// Usage
val email = "user@example.com"
if (email.isValidEmail()) { }
```

### Prefer Immutability

```kotlin
// ✅ Immutable
val items = listOf("a", "b", "c")
val newItems = items + "d"

// ❌ Mutable
var items = mutableListOf("a", "b", "c")
items.add("d")
```

## Compose Specifics

### Composable Naming

```kotlin
// ✅ PascalCase, like classes
@Composable
fun SchedulesScreen() { }

@Composable
fun NoteCard() { }

// ❌ camelCase
@Composable
fun schedulesScreen() { }
```

### State Hoisting

```kotlin
// ✅ Hoist state to caller
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    TextField(value = query, onValueChange = onQueryChange)
}

// ❌ Internal state
@Composable
fun SearchBar() {
    var query by remember { mutableStateOf("") }
    TextField(value = query, onValueChange = { query = it })
}
```

### Remember Complex Calculations

```kotlin
// ✅ Remember expensive calculations
@Composable
fun ScheduleList(schedules: List<Schedule>) {
    val sortedSchedules = remember(schedules) {
        schedules.sortedBy { it.date }
    }
}

// ❌ Recalculate on every recomposition
@Composable
fun ScheduleList(schedules: List<Schedule>) {
    val sortedSchedules = schedules.sortedBy { it.date }
}
```

## Error Handling

### Use Outcome Type

```kotlin
sealed interface Outcome<out T> {
    data class Success<T>(val value: T) : Outcome<T>
    data class Failure(val error: DomainError) : Outcome<Nothing>
}

suspend fun getSchedules(): Outcome<List<Schedule>> = safeOutcome {
    localDataSource.getSchedules()
}

// safeOutcome helper automatically catches and maps exceptions
suspend fun <T> safeOutcome(block: suspend () -> T): Outcome<T> {
    return try {
        Outcome.Success(value = block())
    } catch (e: Exception) {
        Outcome.Failure(error = ErrorMapper.mapToDomainError(e))
    }
}
```

### Specific Exceptions

```kotlin
// ✅ Specific exception types
class NetworkException(message: String) : Exception(message)
class DatabaseException(message: String) : Exception(message)

// ❌ Generic exceptions
throw Exception("Something went wrong")
```

## Performance

### Avoid Premature Optimization

- Write clear code first
- Profile before optimizing
- Optimize hot paths only

### Use Lazy Initialization

```kotlin
// ✅ Lazy for expensive operations
class Repository {
    private val expensiveObject by lazy {
        createExpensiveObject()
    }
}
```

### Use Sequences for Large Collections

```kotlin
// ✅ Lazy evaluation with sequences
val result = list
    .asSequence()
    .map { it.process() }
    .filter { it.isValid() }
    .toList()
```

## Best Practices Summary

### Do ✅

- Use `val` over `var` when possible
- Use data classes for data containers
- Use sealed interfaces for state
- Write KDoc for public APIs
- Keep functions small and focused
- Use meaningful variable names
- Prefer expressions over statements
- Use early returns to reduce nesting
- Use extension functions appropriately
- Write tests for all public APIs

### Don't ❌

- Use mutable collections in public APIs
- Create God classes or functions
- Use magic numbers (use named constants)
- Ignore null safety
- Suppress warnings without good reason
- Use `!!` operator (use safe calls instead)
- Nest conditions deeply
- Leave TODO comments in production code
- Use platform types (add explicit nullability)
- Ignore linter warnings

## Formatting

Formatting is automatically handled by:

- **Kotlin Gradle Plugin** - Built-in formatting
- **Detekt** - Code quality and style checks

Run before committing:

```bash
./gradlew detekt
```

## Code Review Checklist

- [ ] All public APIs have KDoc
- [ ] No compiler warnings
- [ ] No linter errors (Detekt passes)
- [ ] Follows naming conventions
- [ ] No hardcoded strings (use resources)
- [ ] No magic numbers (use constants)
- [ ] Error handling in place
- [ ] Tests written and passing
- [ ] No unused imports
- [ ] Functions are small and focused

---

## See also

- [Official Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- [Jetpack Compose Guidelines](https://developer.android.com/jetpack/compose/philosophy)
- [Testing Rules](testingRules.md)
