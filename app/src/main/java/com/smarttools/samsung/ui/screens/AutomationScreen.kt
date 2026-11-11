package com.smarttools.samsung.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AutomationScreen() {
    var batteryProtectEnabled by remember { mutableStateOf(true) }
    var refreshRate60HzOnLowBattery by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Routines Intelligentes", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

        Card(shape = RoundedCornerShape(24.dp), modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.padding(20.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text("Alerte Protection Batterie (85%)", fontWeight = FontWeight.SemiBold)
                    Text("Avertit lorsque la limite optimale est atteinte.", style = MaterialTheme.typography.bodySmall)
                }
                Switch(checked = batteryProtectEnabled, onCheckedChange = { batteryProtectEnabled = it })
            }
        }

        Card(shape = RoundedCornerShape(24.dp), modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.padding(20.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text("60 Hz en Économie d'énergie", fontWeight = FontWeight.SemiBold)
                    Text("Bascule l'écran dynamique 120Hz à 60Hz sous 20%.", style = MaterialTheme.typography.bodySmall)
                }
                Switch(checked = refreshRate60HzOnLowBattery, onCheckedChange = { refreshRate60HzOnLowBattery = it })
            }
        }
    }
}
