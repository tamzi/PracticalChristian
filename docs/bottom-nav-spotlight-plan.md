# Bottom Navigation Spotlight Indicator - Implementation Plan

## Overview

Implement a theme-adaptive selection indicator for the bottom navigation bar that creates a "spotlight lamp" effect in dark mode while maintaining a clean indicator in light mode.

## Design Concept

### Visual Behavior

**Light Mode:**
- Colored pill indicator at the top (traditional tab indicator)
- **No glow/spotlight effect**
- Standard icon colors (purple selected, gray unselected)
- Clean, minimal look
- Text labels present

**Dark Mode:**
- White pill indicator at the top (acts as a "lamp")
- **Gradient spotlight glow** shining down from the lamp to the icon
- **White icon** for selected item (illuminated by the lamp)
- Dark gray icons for unselected items (in shadow)
- Creates dramatic "lamp spotlight" metaphor
- Text labels present

### Key Visual Elements

| Theme | Indicator | Glow | Selected Icon | Unselected Icon | Labels |
|-------|-----------|------|---------------|-----------------|---------|
| **Light** | Purple pill | None | Purple | Gray | Yes |
| **Dark** | White pill (lamp) | Yes (gradient) | White (lit) | Dark gray (shadow) | Yes |

### Indicator Specifications

**Lamp/Indicator Pill:**
- Width: ~40-60dp (slightly wider than icon)
- Height: ~4-6dp
- Shape: Rounded rectangle (`RoundedCornerShape`)
- Position: Top edge of bottom bar, horizontally centered above selected item
- Color (Light): Brand purple (`navigationSelectedLight`)
- Color (Dark): White (`navigationIndicatorDark`)

**Spotlight Glow (Dark Mode Only):**
- Type: Vertical linear gradient
- Start: White with ~30% opacity (`navigationSpotlightDark`)
- End: Transparent
- Shape: Vertical rectangle from lamp to icon
- Width: Slightly wider than indicator pill

**Bottom Bar Container:**
- Shape: Rounded corners (~16-24dp radius)
- Background: Theme-appropriate surface color
- Maintains current dimensions and padding

---

## Technical Implementation

### Component Architecture

```
Box (layering container)
  └─ SacramentBottomBar (rounded container)
      ├─ SpotlightGlow (dark mode only - behind icons)
      ├─ IndicatorPill (both themes - above icons)
      └─ Row of navigation items
           └─ BottomNavItem (icon + text)
```

### New Components to Create

#### 1. IndicatorPill Component

```kotlin
@Composable
private fun IndicatorPill(
    selectedIndex: Int,
    itemWidth: Dp,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    val indicatorColor = if (isDarkTheme) {
        SacramentTheme.colors.navigation.selectedIndicator // White
    } else {
        SacramentTheme.colors.navigation.selectedIcon // Purple
    }
    
    val offsetX by animateDpAsState(
        targetValue = itemWidth * selectedIndex,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )
    
    Box(
        modifier = modifier
            .offset(x = offsetX)
            .width(48.dp) // Adjust as needed
            .height(4.dp)
            .background(
                color = indicatorColor,
                shape = RoundedCornerShape(2.dp)
            )
    )
}
```

#### 2. SpotlightGlow Component

```kotlin
@Composable
private fun SpotlightGlow(
    selectedIndex: Int,
    itemWidth: Dp,
    modifier: Modifier = Modifier
) {
    val offsetX by animateDpAsState(
        targetValue = itemWidth * selectedIndex,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )
    
    Box(
        modifier = modifier
            .offset(x = offsetX)
            .width(56.dp) // Slightly wider than indicator
            .fillMaxHeight()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SacramentTheme.colors.navigation.spotlightGlow,
                        Color.Transparent
                    )
                )
            )
    )
}
```

### Updated Components

#### 3. SacramentBottomBar Refactor

**Current signature:**
```kotlin
@Composable
fun SacramentBottomBar(
    modifier: Modifier = Modifier,
    colors: SacramentBottomBarColors = SacramentBottomBarDefaults.colors(),
    content: @Composable RowScope.() -> Unit,
)
```

**New signature:**
```kotlin
@Composable
fun SacramentBottomBar(
    selectedIndex: Int = 0, // NEW: Track current selection
    modifier: Modifier = Modifier,
    colors: SacramentBottomBarColors = SacramentBottomBarDefaults.colors(),
    content: @Composable RowScope.() -> Unit,
)
```

**Implementation changes:**
- Add rounded corners to container
- Wrap in `Box` for layering
- Calculate item width for positioning
- Conditionally render spotlight glow (dark mode only)
- Always render indicator pill
- Animate indicator position

#### 4. BottomNavItem Updates

**Icon color logic:**

```kotlin
val isDarkTheme = !SacramentTheme.colors.isLight
val contentColor = when {
    // Dark mode: white when selected (illuminated), dark gray when not
    isDarkTheme && selected -> Color.White
    isDarkTheme && !selected -> SacramentTheme.colors.text.muted // Neutral600
    
    // Light mode: purple when selected, gray when not
    !isDarkTheme && selected -> SacramentTheme.colors.navigation.selectedIcon
    else -> SacramentTheme.colors.navigation.unselectedIcon
}
```

**Keep text labels** (both themes)

---

## Color System Updates

### Required Color Definitions

#### Palette.kt

Verify these colors exist:

```kotlin
object SacramentPalette {
    // Navigation colors - Light theme
    val navigationSelectedLight = brandPurple // Indicator color
    val navigationUnselectedLight = textMuted
    
    // Navigation colors - Dark theme
    val navigationIndicatorDark = Color(0xFFEEEEEE) // White pill/lamp
    val navigationSelectedDark = Color(0xFF8B7AFF) // Lighter purple for icons (if needed)
    val navigationUnselectedDark = Neutral400
    val navigationSpotlightDark = navigationSelectedDark.copy(alpha = 0.3f) // Gradient glow
}
```

#### DarkColors.kt

Verify wiring in `DarkSacramentColors`:

```kotlin
navigation = SacramentNavigationColors(
    barBackground = SacramentPalette.navigationBarDark,
    selectedIndicator = SacramentPalette.navigationIndicatorDark, // White
    selectedIcon = SacramentPalette.navigationSelectedDark, // Purple (or white based on design)
    unselectedIcon = SacramentPalette.navigationUnselectedDark,
    spotlightGlow = SacramentPalette.navigationSpotlightDark,
)
```

### Theme Detection

```kotlin
val isDarkTheme = !SacramentTheme.colors.isLight
```

Or access directly from theme configuration if available.

---

## Implementation Steps

### Phase 1: Preparation
1. ✅ Review and verify color definitions in `Palette.kt`
2. ✅ Verify dark theme color wiring in `DarkColors.kt`
3. ✅ Ensure `SacramentNavigationColors` has `selectedIndicator` and `spotlightGlow` properties

### Phase 2: Core Components
4. Create `IndicatorPill` composable in `SacramentBottomBar.kt`
5. Create `SpotlightGlow` composable in `SacramentBottomBar.kt`
6. Refactor `SacramentBottomBar` to:
   - Accept `selectedIndex` parameter
   - Add rounded corners to container
   - Layer components (Box structure)
   - Calculate item width for positioning
   - Conditionally render spotlight based on theme

### Phase 3: Navigation Item Updates
7. Update `BottomNavItem` in `BottomNavigationBar.kt`:
   - Implement theme-aware icon color logic
   - Keep text labels
   - Ensure proper spacing

8. Update `BottomBarItem` in `BottomBarCatalogScreen.kt`:
   - Same icon color logic
   - Keep text labels

### Phase 4: Integration
9. Update `SharedBottomNavigationBar` in `BottomNavigationBar.kt`:
   - Pass `selectedScreen` index to `SacramentBottomBar`
   - Convert enum to index

10. Update `BottomNavigationBar` in same file:
    - Track or calculate selected index
    - Pass to `SacramentBottomBar`

11. Update demo in `BottomBarCatalogScreen.kt`:
    - Pass `selectedIndex` state to `SacramentBottomBar`

### Phase 5: Animation & Polish
12. Add smooth animations:
    - Indicator position animation (`animateDpAsState`)
    - Icon color transitions (`animateColorAsState`)
    - Consider spotlight fade on theme switch

13. Test in both themes:
    - Verify indicator positioning
    - Check glow appearance in dark mode
    - Verify no glow in light mode
    - Validate touch targets

14. Create Compose previews for both themes

---

## Files to Modify

| File | Changes | Priority |
|------|---------|----------|
| `Palette.kt` | Verify spotlight colors defined | High |
| `DarkColors.kt` | Verify dark navigation colors wired up | High |
| `SacramentBottomBar.kt` | Add indicator pill, conditional glow, selection tracking, layering | High |
| `SacramentBottomBarDefaults.kt` | May need updates if colors change | Medium |
| `BottomNavigationBar.kt` | Theme-aware icon colors, pass selection index | High |
| `BottomBarCatalogScreen.kt` | Same updates for demo | Medium |

---

## Design Considerations

### Accessibility
- Maintain minimum 48x48dp touch targets for icons
- Ensure sufficient color contrast in both themes
- Verify screen reader announcements work properly
- Consider haptic feedback on selection change

### Performance
- Use `animateDpAsState` for smooth position changes
- Spotlight glow only renders in dark mode (performance optimization)
- Gradient uses simple vertical brush (efficient)

### Responsiveness
- Item width calculation should adapt to screen size
- Indicator position scales with available space
- Consider different layouts for tablets vs phones

### Animation Timing
- Indicator movement: ~300ms with `FastOutSlowInEasing`
- Icon color change: ~200ms
- Consider staggered timing for polished feel

---

## Testing Checklist

### Visual Tests
- [ ] Light mode: Indicator shows in brand purple
- [ ] Light mode: No spotlight glow visible
- [ ] Dark mode: Indicator shows in white (lamp)
- [ ] Dark mode: Spotlight glow visible and smooth
- [ ] Selected icon color correct in both themes
- [ ] Unselected icon color correct in both themes
- [ ] Indicator aligns properly above selected item
- [ ] Rounded corners on bottom bar container

### Interaction Tests
- [ ] Tapping items updates selection
- [ ] Indicator animates smoothly between items
- [ ] Icon colors transition smoothly
- [ ] Touch targets are adequate (48dp minimum)
- [ ] Works with 3 items (Home, Books, Notes)
- [ ] Works with different numbers of items

### Theme Switch Tests
- [ ] Switching light → dark shows spotlight
- [ ] Switching dark → light hides spotlight
- [ ] Indicator color updates correctly
- [ ] Icon colors update correctly
- [ ] No visual glitches during transition

### Edge Cases
- [ ] Works on different screen sizes
- [ ] Works in landscape orientation
- [ ] Works with RTL languages (if applicable)
- [ ] Handles rapid tab switching
- [ ] Initial selection renders correctly

---

## Animation Specifications

### Indicator Movement
```kotlin
animateDpAsState(
    targetValue = itemWidth * selectedIndex,
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing
    )
)
```

### Icon Color Transition
```kotlin
animateColorAsState(
    targetValue = contentColor,
    animationSpec = tween(
        durationMillis = 200,
        easing = LinearEasing
    )
)
```

### Spotlight Fade (Optional)
```kotlin
AnimatedVisibility(
    visible = isDarkTheme,
    enter = fadeIn(tween(200)),
    exit = fadeOut(tween(200))
) {
    SpotlightGlow(...)
}
```

---

## Future Enhancements

### Potential Improvements
- **Pulsing effect**: Subtle lamp glow animation
- **Icon scale**: Selected icon slightly larger
- **Ripple effect**: Custom ripple that respects spotlight theme
- **Haptic feedback**: Different patterns for light vs dark mode
- **Dynamic positioning**: Indicator follows gesture during swipe
- **3D depth**: Add subtle shadow to enhance lamp metaphor

### Alternative Approaches
- Use `Canvas` for custom gradient shapes (more control)
- Implement conical gradient for realistic spotlight
- Add subtle noise/texture to spotlight for organic feel
- Experiment with blur effects for softer glow edges

---

## References

### Related Components
- `SacramentBottomBar.kt` - Main container component
- `SacramentBottomBarDefaults.kt` - Default values and colors
- `BottomNavigationBar.kt` - App-specific usage
- `BottomBarCatalogScreen.kt` - Demo/preview screen

### Design Resources
- Original design reference: `@Screenshot 2026-01-20 at 06.31.13.png`
- Current implementation: `@pasted_image_16129651142469863453.png`

### Color Token Structure
```kotlin
data class SacramentNavigationColors(
    val barBackground: Color,      // Bottom bar background
    val selectedIndicator: Color,  // Top pill/lamp indicator
    val selectedIcon: Color,       // Selected icon tint
    val unselectedIcon: Color,     // Unselected icon tint
    val spotlightGlow: Color,      // Gradient glow color (dark mode)
)
```

---

## Notes

- The indicator pill is **always present** in both themes for consistency
- The spotlight glow is **dark mode exclusive** for thematic appropriateness
- Text labels remain visible in current design (icon-only can be future enhancement)
- Animation timing should feel snappy but not rushed (~300ms sweet spot)
- Gradient glow should be subtle (~30% opacity max) to avoid overwhelming design

---

## Status

**Status:** Planning Complete  
**Date:** January 25, 2026  
**Next Step:** Begin Phase 1 (Color verification)
