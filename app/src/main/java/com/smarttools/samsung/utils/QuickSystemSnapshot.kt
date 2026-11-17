package com.smarttools.samsung.utils

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.PowerManager
import kotlin.math.roundToInt

data class QuickSystemSnapshot(
    val ramUsedPercent: Int,
    val ramFreeMb: Long,
    val ramTotalMb: Long,
    val batteryPercent: Int,
    val batteryTempCelsius: Float,
    val isCharging: Boolean,
    val thermalStatusLabel: String
)

object SystemSnapshotReader {

    fun read(context: Context): QuickSystemSnapshot {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        am.getMemoryInfo(memInfo)

        val totalMb = (memInfo.totalMem / (1024L * 1024L)).coerceAtLeast(1L)
        val freeMb = memInfo.availMem / (1024L * 1024L)
        val usedPct = (((memInfo.totalMem - memInfo.availMem).toDouble() / memInfo.totalMem) * 100.0)
            .roundToInt().coerceIn(0, 100)

        val batteryIntent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val level = batteryIntent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = batteryIntent?.getIntExtra(BatteryManager.EXTRA_SCALE, 100) ?: 100
        val batteryPct = if (level >= 0 && scale > 0) ((level * 100f) / scale).roundToInt() else 0

        val tempRaw = batteryIntent?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) ?: 0
        val tempC = tempRaw / 10f

        val status = batteryIntent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
        val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
            status == BatteryManager.BATTERY_STATUS_FULL

        val thermalLabel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            when (pm.currentThermalStatus) {
                PowerManager.THERMAL_STATUS_NONE -> "Normal"
                PowerManager.THERMAL_STATUS_LIGHT -> "Tiède"
                PowerManager.THERMAL_STATUS_MODERATE -> "Modéré"
                PowerManager.THERMAL_STATUS_SEVERE -> "Chaud"
                PowerManager.THERMAL_STATUS_CRITICAL,
                PowerManager.THERMAL_STATUS_EMERGENCY,
                PowerManager.THERMAL_STATUS_SHUTDOWN -> "Critique"
                else -> "Normal"
            }
        } else {
            if (tempC >= 40f) "Chaud" else "Normal"
        }

        return QuickSystemSnapshot(
            ramUsedPercent = usedPct,
            ramFreeMb = freeMb,
            ramTotalMb = totalMb,
            batteryPercent = batteryPct,
            batteryTempCelsius = tempC,
            isCharging = isCharging,
            thermalStatusLabel = thermalLabel
        )
    }
}
