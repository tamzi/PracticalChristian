package com.sacrament.ui.foundation.motion

import androidx.compose.runtime.Immutable
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * Motion timing tokens for consistent animation pacing.
 */
@Immutable
data class SacramentMotion(
    val fast: Duration = 150.milliseconds,
    val medium: Duration = 300.milliseconds,
    val slow: Duration = 500.milliseconds,
)
