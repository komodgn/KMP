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
package io.github.komodgn.kmp.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.komodgn.kmp.NeonGreen
import io.github.komodgn.kmp.calendar.core.CalendarScrollOrientation
import io.github.komodgn.kmp.getModeColor

@Composable
internal fun CalendarControlSection(
    selectedMode: CalendarScrollOrientation,
    onModeChange: (CalendarScrollOrientation) -> Unit,
    showAdjacent: Boolean,
    onAdjacentChange: (Boolean) -> Unit,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
) {
    val currentModeColor = getModeColor(selectedMode)

    Column(
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Scroll Type",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.7f),
            )

            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.height(32.dp),
            ) {
                CalendarScrollOrientation.entries.forEachIndexed { index, mode ->
                    val activeColor = getModeColor(mode)
                    SegmentedButton(
                        selected = selectedMode == mode,
                        onClick = { onModeChange(mode) },
                        shape = SegmentedButtonDefaults.itemShape(index = index, count = 2),
                        colors = SegmentedButtonDefaults.colors(
                            activeContainerColor = activeColor.copy(alpha = 0.2f),
                            activeContentColor = activeColor,
                            activeBorderColor = activeColor.copy(alpha = 0.5f),
                            inactiveContainerColor = Color.Transparent,
                            inactiveContentColor = Color.White.copy(alpha = 0.5f),
                            inactiveBorderColor = Color.White.copy(alpha = 0.2f),
                        ),
                        label = {
                            Text(
                                mode.name,
                                style = MaterialTheme.typography.labelSmall,
                            )
                        },
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) {
                    onAdjacentChange(!showAdjacent)
                }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Adjacent Months",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.7f),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = if (showAdjacent) "Show" else "Hide",
                    style = MaterialTheme.typography.labelMedium,
                    color = if (showAdjacent) currentModeColor else Color.White.copy(alpha = 0.4f),
                )

                Switch(
                    checked = showAdjacent,
                    onCheckedChange = { onAdjacentChange(it) },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = currentModeColor,
                        checkedTrackColor = currentModeColor.copy(alpha = 0.3f),
                        uncheckedThumbColor = Color.Gray,
                        uncheckedTrackColor = Color.Gray.copy(alpha = 0.2f),
                    ),
                    modifier = Modifier.scale(0.8f),
                )
            }
        }
    }
}

@Preview
@Composable
private fun CalendarControlSectionPreview() {
    MaterialTheme {
        CalendarControlSection(
            selectedMode = CalendarScrollOrientation.Horizontal,
            onModeChange = {},
            showAdjacent = true,
            onAdjacentChange = {},
        )
    }
}
