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
package io.github.komodgn.kmp.calendar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.komodgn.kmp.calendar.core.CalendarEngine
import io.github.komodgn.kmp.calendar.core.CalendarScrollOrientation
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MetaCalendar(
    modifier: Modifier = Modifier,
    state: CalendarState = rememberCalendarState(),
    onDayClick: (LocalDate) -> Unit = {},
    onHeaderClick: (year: Int, month: Month) -> Unit = { _, _ -> },
    options: CalendarOptions = CalendarOptions(),
) {
    val calendarColors = options.getColors()

    val days = remember(state.currentYear, state.currentMonth, options.showAdjacentMonths) {
        CalendarEngine().getCalendarDates(
            year = state.currentYear,
            month = state.currentMonth,
            showAdjacentMonths = options.showAdjacentMonths,
        )
    }

    val initialPage = 500
    val pagerState = if (state.scrollOrientation == CalendarScrollOrientation.Horizontal) {
        rememberPagerState(initialPage = initialPage, pageCount = { 1000 })
    } else {
        null
    }

    if (state.scrollOrientation == CalendarScrollOrientation.Horizontal && pagerState != null) {
        LaunchedEffect(pagerState.currentPage) {
            state.updateDateFromPage(pagerState.currentPage, initialPage)
        }
    }

    Surface(
        modifier = modifier,
        color = calendarColors.containerColor,
    ) {
        Column(Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier.fillMaxWidth().height(48.dp),
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = if (state.scrollOrientation == CalendarScrollOrientation.None) {
                        Arrangement.SpaceBetween
                    } else {
                        Arrangement.Center
                    },
                ) {
                    if (state.scrollOrientation == CalendarScrollOrientation.None) {
                        IconButton(onClick = { state.moveToPreviousMonth() }) {
                            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Prev", tint = calendarColors.headerIconTint)
                        }
                    }

                    Text(
                        modifier = Modifier
                            .noRippleClickable { onHeaderClick(state.currentYear, state.currentMonth) },
                        text = "${state.currentMonth.name} ${state.currentYear}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = calendarColors.headerTextColor,
                    )

                    if (state.scrollOrientation == CalendarScrollOrientation.None) {
                        IconButton(onClick = { state.moveToNextMonth() }) {
                            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next", tint = calendarColors.headerIconTint)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            when (state.scrollOrientation) {
                CalendarScrollOrientation.None -> {
                    CalendarMonthSheet(
                        days = days,
                        currentMonth = state.currentMonth,
                        selectedDate = state.selectedDate,
                        onDayClick = { date ->
                            state.selectDate(date)
                            onDayClick(date)
                        },
                        options = options,
                    )
                }

                CalendarScrollOrientation.Horizontal -> {
                    HorizontalPager(
                        state = pagerState!!,
                        modifier = Modifier.fillMaxWidth(),
                    ) { page ->
                        val (displayYear, displayMonth) = CalendarEngine().calculateYearMonthFromPage(
                            page,
                            initialPage,
                            state.initialYear,
                            state.initialMonth,
                        )

                        val displayDays =
                            remember(displayYear, displayMonth, options.showAdjacentMonths) {
                                CalendarEngine().getCalendarDates(
                                    year = displayYear,
                                    month = displayMonth,
                                    showAdjacentMonths = options.showAdjacentMonths,
                                )
                            }

                        CalendarMonthSheet(
                            days = displayDays,
                            currentMonth = displayMonth,
                            selectedDate = state.selectedDate,
                            onDayClick = { date ->
                                state.selectDate(date)
                                onDayClick(date)
                            },
                            options = options,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MetaCalendarPreview() {
    MaterialTheme {
        MetaCalendar(
            state = rememberCalendarState(2026, Month.APRIL),
        )
    }
}
