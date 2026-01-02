# Testing Rules

> **For AI Agents:** Testing standards and practices for PracticalChristian

## Core Principles

- **Write tests first** or immediately after implementation
- **70%+ coverage** required (enforced by CI)
- **Test behavior, not implementation** - Tests should survive refactoring
- **Keep tests simple** - Complex tests indicate complex code
- **Fast feedback** - Unit tests run in seconds

## Coverage Requirements

| Layer                 | Minimum | Target |
|-----------------------|---------|--------|
| **ViewModels**        | 80%     | 90%+   |
| **Use Cases**         | 90%     | 95%+   |
| **Repositories**      | 80%     | 85%+   |
| **Network/DataStore** | 70%     | 80%+   |
| **UI Screens**        | 60%     | 70%+   |

```bash
./gradlew coverageUnit    # Fast - run after code changes
./gradlew coverageAll     # Full - unit + instrumented
```

## Testing Strategy

This project uses **JUnit 6** (JUnit Jupiter) for unit tests and **JUnit 4** for instrumented tests.
JUnit 6 provides modern testing features while maintaining compatibility with Android instrumented tests.

### Test Types and Frameworks

| Test Type              | Location           | Framework | Import Package                                   | Usage                         |
|------------------------|--------------------|-----------|--------------------------------------------------|-------------------------------|
| **Unit Tests**         | `src/test/`        | JUnit 6   | `org.junit.jupiter.api.*`                        | 90% of tests - fast, isolated |
| **Instrumented Tests** | `src/androidTest/` | JUnit 4   | `org.junit.*` + `@RunWith(AndroidJUnit4::class)` | UI, Android framework only    |

**Why This Strategy?**

- ✅ **JUnit 6 (Jupiter) for unit tests** - Modern features, better parameterized tests, nested classes, dynamic tests
- ⚠️ **JUnit 4 for instrumented tests** - Android/AndroidX does not officially support JUnit 6 yet
- 🎯 **Maximize unit tests** - Faster execution, better test design, full JUnit 6 capabilities
- 🎯 **Minimize instrumented tests** - Use only for UI (Espresso) or true device integration
- 📊 **JaCoCo for coverage** - Built-in JaCoCo support for coverage tracking

### Important Notes

1. **Do NOT mix JUnit versions in the same test source set**
    - Unit tests (`src/test/`) → Always use `org.junit.jupiter.api.*` (JUnit 6)
    - Instrumented tests (`src/androidTest/`) → Always use `org.junit.*` with `@RunWith(AndroidJUnit4::class)`

2. **JUnit 6 Requirements**
    - Requires Java 17+ at runtime (your project uses Java 21 ✅)
    - Can still test code compiled with older JDK versions
    - Use `useJUnitPlatform()` in Gradle test configuration

3. **Test Configuration**
    - Unit tests automatically use `useJUnitPlatform()` via convention plugins
    - Instrumented tests use `AndroidJUnitRunner` (configured in `defaultConfig`)

## Testing Stack

**Unit Tests (JVM) - JUnit 6 (Jupiter)**

- **JUnit 6 (Jupiter)** - Test framework (`org.junit.jupiter:junit-jupiter:6.0.1`) - [Docs](https://docs.junit.org/6.0.1/overview.html)
- **kotlinx.coroutines.test** - `runTest`, coroutine testing
- **Truth** - Fluent assertions
- **Turbine** - Flow testing
- **Mockk** - Mocking framework (when needed)
- **Robolectric** - Android framework simulation (when needed)

**Instrumented Tests (Device/Emulator) - JUnit 4**

- **JUnit 4** - Test framework (via `androidx.test.ext:junit`)
- **Compose UI Testing** - UI components
- **Hilt Testing** - `@HiltAndroidTest` for DI
- **AndroidX Test** - Runners and rules
- **Espresso** - UI interactions

## Quick Patterns

### ViewModel Test (JUnit 6 - Hilt)

**ViewModel tests use JUnit 6 with fake repositories. See:**

- Test examples: `feature/presentation/src/test/.../schedules/SchedulesViewModelTest.kt`
- Fake repositories: `core/common/src/test/.../fake/` directory
- Test patterns: Review any `*ViewModelTest.kt` file for testing approaches

### Repository Test (JUnit 6)

**Repository tests verify data layer logic. See:**

- Test examples: `core/data/src/test/.../repository/ScheduleRepositoryTest.kt`
- Fake data sources: `core/common/src/test/.../fake/` for test doubles
- Testing patterns: Review repository tests for outcome mapping verification

### Use Case Test (JUnit 6)

**Use case tests verify business logic. See:**

- Test examples: `core/domain/src/test/.../usecases/GetSchedulesUseCaseTest.kt`
- Business logic testing: Review use case tests for filtering, transformation, and coordination logic

### Coroutine Testing

**Use `runTest` for coroutine testing and Turbine for Flow testing.**

- Suspended functions: Wrap tests in `runTest { }`
- Flow testing: Use Turbine's `.test { }` extension
- Time control: Use `advanceTimeBy()` in tests
- See test files in any module's `src/test/` directory for examples

### Compose UI Test (JUnit 4 - Instrumented)

**Instrumented tests use JUnit 4 with @HiltAndroidTest.**

- UI tests: `feature/presentation/src/androidTest/` directory
- Test patterns: Use `ComposeTestRule` and Hilt rules
- See instrumented test files for Compose testing examples

## Database Testing (JUnit 6 with Robolectric)

**DAO tests use in-memory database with Robolectric.**

- Test location: `core/localdatasource/src/test/.../dao/`
- Use in-memory database (fast, isolated)
- Close database in `@AfterEach`
- Use `@ExtendWith(RobolectricExtension::class)` for JUnit 6
- See DAO test files for database testing patterns

## Test Utilities

### Create Fakes (Preferred)

**Fake implementations for testing. See:**

- Fake repositories: `core/common/src/test/.../fake/FakeScheduleRepository.kt`
- Fake data sources: `core/common/src/test/.../fake/` directory
- Pattern: Implement interface with controllable behavior

### Test Data Builders

**Test data builders create test instances. See:**

- Test data: `core/common/src/test/.../data/TestData.kt`
- Builder pattern: Functions with default parameters for test object creation
    id = id,
    title = title,
    description = "Test description",
    date = date,
    isCompleted = false,
)

fun testNote(
    id: String = "test-note-id",
    title: String = "Test Note",
    content: String = "Test content",
) = Note(
    id = id,
    title = title,
    content = content,
    createdAt = Instant.now(),
    tags = emptyList(),
)
```

### Test Dispatcher Rule (JUnit 6)

**See test dispatcher utilities:**

- MainDispatcherRule: `core/common/src/test/.../util/TestDispatchers.kt`
- Use with `@JvmField @RegisterExtension` in ViewModel tests

## Naming Conventions

**Files:** `{Name}{Type}Test.kt`

- `SchedulesScreenModelTest.kt` - Unit test (JUnit 6)
- `ScheduleRepositoryTest.kt` - Unit test (JUnit 6)
- `GetSchedulesUseCaseTest.kt` - Unit test (JUnit 6)
- `SchedulesScreenTest.kt` - Instrumented test (JUnit 4)

**Test Methods:** Use backticks for readability

```kotlin
// JUnit 6 unit tests (backticks for readability)
@Test
fun `state emits loading then success when data loads`() = runTest { }

// JUnit 4 instrumented tests (snake_case is common)
@Test
fun schedulesScreen_displaysSchedules() { }
```

## Common Assertions

```kotlin
// Truth
assertThat(value).isEqualTo(expected)
assertThat(value).isNull()
assertThat(list).isEmpty()
assertThat(list).hasSize(2)
assertThat(list).contains(item)
assertThat(boolean).isTrue()

// Turbine (Flow)
flow.test {
    assertThat(awaitItem()).isEqualTo(expected)
    awaitComplete()
    cancelAndIgnoreRemainingEvents()
}
```

## Do's and Don'ts

### Do

- ✅ Use **JUnit 6** (`org.junit.jupiter.api.*`) for unit tests (`src/test/`)
- ✅ Use **JUnit 4** with `@RunWith(AndroidJUnit4::class)` for instrumented tests (`src/androidTest/`)
- ✅ Maximize unit tests (fast, modern JUnit 6 features)
- ✅ Minimize instrumented tests (only for UI and true device integration)
- ✅ Use `runTest` for coroutine tests
- ✅ Use fakes over mocks (create in test packages)
- ✅ Use Truth for assertions
- ✅ Use Turbine for Flow testing
- ✅ Test public APIs only
- ✅ Use descriptive test names with backticks
- ✅ Test edge cases (empty, null, errors)
- ✅ Keep tests independent
- ✅ Use JaCoCo for coverage tracking

### Don't

- ❌ Don't mix JUnit versions in the same test source set
- ❌ Don't use JUnit 4 imports in unit tests (`src/test/`)
- ❌ Don't use JUnit 6 in instrumented tests (`src/androidTest/`) - not supported by Android
- ❌ Don't use Mockk unless necessary (prefer fakes)
- ❌ Don't test implementation details
- ❌ Don't write flaky tests
- ❌ Don't skip coverage after changes
- ❌ Don't test framework code (Room, Compose, Navigation, etc.)
- ❌ Don't use `Thread.sleep()` - use `advanceTimeBy()`
- ❌ Don't ignore test failures
- ❌ Don't put Loading/Empty states in domain tests
- ❌ Don't test Outcome<T> mapping in presentation layer tests

## Pre-Commit Checklist

Before committing:

1. **Build & Test**
   ```bash
   ./gradlew build test coverageUnit
   ```

2. **Review Changes**
   ```bash
   git diff --staged
   ```

3. **Verify:**
    - No duplicated code
    - All imports exist
    - Coverage didn't drop
    - Tests pass

**Related:** [Coding Standards](./codingStandards.md) • [Architecture Rules](./architectureRules.md)
