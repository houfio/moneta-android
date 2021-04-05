package io.houf.moneta.util.qr

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun generateQr(content: String, onSuccess: (ImageBitmap?) -> Unit) {
    if (content.isEmpty()) {
        return onSuccess(null)
    }

    val writer = QRCodeWriter()

    GlobalScope.launch {
        var result: ImageBitmap? = null

        try {
            val matrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512)
            val width = matrix.width
            val height = matrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (matrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }

            result = bitmap.asImageBitmap()
        } catch (e: WriterException) {
            Log.d("moneta.qr", "QR code generation failed", e)
        }

        withContext(Dispatchers.Main) {
            onSuccess(result)
        }
    }
}
