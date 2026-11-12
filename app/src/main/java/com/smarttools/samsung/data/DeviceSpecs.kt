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
