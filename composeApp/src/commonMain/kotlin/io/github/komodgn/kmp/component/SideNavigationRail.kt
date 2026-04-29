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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.komodgn.kmp.DarkBackground
import kmp.composeapp.generated.resources.Res
import kmp.composeapp.generated.resources.github
import org.jetbrains.compose.resources.painterResource

@Composable
fun SideNavigationRail(
    modifier: Modifier = Modifier,
    selectedRoute: String = "Home",
    onRouteSelected: (String) -> Unit = {},
) {
    val uriHandler = LocalUriHandler.current

    NavigationRail(
        modifier = modifier.fillMaxHeight(),
        containerColor = DarkBackground.copy(alpha = 0.6f),
        contentColor = Color.White,
        header = {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = "Logo",
                modifier = Modifier.size(32.dp).padding(vertical = 16.dp),
                tint = MaterialTheme.colorScheme.primary,
            )
        },
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NavRailItem(
                label = "Home",
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                selected = selectedRoute == "Home",
                onClick = { onRouteSelected("Home") },
            )

            Spacer(modifier = Modifier.height(16.dp))

            NavRailItem(
                label = "Customs",
                icon = { Icon(Icons.Default.DashboardCustomize, contentDescription = "Customs") },
                selected = selectedRoute == "Customs",
                onClick = { onRouteSelected("Customs") },
            )

            Spacer(modifier = Modifier.weight(1f))
            NavRailItem(
                label = "GitHub",
                icon = {
                    Icon(
                        painter = painterResource(Res.drawable.github),
                        contentDescription = "GitHub",
                        modifier = Modifier.size(24.dp),
                    )
                },
                selected = selectedRoute == "GitHub",
                onClick = {
                    onRouteSelected("GitHub")
                    uriHandler.openUri("https://github.com/komodgn/KMP")
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun NavRailItem(
    label: String,
    icon: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = icon,
        label = { Text(label, style = MaterialTheme.typography.labelSmall) },
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = Color.White,
            selectedTextColor = Color.White,
            indicatorColor = DarkBackground.copy(alpha = 0.1f),
            unselectedIconColor = Color.White.copy(alpha = 0.6f),
            unselectedTextColor = Color.White.copy(alpha = 0.6f),
        ),
    )
}

@Preview
@Composable
private fun SideNavigationRail() {
    MaterialTheme {
        SideNavigationRail()
    }
}
