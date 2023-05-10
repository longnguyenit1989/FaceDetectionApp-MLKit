package com.example.myfacedetection

import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myfacedetection.databinding.ActivityMainBinding
import com.example.myfacedetection.extension.isPermissionGranted
import com.example.myfacedetection.extension.showDialogCameraPermissionRequest
import com.example.myfacedetection.facedetector.FaceDetectionActivity
import com.example.myfacedetection.utils.DeviceUtils
import com.example.myfacedetection.utils.permission.PermissionUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted == true) {
                startCamera()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleClickListener()
    }

    private fun handleClickListener() {
        binding.apply {
            btOpenFaceDetection.setOnClickListener {
                requestCameraOrStart()
            }
        }
    }

    private fun startCamera() {
        FaceDetectionActivity.startActivity(this@MainActivity)
    }

    private fun requestCameraOrStart() {
        when {
            isPermissionGranted(PermissionUtils.Manifest_CAMERA) -> {
                startCamera()
            }
            shouldShowRequestPermissionRationale(PermissionUtils.Manifest_CAMERA) -> {
                showDialogCameraPermissionRequest(positive = { DeviceUtils.openSettingsApp(this@MainActivity) })
            }
            else -> {
                requestPermissionLauncher.launch(PermissionUtils.Manifest_CAMERA)
            }
        }
    }
}