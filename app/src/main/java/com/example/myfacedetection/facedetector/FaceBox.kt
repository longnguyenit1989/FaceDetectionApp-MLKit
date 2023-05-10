package com.example.myfacedetection.facedetector

import android.graphics.*
import com.google.mlkit.vision.face.Face


class FaceBox(
    overlay: FaceBoxOverlay,
    private val face: Face,
    private val imageRectF: Rect
) : FaceBoxOverlay.FaceBox(overlay) {

    private val paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 6.0f
    }

    override fun draw(canvas: Canvas?) {
        val rect = getBoxRect(
            imageRectWidth = imageRectF.width().toFloat(),
            imageRectHeight = imageRectF.height().toFloat(),
            faceBounding = face.boundingBox
        )
        canvas?.drawRect(rect, paint)
    }
}