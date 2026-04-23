package io.github.komodgn.kmp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.komodgn.kmp.calendar.ui.MetaCalendar
import kotlinx.coroutines.launch
import kotlinx.datetime.Month

@Composable
@Preview
fun App() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    MaterialTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        ) { innerPadding ->
            MetaCalendar(
                modifier = Modifier.padding(innerPadding),
                year = 2026,
                month = Month.APRIL,
                onDayClick = { date ->
                    scope.launch {
                        snackbarHostState.showSnackbar("Select ${date.dayOfMonth}")
                    }
                },
            )
        }
    }
}