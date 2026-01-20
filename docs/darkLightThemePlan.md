# Dark + Light Theme Plan

## Goal
Create a cohesive light and dark theme for the app that matches the provided references, while
keeping existing icons unchanged.

Users choose whether the app runs in light or dark mode (explicit preference, not automatic only).

## Reference Inputs
- Dark mode bottom navigation reference (rounded bar with spotlight above selected icon).
- Light/dark theme switcher reference (sun/moon toggle with circular knob).

## Scope
- Sacrament theme tokens (light + dark palettes).
- Bottom navigation appearance in dark mode (colors, spotlight, elevation, and contrast).
- Theme switcher UI and behavior (visuals + persisted preference).
- Switcher component styling in Sacrament (track, thumb, icon slots, states).
- User-selectable theme preference (light or dark).
- Previews and validation for both themes.

## Plan
1. Inventory current theme and navigation bar usage.
   - Review Sacrament theme entry points: `sacrament/src/main/java/com/sacrament/ui/foundation/Theme.kt`.
   - Review color tokens: `sacrament/src/main/java/com/sacrament/ui/foundation/color/`.
   - Review bottom bar component: `sacrament/src/main/java/com/sacrament/ui/components/navigation/`.
   - Identify app-level usage of the bottom bar: `feature/*/` screens and `app/src/main/java/...`.

2. Define light and dark token targets.
   - Confirm surface, on-surface, accent, and shadow values needed for the bottom bar.
   - Map tokens to ensure the dark bottom bar uses the spotlight effect while keeping icons unchanged.
   - Validate contrast for text/icons against background.

3. Theme switcher design alignment.
   - Add a new switcher in the Settings screen: `feature/settings/src/main/java/com/practicalchristian/app/feature/settings/SettingsScreen.kt`.
   - Review switcher component and defaults in `sacrament/src/main/java/com/sacrament/ui/components/input/`.
   - Align toggle visuals to the reference (sun/moon icons, track/knob colors, rounded shape).
   - Ensure tap target and accessibility remain compliant.

4. Switcher component coverage.
   - Confirm switcher states in light/dark (off/on) map to the new tokens.
   - Verify the switcher supports the needed icon placement without altering the icon assets.
   - Ensure sizes and padding match the reference while preserving hit targets.
   - Add the updated switcher to the Sacrament demo app for review.

5. Persisted theme behavior.
   - Review theme preference storage: `core/datasource/local/src/main/java/com/practicalchristian/app/core/localdatasource/preferences/user/`.
   - Confirm the app uses the stored preference to select the theme in `app/src/main/java/...`.
   - Ensure user selection (light or dark) is respected across app launches.
   - Define any missing state wiring needed for the toggle to update theme in real time.

6. Preview and QA checklist.
   - Add/verify previews for light and dark variants in Sacrament components.
   - Validate bottom bar in light + dark themes, including spotlight alignment.
   - Check system bars, navigation bar appearance, and contrast ratios.

## Acceptance Criteria
- Dark mode bottom bar matches the reference: rounded bar, spotlight highlight above selected icon.
- Light mode top indicator remains; icons are not altered or replaced.
- Theme toggle matches the switcher reference and changes theme consistently.
- Users can explicitly choose light or dark mode, and the choice persists.
- Light and dark themes are accessible and consistent across core screens.
