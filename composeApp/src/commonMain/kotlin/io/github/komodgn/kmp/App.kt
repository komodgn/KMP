package io.github.komodgn.kmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.komodgn.kmp.calendar.core.CalendarEngine
import kotlinx.datetime.Month

@Composable
@Preview
fun App() {
    MaterialTheme {
        val days = remember { CalendarEngine().getDaysInMonth(2026, Month.APRIL) }

        Scaffold { innerPadding ->
            Column(Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            ) {
                Text("April 2026", style = MaterialTheme.typography.headlineSmall)

                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(days) { date ->
                        Box(Modifier.height(40.dp), contentAlignment = Alignment.Center) {
                            Text(text = date?.dayOfMonth?.toString() ?: "")
                        }
                    }
                }
            }
        }
    }
}