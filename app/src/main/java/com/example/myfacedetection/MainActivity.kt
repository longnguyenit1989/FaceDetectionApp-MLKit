package com.example.myfacedetection

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myfacedetection.camera.CameraActivity
import com.example.myfacedetection.databinding.ActivityMainBinding
import com.example.myfacedetection.extension.isPermissionGranted
import com.example.myfacedetection.extension.showDialogCameraPermissionRequest
import com.example.myfacedetection.utils.DeviceUtils
import com.example.myfacedetection.utils.permission.PermissionUtils

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted == true) {
                startCameraActivity()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    private fun startCameraActivity() {
        CameraActivity.startActivity(this@MainActivity)
    }

    private fun requestCameraOrStart() {
        when {
            isPermissionGranted(PermissionUtils.Manifest_CAMERA) -> {
                startCameraActivity()
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