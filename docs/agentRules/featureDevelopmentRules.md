# Feature Development Rules

> **For AI Agents:** Guidelines for building features in PracticalChristian

## Feature Module Structure

### Module Organization

```
feature/presentation/
├── build.gradle.kts
└── src/
    ├── main/
    │   └── kotlin/com/practicalchristian/app/feature/presentation/
    │       ├── features/
    │       │   ├── schedules/
    │       │   │   ├── SchedulesScreen.kt
    │       │   │   ├── SchedulesScreenModel.kt
    │       │   │   └── SchedulesUiState.kt
    │       │   ├── notes/
    │       │   │   ├── NotesScreen.kt
    │       │   │   ├── NotesScreenModel.kt
    │       │   │   └── NotesUiState.kt
    │       │   └── settings/
    │       │       ├── SettingsScreen.kt
    │       │       ├── SettingsScreenModel.kt
    │       │       └── SettingsUiState.kt
    │       └── navigation/
    │           └── NavigationHost.kt
    └── test/
        └── kotlin/com/practicalchristian/app/feature/presentation/
            └── features/
                └── schedules/
                    └── SchedulesScreenModelTest.kt
```

### Build Configuration

See `feature/presentation/build.gradle.kts` for feature module build configuration. Feature modules use:

- `practicalchristian.android.feature` plugin - Provides Compose, Hilt, Lifecycle, Testing
- Dependencies on `core:domain`, `core:common`, `sacrament`
- Navigation Compose libraries

## ViewModel Pattern (Hilt)

### Structure

- Use standard `@HiltViewModel` with `ViewModel` for state management
- Use `@Inject` constructor for dependency injection
- Expose UI state as `StateFlow`
- Use `viewModelScope` for coroutines
- Map domain `Outcome<T>` to UI `ItemState<T>`
- Handle user intents/actions

### Template

**See ViewModel implementations:**

- Pattern example: `feature/presentation/src/.../schedules/SchedulesViewModel.kt`
- State management: Review any ViewModel in `feature/presentation/src/.../` for patterns
- Error handling: Check how ViewModels map Outcome to ItemState in actual implementations

## UI State Management

### State Definition

**UI state management with data classes. See:**

- State example: `feature/presentation/src/.../schedules/SchedulesUiState.kt`
- ItemState definition: `feature/presentation/src/.../ItemState.kt`
- State patterns: Review any `*UiState.kt` file in `feature/presentation/src/.../`

### Screen Implementation

**Screen composables integrate ViewModels with UI. See:**

- Screen example: `feature/presentation/src/.../schedules/SchedulesScreen.kt`
- State handling: Review how screens handle ItemState in any `*Screen.kt` file
- Content separation: Check how screens separate stateful and stateless composables

## Navigation with Navigation Compose

### Navigation Patterns

**Navigation Compose handles type-safe navigation. See:**

- Navigation setup: `feature/presentation/src/.../navigation/PracticalChristianNavHost.kt`
- Destinations: Review navigation definitions in the navigation package
- Screen integration: Check how screens integrate with Navigation Compose

## Dependency Injection

### Dependency Injection

**Hilt provides ViewModels with @HiltViewModel. See:**

- ViewModel injection: `feature/presentation/src/.../*ViewModel.kt`
- Screen integration: Any `*Screen.kt` file shows hiltViewModel() usage

## State Collection

### State Collection

**Always use `collectAsStateWithLifecycle()` for lifecycle-aware collection.**

- ❌ Don't use `collectAsState()` - not lifecycle-aware
- ✅ Use `collectAsStateWithLifecycle()` - respects lifecycle
- See any Screen.kt file in `feature/presentation/src/.../` for examples

## Feature Module Dependencies

### What Feature Modules Can Depend On

✅ **Allowed:**

- `core:domain` - Use cases, Outcome<T>
- `core:common` - Utilities
- `sacrament` - Design system
- Navigation Compose libraries - Navigation

❌ **Not Allowed:**

- `core:data` directly (use `core:domain` instead)
- `core:datasource:local` directly
- `core:datasource:remote` directly

### Dependency Declaration

See `feature/presentation/build.gradle.kts` for proper dependency setup:

- Domain layer: `core:domain`, `core:common`
- Design system: `sacrament`
- Navigation: Navigation Compose libraries

## Design System Usage

### Design System Rules

**All UI must use Sacrament design system components exclusively.**

✅ **Required:**
- Use `SacramentTheme` for all previews and screens
- Use Sacrament components (`SacramentButton`, `SacramentCard`, `SacramentTextField`, etc.)
- Use design system tokens (`SacramentTheme.colors`, `SacramentTheme.spacing`, etc.)
- Use `SacramentScreenScaffold` for screen layouts

❌ **Prohibited:**
- **No Material3 components** (`androidx.compose.material3.*`) outside `sacrament` module
- **No hardcoded colors** (`Color(0x...)`) outside `sacrament` module
- **No MaterialTheme** - use `SacramentTheme` instead
- **No Material icons** - use `androidx.compose.material.icons.*` (allowed)

### Component Migration

When building new features or updating existing ones:
1. Use Sacrament components from `com.sacrament.ui.components.*`
2. Use Sacrament primitives from `com.sacrament.ui.primitives.*`
3. Use Sacrament patterns from `com.sacrament.ui.patterns.*`
4. Reference design system guide: `docs/tech/sacrament/designSystem.md`

### Preview Requirements

All `@Preview` functions must wrap content with `SacramentTheme`:
```kotlin
@Preview
@Composable
fun MyScreenPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        MyScreenContent(...)
    }
}
```

## Best Practices

### Do ✅

- **Use @HiltViewModel for state management** - Standard Hilt pattern
- **Separate Screen and Content composables** - Screen handles ViewModel
- **Use Hilt for all dependencies** - No manual injection
- **Map Outcome<T> to ItemState<T>** - Keep domain pure
- **Handle loading and error states** - Always show feedback
- **Use collectAsStateWithLifecycle** - Lifecycle-aware collection
- **Keep ViewModels testable** - Inject use cases, not repositories
- **Write ViewModel tests** - 80%+ coverage target
- **Use Navigation Compose destinations** - Type-safe navigation
- **Set Loading state BEFORE repository calls** - Clear UI feedback

### Don't ❌

- **Don't put business logic in ViewModels** - That's for use cases
- **Don't access repositories directly** - Use domain layer
- **Don't use Context in ViewModels** - Pass resources via use cases
- **Don't create God ViewModels** - Split into multiple if needed
- **Don't forget error handling** - Every network call can fail
- **Don't use collectAsState** - Use lifecycle-aware version
- **Don't skip testing** - ScreenModels are critical to test
- **Don't put Loading/Empty in domain layer** - These are UI states

## Common Patterns

### Common Patterns

**Standard patterns for ViewModels. See implementations in `feature/presentation/src/.../`:**

- Loading → Success → Error: Check how ViewModels handle state transitions
- Combining flows: Review ViewModels that use `combine()` for multiple sources
- User actions: See how ViewModels handle user interactions and trigger use cases

## File Naming Conventions

| Type | Convention | Example |
|------|-----------|---------|
| **Screen** | `{Feature}Screen.kt` | `SchedulesScreen.kt` |
| **ScreenModel** | `{Feature}ScreenModel.kt` | `SchedulesScreenModel.kt` |
| **UI State** | `{Feature}UiState.kt` | `SchedulesUiState.kt` |
| **Tab** | `{Feature}Tab.kt` | `HomeTab.kt` |
| **Tests** | `{Feature}ScreenModelTest.kt` | `SchedulesScreenModelTest.kt` |

## Quick Reference

### Minimal Feature Checklist

- [ ] Created Screen composable function
- [ ] Defined UI state data class
- [ ] Created ViewModel with @HiltViewModel annotation
- [ ] Implemented Content composable (separate from Screen)
- [ ] Added hiltViewModel() integration
- [ ] Setup navigation integration with AppDestination
- [ ] Wrote ViewModel tests
- [ ] Verified coverage > 70%

## Related Documentation

- [Architecture Rules](./architectureRules.md)
- [Testing Rules](./testingRules.md)
- [Coding Standards](./codingStandards.md)
