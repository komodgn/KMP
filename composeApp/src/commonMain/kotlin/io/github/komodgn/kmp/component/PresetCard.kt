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
package io.github.komodgn.kmp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.komodgn.kmp.CardBackground
import io.github.komodgn.kmp.calendar.core.CalendarScrollOrientation
import io.github.komodgn.kmp.calendar.ui.CalendarOptions
import io.github.komodgn.kmp.calendar.ui.MetaCalendar
import io.github.komodgn.kmp.calendar.ui.rememberCalendarState
import io.github.komodgn.kmp.calendar.ui.theme.CalendarDefaults
import kotlinx.datetime.Month

@Composable
fun PresetCard(
    name: String,
    color: Color,
    scrollMode: String,
    backgroundContent: @Composable () -> Unit = { Box(Modifier.fillMaxSize().background(Color.Transparent)) },
) {
    val orientation = if (scrollMode == "Horizontal") {
        CalendarScrollOrientation.Horizontal
    } else {
        CalendarScrollOrientation.None
    }
    val calendarState = rememberCalendarState(2026, Month.APRIL, orientation)

    Column(
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(CardBackground)
            .border(1.dp, color.copy(alpha = 0.2f), RoundedCornerShape(24.dp))
            .padding(16.dp),
    ) {
        Text(name, color = color.copy(0.8f), fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .height(300.dp)
                .clip(RoundedCornerShape(16.dp)),
        ) {
            backgroundContent()
            MetaCalendar(
                state = calendarState,
                options = CalendarOptions(
                    colors = CalendarDefaults.colors(
                        selectedContainerColor = color.copy(0.7f),
                        selectedContentColor = Color.Black,
                        headerTextColor = color,
                        headerIconTint = color.copy(0.5f),
                    ),
                ),
            )
        }
    }
}
