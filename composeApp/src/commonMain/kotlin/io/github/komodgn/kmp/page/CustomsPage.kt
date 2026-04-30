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
package io.github.komodgn.kmp.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.komodgn.kmp.DarkBackground
import io.github.komodgn.kmp.NeonGreen
import io.github.komodgn.kmp.NeonPurple
import io.github.komodgn.kmp.NeonRed
import io.github.komodgn.kmp.NeonSky
import io.github.komodgn.kmp.component.PresetCard

@Composable
fun CustomsPage() {
    val presets = listOf(
        Triple("Neon Sky", NeonSky, "Horizontal"),
        Triple("Hot Pink", NeonPurple, "None"),
        Triple("Cyber Green", NeonGreen, "Horizontal"),
        Triple("Vivid Red", NeonRed, "None"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(40.dp),
    ) {
        Text(
            text = "Design Presets",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(bottom = 8.dp),
        )
        Text(
            text = "Explore various styles.",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 40.dp),
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            presets.forEach { (name, color, scroll) ->
                PresetCard(
                    name,
                    color,
                    scroll,
                    backgroundContent = {
                        Box(modifier = Modifier.fillMaxSize().background(DarkBackground))
                    },
                )
            }
        }
    }
}
