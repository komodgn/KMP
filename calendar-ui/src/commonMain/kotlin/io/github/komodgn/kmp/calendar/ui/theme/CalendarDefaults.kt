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
package io.github.komodgn.kmp.calendar.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object CalendarDefaults {
    @Composable
    fun colors(
        containerColor: Color = Color.Transparent,
        headerTextColor: Color = MaterialTheme.colorScheme.onSurface,
        headerIconTint: Color = headerTextColor,
        weekHeaderTextColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
        dayTextColor: Color = MaterialTheme.colorScheme.onSurface,
        selectedContainerColor: Color = MaterialTheme.colorScheme.primary,
        selectedContentColor: Color = MaterialTheme.colorScheme.onPrimary,
        adjacentMonthAlpha: Float = 0.3f,
    ): CalendarColors = CalendarColors(
        containerColor = containerColor,
        headerTextColor = headerTextColor,
        headerIconTint = headerIconTint,
        weekHeaderTextColor = weekHeaderTextColor,
        dayTextColor = dayTextColor,
        selectedContainerColor = selectedContainerColor,
        selectedContentColor = selectedContentColor,
        adjacentMonthAlpha = adjacentMonthAlpha,
    )
}
