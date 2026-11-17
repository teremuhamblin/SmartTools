package com.smarttools.samsung.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

object RoutineNotifier {

    const val CHANNEL_THERMAL = "smarttools_thermal_alerts"
    const val CHANNEL_RAM = "smarttools_ram_alerts"

    fun ensureChannels(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val thermal = NotificationChannel(
            CHANNEL_THERMAL,
            "Alertes thermiques (Modes & Routines)",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Déclencheurs utilisables dans Samsung Modes et Routines en cas de surchauffe."
        }

        val ram = NotificationChannel(
            CHANNEL_RAM,
            "Alertes mémoire RAM (Modes & Routines)",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Déclencheurs utilisables dans Samsung Modes et Routines en cas de RAM saturée."
        }

        nm.createNotificationChannels(listOf(thermal, ram))
    }

    fun sendThermalAlert(context: Context, tempCelsius: Float) {
        notifyIfAllowed(
            context = context,
            channelId = CHANNEL_THERMAL,
            notificationId = 1001,
            title = "Alerte Surchauffe SmartTools",
            body = String.format("Température batterie élevée : %.1f°C", tempCelsius)
        )
    }

    fun sendRamAlert(context: Context, ramUsedPct: Int) {
        notifyIfAllowed(
            context = context,
            channelId = CHANNEL_RAM,
            notificationId = 1002,
            title = "Alerte RAM critique SmartTools",
            body = "Mémoire utilisée à $ramUsedPct% — optimisation recommandée"
        )
    }

    private fun notifyIfAllowed(
        context: Context,
        channelId: String,
        notificationId: Int,
        title: String,
        body: String
    ) {
        ensureChannels(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.stat_sys_warning)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(notificationId, notification)
    }
}
