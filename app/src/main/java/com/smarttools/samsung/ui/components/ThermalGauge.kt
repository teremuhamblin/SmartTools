package com.smarttools.samsung.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttools.samsung.ui.theme.StatusGreen
import com.smarttools.samsung.ui.theme.StatusOrange
import com.smarttools.samsung.ui.theme.StatusRed

@Composable
fun ThermalGauge(temperature: Float) {
    val maxTemp = 55.0f
    val progress = (temperature / maxTemp).coerceIn(0.0f, 1.0f)

    val color by animateColorAsState(
        targetValue = when {
            temperature < 36f -> StatusGreen
            temperature in 36f..42f -> StatusOrange
            else -> StatusRed
        },
        label = "ThermalColor"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Température : $temperature °C",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = color
        )
    }
}
