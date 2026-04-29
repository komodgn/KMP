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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.komodgn.kmp.calendar.core.CalendarScrollOrientation
import io.github.komodgn.kmp.calendar.ui.CalendarOptions
import io.github.komodgn.kmp.calendar.ui.MetaCalendar
import io.github.komodgn.kmp.component.CalendarControlSection
import io.github.komodgn.kmp.component.SideNavigationRail
import io.github.komodgn.kmp.util.generateCalendarCode
import kmp.composeapp.generated.resources.Res
import kmp.composeapp.generated.resources.github
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import org.jetbrains.compose.resources.painterResource

@Composable
fun DemoLandingPage(
    onDayClick: (LocalDate) -> Unit,
    onHeaderClick: (year: Int, month: Month) -> Unit = { _, _ -> },
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val wideScreen = maxWidth > 600.dp

        var showAdjacent by remember { mutableStateOf(true) }
        var selectedMode by remember { mutableStateOf(CalendarScrollOrientation.Horizontal) }

        val code = generateCalendarCode(
            selectedMode,
            showAdjacent,
            2026,
            Month.APRIL,
        )
        val glowColor = getModeColor(selectedMode)
        val uriHandler = LocalUriHandler.current
        val clipboardManager = LocalClipboardManager.current

        if (wideScreen) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                SideNavigationRail(modifier = Modifier.width(80.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 40.dp, vertical = 60.dp),
                    verticalArrangement = Arrangement.spacedBy(40.dp),
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = "Editor & Preview",
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.ExtraBold,
                        )
                        Text(
                            text = "Customize your calendar in real-time.",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }

                    Column {
                        Text(
                            "Control Panel",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 20.dp),
                        )
                        CalendarControlSection(
                            selectedMode = selectedMode,
                            onModeChange = { selectedMode = it },
                            showAdjacent = showAdjacent,
                            onAdjacentChange = { showAdjacent = it },
                        )
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom,
                        ) {
                            Text(
                                "Code Snippet",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )

                            Text(
                                text = "Copy",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .clickable {
                                        clipboardManager.setText(
                                            AnnotatedString(
                                                code,
                                            ),
                                        )
                                    }
                                    .padding(horizontal = 8.dp, vertical = 2.dp),
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                .padding(16.dp),
                        ) {
                            Text(
                                text = code,
                                fontFamily = FontFamily.Monospace,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1.2f)
                        .fillMaxHeight()
                        .padding(40.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(0.9f)
                            .shadow(
                                elevation = 120.dp,
                                shape = RoundedCornerShape(40.dp),
                                clip = false,
                                ambientColor = glowColor,
                                spotColor = glowColor,
                            ),
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(40.dp))
                            .background(CardBackground)
                            .border(2.dp, glowColor.copy(alpha = 0.1f), RoundedCornerShape(40.dp)),
                    ) {
                        MetaCalendar(
                            modifier = Modifier.fillMaxSize().padding(16.dp),
                            scrollOrientation = selectedMode,
                            initialYear = 2026,
                            initialMonth = Month.APRIL,
                            onDayClick = onDayClick,
                            onHeaderClick = onHeaderClick,
                            options = CalendarOptions(showAdjacentMonths = showAdjacent),
                        )
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Column(
                        modifier = Modifier.padding(top = 60.dp, bottom = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "KMP/CMP Playground",
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = (-0.5).sp,
                            ),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "Open to contributions!",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        OutlinedButton(
                            onClick = {
                                uriHandler.openUri("https://github.com/komodgn/KMP")
                            },
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color.White,
                            ),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.github),
                                    contentDescription = "GitHub",
                                    modifier = Modifier.size(30.dp),
                                )
                                Text(
                                    text = "View on GitHub",
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(48.dp))

                        Text(
                            text = "MetaCalendar",
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                item {
                    SingleChoiceSegmentedButtonRow(
                        modifier = Modifier.padding(bottom = 5.dp),
                    ) {
                        CalendarScrollOrientation.entries.forEachIndexed { index, mode ->
                            val activeColor = getModeColor(mode)

                            SegmentedButton(
                                selected = selectedMode == mode,
                                onClick = { selectedMode = mode },
                                shape = SegmentedButtonDefaults.itemShape(index = index, count = 2),
                                colors = SegmentedButtonDefaults.colors(
                                    activeContainerColor = activeColor.copy(alpha = 0.1f),
                                    activeContentColor = activeColor,
                                    activeBorderColor = activeColor,
                                    inactiveContentColor = Color.White.copy(alpha = 0.5f),
                                    inactiveBorderColor = Color(0xFF30363D),
                                ),
                            ) {
                                Text(mode.name)
                            }
                        }
                    }

                    Text(
                        text = "Scroll Type",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.sp,
                        ),
                        color = getModeColor(selectedMode).copy(alpha = 0.8f),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(CardBackground.copy(alpha = 0.5f))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable { showAdjacent = !showAdjacent },
                    ) {
                        Switch(
                            checked = showAdjacent,
                            onCheckedChange = { showAdjacent = it },
                            colors = androidx.compose.material3.SwitchDefaults.colors(
                                checkedThumbColor = NeonGreen,
                                checkedTrackColor = NeonGreen.copy(alpha = 0.3f),
                            ),
                        )
                        Text(
                            text = "Show Adjacent Months",
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (showAdjacent) Color.White else Color.White.copy(alpha = 0.5f),
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(600.dp)
                            .shadow(20.dp, RoundedCornerShape(32.dp))
                            .clip(RoundedCornerShape(32.dp))
                            .background(CardBackground)
                            .border(1.dp, Color(0xFF30363D), RoundedCornerShape(32.dp)),
                    ) {
                        MetaCalendar(
                            modifier = Modifier.fillMaxSize().padding(16.dp),
                            scrollOrientation = selectedMode,
                            initialYear = 2026,
                            initialMonth = Month.APRIL,
                            onDayClick = onDayClick,
                            onHeaderClick = onHeaderClick,
                            options = CalendarOptions(showAdjacentMonths = showAdjacent),
                        )
                    }
                }

                item { Spacer(modifier = Modifier.height(100.dp)) }
            }
        }
    }
}

@Preview
@Composable
private fun DemoLandingPagePreview() {
    MaterialTheme(
        colorScheme = CustomColorScheme,
    ) {
        DemoLandingPage(
            onDayClick = {},
        )
    }
}
