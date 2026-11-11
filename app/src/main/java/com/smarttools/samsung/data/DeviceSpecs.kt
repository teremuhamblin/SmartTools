package com.smarttools.samsung.data

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.util.DisplayMetrics
import android.view.WindowManager
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Modèles de données représentant la fiche technique complète de l'appareil.
 */
data class FullDeviceSpecs(
    val identity: DeviceIdentity,
    val software: SoftwareSpecs,
    val memory: MemorySpecs,
    val display: DisplaySpecs,
    val security: SecuritySpecs
)

data class DeviceIdentity(
    val manufacturer: String,
    val model: String,
    val deviceCode: String,
    val board: String,
    val soc: String
)

data class SoftwareSpecs(
    val androidVersion: String,
    val sdkInt: Int,
    val oneUiVersion: String,
    val securityPatch: String,
    val buildNumber: String
)

data class MemorySpecs(
    val totalRamBytes: Long,
    val availableRamBytes: Long,
    val totalStorageBytes: Long,
    val availableStorageBytes: Long
) {
    val totalRamGb: Float get() = totalRamBytes / (1024f * 1024f * 1024f)
    val availableRamGb: Float get() = availableRamBytes / (1024f * 1024f * 1024f)
    val totalStorageGb: Float get() = totalStorageBytes / (1024f * 1024f * 1024f)
    val availableStorageGb: Float get() = availableStorageBytes / (1024f * 1024f * 1024f)
}

data class DisplaySpecs(
    val resolutionWidth: Int,
    val resolutionHeight: Int,
    val densityDpi: Int,
    val currentRefreshRate: Float,
    val supportedRefreshRates: List<Float>
)

data class SecuritySpecs(
    val knoxStatus: String,
    val isKnoxIntact: Boolean,
    val isSeLinuxEnforcing: Boolean
)

/**
 * Fournisseur de données extrayant les informations directement du système Samsung.
 */
object DeviceSpecsProvider {

    fun getDeviceSpecs(context: Context): FullDeviceSpecs {
        return FullDeviceSpecs(
            identity = getIdentity(),
            software = getSoftware(),
            memory = getMemorySpecs(context),
            display = getDisplaySpecs(context),
            security = getSecuritySpecs()
        )
    }

    private fun getIdentity(): DeviceIdentity {
        val soc = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Build.SOC_MODEL.ifEmpty { Build.HARDWARE }
        } else {
            Build.HARDWARE
        }

        return DeviceIdentity(
            manufacturer = Build.MANUFACTURER.replaceFirstChar { it.uppercase() },
            model = Build.MODEL,
            deviceCode = Build.DEVICE,
            board = Build.BOARD,
            soc = soc
        )
    }

    private fun getSoftware(): SoftwareSpecs {
        val sepVersionCode = getSystemProperty("ro.build.version.sep")
        val oneUiDisplay = when {
            sepVersionCode.startsWith("160000") -> "One UI 6.0"
            sepVersionCode.startsWith("160100") -> "One UI 6.1"
            sepVersionCode.startsWith("170000") -> "One UI 7.0"
            sepVersionCode.isNotEmpty() -> "One UI (SEP: $sepVersionCode)"
            else -> "Android Stock / One UI standard"
        }

        return SoftwareSpecs(
            androidVersion = Build.VERSION.RELEASE,
            sdkInt = Build.VERSION.SDK_INT,
            oneUiVersion = oneUiDisplay,
            securityPatch = Build.VERSION.SECURITY_PATCH ?: "Inconnue",
            buildNumber = Build.DISPLAY
        )
    }

    private fun getMemorySpecs(context: Context): MemorySpecs {
        // RAM
        val actManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        actManager.getMemoryInfo(memInfo)

        // Stockage Interne
        val statFs = StatFs(Environment.getDataDirectory().path)
        val totalStorage = statFs.totalBytes
        val availableStorage = statFs.availableBytes

        return MemorySpecs(
            totalRamBytes = memInfo.totalMem,
            availableRamBytes = memInfo.availMem,
            totalStorageBytes = totalStorage,
            availableStorageBytes = availableStorage
        )
    }

    private fun getDisplaySpecs(context: Context): DisplaySpecs {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            context.display ?: windowManager.defaultDisplay
        } else {
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay
        }

        val metrics = DisplayMetrics()
        @Suppress("DEPRECATION")
        display.getRealMetrics(metrics)

        val supportedRates = display.supportedModes.map { it.refreshRate }.distinct().sorted()

        return DisplaySpecs(
            resolutionWidth = metrics.widthPixels,
            resolutionHeight = metrics.heightPixels,
            densityDpi = metrics.densityDpi,
            currentRefreshRate = display.mode.refreshRate,
            supportedRefreshRates = supportedRates
        )
    }

    private fun getSecuritySpecs(): SecuritySpecs {
        val warrantyBit = getSystemProperty("ro.boot.warranty_bit")
        val isIntact = warrantyBit == "0"
        val knoxLabel = when (warrantyBit) {
            "0" -> "0x0 (Knox Intact / Sécurisé)"
            "1" -> "0x1 (Knox Altéré / Garantie révoquée)"
            else -> "Non disponible"
        }

        val selinuxState = getSystemProperty("ro.boot.selinux") != "permissive"

        return SecuritySpecs(
            knoxStatus = knoxLabel,
            isKnoxIntact = isIntact,
            isSeLinuxEnforcing = selinuxState
        )
    }

    private fun getSystemProperty(property: String): String {
        return try {
            val process = Runtime.getRuntime().exec("getprop $property")
            BufferedReader(InputStreamReader(process.inputStream)).use { it.readLine() ?: "" }
        } catch (_: Exception) {
            ""
        }
    }
}
