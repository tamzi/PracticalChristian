# Architecture Rules

> **For AI Agents:** Architecture patterns and module dependency rules for PracticalChristian

## Clean Architecture Layers

PracticalChristian follows Clean Architecture with three main layers:

```
UI Layer (Presentation)
       ↓
Domain Layer (Business Logic)
       ↓
Data Layer (Data Sources)
```

### Layer Responsibilities

| Layer | Responsibility | Can Depend On |
|-------|---------------|---------------|
| **UI** | ScreenModels, Screens, Navigation | Domain, Common |
| **Domain** | Use Cases, Business Logic, Outcome<T> | Common |
| **Data** | Repositories, Data Sources | Domain, Common, LocalDataSource, RemoteDataSource |

### Dependency Direction

- **UI → Domain → Data** (allowed)
- **Data → UI** (❌ not allowed)
- **Domain → UI** (❌ not allowed)
- **Domain → Data** (❌ not allowed)

## Module Structure

### Module Categories

```
PracticalChristian/
├── app/                          # Application entry point
├── feature/                      # Feature modules (UI layer)
│   └── presentation/             # All UI screens and ScreenModels
├── core/                         # Core infrastructure
│   ├── domain/                   # Use cases, Outcome<T> (Domain layer)
│   ├── data/                     # Repositories (Data layer)
│   ├── localdatasource/          # Room database (Data layer)
│   ├── remotedatasource/         # API clients (Data layer)
│   └── common/                   # Utilities (All layers)
└── sacrament/                    # Design system
```

## Module Dependency Rules

### Feature Module Dependencies

✅ **Allowed Dependencies:**

```
feature:presentation
    ├── core:domain        (use cases, Outcome<T>)
    ├── core:common        (utilities)
    └── sacrament          (design system)
```

❌ **Forbidden Dependencies:**

```
feature:presentation
    ├── core:data          ❌ Use domain layer instead
    ├── core:datasource:local   ❌ Use domain layer instead
    └── core:datasource:remote  ❌ Use domain layer instead
```

### Core Module Dependencies

#### core:domain (Use Cases, Outcome<T>)

✅ **Allowed:**

```
core:domain
    └── core:common        (utilities)
```

❌ **Forbidden:**

```
core:domain
    ├── feature:*          ❌ Domain doesn't know about UI
    ├── core:data          ❌ Wrong direction
    ├── core:datasource:local   ��� Wrong direction
    └── core:datasource:remote  ❌ Wrong direction
```

#### core:data (Repositories)

✅ **Allowed:**

```
core:data
    ├── core:domain            (domain models, Outcome<T>)
    ├── core:datasource:local   (Room DAOs)
    ├── core:datasource:remote  (API clients)
    └── core:common            (utilities)
```

❌ **Forbidden:**

```
core:data
    └── feature:*          ❌ Data doesn't know about UI
```

#### core:datasource:local (Room Database)

✅ **Allowed:**

```
core:datasource:local
    └── core:common        (utilities)
```

❌ **Forbidden:**

```
core:datasource:local
    ├── core:data          ❌ Wrong direction
    ├── core:domain        ❌ Database doesn't know about business logic
    └── feature:*          ❌ Database doesn't know about UI
```

#### core:datasource:remote (API Clients)

✅ **Allowed:**

```
core:datasource:remote
    └── core:common        (utilities)
```

❌ **Forbidden:**

```
core:datasource:remote
    ├── core:data          ❌ Wrong direction
    ├── core:domain        ❌ Network doesn't know about business logic
    └── feature:*          ❌ Network doesn't know about UI
```

### App Module Dependencies

✅ **Allowed:**

```
app
    ├── feature:presentation   (UI screens)
    ├── core:common            (utilities)
    └── sacrament              (design system)
```

❌ **Forbidden:**

```
app
    ├── core:data              ❌ App doesn't access data directly
    ├── core:datasource:local   ❌ App doesn't access database directly
    └── core:datasource:remote  ❌ App doesn't access network directly
```

## Data Flow Patterns

### Repository Pattern

```
Feature (UI) → Use Case (Domain) → Repository (Data) → Data Source
```

**See actual implementations:**

- UI Layer: `feature/presentation/src/.../schedules/SchedulesScreen.kt`
- Domain Layer: `core/domain/src/.../usecases/GetSchedulesUseCase.kt`
- Data Layer: `core/data/src/.../repository/ScheduleRepositoryImpl.kt`

### Offline-First Pattern

1. **Expose database as source of truth**
2. **Fetch from network in background**
3. **Update database with network data**
4. **UI observes database changes**

See repository implementations in `core/data/src/.../repository/` for offline-first pattern examples.

### Use Case Pattern

**Use cases encapsulate business logic. See examples:**

- Simple use case: `core/domain/src/.../usecases/GetNotesUseCase.kt`
- Filtering logic: `core/domain/src/.../usecases/GetNotesByTagUseCase.kt`
- Multiple repositories: `core/domain/src/.../usecases/` for complex coordination examples

## Result Type Mapping

### Layer-Specific Result Types

```
RemoteDataSource → LocalResult<T>
       ↓  map to
Repository → Outcome<T>
       ↓  map to
ScreenModel → ItemState<T>
```

**See type definitions:**

- LocalResult: `core/localdatasource/src/.../LocalResult.kt`
- Outcome: `core/domain/src/.../Outcome.kt`
- ItemState: `feature/presentation/src/.../ItemState.kt`

### Mapper Functions (in core:data)

**Mappers convert between layer-specific types. See:**

- `core/data/src/.../mappers/ResultMappers.kt` - Result type conversions
- `core/data/src/.../mappers/ErrorMappers.kt` - Error mapping logic

## State Management

### ViewModel State Pattern (Hilt)

**ViewModels manage UI state with StateFlow. See examples:**

- ViewModel pattern: `feature/presentation/src/.../schedules/SchedulesViewModel.kt`
- UI state definition: `feature/presentation/src/.../schedules/SchedulesUiState.kt`
- State updates: Review any ViewModel in `feature/presentation/src/.../` for state management patterns

### State Hoisting in Compose

**State hoisting separates stateful and stateless composables. See:**

- Screen with ViewModel: `feature/presentation/src/.../schedules/SchedulesScreen.kt`
- Stateless content: Review any Screen.kt file in `feature/presentation/src/.../` for content composables

## Dependency Injection

### Module-Level Organization

**Hilt modules organize dependency injection. See:**

- Data module: `core/data/src/.../di/DataModule.kt`
- Database module: `core/localdatasource/src/.../di/LocalDataSourceModule.kt`
- Network module: `core/remotedatasource/src/.../di/RemoteDataSourceModule.kt`

### Injection Points

**Constructor injection with @Inject. Review any of these files for patterns:**

- ViewModels: `feature/presentation/src/.../*ViewModel.kt`
- Repositories: `core/data/src/.../repository/*RepositoryImpl.kt`
- Use cases: `core/domain/src/.../usecases/*UseCase.kt`

## Module Boundaries

### What Belongs Where

**Feature Modules (feature:presentation):**

- ViewModels (Hilt)
- UI Screens
- Navigation definitions
- Feature-specific UI state (ItemState)

**core:domain:**

- Use cases
- Business logic
- Domain-specific validation
- Outcome<T> and DomainError
- Complex data transformations

**core:data:**

- Repository interfaces
- Repository implementations
- Data source coordination
- Caching logic
- LocalResult → Outcome mapping

**core:datasource:local:**

- Room database
- DAOs
- Entity models
- Database migrations
- LocalResult<T> type

**core:datasource:remote:**

- Retrofit interfaces
- Network models
- API client configuration
- Fake data sources (for testing)

**core:common:**

- Extension functions
- Utility classes
- Constants
- Shared types

**sacrament:**

- Design system components
- Theme (colors, typography, shapes)
- Common UI utilities
- Atomic design elements

## Architecture Violations

### Common Mistakes

❌ **Domain depending on data:**
- Use cases should depend on repository interfaces, not data sources
- Keep domain layer independent of data layer

❌ **Presentation depending on data sources:**
- ViewModels should use use cases, not databases directly
- Never inject Room database or data sources into UI layer

❌ **Data sources depending on domain:**
- DAOs should return LocalResult, not Outcome
- Keep data source types separate from domain types

### Correct Patterns

✅ **Domain depends on abstractions:**
- Use cases depend on repository interfaces
- See examples in `core/domain/src/.../usecases/`

✅ **Data depends on domain and sources:**
- Repositories implement domain interfaces
- See examples in `core/data/src/.../repository/`

✅ **Presentation depends on domain:**
- ViewModels use use cases
- See examples in `feature/presentation/src/.../`

## Best Practices

### Do ✅

- Follow Clean Architecture layers strictly
- Keep domain layer pure (no Android dependencies)
- Use repositories as single source of truth
- Map between layer-specific result types
- Inject interfaces, not implementations
- Keep feature modules isolated
- Use use cases for business logic
- Expose data as Flow from repositories
- Handle errors at repository level
- Use Outcome<T> in domain layer
- Use ItemState<T> in presentation layer

### Don't ❌

- Mix concerns across layers
- Create circular dependencies
- Depend on concrete implementations
- Access data sources directly from UI
- Put business logic in ScreenModels
- Expose mutable state from repositories
- Use platform types (add explicit nullability)
- Skip the domain layer "to save time"
- Put UI states (Loading/Empty) in domain layer
- Return LocalResult from repositories (use Outcome)

## Verification

### Check Module Dependencies

```bash
# Verify no circular dependencies
./gradlew buildEnvironment

# Check dependency tree
./gradlew :feature:presentation:dependencies
```

### Enforce with Lint

Custom lint rules can enforce:

- Correct dependency directions
- Domain layer has no Android dependencies
- Proper result type usage per layer

## See Also

- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Android Architecture Guide](https://developer.android.com/jetpack/guide)
- [docs/architecture.md](../architecture.md) - Complete architecture details

## Related Documentation

- [Coding Standards](./codingStandards.md)
- [Testing Rules](./testingRules.md)
- [Feature Development Rules](./featureDevelopmentRules.md)
