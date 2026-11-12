package com.smarttools.samsung.utils

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import com.smarttools.samsung.data.DiagnosticReport
import java.io.File
import java.io.FileOutputStream

object PdfExporter {

    fun exportToPdf(context: Context, report: DiagnosticReport): File {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // Format A4 standard
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        val titlePaint = Paint().apply {
            color = Color.rgb(3, 129, 254) // One UI Blue
            textSize = 20f
            isFakeBoldText = true
        }
        val headerPaint = Paint().apply {
            color = Color.DKGRAY
            textSize = 12f
        }
        val textPaint = Paint().apply {
            color = Color.BLACK
            textSize = 14f
        }

        var y = 50f
        canvas.drawText("SmartTools — Rapport de Diagnostic Galaxy", 40f, y, titlePaint)
        y += 25f
        canvas.drawText("Généré le : ${report.getFormattedDate()}", 40f, y, headerPaint)
        y += 40f

        val lines = listOf(
            "Modèle : ${report.specs.modelName} (${report.specs.deviceCode})",
            "Système : ${report.specs.androidVersion} | One UI : ${report.specs.oneUiVersion}",
            "Processeur : ${report.specs.processor}",
            "État Knox : ${report.specs.knoxStatus}",
            "RAM : ${report.specs.availableRamMb} Mo libres sur ${report.specs.totalRamMb} Mo",
            "Stockage : ${report.specs.freeStorageGb} Go libres sur ${report.specs.totalStorageGb} Go",
            "Niveau Batterie : ${report.batteryLevel}% (En charge : ${if (report.isCharging) "Oui" else "Non"})",
            "Température Batterie : ${report.batteryTemperature} °C",
            "Score de Santé Global : ${report.healthScore} / 100"
        )

        for (line in lines) {
            canvas.drawText("• $line", 40f, y, textPaint)
            y += 28f
        }

        pdfDocument.finishPage(page)

        val file = File(context.cacheDir, "Diagnostic_${System.currentTimeMillis()}.pdf")
        val outputStream = FileOutputStream(file)
        pdfDocument.writeTo(outputStream)
        pdfDocument.close()
        outputStream.close()

        return file
    }
}
