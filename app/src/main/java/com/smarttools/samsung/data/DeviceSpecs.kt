package com.smarttools.samsung.data

data class DeviceSpecs(
    val modelName: String,
    val deviceCode: String,
    val androidVersion: String,
    val oneUiVersion: String,
    val processor: String,
    val knoxStatus: String,
    val totalRamMb: Long,
    val availableRamMb: Long,
    val totalStorageGb: Long,
    val freeStorageGb: Long,
    val batteryHealth: String
)

fun hasSPenSupport(context: Context): Boolean {
    if (context.packageManager.hasSystemFeature("com.sec.feature.spen_usp")) return true
    return android.view.InputDevice.getDeviceIds().any { id ->
        val dev = android.view.InputDevice.getDevice(id) ?: return@any false
        (dev.sources and android.view.InputDevice.SOURCE_STYLUS) == android.view.InputDevice.SOURCE_STYLUS
    }
}
