package com.smarttools.samsung.data

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class DiagnosticReport(
    val specs: DeviceSpecs,
    val batteryLevel: Int,
    val batteryTemperature: Float,
    val isCharging: Boolean,
    val healthScore: Int,
    val generatedAt: Long = System.currentTimeMillis()
) {
    fun getFormattedDate(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return sdf.format(Date(generatedAt))
    }
}
