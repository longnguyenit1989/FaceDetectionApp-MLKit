package com.example.myfacedetection.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity

object DeviceUtils {

  fun openSettingsApp(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.parse("package:" + context.packageName)
    context.startActivity(intent)
  }

  fun openSettingApp(activity: FragmentActivity, callBack: ActivityResultCallback<ActivityResult>) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.parse("package:" + activity.packageName)
    activity.activityResultRegistry
      .register("openSetting", ActivityResultContracts.StartActivityForResult(), callBack)
      .launch(intent)
  }

  fun openBrowserWithUrl(activity: Activity, urlParam: String) {
    var url = urlParam
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
      url = "http://$url"
    }
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    activity.startActivity(browserIntent)
  }
}
