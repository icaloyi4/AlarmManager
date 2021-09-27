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
import android.widget.TextView
import androidx.core.app.NotificationCompat
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    lateinit var timePicker : TimePicker
    val NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL"
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timePicker = findViewById(R.id.timepicker)

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

            /*val sharedData = SharedData(this)
            //var data = sharedData.getDataLong("myAlarm")
            sharedData.setDataLong("myAlarm", calendar.timeInMillis)


            val alarmSetting = AlarmSetting(this)
            alarmSetting.setAlarm(calendar.timeInMillis)*/

            openWhatsApp("62", "6281333169082")
            Toast.makeText(this, "Click Bos que", Toast.LENGTH_SHORT).show()
            count++
            findViewById<TextView>(R.id.txt_count).text = count.toString()

//            showNotification(this, "Test", "Test Bosque", null)

        }
    }

    private fun openWhatsApp(countryCode: String, mobile: String) {
        val smsNumber = "7****" // E164 format without '+' sign

        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.type = "text/plain"
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
        sendIntent.putExtra("jid", "$mobile@s.whatsapp.net") //phone number without "+" prefix

        sendIntent.setPackage("com.whatsapp")
        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "Error/n", Toast.LENGTH_SHORT).show()
            return
        }
        startActivity(sendIntent)
    }


}