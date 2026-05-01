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

import kotlinx.datetime.Month
import kotlinx.datetime.number

fun Month.length(isLeapYear: Boolean): Int = when (this) {
    Month.FEBRUARY -> if (isLeapYear) 29 else 28
    Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
    else -> 31
}

internal fun Month.previous(year: Int): Pair<Int, Month> = if (this == Month.JANUARY) {
    (year - 1) to Month.DECEMBER
} else {
    year to Month(this.number - 1)
}

internal fun Month.next(year: Int): Pair<Int, Month> = if (this == Month.DECEMBER) {
    (year + 1) to Month.JANUARY
} else {
    year to Month(this.number + 1)
}

internal fun Int.isLeapYear() = (this % 4 == 0 && this % 100 != 0) || (this % 400 == 0)
