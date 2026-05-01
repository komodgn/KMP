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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import io.github.komodgn.kmp.calendar.core.CalendarEngine
import io.github.komodgn.kmp.calendar.core.CalendarScrollOrientation
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.number

class CalendarState(
    val initialYear: Int,
    val initialMonth: Month,
    val scrollOrientation: CalendarScrollOrientation,
) {
    var currentYear by mutableStateOf(initialYear)
    var currentMonth by mutableStateOf(initialMonth)

    var selectedDate by mutableStateOf<LocalDate?>(null)
        private set

    fun moveToNextMonth() {
        if (currentMonth == Month.DECEMBER) {
            currentYear += 1
            currentMonth = Month.JANUARY
        } else {
            currentMonth = Month(currentMonth.number + 1)
        }
    }

    fun moveToPreviousMonth() {
        if (currentMonth == Month.JANUARY) {
            currentYear -= 1
            currentMonth = Month.DECEMBER
        } else {
            currentMonth = Month(currentMonth.number - 1)
        }
    }

    fun updateCurrentMonth(year: Int, month: Month) {
        currentYear = year
        currentMonth = month
    }

    fun updateDateFromPage(page: Int, initialPage: Int) {
        val (year, month) = CalendarEngine().calculateYearMonthFromPage(
            page = page,
            initialPage = initialPage,
            initialYear = initialYear,
            initialMonth = initialMonth,
        )
        currentYear = year
        currentMonth = month
    }

    fun selectDate(date: LocalDate?) {
        selectedDate = date
    }
}

@Composable
fun rememberCalendarState(
    initialYear: Int = 2026,
    initialMonth: Month = Month.APRIL,
    scrollOrientation: CalendarScrollOrientation = CalendarScrollOrientation.None,
): CalendarState = rememberSaveable(
    saver = Saver(
        save = { listOf(it.currentYear, it.currentMonth.number, it.selectedDate?.toString()) },
        restore = { value ->
            val list = value as List<*>
            CalendarState(
                initialYear = list[0] as Int,
                initialMonth = Month(list[1] as Int),
                scrollOrientation = scrollOrientation,
            ).apply {
                updateCurrentMonth(list[0] as Int, Month(list[1] as Int))
                selectDate((list[2] as? String)?.let { LocalDate.parse(it) })
            }
        },
    ),
) {
    CalendarState(initialYear, initialMonth, scrollOrientation)
}
