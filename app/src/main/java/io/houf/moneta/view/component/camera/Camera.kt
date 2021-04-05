package io.houf.moneta.view.component.camera

import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun Camera(getCases: () -> Array<UseCase>, onError: () -> Unit) {
    val owner = LocalLifecycleOwner.current

    AndroidView(
        factory = { context ->
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            val previewView = PreviewView(context).apply {
                scaleType = PreviewView.ScaleType.FILL_CENTER
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            }

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder()
                    .build()
                    .also { it.setSurfaceProvider(previewView.surfaceProvider) }

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        owner,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview,
                        *getCases()
                    )
                } catch (e: Exception) {
                    Log.d("moneta.camera", "Camera lifecycle binding failed", e)
                    onError()
                }
            }, context.mainExecutor)

            previewView
        }
    )
}
