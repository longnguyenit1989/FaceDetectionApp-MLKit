package com.example.myfacedetection.utils.permission

import android.Manifest

class PermissionUtils {
  companion object {
    const val Manifest_BODY_SENSORS = Manifest.permission.BODY_SENSORS
    const val Manifest_READ_CALENDAR = Manifest.permission.READ_CALENDAR
    const val Manifest_WRITE_CALENDAR = Manifest.permission.WRITE_CALENDAR
    const val Manifest_READ_CONTACTS = Manifest.permission.READ_CONTACTS
    const val Manifest_WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS
    const val Manifest_GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS
    const val Manifest_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    const val Manifest_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
    const val Manifest_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO
    const val Manifest_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE
    const val Manifest_CALL_PHONE = Manifest.permission.CALL_PHONE
    const val Manifest_READ_CALL_LOG = Manifest.permission.READ_CALL_LOG
    const val Manifest_WRITE_CALL_LOG = Manifest.permission.WRITE_CALL_LOG
    const val Manifest_ADD_VOICEMAIL = Manifest.permission.ADD_VOICEMAIL
    const val Manifest_USE_SIP = Manifest.permission.USE_SIP
    const val Manifest_PROCESS_OUTGOING_CALLS = Manifest.permission.PROCESS_OUTGOING_CALLS
    const val Manifest_CAMERA = Manifest.permission.CAMERA
    const val Manifest_SEND_SMS = Manifest.permission.SEND_SMS
    const val Manifest_RECEIVE_SMS = Manifest.permission.RECEIVE_SMS
    const val Manifest_READ_SMS = Manifest.permission.READ_SMS
    const val Manifest_RECEIVE_WAP_PUSH = Manifest.permission.RECEIVE_WAP_PUSH
    const val Manifest_RECEIVE_MMS = Manifest.permission.RECEIVE_MMS
    const val Manifest_POST_NOTIFICATIONS = Manifest.permission.POST_NOTIFICATIONS

    const val Manifest_GROUP_CALENDAR = Manifest.permission_group.CALENDAR
    const val Manifest_GROUP_CAMERA = Manifest.permission_group.CAMERA
    const val Manifest_GROUP_CONTACTS = Manifest.permission_group.CONTACTS
    const val Manifest_GROUP_LOCATION = Manifest.permission_group.LOCATION
    const val Manifest_GROUP_MICROPHONE = Manifest.permission_group.MICROPHONE
    const val Manifest_GROUP_PHONE = Manifest.permission_group.PHONE
    const val Manifest_GROUP_SENSORS = Manifest.permission_group.SENSORS
    const val Manifest_GROUP_SMS = Manifest.permission_group.SMS
    const val Manifest_GROUP_STORAGE = Manifest.permission_group.STORAGE
  }
}
