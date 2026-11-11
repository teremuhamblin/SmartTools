package com.smarttools.samsung.utils

import android.os.Build
import java.io.BufferedReader
import java.io.InputStreamReader

object SamsungSystemHelper {

    /**
     * Lit une propriété système Android (ex: ro.boot.warranty_bit pour Knox).
     */
    private fun getSystemProperty(propName: String): String {
        return try {
            val process = Runtime.getRuntime().exec("getprop $propName")
            BufferedReader(InputStreamReader(process.inputStream)).use { it.readLine() ?: "" }
        } catch (_: Exception) {
            "N/A"
        }
    }

    // Knox est intact si le bit vaut 0x0
    fun getKnoxStatus(): String {
        val warrantyBit = getSystemProperty("ro.boot.warranty_bit")
        return when (warrantyBit) {
            "0" -> "0x0 (Officiel / Intact)"
            "1" -> "0x1 (Altéré / Knox Trip)"
            else -> "Inconnu (Non Samsung ou inaccessible)"
        }
    }

    fun getDeviceModel(): String = "${Build.MANUFACTURER.uppercase()} ${Build.MODEL} (${Build.DEVICE})"

    fun getOneUiVersion(): String {
        val sepVersion = getSystemProperty("ro.build.version.sep")
        return if (sepVersion.isNotEmpty()) "One UI base (SEP: $sepVersion)" else "One UI standard"
    }
}
