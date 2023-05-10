package com.example.myfacedetection.facedetector

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.ceil

class FaceBoxOverlay(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val lock = Any()
    private val faceBoxes: MutableList<FaceBox> = mutableListOf()

    var mScale: Float? = null
    var mOffsetX: Float? = null
    var mOffsetY: Float? = null

    abstract class FaceBox(private val overlay: FaceBoxOverlay) {
        abstract fun draw(canvas: Canvas?)

        fun getBoxRect(imageRectWidth: Float, imageRectHeight: Float, faceBounding: Rect) : RectF {
            val overlayWidthFloat = overlay.width.toFloat()
            val overlayHeightFloat = overlay.height.toFloat()

            val scaleX = overlayWidthFloat / imageRectHeight
            val scaleY = overlayHeightFloat / imageRectWidth
            val scale = scaleX.coerceAtLeast(scaleY)

            overlay.mScale = scale

            val offsetX = (overlayWidthFloat - ceil(imageRectHeight * scale)) / 2.0f
            val offsetY = (overlayHeightFloat - ceil(imageRectWidth * scale)) / 2.0f

            overlay.mOffsetX = offsetX
            overlay.mOffsetY = offsetY

            val mappedBox = RectF().apply {
                left = faceBounding.right * scale + offsetX
                right = faceBounding.left * scale + offsetX
                top = faceBounding.top * scale + offsetY
                bottom = faceBounding.bottom * scale + offsetY
            }

            val centerX = overlayWidthFloat/2

            return mappedBox.apply {
                left = centerX + (centerX - left)
                right = centerX - (right - centerX)
            }
        }
    }

    fun clear() {
        synchronized(lock) {
            faceBoxes.clear()
        }
        postInvalidate()
    }

    fun add(faceBox: FaceBox) {
        synchronized(lock) {
            faceBoxes.add(faceBox)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        synchronized(lock) {
            faceBoxes.forEach {
                it.draw(canvas)
            }
        }
    }
}