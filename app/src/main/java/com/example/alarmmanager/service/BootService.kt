package com.example.alarmmanager.service

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.alarmmanager.AlarmSetting
import com.example.alarmmanager.utils.SharedData
import androidx.core.content.ContextCompat.startActivity

import android.content.pm.PackageManager

import android.content.pm.ResolveInfo

import android.content.ComponentName
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import java.lang.Exception


class BootService : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context!=null){
            val sharedData = SharedData(context)
            var myAlarm = sharedData.getDataLong("myAlarm")

            if (!myAlarm.equals(0)){
                val alarmSetting = AlarmSetting(context)
                alarmSetting.setAlarm(myAlarm)
            }

        }

//        if (intent?.action == "android.intent.action.BOOT_COMPLETED") {
//            Toast.makeText(context, "Masuk Boot Receivernya", Toast.LENGTH_SHORT).show()
//            if (context!=null){
//                val sharedData = SharedData(context)
//                var myAlarm = sharedData.getDataLong("myAlarm")
//
//                if (!myAlarm.equals(0)){
//                    val alarmSetting = AlarmSetting(context)
//                    alarmSetting.setAlarm(myAlarm)
//                }
//
//            }
//        }
    }

    fun showNotification(context: Context, title: String?, body: String?, intent: Intent?) {
        try{
            val notificationManager =
                context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            val notificationId = 1
            val channelId = "channel-01"
            val channelName = "Channel Name"
            val importance = NotificationManager.IMPORTANCE_HIGH
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val mChannel = NotificationChannel(
                    channelId, channelName, importance
                )
                notificationManager.createNotificationChannel(mChannel)
            }
            val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(com.example.alarmmanager.R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
            /*val stackBuilder: TaskStackBuilder = TaskStackBuilder.create(context)
            val resultPendingIntent: PendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            mBuilder.setContentIntent(resultPendingIntent)*/
            notificationManager.notify(notificationId, mBuilder.build())
        } catch (e : Exception){
            Log.e("Error", e.toString())
        }

    }
}