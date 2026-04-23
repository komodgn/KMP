package io.github.komodgn.kmp.calendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.komodgn.kmp.calendar.core.CalendarEngine
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

@Composable
fun MetaCalendar(
    modifier: Modifier = Modifier,
    year: Int,
    month: Month,
    onDayClick: (LocalDate) -> Unit = {},
) {
    val days = remember(year, month) { CalendarEngine().getDaysInMonth(year, month) }

    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    Column(modifier.padding(16.dp)) {
        Text("$month $year", style = MaterialTheme.typography.headlineSmall)

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(days) { date ->
                val isSelected = date != null && date == selectedDate

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        Modifier
                            .height(40.dp)
                            .aspectRatio(0.9f)
                            .clip(CircleShape)
                            .background(if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
                            .clickable(enabled = date != null) {
                                selectedDate = date
                                if (date != null) onDayClick(date)
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = date?.dayOfMonth?.toString() ?: "",
                            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            }
        }
    }
}