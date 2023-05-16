package com.example.myfacedetection.utils

import android.content.res.Configuration
import android.graphics.Rect
import android.graphics.RectF
import androidx.camera.core.CameraSelector
import com.example.myfacedetection.camera.CameraManager
import com.example.myfacedetection.graphic.GraphicOverlay
import kotlin.math.ceil

object CameraUtils {
    private var mScale: Float? = null
    private var mOffsetX: Float? = null
    private var mOffsetY: Float? = null

    fun calculateRect(
        overlay: GraphicOverlay<*>,
        height: Float,
        width: Float,
        boundingBoxT: Rect
    ): RectF {
        fun isLandScapeMode(): Boolean {
            return overlay.context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        }

        fun whenLandScapeModeWidth(): Float {
            return when (isLandScapeMode()) {
                true -> width
                false -> height
            }
        }

        fun whenLandScapeModeHeight(): Float {
            return when (isLandScapeMode()) {
                true -> height
                false -> width
            }
        }

        val overlayWidthFloat = overlay.width.toFloat()
        val overlayHeightFloat = overlay.height.toFloat()

        val scaleX = overlayWidthFloat / whenLandScapeModeWidth()
        val scaleY = overlayHeightFloat / whenLandScapeModeHeight()
        val scale = scaleX.coerceAtLeast(scaleY)
        this.mScale = scale

        val offsetX = (overlayWidthFloat - ceil(whenLandScapeModeWidth() * scale)) / 2.0f
        val offsetY = (overlayHeightFloat - ceil(whenLandScapeModeHeight() * scale)) / 2.0f
        this.mOffsetX = offsetX
        this.mOffsetY = offsetY

        val mappedBox = RectF().apply {
            left = boundingBoxT.right * scale + offsetX
            top = boundingBoxT.top * scale + offsetY
            right = boundingBoxT.left * scale + offsetX
            bottom = boundingBoxT.bottom * scale + offsetY
        }

        if (isFrontCamera()) {
            val centerX = overlay.width.toFloat() / 2
            mappedBox.apply {
                left = centerX + (centerX - left)
                right = centerX - (right - centerX)
            }
        }

        return mappedBox
    }

    private fun isFrontCamera() = CameraManager.cameraOption == CameraSelector.LENS_FACING_FRONT

}