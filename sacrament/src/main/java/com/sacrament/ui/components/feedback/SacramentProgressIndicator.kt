package com.sacrament.ui.components.feedback

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Progress indicator variants.
 */
enum class SacramentProgressVariant {
    Circular,
    Linear,
}

/**
 * Progress indicator with determinate or indeterminate modes.
 */
@Composable
fun SacramentProgressIndicator(
    progress: Float? = null,
    variant: SacramentProgressVariant = SacramentProgressVariant.Circular,
    color: Color = SacramentTheme.colors.brand.primary,
    trackColor: Color = SacramentTheme.colors.utilities.progressTrack,
    strokeWidth: Dp = 4.dp,
    modifier: Modifier = Modifier,
) {
    when (variant) {
        SacramentProgressVariant.Circular -> {
            val infiniteTransition = rememberInfiniteTransition(label = "SacramentProgress")
            val rotation by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 1200, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart,
                ),
                label = "SacramentProgressRotation",
            )
            Canvas(modifier = modifier.size(32.dp)) {
                val stroke = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
                drawArc(
                    color = trackColor,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = stroke,
                )
                val sweep = (progress ?: 0.75f) * 360f
                drawArc(
                    color = color,
                    startAngle = rotation,
                    sweepAngle = sweep,
                    useCenter = false,
                    style = stroke,
                )
            }
        }
        SacramentProgressVariant.Linear -> {
            Canvas(modifier = modifier.height(strokeWidth)) {
                val strokeHeight = strokeWidth.toPx()
                val width = size.width
                val height = strokeHeight
                drawRect(
                    color = trackColor,
                    size = Size(width, height),
                )
                val progressWidth = if (progress != null) width * progress.coerceIn(0f, 1f) else width * 0.4f
                drawRect(
                    color = color,
                    size = Size(progressWidth, height),
                    topLeft = Offset.Zero,
                )
            }
        }
    }
}

@Preview
@Composable
private fun SacramentProgressIndicatorPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentProgressIndicator()
    }
}
