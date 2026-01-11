# Design System Migration Inventory

**Date:** 2024-12-19  
**Status:** In Progress  
**Epic:** Design system setup + enforcement

## Summary

This document provides a comprehensive inventory of Material3 usage across the codebase that needs to be migrated to the Sacrament design system.

## Material3 Components Usage by Feature

### Feature: tags
**File:** `feature/tags/src/main/java/com/practicalchristian/app/feature/tags/TagsScreen.kt`

**Material3 Components Used:**
- `Scaffold` - Replace with `SacramentScreenScaffold`
- `Button` - Replace with `SacramentButton`
- `OutlinedButton` - Replace with `SacramentButton` (variant: Outlined)
- `Card` - Replace with `SacramentCard`
- `CardDefaults` - Remove (use `SacramentCardDefaults`)
- `CircularProgressIndicator` - Replace with `SacramentProgressIndicator`
- `LinearProgressIndicator` - Replace with `SacramentProgressIndicator`
- `FloatingActionButton` - Replace with `SacramentFab`
- `Icon` - Replace with `SacramentIcon`
- `IconButton` - Replace with `SacramentIconButton`
- `ModalBottomSheet` - **MISSING** - Need to implement `SacramentModalBottomSheet` or use `SacramentSheet` with modal
- `Surface` - Replace with `SacramentSurface` (if exists) or use `SacramentCard`
- `TextField` - Replace with `SacramentTextField`
- `Text` - Replace with `SacramentText`
- `TopAppBar` - Replace with `SacramentTopAppBar`
- `rememberModalBottomSheetState` - Need custom implementation

**Status:** Heavy usage - Priority migration target

---

### Feature: schedules
**Files:**
- `feature/schedules/src/main/java/com/practicalchristian/app/feature/schedules/SchedulesScreen.kt`
- `feature/schedules/src/main/java/com/practicalchristian/app/feature/schedules/ScheduleScreen.kt`

**Material3 Components Used:**
- `Scaffold` - Replace with `SacramentScreenScaffold`
- `Button` - Replace with `SacramentButton`
- `OutlinedButton` - Replace with `SacramentButton` (variant: Outlined)
- `Card` - Replace with `SacramentCard`
- `CardDefaults` - Remove
- `CircularProgressIndicator` - Replace with `SacramentProgressIndicator`
- `LinearProgressIndicator` - Replace with `SacramentProgressIndicator`
- `Icon` - Replace with `SacramentIcon`
- `IconButton` - Replace with `SacramentIconButton`
- `IconButtonDefaults` - Remove
- `Snackbar` - Replace with `SacramentSnackbar`
- `Text` - Replace with `SacramentText`
- `TopAppBar` - Replace with `SacramentTopAppBar`
- `DatePicker` - **MISSING** - Need to implement `SacramentDatePicker` or use alternative
- `DatePickerDialog` - **MISSING** - Need to implement `SacramentDatePickerDialog`
- `TimePicker` - **MISSING** - Need to implement `SacramentTimePicker` or use alternative
- `TimeInput` - **MISSING** - Need to implement `SacramentTimeInput`
- `rememberDatePickerState` - Need custom implementation
- `rememberTimePickerState` - Need custom implementation

**Status:** Heavy usage + complex pickers - Requires new components

---

### Feature: notes
**Files:**
- `feature/notes/src/main/java/com/practicalchristian/app/feature/notes/list/NotesScreen.kt`
- `feature/notes/src/main/java/com/practicalchristian/app/feature/notes/edit/EditNoteScreen.kt`
- `feature/notes/src/main/java/com/practicalchristian/app/feature/notes/detail/NoteScreen.kt`

**Material3 Components Used:**
- `Scaffold` - Replace with `SacramentScreenScaffold` (already partially migrated)
- `Button` - Replace with `SacramentButton` (already partially migrated)
- `Card` - Replace with `SacramentCard` (already partially migrated)
- `CardDefaults` - Remove
- `CircularProgressIndicator` - Replace with `SacramentProgressIndicator` (already partially migrated)
- `Icon` - Replace with `SacramentIcon` (already partially migrated)
- `IconButton` - Replace with `SacramentIconButton` (already partially migrated)
- `ModalBottomSheet` - **MISSING** - Need `SacramentModalBottomSheet`
- `Surface` - Replace with `SacramentSurface` or `SacramentCard`
- `TextField` - Replace with `SacramentTextField`
- `TextFieldDefaults` - Remove
- `Text` - Replace with `SacramentText` (already partially migrated)
- `TopAppBar` - Replace with `SacramentTopAppBar` (already partially migrated)
- `Snackbar` - Replace with `SacramentSnackbar`

**Status:** Partially migrated - Complete migration needed

---

### Feature: books
**Files:**
- `feature/books/src/main/java/com/practicalchristian/app/feature/books/list/BooksScreen.kt`
- `feature/books/src/main/java/com/practicalchristian/app/feature/books/detail/BookScreen.kt`

**Material3 Components Used:**
- `Scaffold` - Replace with `SacramentScreenScaffold`
- `Card` - Replace with `SacramentCard`
- `CardDefaults` - Remove
- `CircularProgressIndicator` - Replace with `SacramentProgressIndicator`
- `Icon` - Replace with `SacramentIcon`
- `IconButton` - Replace with `SacramentIconButton`
- `Text` - Replace with `SacramentText`
- `TopAppBar` - Replace with `SacramentTopAppBar`

**Status:** Moderate usage - Standard migration

---

### Feature: auth
**File:** `feature/auth/src/main/java/com/practicalchristian/app/feature/auth/AuthenticationScreen.kt`

**Material3 Components Used:**
- `Button` - Replace with `SacramentButton`
- `ButtonDefaults` - Remove
- `Icon` - Replace with `SacramentIcon`
- `Surface` - Replace with `SacramentSurface` or `SacramentCard`
- `Text` - Replace with `SacramentText`

**Status:** Light usage - Simple migration

---

### Feature: home
**File:** `feature/home/src/main/java/com/practicalchristian/app/feature/home/home/HomeScreen.kt`

**Material3 Components Used:**
- `Scaffold` - Replace with `SacramentScreenScaffold`
- `Button` - Replace with `SacramentButton`
- `ButtonDefaults` - Remove
- `Card` - Replace with `SacramentCard`
- `CardDefaults` - Remove
- `Icon` - Replace with `SacramentIcon`
- `IconButton` - Replace with `SacramentIconButton`
- `Text` - Replace with `SacramentText`
- `TextButton` - Replace with `SacramentButton` (variant: Text)
- `CircularProgressIndicator` - Replace with `SacramentProgressIndicator`

**Status:** Moderate usage - Standard migration

---

### Feature: onboarding
**File:** `feature/onboarding/src/main/java/com/practicalchristian/app/feature/onboarding/OnboardingScreen.kt`

**Material3 Components Used:**
- `ButtonDefaults` - Remove
- `Icon` - Replace with `SacramentIcon`
- `IconButton` - Replace with `SacramentIconButton`
- `Surface` - Replace with `SacramentSurface` or `SacramentCard`
- `Text` - Replace with `SacramentText`
- `TextButton` - Replace with `SacramentButton` (variant: Text)

**Status:** Light usage - Simple migration

---

### Feature: settings
**File:** `feature/settings/src/main/java/com/practicalchristian/app/feature/settings/SettingsScreen.kt`

**Material3 Components Used:**
- `Scaffold` - Replace with `SacramentScreenScaffold`
- `Icon` - Replace with `SacramentIcon`
- `IconButton` - Replace with `SacramentIconButton`
- `LargeTopAppBar` - **MISSING** - Need to check if `SacramentTopAppBar` supports large variant or implement
- `Text` - Replace with `SacramentText`

**Status:** Light usage - May need LargeTopAppBar variant

---

### Feature: setup
**File:** `feature/setup/src/main/java/com/practicalchristian/app/feature/setup/SetupScreen.kt`

**Material3 Components Used:**
- `Scaffold` - Replace with `SacramentScreenScaffold`
- `Button` - Replace with `SacramentButton`
- `Text` - Replace with `SacramentText`
- `TextField` - Replace with `SacramentTextField`

**Status:** Light usage - Simple migration

---

### Feature: profile
**File:** `feature/profile/src/main/java/com/practicalchristian/app/feature/profile/ProfileScreen.kt`

**Material3 Components Used:**
- `Scaffold` - Replace with `SacramentScreenScaffold`
- `Card` - Replace with `SacramentCard`
- `CardDefaults` - Remove
- `Icon` - Replace with `SacramentIcon`
- `IconButton` - Replace with `SacramentIconButton`
- `LargeTopAppBar` - **MISSING** - Need to check if `SacramentTopAppBar` supports large variant
- `Text` - Replace with `SacramentText`

**Status:** Light usage - May need LargeTopAppBar variant

---

## Sacrament Module Internal Usage

### File: `sacrament/src/main/java/com/sacrament/ui/patterns/SacramentScreenScaffold.kt`

**Material3 Components Used:**
- `Scaffold` - **NEEDS REPLACEMENT** - This is the internal Material3 dependency that needs to be removed

**Status:** Critical - Blocks full Material3 removal

---

## Missing Sacrament Components

The following Material3 components are used but don't have Sacrament equivalents yet:

1. **ModalBottomSheet** - Used in `tags` and `notes`
   - `SacramentSheet` exists but is just a surface
   - Need full modal bottom sheet implementation with state management

2. **DatePicker** - Used in `schedules`
   - Complex component requiring date selection UI
   - May need to use alternative library or build custom

3. **DatePickerDialog** - Used in `schedules`
   - Dialog wrapper for DatePicker

4. **TimePicker** - Used in `schedules`
   - Complex component requiring time selection UI
   - May need to use alternative library or build custom

5. **TimeInput** - Used in `schedules`
   - Text input for time

6. **LargeTopAppBar** - Used in `settings` and `profile`
   - Variant of TopAppBar with expanded/collapsed states
   - Check if `SacramentTopAppBar` can be extended or needs new component

---

## Hardcoded Colors

**Status:** ✅ No hardcoded hex colors found in feature modules (only in `sacrament/src/main/java/com/sacrament/ui/foundation/color/Palette.kt` which is allowed)

---

## Material Icons Usage

**Status:** ✅ Material icons are allowed per the design system rules. All usages are from `androidx.compose.material.icons.*` which is permitted.

---

## Migration Priority

Based on usage complexity and dependencies:

1. **High Priority:**
   - `tags` - Heavy usage, blocking other features
   - `schedules` - Complex pickers, but can be deferred if pickers are built first
   - `notes` - Partially migrated, quick win

2. **Medium Priority:**
   - `books` - Standard migration
   - `home` - Standard migration

3. **Low Priority:**
   - `auth`, `onboarding`, `setup`, `settings`, `profile` - Light usage, quick migrations

4. **Critical (Blocks Everything):**
   - `SacramentScreenScaffold` - Remove Material3 Scaffold dependency

---

## Next Steps

1. ✅ **Inventory Complete** - This document
2. ⏳ **Validate Script** - Run `check-design-system-usage.sh` and verify output
3. ⏳ **Build Missing Components** - ModalBottomSheet, DatePicker, TimePicker (if needed)
4. ⏳ **Replace Scaffold in Sacrament** - Remove Material3 dependency from `SacramentScreenScaffold`
5. ⏳ **Feature Migrations** - Start with high-priority features
