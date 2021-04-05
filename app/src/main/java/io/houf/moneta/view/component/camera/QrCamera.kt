package io.houf.moneta.view.component.camera

import androidx.camera.core.ImageAnalysis
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import io.houf.moneta.util.qr.QrAnalyser
import java.util.concurrent.Executors

@Composable
fun QrCamera(onSuccess: (String) -> Unit, onError: () -> Unit) {
    val cameraExecutor = Executors.newSingleThreadExecutor()

    Camera(
        getCases = {
            arrayOf(
                ImageAnalysis.Builder()
                    .build()
                    .also { it.setAnalyzer(cameraExecutor, QrAnalyser(onSuccess, onError)) })
        },
        onError = onError
    )

    DisposableEffect(Unit) {
        onDispose {
            cameraExecutor.shutdown()
        }
    }
}
