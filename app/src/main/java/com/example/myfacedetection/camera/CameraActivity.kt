package com.example.myfacedetection.camera

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myfacedetection.databinding.ActivityCameraBinding

class CameraActivity : AppCompatActivity() {
    companion object {
        private val Tag = CameraActivity::class.java.simpleName

        fun startActivity(context: Context) {
            val intent = Intent(context, CameraActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var cameraManager: CameraManager
    private val binding by lazy { ActivityCameraBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handleClickListener()
        initCamera()
    }

    private fun initCamera() {
        cameraManager =
            CameraManager(this, binding.viewCameraPreview, binding.viewGraphicOverlay, this)
        cameraManager.cameraStart()
    }

    private fun handleClickListener() {
        binding.apply {
            btTurnCamera.setOnClickListener {
                cameraManager.changeCamera()
            }
            btStopCamera.setOnClickListener {
                cameraManager.cameraStop()
                buttonVisibility(false)
            }
            btStartCamera.setOnClickListener {
                cameraManager.cameraStart()
                buttonVisibility(true)
            }
        }
    }

    private fun buttonVisibility(forStart: Boolean) {
        binding.apply {
            if (forStart) {
                btStopCamera.visibility = View.VISIBLE
                btStartCamera.visibility = View.INVISIBLE
            } else {
                btStopCamera.visibility = View.INVISIBLE
                btStartCamera.visibility = View.VISIBLE
            }
        }
    }
}