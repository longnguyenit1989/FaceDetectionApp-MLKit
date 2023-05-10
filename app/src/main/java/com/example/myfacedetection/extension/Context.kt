package com.example.myfacedetection.extension

import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.isPermissionGranted(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Context.showDialogCameraPermissionRequest(positive: () -> Unit) {
    AlertDialog.Builder(this)
        .setTitle("Camera Permission Required")
        .setMessage("Without accessing the camera it is not possible to Face Detection...")
        .setPositiveButton("Allow Camera") { dialogf                                                                                                                                                                                                                                , which ->
            positive.invoke()
        }.setNegativeButton("Cancel") { dialog, which ->

        }.show()
}