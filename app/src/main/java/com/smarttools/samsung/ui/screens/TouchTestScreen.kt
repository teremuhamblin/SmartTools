package com.smarttools.samsung.ui.screens

import android.app.Activity
import android.os.Build
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.smarttools.samsung.ui.components.SPenTelemetry
import com.smarttools.samsung.ui.components.TouchMatrixView
import com.smarttools.samsung.ui.components.TouchTestMode

data class ScreenDiagnosticResult(
    val oledChecked: Boolean,
    val touchCoveragePct: Int,
    val touchPassed: Boolean,
    val sPenTested: Boolean,
    val sPenMaxPressure: Float,
    val sPenHoverDetected: Boolean
)

@Composable
fun TouchTestScreen(
    hasSPenSupport: Boolean,
    onFinish: (ScreenDiagnosticResult) -> Unit
) {
    val context = LocalContext.current
    var mode by remember { mutableStateOf(TouchTestMode.TOUCH_MATRIX) }
    var oledColorLabel by remember { mutableStateOf("Rouge (#FF0000)") }
    var oledVisited by remember { mutableStateOf(false) }
    var matrixProgress by remember { mutableStateOf(0 to 200) }
    var maxTouchPct by remember { mutableIntStateOf(0) }
    var sPenTelemetry by remember { mutableStateOf(SPenTelemetry()) }
    var matrixViewRef by remember { mutableStateOf<TouchMatrixView?>(null) }

    // Plein écran immersif + encoche + luminosité auto restaurée à la sortie
    DisposableEffect(mode) {
        val window = (context as? Activity)?.window
        val originalBrightness = window?.attributes?.screenBrightness ?: -1f
        val originalCutout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window?.attributes?.layoutInDisplayCutoutMode ?: 0
        } else 0

        if (window != null) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowInsetsControllerCompat(window, window.decorView).apply {
                hide(WindowInsetsCompat.Type.systemBars())
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
            window.attributes = window.attributes.apply {
                screenBrightness = if (mode == TouchTestMode.OLED_PIXELS) 1.0f else originalBrightness
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    layoutInDisplayCutoutMode =
                        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                }
            }
        }

        onDispose {
            if (window != null) {
                WindowCompat.setDecorFitsSystemWindows(window, true)
                WindowInsetsControllerCompat(window, window.decorView)
                    .show(WindowInsetsCompat.Type.systemBars())
                window.attributes = window.attributes.apply {
                    screenBrightness = originalBrightness
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        layoutInDisplayCutoutMode = originalCutout
                    }
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                TouchMatrixView(ctx).apply {
                    onMatrixProgress = { done, total ->
                        matrixProgress = done to total
                        val pct = if (total > 0) (done * 100) / total else 0
                        if (pct > maxTouchPct) maxTouchPct = pct
                    }
                    onSPenTelemetry = { t -> sPenTelemetry = t }
                    onOledColorChanged = { label ->
                        oledColorLabel = label
                        oledVisited = true
                    }
                    matrixViewRef = this
                }
            },
            update = { view ->
                if (view.mode != mode) view.mode = mode
            }
        )

        // HUD flottant discret en haut
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 28.dp, start = 12.dp, end = 12.dp)
                .background(Color.Black.copy(alpha = 0.68f), RoundedCornerShape(20.dp))
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                FilterChip(
                    selected = mode == TouchTestMode.TOUCH_MATRIX,
                    onClick = { mode = TouchTestMode.TOUCH_MATRIX },
                    label = { Text("Tactile") }
                )
                FilterChip(
                    selected = mode == TouchTestMode.OLED_PIXELS,
                    onClick = { mode = TouchTestMode.OLED_PIXELS },
                    label = { Text("OLED") }
                )
                FilterChip(
                    selected = mode == TouchTestMode.SPEN_DIGITIZER,
                    onClick = { mode = TouchTestMode.SPEN_DIGITIZER },
                    label = { Text("S-Pen") }
                )
            }

            Spacer(Modifier.height(4.dp))

            val statusText = when (mode) {
                TouchTestMode.TOUCH_MATRIX ->
                    "Grille : ${matrixProgress.first}/${matrixProgress.second} ($maxTouchPct%)"
                TouchTestMode.OLED_PIXELS ->
                    "Couleur : $oledColorLabel (touchez pour changer)"
                TouchTestMode.SPEN_DIGITIZER -> if (!hasSPenSupport) {
                    "Appareil sans numériseur S-Pen détecté"
                } else {
                    String.format(
                        "Pression: %.0f%% (Max %.0f%%) • Inclinaison: %.0f° • Hover: %s • Bouton: %s",
                        sPenTelemetry.pressure * 100,
                        sPenTelemetry.maxPressure * 100,
                        sPenTelemetry.tiltDegrees,
                        if (sPenTelemetry.hoverDetected) "OK" else "-",
                        if (sPenTelemetry.primaryButtonPressed) "OK" else "-"
                    )
                }
            }
            Text(text = statusText, color = Color.White, style = MaterialTheme.typography.labelMedium)

            Spacer(Modifier.height(6.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TextButton(onClick = { matrixViewRef?.resetCurrentTest() }) {
                    Text("Réinitialiser", color = Color.LightGray)
                }
                Button(onClick = {
                    onFinish(
                        ScreenDiagnosticResult(
                            oledChecked = oledVisited,
                            touchCoveragePct = maxTouchPct,
                            touchPassed = maxTouchPct >= 95,
                            sPenTested = sPenTelemetry.maxPressure > 0f,
                            sPenMaxPressure = sPenTelemetry.maxPressure,
                            sPenHoverDetected = sPenTelemetry.hoverDetected
                        )
                    )
                }) {
                    Text("Terminer & Sauvegarder")
                }
            }
        }
    }
}
