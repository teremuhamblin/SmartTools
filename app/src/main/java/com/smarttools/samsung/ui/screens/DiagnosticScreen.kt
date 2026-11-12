package com.smarttools.samsung.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttools.samsung.data.DiagnosticReport
import com.smarttools.samsung.ui.components.OneUiCard
import com.smarttools.samsung.ui.components.ThermalGauge
import com.smarttools.samsung.ui.theme.OneUiBlue
import com.smarttools.samsung.utils.BatteryHelper
import com.smarttools.samsung.utils.PdfExporter
import com.smarttools.samsung.utils.SamsungSystemHelper

@Composable
fun DiagnosticScreen() {
    val context = LocalContext.current
    val specs = remember { SamsungSystemHelper.getDeviceSpecs(context) }
    val temp = remember { BatteryHelper.getBatteryTemperature(context) }
    val batteryLvl = remember { BatteryHelper.getBatteryLevel(context) }
    val isCharging = remember { BatteryHelper.isCharging(context) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 80.dp)
    ) {
        OneUiCard {
            Text(
                text = specs.modelName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(text = specs.androidVersion, color = MaterialTheme.colorScheme.primary)
            Text(text = "One UI : ${specs.oneUiVersion}", fontSize = 14.sp)
            Text(text = "Statut Knox : ${specs.knoxStatus}", fontSize = 14.sp)
        }

        OneUiCard {
            Text(
                text = "État de la Batterie",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = "Niveau : $batteryLvl% ${if (isCharging) "(En charge)" else ""}")
            Spacer(modifier = Modifier.height(10.dp))
            ThermalGauge(temperature = temp)
        }

        OneUiCard {
            Text(
                text = "Mémoire & Espace",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = "RAM Disponible : ${specs.availableRamMb} Mo / ${specs.totalRamMb} Mo")
            Text(text = "Stockage Libre : ${specs.freeStorageGb} Go / ${specs.totalStorageGb} Go")
        }

        OneUiCard {
            Button(
                onClick = {
                    val report = DiagnosticReport(
                        specs = specs,
                        batteryLevel = batteryLvl,
                        batteryTemperature = temp,
                        isCharging = isCharging,
                        healthScore = 96
                    )
                    val pdf = PdfExporter.exportToPdf(context, report)
                    Toast.makeText(context, "Exporté : ${pdf.name}", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = OneUiBlue)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = null)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text("Exporter le Diagnostic (PDF)")
                }
            }
        }
    }
}
