# Theme Token Audit - Dark/Light Mode Support

**Date**: January 23, 2026  
**Status**: Initial Audit Complete  
**Related**: `docs/darkLightThemePlan.md`

## Executive Summary

This document audits the current theme token implementation in the Sacrament design system and identifies gaps needed to support full dark/light mode functionality as specified in the plan.

## Current Implementation

### Theme Structure (`Theme.kt`)

**✅ What's Working:**
- Theme switching infrastructure is in place with `darkTheme: Boolean` parameter
- System bar color management implemented (status bar & navigation bar)
- Automatic system bar appearance (light/dark icons) based on theme
- Design system properly uses CompositionLocal pattern
- Both `LightSacramentColors` and `DarkSacramentColors` defined

**Architecture:**
```kotlin
SacramentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    navigationBar: Bar,
    statusBar: Bar,
    content: @Composable () -> Unit
)
```

### Color Token Categories (`SacramentColorTokens.kt`)

The design system defines 7 color categories:

1. **Brand Colors** - Primary, Secondary, Tertiary accents
2. **Text Colors** - Strong, Muted, Inverse, OnBrand
3. **Surface Colors** - Background, Surface, SurfaceVariant, plus tinted surfaces (Sunlight, Lavender, Rose, Sky, Mint, Peach)
4. **Semantic Colors** - Success, Warning, Error, Info (with Light/Dark variants)
5. **Utility Colors** - ProgressTrack, AuthProviderSurface, SuccessAction, OnSuccessAction
6. **Onboarding Colors** - 4 accent colors for onboarding flow
7. **Tag Colors** - 15 vibrant colors for user categorization

### Light Theme (`LightColors.kt`)

**✅ Fully Defined:**
- All 7 categories have complete token assignments
- Uses proper palette references
- Clear semantic mappings

**Key Colors:**
- Background: `#F5F5F5` (light gray)
- Surface: `#FFFFFF` (white)
- Text Strong: `#1A1A1A` (near black)
- Text Muted: `#666666` (medium gray)

### Dark Theme (`DarkColors.kt`)

**⚠️ CRITICAL GAP - Incomplete Implementation:**

Currently only overrides 3 categories:
```kotlin
internal val DarkSacramentColors = LightSacramentColors.copy(
    text = SacramentTextColors(
        strong = Neutral50,      // #FAFAFA (light for contrast)
        muted = Neutral400,      // #BDBDBD (lighter gray)
        inverse = Color.Black,   // Black (reversed)
        onBrand = Color.White,   // Unchanged
    ),
    surfaces = LightSacramentColors.surfaces.copy(
        background = BackgroundDark,    // #141414 (very dark gray)
        surface = SurfaceDark,          // #202020 (dark gray)
        surfaceVariant = Neutral800,    // #424242 (medium-dark gray)
    ),
    // Comment notes: Tags inherit from light theme
)
```

**What's Missing:**
- Only overrides `text` and partial `surfaces`
- All other categories (brand, semantic, utilities, onboarding) use light theme values
- Tinted surface colors (sunlight, lavender, rose, etc.) not adjusted for dark mode
- No dark-specific accent colors
- No consideration for contrast ratios in dark mode

### Palette (`Palette.kt`)

**✅ Good Coverage:**
- Comprehensive color definitions
- Semantic colors defined with light/dark variants
- Neutral scale from 50-900
- Material-inspired color spectrum
- Dark background/surface colors defined:
  - `BackgroundDark = #141414`
  - `SurfaceDark = #202020`

## Gaps Analysis

### 🔴 Critical Gaps (Must Fix for MVP)

#### 1. Incomplete Dark Mode Color Mapping

**Issue**: `DarkSacramentColors` only overrides text and partial surfaces, inheriting all other categories from light theme.

**Impact**: 
- Brand colors may lack contrast on dark backgrounds
- Tinted surfaces (lavender, rose, peach, etc.) appear too bright/saturated in dark mode
- Utilities like `progressTrack` may be invisible or jarring
- Onboarding accents not optimized for dark backgrounds

**Required Actions**:
- Define dark-appropriate versions of all tinted surfaces
- Adjust utility colors for dark mode (e.g., progressTrack should be darker/lighter)
- Verify brand colors have sufficient contrast on dark backgrounds
- Consider whether onboarding accents need adjustment

#### 2. Missing Navigation Bar Tokens

**Issue**: Plan requires "spotlight effect" for dark mode bottom navigation, but no specific tokens exist for navigation components.

**From Plan**: 
> "Confirm surface, on-surface, accent, and shadow values needed for the bottom bar"

**Impact**: Cannot implement reference design for bottom navigation without dedicated tokens

**Required Actions**:
- Add navigation-specific token subcategory (e.g., `SacramentNavigationColors`)
- Define tokens for:
  - Bar background
  - Selected indicator/spotlight
  - Unselected icon tint
  - Selected icon tint
  - Shadow/elevation for spotlight effect

#### 3. Missing Theme Switcher Tokens

**Issue**: No tokens defined for the theme switcher component (sun/moon toggle).

**From Plan**:
> "Identify required track/thumb/icon tokens for the switcher"

**Required Actions**:
- Add switcher-specific tokens (can be part of utilities or new category)
- Define:
  - Track background (on/off states)
  - Thumb background (on/off states)
  - Icon colors (sun/moon)
  - Border/outline if needed

### 🟡 Medium Priority Gaps

#### 4. Tinted Surface Colors Not Adapted for Dark Mode

**Issue**: Surfaces like `sunlight`, `lavender`, `rose`, etc. use identical values in both themes.

**Impact**: These surfaces may appear oversaturated or lack proper contrast in dark mode

**Examples**:
- `surfaceSunlight = #FFF9E6` (very light yellow) - would be jarring on dark background
- `surfaceLavender = #E8E6FF` (light purple) - too bright for dark theme
- `surfaceRose = #FFE8F0` (light pink) - needs darkening

**Required Actions**:
- Create dark variants of each tinted surface with:
  - Lower lightness values
  - Reduced saturation
  - Maintained color identity
- Update `DarkSacramentColors` to use these variants

#### 5. Semantic Color Contrast in Dark Mode

**Issue**: Semantic colors (success, warning, error, info) inherit from light theme without validation.

**Concern**: These colors should work on dark backgrounds, but haven't been explicitly validated for:
- Text contrast (WCAG AA: 4.5:1 for normal text, 3:1 for large text)
- Icon visibility
- Background contrast when used as surfaces

**Required Actions**:
- Audit semantic colors on dark backgrounds
- Use light/dark variants appropriately (e.g., `successLight` in dark mode, `successDark` in light mode)
- Document which variant to use in which theme

### 🟢 Nice-to-Have / Future Enhancements

#### 6. Elevation/Shadow System for Dark Mode

**Current**: `SacramentElevation` appears theme-agnostic

**Enhancement**: Dark mode typically uses lighter shadows or surface tinting for elevation

#### 7. Interactive State Colors

**Gap**: No explicit hover/pressed/focused state colors defined for either theme

**Note**: May be derived from existing tokens, but explicit tokens would improve consistency

## Recommended Token Additions

### 1. Navigation Colors

```kotlin
@Immutable
data class SacramentNavigationColors(
    val barBackground: Color,
    val selectedIndicator: Color,
    val selectedIcon: Color,
    val unselectedIcon: Color,
    val spotlightGlow: Color,  // For dark mode spotlight effect
)
```

### 2. Interactive Component Colors

```kotlin
@Immutable
data class SacramentInteractiveColors(
    val switchTrackOn: Color,
    val switchTrackOff: Color,
    val switchThumbOn: Color,
    val switchThumbOff: Color,
    val switchIconOn: Color,
    val switchIconOff: Color,
)
```

### 3. Dark Tinted Surfaces

Need dark variants for:
- `sunlightDark` (warm dark tone instead of bright yellow)
- `lavenderDark` (deep purple tint)
- `roseDark` (deep pink/mauve tint)
- `skyDark` (deep blue tint)
- `mintDark` (deep green tint)
- `peachDark` (deep orange tint)

## Validation Checklist

### For Each New Token:

- [ ] **Contrast Ratio**: Meets WCAG AA (4.5:1 for text, 3:1 for UI components)
- [ ] **Visual Consistency**: Aligns with brand identity in both themes
- [ ] **Semantic Clarity**: Token name clearly indicates purpose
- [ ] **Theme Parity**: Both light and dark themes provide equivalent token
- [ ] **Pattern Alignment**: Follows existing Sacrament naming conventions
- [ ] **Preview Coverage**: Has Compose preview demonstrating both themes

### For Dark Theme Specifically:

- [ ] Background colors are sufficiently dark (`#141414` baseline is good)
- [ ] Text has proper contrast on all background types
- [ ] Brand colors maintain vibrancy without being harsh
- [ ] Tinted surfaces feel cohesive with overall dark aesthetic
- [ ] Semantic colors communicate intent clearly
- [ ] Navigation elements have clear hierarchy
- [ ] Interactive components have clear affordances

## Next Steps

1. **Define Navigation Tokens** (STORY: Define light/dark token mappings for navigation and switcher)
   - Add `SacramentNavigationColors` data class
   - Define light and dark variants
   - Map to bottom bar visual requirements

2. **Define Switcher Tokens** (Same story)
   - Add switcher tokens to utilities or create new category
   - Align with sun/moon toggle reference design

3. **Expand Dark Theme** (This story - TASK: Document gaps)
   - Create dark variants of tinted surfaces
   - Update `DarkSacramentColors` with complete mappings
   - Validate semantic colors on dark backgrounds

4. **Code Review** (This story - final task)
   - Verify all tokens follow naming patterns
   - Check contrast ratios programmatically if possible
   - Ensure no hardcoded colors remain in components
   - Validate with design team (if applicable)

## References

- **Plan**: `docs/darkLightThemePlan.md`
- **Theme Entry**: `sacrament/src/main/java/com/sacrament/ui/foundation/Theme.kt`
- **Color Tokens**: `sacrament/src/main/java/com/sacrament/ui/foundation/color/SacramentColorTokens.kt`
- **Light Colors**: `sacrament/src/main/java/com/sacrament/ui/foundation/color/LightColors.kt`
- **Dark Colors**: `sacrament/src/main/java/com/sacrament/ui/foundation/color/DarkColors.kt`
- **Palette**: `sacrament/src/main/java/com/sacrament/ui/foundation/color/Palette.kt`

## Conclusion

The Sacrament design system has a solid foundation for dark/light theme support with proper infrastructure in place. However, the dark theme implementation is currently minimal, requiring significant expansion to match light theme completeness and meet the requirements outlined in the dark/light theme plan.

The most critical gaps are:
1. Incomplete dark mode color mappings (only text and basic surfaces defined)
2. Missing navigation-specific tokens for bottom bar styling
3. Missing switcher component tokens

Addressing these gaps will enable full implementation of the dark mode features described in the plan.
