package io.github.komodgn.kmp

import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.github.komodgn.kmp.calendar.core.CalendarScrollOrientation

val DarkBackground = Color(0xFF0B0F19)
val CardBackground = Color(0xFF161B22)
val NeonGreen = Color(0xFF00E676)
val NeonPurple = Color(0xFFD500F9)
val NeonBlue = Color(0xFF2979FF)

internal val CustomColorScheme = darkColorScheme(
    primary = Color(0xFF38BDF8),
    surface = DarkBackground,
    onSurface = Color.White,
    onSurfaceVariant = Color(0xFF94A3B8),
    outline = Color(0xFF30363D)
)

@Composable
internal fun getModeColor(mode: CalendarScrollOrientation): Color {
    return when (mode) {
        CalendarScrollOrientation.Horizontal -> NeonGreen
        CalendarScrollOrientation.None -> NeonBlue
    }
}
