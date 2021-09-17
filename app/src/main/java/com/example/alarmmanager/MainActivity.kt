package com.example.alarmmanager

import android.app.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TimePicker
import java.util.*
import android.widget.Toast

import android.content.Context

import android.content.Intent
import android.net.Uri
import com.example.alarmmanager.service.AlarmService
import com.example.alarmmanager.service.MyService
import com.example.alarmmanager.utils.SharedData
import android.content.pm.PackageManager

import android.content.pm.ResolveInfo

import android.content.ComponentName
import android.util.Log
import androidx.core.app.NotificationCompat
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    lateinit var timePicker : TimePicker
    val NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timePicker = findViewById(R.id.timepicker)
        addAutoStartup()

        findViewById<Button>(R.id.btn_set_alarm).setOnClickListener {
            // Do some work here
            val calendar: Calendar = Calendar.getInstance()
            if (Build.VERSION.SDK_INT >= 23) {
                calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.hour,
                    timePicker.minute,
                    0
                )
            } else {
                calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.currentHour,
                    timePicker.currentMinute,
                    0
                )
            }

            val sharedData = SharedData(this)
            //var data = sharedData.getDataLong("myAlarm")
            sharedData.setDataLong("myAlarm", calendar.timeInMillis)


            val alarmSetting = AlarmSetting(this)
            alarmSetting.setAlarm(calendar.timeInMillis)


//            showNotification(this, "Test", "Test Bosque", null)

        }
    }

    private fun addAutoStartup() {
        try {
            val intent = Intent()
            val manufacturer = Build.MANUFACTURER
            if ("xiaomi".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.miui.securitycenter",
                    "com.miui.permcenter.autostart.AutoStartManagementActivity"
                )
            } else if ("oppo".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.coloros.safecenter",
                    "com.coloros.safecenter.permission.startup.StartupAppListActivity"
                )
            } else if ("vivo".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.vivo.permissionmanager",
                    "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"
                )
            } else if ("Letv".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.letv.android.letvsafe",
                    "com.letv.android.letvsafe.AutobootManageActivity"
                )
            } else if ("Honor".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.huawei.systemmanager",
                    "com.huawei.systemmanager.optimize.process.ProtectActivity"
                )
            }
            val list =
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
            if (list.size > 0) {
                startActivity(intent)
            }
        } catch (e: Exception) {
            Log.e("exc", e.toString())
        }
    }


}