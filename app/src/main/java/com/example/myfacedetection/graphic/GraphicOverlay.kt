package com.example.myfacedetection.graphic

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class GraphicOverlay<T : GraphicOverlay.Graphic>(context: Context, attrs: AttributeSet) :
    View(context, attrs) {

    private val mLock = Any()
    private val mGraphics = HashSet<T>()

    abstract class Graphic {
        abstract fun draw(canvas: Canvas)
    }

    fun clear() {
        synchronized(mLock) {
            mGraphics.clear()
        }
        postInvalidate()
    }

    fun add(graphic: Graphic) {
        synchronized(mLock) {
            mGraphics.add(graphic as T)
        }
        postInvalidate()
    }

    fun remove(graphic: T) {
        synchronized(mLock) {
            mGraphics.remove(graphic)
        }
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        synchronized(mLock) {
            for (graphic in mGraphics) {
                graphic.draw(canvas)
            }
        }
    }
}