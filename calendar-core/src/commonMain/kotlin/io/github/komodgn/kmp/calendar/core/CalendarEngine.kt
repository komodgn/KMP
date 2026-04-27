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

fun Month.length(isLeapYear: Boolean): Int = when (this) {
    Month.FEBRUARY -> if (isLeapYear) 29 else 28
    Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
    else -> 31
}

class CalendarEngine {
    fun getDaysInMonth(year: Int, month: Month): List<LocalDate?> {
        val firstDayOfMonth = LocalDate(year, month, 1)
        val daysInMonth = firstDayOfMonth.month.length(isLeapYear(year))
        val firstDayOfWeek = firstDayOfMonth.dayOfWeek.isoDayNumber

        val days = mutableListOf<LocalDate?>()

        repeat(firstDayOfWeek % 7) { days.add(null) }

        for (i in 1..daysInMonth) {
            days.add(LocalDate(year, month, i))
        }

        return days
    }

    fun calculateYearMonthFromPage(
        page: Int,
        initialPage: Int,
        initialYear: Int,
        initialMonth: Month,
    ): Pair<Int, Month> {
        val diff = page - initialPage
        val totalMonths = (initialYear * 12 + (initialMonth.number - 1)) + diff

        val year = if (totalMonths >= 0) totalMonths / 12 else (totalMonths - 11) / 12
        val monthIndex = ((totalMonths % 12) + 12) % 12
        val month = Month(monthIndex + 1)

        return year to month
    }

    private fun isLeapYear(year: Int) = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}
