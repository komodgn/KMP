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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.komodgn.kmp.calendar.core.CalendarEngine
import io.github.komodgn.kmp.calendar.core.CalendarScrollOrientation
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.number
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MetaCalendar(
    modifier: Modifier = Modifier,
    scrollOrientation: CalendarScrollOrientation = CalendarScrollOrientation.None,
    initialYear: Int,
    initialMonth: Month,
    onDayClick: (LocalDate) -> Unit = {},
    onHeaderClick: (year: Int, month: Month) -> Unit = { _, _ -> },
    options: CalendarOptions = CalendarOptions(),
) {
    var currentYear by remember { mutableStateOf(initialYear) }
    var currentMonth by remember { mutableStateOf(initialMonth) }

    val days = remember(currentYear, currentMonth, options.showAdjacentMonths) {
        CalendarEngine().getCalendarDates(
            year = currentYear,
            month = currentMonth,
            showAdjacentMonths = options.showAdjacentMonths,
        )
    }

    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    val handleDayClick: (LocalDate) -> Unit = { date ->
        selectedDate = date
        onDayClick(date)
    }

    when (scrollOrientation) {
        CalendarScrollOrientation.None -> {
            Column(modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    IconButton(onClick = {
                        if (currentMonth == Month.JANUARY) {
                            currentMonth = Month.DECEMBER
                            currentYear -= 1
                        } else {
                            currentMonth = Month(currentMonth.number - 1)
                        }
                    }) {
                        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Previous Month")
                    }

                    Text(
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) {
                                onHeaderClick(currentYear, currentMonth)
                            },
                        text = "${currentMonth.name} $currentYear",
                        style = MaterialTheme.typography.headlineSmall,
                    )

                    IconButton(onClick = {
                        if (currentMonth == Month.DECEMBER) {
                            currentMonth = Month.JANUARY
                            currentYear += 1
                        } else {
                            currentMonth = Month(currentMonth.number + 1)
                        }
                    }) {
                        Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next Month")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                CalendarMonthSheet(
                    days = days,
                    currentMonth = currentMonth,
                    selectedDate = selectedDate,
                    onDayClick = handleDayClick,
                    options = options,
                )
            }
        }

        CalendarScrollOrientation.Horizontal -> {
            val initialPage = 500
            val pagerState = rememberPagerState(
                initialPage = initialPage,
                pageCount = { 1000 },
            )

            val (headerYear, headerMonth) = CalendarEngine().calculateYearMonthFromPage(
                pagerState.currentPage,
                initialPage,
                initialYear,
                initialMonth,
            )

            Column(modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) {
                                onHeaderClick(headerYear, headerMonth)
                            },
                        text = "${headerMonth.name} $headerYear",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth(),
                ) { page ->
                    val (displayYear, displayMonth) = CalendarEngine().calculateYearMonthFromPage(
                        page,
                        initialPage,
                        initialYear,
                        initialMonth,
                    )

                    val displayDays = remember(displayYear, displayMonth, options.showAdjacentMonths) {
                        CalendarEngine().getCalendarDates(
                            year = displayYear,
                            month = displayMonth,
                            showAdjacentMonths = options.showAdjacentMonths,
                        )
                    }

                    CalendarMonthSheet(
                        days = displayDays,
                        currentMonth = displayMonth,
                        selectedDate = selectedDate,
                        onDayClick = handleDayClick,
                        options = options,
                    )
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
            initialYear = 2026,
            initialMonth = Month.APRIL,
        )
    }
}
