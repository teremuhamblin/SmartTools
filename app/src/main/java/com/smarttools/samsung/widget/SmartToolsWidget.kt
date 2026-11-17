package com.smarttools.samsung.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalSize
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.updateAll
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.smarttools.samsung.MainActivity
import com.smarttools.samsung.utils.QuickSystemSnapshot
import com.smarttools.samsung.utils.SystemSnapshotReader

class SmartToolsWidget : GlanceAppWidget() {

    companion object {
        private val COMPACT_SIZE = DpSize(120.dp, 60.dp)
        private val WIDE_SIZE = DpSize(240.dp, 60.dp)
    }

    override val sizeMode: SizeMode = SizeMode.Responsive(setOf(COMPACT_SIZE, WIDE_SIZE))

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val snapshot = SystemSnapshotReader.read(context)
        provideContent {
            GlanceTheme {
                WidgetContent(snapshot)
            }
        }
    }

    @Composable
    private fun WidgetContent(snapshot: QuickSystemSnapshot) {
        val isWide = LocalSize.current.width >= WIDE_SIZE.width

        Row(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(GlanceTheme.colors.surface)
                .cornerRadius(24.dp)
                .clickable(actionStartActivity<MainActivity>())
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Bloc RAM
            Column(modifier = GlanceModifier.defaultWeight()) {
                Text(
                    text = "RAM ${snapshot.ramUsedPercent}%",
                    style = TextStyle(
                        color = GlanceTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
                if (isWide) {
                    Text(
                        text = "${snapshot.ramFreeMb} Mo libres",
                        style = TextStyle(
                            color = GlanceTheme.colors.onSurfaceVariant,
                            fontSize = 11.sp
                        )
                    )
                }
            }

            Spacer(GlanceModifier.width(8.dp))

            // Bloc Batterie & Température
            Column(modifier = GlanceModifier.defaultWeight()) {
                val chargeIcon = if (snapshot.isCharging) "⚡" else "🔋"
                Text(
                    text = "$chargeIcon ${snapshot.batteryPercent}%",
                    style = TextStyle(
                        color = GlanceTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
                if (isWide) {
                    Text(
                        text = String.format("%.1f°C • %s", snapshot.batteryTempCelsius, snapshot.thermalStatusLabel),
                        style = TextStyle(
                            color = GlanceTheme.colors.onSurfaceVariant,
                            fontSize = 11.sp
                        )
                    )
                }
            }

            Spacer(GlanceModifier.width(6.dp))

            // Bouton Actualiser sans quitter l'écran d'accueil
            Box(
                modifier = GlanceModifier
                    .background(GlanceTheme.colors.primaryContainer)
                    .cornerRadius(14.dp)
                    .clickable(actionRunCallback<RefreshWidgetAction>())
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "↻",
                    style = TextStyle(
                        color = GlanceTheme.colors.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                )
            }
        }
    }
}

class RefreshWidgetAction : ActionCallback {
    override suspend fun onAction(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        SmartToolsWidget().updateAll(context)
    }
}
