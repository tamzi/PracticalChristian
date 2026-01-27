# Clean Architecture: Domain Result Types

## Overview

The application uses distinct result types per architectural layer to maintain separation of 
concerns and enable clean boundaries between domain logic, data infrastructure, and UI presentation.

## Layer Diagram

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

**Location:** `core/domain/src/.../models/`

**Components:**
- **Outcome<T>**: Sealed interface with `Success<T>` and `Failure(DomainError)` cases
- **DomainError**: Sealed interface for business-meaningful errors (Network, NotFound, Validation, Unauthorized, Unknown)
- **Repository interfaces**: Define data contracts using `Outcome<T>` return types

**Constraint:** Returns ONLY Success or Failure. Empty collections are `Success(emptyList())`, not a separate state.

### Data Layer (core/data)

**Location:** `core/data/src/.../`

**Components:**
- **Result mappers** (`mappers/ResultMappers.kt`): Transform infrastructure results to domain `Outcome<T>`
- **Error mappers** (`mappers/ErrorMapper.kt`): Convert low-level exceptions to `DomainError`
- **Safe execution wrappers** (`mappers/SafeOutcome.kt`): Eliminate try-catch boilerplate
- **Repository implementations** (`repository/*RepositoryImpl.kt`): Coordinate data sources and map to domain types

**Responsibilities:**
- LocalResult → Outcome mapping
- Exception → DomainError mapping
- DTO → Domain entity mapping

### Presentation Layer (core/ui, feature/*)

**Location:** `core/ui/src/.../helpers/ItemState.kt`

**Components:**
- **ItemState<T>**: Sealed interface with Loading, Error, Success states for UI lifecycle
- **ViewModels**: Consume `Outcome<T>` from domain, expose `ItemState<T>` to UI
- **Error presentation**: Map `DomainError` to user-friendly messages

**Flow:**
1. UI sets Loading before repository call
2. Repository returns Outcome (Success | Failure)
3. ViewModel maps DomainError to user message
4. UI determines Empty state from business data

## Architectural Principles

### 1. Domain is Pure
- Only business outcomes: Success or Failure
- Errors are business-meaningful (NotFound, Validation, etc.)
- NO infrastructure types (no Retrofit/Room exceptions)
- NO UI states (no Loading/Idle)

### 2. Data Maps Everything
- Infrastructure errors → Domain errors
- DTOs → Domain entities
- LocalResult → Outcome
- Keeps infrastructure concerns isolated

### 3. Presentation Derives UI State
- Maps Outcome → ItemState
- Sets Loading before calling repository
- Determines Empty from business data
- Maps DomainError → user messages
- Handles all UI lifecycle (Idle/Loading/Empty)

### 4. Separation of Concerns
- **Domain**: "What happened?" (Success/Failure)
- **Presentation**: "What to show?" (Loading/Empty/Error UI)
- **Data**: "How to get it?" (API/DB/Cache)
