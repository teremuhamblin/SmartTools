package com.smarttools.samsung.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttools.samsung.ui.components.OneUiCard
import com.smarttools.samsung.ui.theme.OneUiBlue
import com.smarttools.samsung.utils.SamsungSystemHelper

@Composable
fun OptimizationScreen() {
    val context = LocalContext.current
    var statusText by remember { mutableStateOf("Appareil prêt pour l'optimisation") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp)
    ) {
        OneUiCard {
            Text(
                text = "Nettoyage Rapide de la RAM",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Ferme les processus en tâche de fond inactifs pour libérer la mémoire vive.",
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    SamsungSystemHelper.clearRam(context)
                    statusText = "Mémoire RAM libérée avec succès !"
                    Toast.makeText(context, statusText, Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = OneUiBlue)
            ) {
                Text("Optimiser la Mémoire")
            }
        }

        OneUiCard {
            Text(
                text = "État Actuel",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = statusText)
        }
    }
}
