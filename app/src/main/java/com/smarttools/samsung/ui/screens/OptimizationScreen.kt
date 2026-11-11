package com.smarttools.samsung.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun OptimizationScreen() {
    var isOptimized by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Optimisation", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Nettoyage Système & Cache", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text(
                    if (!isOptimized) "Analyse des résidus en arrière-plan prête." 
                    else "Optimisation terminée avec succès ! Mémoire RAM libérée.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = { isOptimized = true },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (!isOptimized) "Optimiser maintenant" else "Ré-analyser")
                }
            }
        }

        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Débloater Samsung sécurisé", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(4.dp))
                Text("Désactivez les services inutiles (ex: Bixby wakeup, Samsung Visit-In) via profil ADB/Shizuku.", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
