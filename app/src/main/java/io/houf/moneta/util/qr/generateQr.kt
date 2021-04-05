package io.houf.moneta.util.qr

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

fun generateQr(content: String): ImageBitmap? {
    if (content.isEmpty()) {
        return null
    }

    val writer = QRCodeWriter()

    return try {
        val matrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512)
        val width = matrix.width
        val height = matrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (matrix[x, y]) Color.BLACK else Color.WHITE)
            }
        }

        bitmap.asImageBitmap()
    } catch (e: WriterException) {
        e.printStackTrace()
        null
    }
}
