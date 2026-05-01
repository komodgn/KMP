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
package io.github.komodgn.kmp.calendar.core

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.number

private const val CALENDAR_GRID_SIZE = 42

class CalendarEngine {

    fun getCalendarDates(year: Int, month: Month, showAdjacentMonths: Boolean): List<LocalDate?> {
        val days = mutableListOf<LocalDate?>()
        val firstDayOfMonth = LocalDate(year, month, 1)
        val firstDayOfWeek = firstDayOfMonth.dayOfWeek.isoDayNumber % 7

        if (showAdjacentMonths) {
            val (prevYear, prevMonth) = month.previous(year)
            val prevMonthLen = prevMonth.length(prevYear.isLeapYear())
            for (day in (prevMonthLen - firstDayOfWeek + 1)..prevMonthLen) {
                days.add(LocalDate(prevYear, prevMonth, day))
            }
        } else {
            repeat(firstDayOfWeek) { days.add(null) }
        }

        val daysInMonth = month.length(year.isLeapYear())
        for (day in 1..daysInMonth) {
            days.add(LocalDate(year, month, day))
        }

        if (showAdjacentMonths) {
            val (nextYear, nextMonth) = month.next(year)
            val remainingSlots = CALENDAR_GRID_SIZE - days.size
            for (day in 1..remainingSlots) {
                days.add(LocalDate(nextYear, nextMonth, day))
            }
        }

        return days
    }

    fun calculateYearMonthFromPage(
        page: Int,
        initialPage: Int,
        initialYear: Int,
        initialMonth: Month,
    ): Pair<Int, Month> {
        val totalMonths = (initialYear * 12 + (initialMonth.number - 1)) + (page - initialPage)

        val year = if (totalMonths >= 0) totalMonths / 12 else (totalMonths - 11) / 12
        val monthIndex = ((totalMonths % 12) + 12) % 12

        return year to Month(monthIndex + 1)
    }
}
