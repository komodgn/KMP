package io.github.komodgn.kmp.calendar.core

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.isoDayNumber

fun Month.length(isLeapYear: Boolean): Int {
    return when (this) {
        Month.FEBRUARY -> if (isLeapYear) 29 else 28
        Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
        else -> 31
    }
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

    private fun isLeapYear(year: Int) = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}