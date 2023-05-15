package com.example.myfacedetection.camera

import android.annotation.SuppressLint
import android.graphics.Rect
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.myfacedetection.graphic.GraphicOverlay
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face

abstract class BaseCameraAnalyzer<T : List<Face>> : ImageAnalysis.Analyzer {
    abstract val graphicOverlay: GraphicOverlay<*>

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        mediaImage?.let { image ->
            val task = detectInImage(InputImage.fromMediaImage(image, imageProxy.imageInfo.rotationDegrees))
            task.addOnSuccessListener { results ->
                onSuccess(results, graphicOverlay, image.cropRect)
                imageProxy.close()
            }
            task.addOnFailureListener {
                onFailure(it)
                imageProxy.close()
            }
        }
    }

    protected abstract fun detectInImage(image: InputImage): Task<T>

    abstract fun stop()

    protected abstract fun onSuccess(
        results: List<Face>,
        graphicOverlay: GraphicOverlay<*>,
        rect: Rect
    )

    protected abstract fun onFailure(e: Exception)
}