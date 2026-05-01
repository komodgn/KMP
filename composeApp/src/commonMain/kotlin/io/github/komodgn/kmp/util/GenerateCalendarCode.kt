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
package io.github.komodgn.kmp.util

import io.github.komodgn.kmp.calendar.core.CalendarScrollOrientation

internal fun generateCalendarCode(
    orientation: CalendarScrollOrientation,
    showAdjacent: Boolean,
    containerColor: String = "Color.Transparent",
    selectedColor: String = "glowColor",
): String = """
        val calendarState = rememberCalendarState(
            initialYear = 2026,
            initialMonth = Month.APRIL,
            scrollOrientation = CalendarScrollOrientation.${orientation.name}
        )
    
        MetaCalendar(
            state = calendarState,
            options = CalendarOptions(
                showAdjacentMonths = $showAdjacent,
                colors = CalendarDefaults.colors(
                    containerColor = $containerColor,
                    selectedContainerColor = $selectedColor.copy(alpha = 0.2f),
                    selectedContentColor = $selectedColor, 
                ),
            ),
        )
""".trimIndent()
