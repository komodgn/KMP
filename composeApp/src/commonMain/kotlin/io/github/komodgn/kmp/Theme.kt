/*
 * Copyright (C) 2026 komodgn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    outline = Color(0xFF30363D),
)

@Composable
internal fun getModeColor(mode: CalendarScrollOrientation): Color = when (mode) {
    CalendarScrollOrientation.Horizontal -> NeonGreen
    CalendarScrollOrientation.None -> NeonBlue
}
