package com.sacrament.ui.foundation.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
enum class BrandColorRole {
    Primary,
    Secondary,
    Tertiary,
}

@Immutable
enum class SemanticColorRole {
    Success,
    Warning,
    Error,
    Info,
}

fun SacramentColorTokens.brand(role: BrandColorRole): Color = when (role) {
    BrandColorRole.Primary -> brand.primary
    BrandColorRole.Secondary -> brand.secondary
    BrandColorRole.Tertiary -> brand.tertiary
}

fun SacramentColorTokens.semantic(role: SemanticColorRole): Color = when (role) {
    SemanticColorRole.Success -> semantic.success
    SemanticColorRole.Warning -> semantic.warning
    SemanticColorRole.Error -> semantic.error
    SemanticColorRole.Info -> semantic.info
}
