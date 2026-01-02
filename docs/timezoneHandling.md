# Timezone Handling Strategy

## Overview

This project follows the industry best practice: **Store UTC, Display Local**. All timestamps
throughout the system (domain models, database, and future API communication) are stored in UTC to
ensure consistency across devices and eliminate timezone offset issues during synchronization.

## Why UTC?

### Problems with Local Timezone Storage

**❌ WRONG:** Using local timezone for storage causes:
- Inconsistent values across different user locations
- Incorrect sorting when syncing across timezones
- Offset issues during server synchronization

### Benefits of UTC Storage

✅ **Consistency**: Same timestamp value for all users regardless of location  
✅ **No Offset Issues**: Server synchronization works correctly  
✅ **Correct Sorting**: Timestamps can be compared and sorted accurately  
✅ **Standard Practice**: Follows industry convention used by major platforms  
✅ **Future-Proof**: Ready for multi-timezone deployments

## Implementation

### 1. Domain Layer (UTC Storage)

**All domain models store timestamps in UTC.**

- Domain models: `core/domain/src/.../models/` (e.g., `NoteDomain.kt`)
- UTC extension: `core/common/src/.../extensions/DateExtensions.kt`

The `.datetime` extension converts `Instant` to `LocalDateTime` in UTC (FixedOffsetTimeZone with UtcOffset.ZERO).

### 2. Database Layer (UTC Milliseconds)

**Database stores timestamps as `Long` (milliseconds since epoch in UTC).**

- Entity models: `core/localdatasource/src/.../entity/` (e.g., `NoteCache.kt`)
- All timestamp fields stored as Long milliseconds in UTC

### 3. Data Mapping (UTC Preservation)

**Mappers maintain UTC throughout conversions:**

- Mapper functions: `core/data/src/.../mappers/` (e.g., `NoteMapper.kt`)
- Database → Domain: Long millis → LocalDateTime UTC
- Domain → Database: LocalDateTime UTC → Long millis

### 4. Presentation Layer (Convert to Local for Display)

**Important:** Only convert to local timezone when displaying to the user.

**See actual implementation:**
- Display date conversion: `core/domain/src/.../models/NoteDomain.kt` (displayDate property)
- Uses `.toLocalTimezone()` to convert UTC to user's local timezone for display
- UI displays local time while domain stores UTC

### 5. User Input (Convert from Local to UTC)

**When accepting datetime input from UI:**

**See actual implementation:**
- Date selection handling in ViewModels: `feature/presentation/src/.../`
- Uses `.toUtc()` to convert user's local time to UTC before storage
- Pattern: Accept local time from UI → Convert to UTC → Save to domain

## Extension Functions Reference

**See `core/common/src/.../extensions/DateExtensions.kt` for all timezone utilities:**

- UTC conversion functions (for storage)
- Local timezone conversion functions (for display)
- Instant/Long/LocalDateTime utilities

## Usage Examples

**See actual implementations for timezone handling patterns:**

- **Creating entities:** Domain models automatically use UTC (`.datetime` extension)
- **Displaying:** Convert to local timezone in UI layer (`.toLocalTimezone()`)
- **User input:** Convert local time to UTC before saving (`.toUtc()`)
- **Server sync:** Use UTC milliseconds for API communication

**Example files:**
- Domain models: `core/domain/src/.../models/`
- Mappers: `core/data/src/.../mappers/`
- UI display: `feature/presentation/src/.../` screens

## Testing Considerations

**See test files for timezone testing patterns:**

- **Unit tests:** Verify UTC storage in `core/domain/src/test/`
- **Integration tests:** Verify database UTC preservation in `core/localdatasource/src/test/`
- **Mapper tests:** Verify UTC maintained through conversions in `core/data/src/test/`

## Common Pitfalls

### ❌ Don't: Use Local Timezone for Storage

**WRONG:** Using `TimeZone.currentSystemDefault()` creates timezone offset issues across devices.

### ❌ Don't: Display UTC Directly

**WRONG:** Displaying UTC timestamps directly shows wrong time to users in different timezones.

**See correct pattern:** Use `.toLocalTimezone()` or `.displayDate` property in actual code

### ❌ Don't: Mix Timezones

**WRONG:** Mixing UTC and local timezones in the same entity creates inconsistent data.

**See correct pattern:** All timestamps in domain models use `.datetime` extension (UTC)

### ✅ Do: Store UTC, Display Local

**CORRECT patterns - See actual implementations:**

- **Storage:** Domain models use `Clock.System.now().datetime` (UTC)
- **Display:** UI uses `.toLocalTimezone()` or `.displayDate` property
- **Example files:** `core/domain/src/.../models/NoteDomain.kt` and `feature/presentation/src/.../` screens

## Migration Notes

The project recently migrated from using `TimeZone.currentSystemDefault()` (inconsistent local timezone) to using the `.datetime` extension (consistent UTC).

This change **improves** server synchronization by eliminating timezone offset issues. All existing data should be migrated to UTC if necessary.

**See migration implementations in:**
- Domain models: `core/domain/src/.../models/`
- Database migrations: `core/localdatasource/src/.../migrations/`

## Related Files

- `core/domain/src/main/java/com/daily/app/core/domain/extensions/DateExtensions.kt` - Core timezone
  utilities
- `core/domain/src/main/java/com/daily/app/core/domain/models/NoteDomain.kt` - Domain model with UTC
  timestamps
- `core/data/src/main/java/com/daily/app/core/data/mappers/NoteMapper.kt` - UTC-preserving
  conversions
-
`core/localdatasource/src/main/java/com/daily/app/core/localdatasource/helpers/LocalDateTimeHelpers.kt` -
Database utilities

## References

- [ISO 8601 DateTime Standard](https://en.wikipedia.org/wiki/ISO_8601)
- [Best Practices for Time Zones](https://codeblog.jonskeet.uk/2019/03/27/storing-utc-is-not-a-silver-bullet/)
- [Kotlin DateTime Documentation](https://github.com/Kotlin/kotlinx-datetime)

---

**Summary**: Always store UTC, convert to local timezone only for display. This ensures data
consistency across devices and eliminates synchronization issues.
