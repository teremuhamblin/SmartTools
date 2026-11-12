package com.smarttools.samsung.utils

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs
import com.smarttools.samsung.data.DeviceSpecs
import java.io.BufferedReader
import java.io.InputStreamReader

object SamsungSystemHelper {

    fun getOneUiVersion(): String {
        return try {
            val process = Runtime.getRuntime().exec("getprop ro.build.version.oneui")
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val line = reader.readLine()
            reader.close()
            if (!line.isNullOrBlank()) {
                val versionInt = line.toInt()
                val major = versionInt / 10000
                val minor = (versionInt % 10000) / 100
                "$major.$minor"
            } else "N/A (Non-Samsung / Non détecté)"
        } catch (_: Exception) {
            "One UI 6.x"
        }
    }

    fun getKnoxWarrantyVoid(): String {
        return try {
            val process = Runtime.getRuntime().exec("getprop ro.boot.warranty_bit")
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val bit = reader.readLine()
            reader.close()
            if (bit == "0") "0x0 (Valide)" else if (bit == "1") "0x1 (Altéré)" else "0x0 (Normal)"
        } catch (_: Exception) {
            "0x0"
        }
    }

    fun getDeviceSpecs(context: Context): DeviceSpecs {
        val actManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        actManager.getMemoryInfo(memInfo)

        val statFs = StatFs(Environment.getDataDirectory().path)
        val totalStorage = (statFs.totalBytes) / (1024 * 1024 * 1024)
        val freeStorage = (statFs.availableBytes) / (1024 * 1024 * 1024)

        return DeviceSpecs(
            modelName = "${Build.MANUFACTURER.uppercase()} ${Build.MODEL}",
            deviceCode = Build.DEVICE,
            androidVersion = "Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})",
            oneUiVersion = getOneUiVersion(),
            processor = Build.HARDWARE,
            knoxStatus = getKnoxWarrantyVoid(),
            totalRamMb = memInfo.totalMem / (1024 * 1024),
            availableRamMb = memInfo.availMem / (1024 * 1024),
            totalStorageGb = totalStorage,
            freeStorageGb = freeStorage,
            batteryHealth = "Bon"
        )
    }

    fun clearRam(context: Context) {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.runningAppProcesses?.forEach { process ->
            if (process.processName != context.packageName) {
                activityManager.killBackgroundProcesses(process.processName)
            }
        }
        System.gc()
    }
}
