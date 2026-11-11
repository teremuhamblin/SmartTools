package com.smarttools.samsung.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.smarttools.samsung.utils.BatteryHelper
import com.smarttools.samsung.utils.SamsungSystemHelper

@Composable
fun DiagnosticScreen() {
    val context = LocalContext.current
    val battery = remember { BatteryHelper.getBatteryInfo(context) }
    val knox = remember { SamsungSystemHelper.getKnoxStatus() }
    val device = remember { SamsungSystemHelper.getDeviceModel() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Diagnostic Matériel", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

        // Carte État Samsung
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Appareil", style = MaterialTheme.typography.labelMedium)
                Text(device, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(8.dp))
                Text("État Knox", style = MaterialTheme.typography.labelMedium)
                Text(knox, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
            }
        }

        // Carte Batterie One UI
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.BatteryChargingFull, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.width(8.dp))
                    Text("Batterie", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(12.dp))
                Text("Niveau : ${battery.level}% (${if (battery.isCharging) "En charge" else "Sur batterie"})")
                Text("Température : ${battery.temperatureCelsius}°C")
                Text("Santé globale : ${battery.health}")
            }
        }
    }
}
