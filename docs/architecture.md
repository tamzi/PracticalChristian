# Clean Architecture: Domain Result Types

## The Problem (Before)

The original `DataResult` type mixed UI lifecycle concerns (Loading, Idle, Empty) with business
outcomes:

**❌ WRONG pattern** - This mixed UI lifecycle concerns with business outcomes.

**Why this violates Clean Architecture:**

1. **Loading/Idle are presentation states** - they describe when to show spinners, not business
   facts
2. **Forces all consumers to handle UI timing** - CLI tools, background workers, tests must all deal
   with view lifecycle
3. **Empty is ambiguous** - does it mean `Success(emptyList())` or a business error like "KYC not
   found"?
4. **Violates Single Responsibility** - domain models business logic, not UI state

## The Solution (Clean Architecture)

### Layer Diagram

```
┌─────────────────────────────────────────────────────────────────────────┐
│ Presentation Layer (UI)                                                 │
│ • ItemState { Idle, Loading, Empty, Success<T>, Error }                │
│ • UiListState { Idle, Loading, Success(Empty|Data), Error }            │
│ • Derives UI lifecycle from domain streams                              │
│ • Maps DomainError → user messages                                      │
└─────────────────────────────────────────────────────────────────────────┘
                          ↑  runtime: Flow<Outcome<T>>
                          ↓  compile: depends on domain interfaces
┌─────────────────────────────────────────────────────────────────────────┐
│ Domain Layer (Business Logic)                                           │
│ • Outcome<T> = Success<T> | Failure(DomainError)                       │
│ • DomainError { Network, NotFound, Validation, Unauthorized, Unknown }  │
│ • Repository interfaces returning Outcome<T>                            │
│ • Use Cases coordinating business operations                            │
│ • NO Loading/Idle/Empty states                                          │
└─────────────────────────────────────────────────────────────────────────┘
                          ↑  runtime: maps LocalResult → Outcome
                          ↓  compile: depends on domain models
┌─────────────────────────────────────────────────────────────────────────┐
│ Data Layer (Infrastructure)                                             │
│ • LocalResult<T> = Success<T> | Error(message)                         │
│ • Repository implementations                                             │
│ • Data sources (API, Database, Cache)                                   │
│ • Maps: LocalResult → Outcome, InfraError → DomainError                │
│ • Maps: DTO → Domain entities                                           │
└─────────────────────────────────────────────────────────────────────────┘
```

### Domain Layer (core/domain)

**Business-level result types:**

- Outcome type: `core/domain/src/.../Outcome.kt`
- DomainError: `core/domain/src/.../DomainError.kt`
- Repository interfaces: `core/domain/src/.../repository/`

**Key principle:** Returns ONLY Success or Failure. Empty list = Success(emptyList()), NOT a separate Empty state.

### Data Layer (core/data)

**Maps infrastructure results to domain results:**

- Result mappers: `core/data/src/.../mappers/ResultMappers.kt`
- Error mappers: `core/data/src/.../mappers/ErrorMapper.kt`
- Safe execution wrappers: `core/data/src/.../mappers/SafeOutcome.kt`
- Repository implementations: `core/data/src/.../repository/*RepositoryImpl.kt`

**Key patterns:**
- LocalResult → Outcome mapping
- Exception → DomainError mapping
- Safe execution helpers eliminate try-catch boilerplate

### Presentation Layer (feature/presentation)

**UI lifecycle states - where Loading/Idle/Empty BELONG:**

- ItemState: `feature/presentation/src/.../ItemState.kt`
- Error mapping: `feature/presentation/src/.../mappers/ErrorMessageMapper.kt`
- ViewModel examples: `feature/presentation/src/.../*ViewModel.kt`

**Key pattern:**
1. UI sets Loading BEFORE repository call
2. Repository returns Outcome (Success | Failure)
3. Map DomainError to user message in presentation
4. UI determines Empty state from business data

## Migration Strategy

### Phase 1: Domain & Data Layers ✅ DONE

- [x] Create `Outcome<T>` and `DomainError` in domain
- [x] Add `@Deprecated` typealias `DataResult = Outcome` for compatibility
- [x] Update repositories to return `Outcome<T>`
- [x] Create `.toOutcome()` and `.toDomainError()` mappers in data layer

### Phase 2: Presentation Layer (TODO)

- [ ] Add `Idle` and `Empty` to `ItemState` ✅ DONE
- [ ] Create `.toUserMessage()` extension for `DomainError` ✅ DONE
- [ ] Update ScreenModels to:
    - Replace `is DataResult.Error` → `is Outcome.Failure`
    - Replace `is DataResult.Success` → `is Outcome.Success`
    - Replace `result.message` → `result.error.toUserMessage()`
    - Replace `result.data` → `result.value`
    - Set `ItemState.Loading` BEFORE repository calls
    - Handle `null` results as `ItemState.Empty`
- [ ] Update UI Screens to handle `ItemState.Idle` and `ItemState.Empty`
- [ ] Remove all `DataResult.Loading/Idle/Empty` references

### Phase 3: Cleanup

- [ ] Remove `@Deprecated` typealias
- [ ] Update documentation
- [ ] Add lint rules to prevent UI states in domain

## Key Principles

1. **Domain is Pure**
    - Only business outcomes: Success or Failure
    - Errors are business-meaningful (NotFound, Validation, etc.)
    - NO infrastructure types (no Retrofit/Room exceptions)
    - NO UI states (no Loading/Idle)

2. **Data Maps Everything**
    - Infrastructure errors → Domain errors
    - DTOs → Domain entities
    - LocalResult → Outcome
    - Keeps infrastructure concerns isolated

3. **Presentation Derives UI State**
    - Maps Outcome → ItemState
    - Sets Loading before calling repository
    - Determines Empty from business data
    - Maps DomainError → user messages
    - Handles all UI lifecycle (Idle/Loading/Empty)

4. **Separation of Concerns**
    - Domain: "What happened?" (Success/Failure)
    - Presentation: "What to show?" (Loading/Empty/Error UI)
    - Data: "How to get it?" (API/DB/Cache)

## Benefits

✅ **Testability** - Domain tests don't need UI mocks
✅ **Reusability** - CLI tools don't handle Loading states  
✅ **Clarity** - Empty = `Success(emptyList())`, not ambiguous
✅ **Clean** - Each layer has single responsibility
✅ **Type Safety** - Compiler enforces proper error handling

## References

- Original Review Comment: Lines 12-16 of `DataResult.kt`
- Clean Architecture by Robert C. Martin
- Domain-Driven Design principles
