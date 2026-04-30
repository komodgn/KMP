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
package io.github.komodgn.kmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.komodgn.kmp.component.SideNavigationRail
import io.github.komodgn.kmp.page.CustomsPage
import io.github.komodgn.kmp.page.DemoLandingPage
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var currentRoute by remember { mutableStateOf("Home") }

    MaterialTheme(
        colorScheme = CustomColorScheme,
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val isWideScreen = maxWidth > 600.dp

            Scaffold(
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
            ) { innerPadding ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                ) {
                    if (isWideScreen) {
                        SideNavigationRail(
                            modifier = Modifier.width(80.dp),
                            selectedRoute = currentRoute,
                            onRouteSelected = { currentRoute = it },
                        )
                    }

                    Box(modifier = Modifier.weight(1f)) {
                        when (currentRoute) {
                            "Home" -> DemoLandingPage(
                                isWideScreen = isWideScreen,
                                onDayClick = { date ->
                                    scope.launch { snackbarHostState.showSnackbar("$date") }
                                },
                                onHeaderClick = { year, month ->
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Click the calendar header to trigger a custom event. $year $month")
                                    }
                                },
                            )

                            "Customs" -> CustomsPage()
                        }
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 1700)
@Composable
private fun AppWidePreview() {
    MaterialTheme(
        colorScheme = CustomColorScheme,
    ) {
        App()
    }
}
