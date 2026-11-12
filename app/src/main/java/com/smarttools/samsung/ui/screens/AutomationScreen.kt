package com.smarttools.samsung.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttools.samsung.ui.components.OneUiCard
import com.smarttools.samsung.ui.theme.OneUiBlue

@Composable
fun AutomationScreen() {
    var batteryAlert85 by remember { mutableStateOf(true) }
    var nightModeAuto by remember { mutableStateOf(false) }
    var gamePerformanceMode by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        OneUiCard {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Protection Batterie 85%",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Alerte sonore dès que le seuil de charge atteint 85%.",
                        fontSize = 13.sp
                    )
                }
                Switch(
                    checked = batteryAlert85,
                    onCheckedChange = { batteryAlert85 = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = OneUiBlue)
                )
            }
        }

        OneUiCard {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Mode Nuit Éco Intelligent",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Réduit les activités d'arrière-plan entre 23h et 07h.",
                        fontSize = 13.sp
                    )
                }
                Switch(
                    checked = nightModeAuto,
                    onCheckedChange = { nightModeAuto = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = OneUiBlue)
                )
            }
        }

        OneUiCard {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Profil Performance Jeu",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Alloue un maximum de mémoire vive aux applications au premier plan.",
                        fontSize = 13.sp
                    )
                }
                Switch(
                    checked = gamePerformanceMode,
                    onCheckedChange = { gamePerformanceMode = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = OneUiBlue)
                )
            }
        }
    }
}
