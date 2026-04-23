package io.github.komodgn.kmp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.komodgn.kmp.calendar.ui.MetaCalendar
import kotlinx.datetime.Month

@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold { innerPadding ->
            MetaCalendar(
                modifier = Modifier.padding(innerPadding),
                year = 2026,
                month = Month.APRIL,
            )
        }
    }
}