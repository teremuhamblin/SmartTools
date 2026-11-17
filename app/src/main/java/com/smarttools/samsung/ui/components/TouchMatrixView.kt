package com.smarttools.samsung.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.os.Build
import android.view.MotionEvent
import android.view.View

enum class TouchTestMode {
    OLED_PIXELS,
    TOUCH_MATRIX,
    SPEN_DIGITIZER
}

data class SPenTelemetry(
    val pressure: Float = 0f,
    val maxPressure: Float = 0f,
    val tiltDegrees: Float = 0f,
    val hoverDetected: Boolean = false,
    val primaryButtonPressed: Boolean = false,
    val isEraser: Boolean = false
)

class TouchMatrixView(context: Context) : View(context) {

    var mode: TouchTestMode = TouchTestMode.TOUCH_MATRIX
        set(value) {
            field = value
            resetCurrentTest()
        }

    var onMatrixProgress: ((validated: Int, total: Int) -> Unit)? = null
    var onSPenTelemetry: ((SPenTelemetry) -> Unit)? = null
    var onOledColorChanged: ((colorName: String) -> Unit)? = null

    // OLED Solid Colors (Dead pixels & AMOLED burn-in check)
    private val oledColors = listOf(
        Color.RED to "Rouge (#FF0000)",
        Color.GREEN to "Vert (#00FF00)",
        Color.BLUE to "Bleu (#0000FF)",
        Color.WHITE to "Blanc (#FFFFFF)",
        Color.BLACK to "Noir OLED (#000000)",
        Color.rgb(128, 128, 128) to "Gris 50% (Burn-in)"
    )
    private var oledIndex = 0

    // Touch Grid
    private val cols = 10
    private val rows = 20
    private val visitedCells = BooleanArray(cols * rows)
    private var activePointers = listOf<Pair<Float, Float>>()

    // S-Pen Strokes
    private data class StrokeSegment(val path: Path, val width: Float, val color: Int)
    private val stylusStrokes = mutableListOf<StrokeSegment>()
    private var currentPath: Path? = null
    private var hoverPoint: Pair<Float, Float>? = null
    private var telemetry = SPenTelemetry()

    private val gridBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.argb(90, 200, 200, 200)
        style = Paint.Style.STROKE
        strokeWidth = 2f
    }
    private val cellDonePaint = Paint().apply {
        color = Color.argb(180, 22, 163, 74)
        style = Paint.Style.FILL
    }
    private val pointerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.CYAN
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }
    private val stylusPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }
    private val hoverPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.YELLOW
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setOnHoverListener { _, event ->
            if (mode == TouchTestMode.SPEN_DIGITIZER &&
                event.getToolType(0) == MotionEvent.TOOL_TYPE_STYLUS
            ) {
                when (event.actionMasked) {
                    MotionEvent.ACTION_HOVER_ENTER, MotionEvent.ACTION_HOVER_MOVE -> {
                        hoverPoint = event.x to event.y
                        telemetry = telemetry.copy(
                            hoverDetected = true,
                            tiltDegrees = Math.toDegrees(event.getAxisValue(MotionEvent.AXIS_TILT).toDouble()).toFloat()
                        )
                        onSPenTelemetry?.invoke(telemetry)
                        invalidate()
                        true
                    }
                    MotionEvent.ACTION_HOVER_EXIT -> {
                        hoverPoint = null
                        invalidate()
                        true
                    }
                    else -> false
                }
            } else {
                false
            }
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        // Empêche le geste Retour One UI d'interrompre le test sur les bords (max ~200dp par bord)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && height > 0) {
            val edgeWidth = (36 * resources.displayMetrics.density).toInt()
            val maxBand = (200 * resources.displayMetrics.density).toInt().coerceAtMost(height)
            val topY = ((height - maxBand) / 2).coerceAtLeast(0)
            systemGestureExclusionRects = listOf(
                Rect(0, topY, edgeWidth, topY + maxBand),
                Rect(width - edgeWidth, topY, width, topY + maxBand)
            )
        }
    }

    fun resetCurrentTest() {
        visitedCells.fill(false)
        stylusStrokes.clear()
        currentPath = null
        hoverPoint = null
        activePointers = emptyList()
        oledIndex = 0
        if (mode == TouchTestMode.TOUCH_MATRIX) {
            onMatrixProgress?.invoke(0, visitedCells.size)
        } else if (mode == TouchTestMode.OLED_PIXELS) {
            onOledColorChanged?.invoke(oledColors[oledIndex].second)
        }
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        when (mode) {
            TouchTestMode.OLED_PIXELS -> {
                canvas.drawColor(oledColors[oledIndex].first)
            }
            TouchTestMode.TOUCH_MATRIX -> {
                canvas.drawColor(Color.rgb(18, 24, 34))
                val cellW = width.toFloat() / cols
                val cellH = height.toFloat() / rows
                for (r in 0 until rows) {
                    for (c in 0 until cols) {
                        val l = c * cellW
                        val t = r * cellH
                        if (visitedCells[r * cols + c]) {
                            canvas.drawRect(l, t, l + cellW, t + cellH, cellDonePaint)
                        }
                        canvas.drawRect(l, t, l + cellW, t + cellH, gridBorderPaint)
                    }
                }
                activePointers.forEach { (px, py) ->
                    canvas.drawCircle(px, py, 44f, pointerPaint)
                }
            }
            TouchTestMode.SPEN_DIGITIZER -> {
                canvas.drawColor(Color.rgb(12, 16, 24))
                stylusStrokes.forEach { seg ->
                    stylusPaint.strokeWidth = seg.width
                    stylusPaint.color = seg.color
                    canvas.drawPath(seg.path, stylusPaint)
                }
                hoverPoint?.let { (hx, hy) ->
                    canvas.drawCircle(hx, hy, 22f, hoverPaint)
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (mode) {
            TouchTestMode.OLED_PIXELS -> {
                if (event.actionMasked == MotionEvent.ACTION_UP) {
                    oledIndex = (oledIndex + 1) % oledColors.size
                    onOledColorChanged?.invoke(oledColors[oledIndex].second)
                    invalidate()
                }
                return true
            }
            TouchTestMode.TOUCH_MATRIX -> {
                handleMatrixTouch(event)
                return true
            }
            TouchTestMode.SPEN_DIGITIZER -> {
                handleSPenTouch(event)
                return true
            }
        }
    }

    private fun handleMatrixTouch(event: MotionEvent) {
        if (width <= 0 || height <= 0) return
        val pointers = mutableListOf<Pair<Float, Float>>()

        if (event.actionMasked != MotionEvent.ACTION_UP &&
            event.actionMasked != MotionEvent.ACTION_CANCEL
        ) {
            for (p in 0 until event.pointerCount) {
                for (h in 0 until event.historySize) {
                    markCell(event.getHistoricalX(p, h), event.getHistoricalY(p, h))
                }
                val x = event.getX(p)
                val y = event.getY(p)
                markCell(x, y)
                pointers.add(x to y)
            }
        }
        activePointers = pointers
        onMatrixProgress?.invoke(visitedCells.count { it }, visitedCells.size)
        invalidate()
    }

    private fun markCell(x: Float, y: Float) {
        val c = ((x / width) * cols).toInt().coerceIn(0, cols - 1)
        val r = ((y / height) * rows).toInt().coerceIn(0, rows - 1)
        visitedCells[r * cols + c] = true
    }

    private fun handleSPenTouch(event: MotionEvent) {
        val toolType = event.getToolType(0)
        // Rejet de paume : on n'accepte que le stylet (ou la gomme du stylet)
        if (toolType != MotionEvent.TOOL_TYPE_STYLUS && toolType != MotionEvent.TOOL_TYPE_ERASER) {
            return
        }

        val pressure = event.pressure.coerceIn(0f, 1f)
        val isEraser = toolType == MotionEvent.TOOL_TYPE_ERASER
        val btnPressed = (event.buttonState and MotionEvent.BUTTON_STYLUS_PRIMARY) != 0
        val tilt = Math.toDegrees(event.getAxisValue(MotionEvent.AXIS_TILT).toDouble()).toFloat()

        telemetry = telemetry.copy(
            pressure = pressure,
            maxPressure = maxOf(telemetry.maxPressure, pressure),
            tiltDegrees = tilt,
            primaryButtonPressed = telemetry.primaryButtonPressed || btnPressed,
            isEraser = isEraser
        )
        onSPenTelemetry?.invoke(telemetry)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                hoverPoint = null
                val path = Path().apply { moveTo(event.x, event.y) }
                currentPath = path
                val color = if (isEraser) Color.rgb(239, 68, 68) else Color.rgb(56, 189, 248)
                stylusStrokes.add(StrokeSegment(path, 4f + pressure * 24f, color))
            }
            MotionEvent.ACTION_MOVE -> {
                val path = currentPath ?: return
                for (h in 0 until event.historySize) {
                    path.lineTo(event.getHistoricalX(0, h), event.getHistoricalY(0, h))
                }
                path.lineTo(event.x, event.y)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                currentPath = null
            }
        }
        invalidate()
    }
}
