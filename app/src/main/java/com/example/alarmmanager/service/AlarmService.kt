package com.example.alarmmanager.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.AlarmManager

import android.app.PendingIntent
import android.media.MediaPlayer
import android.net.Uri

import android.provider.Settings
import android.util.Log
import com.example.alarmmanager.MainActivity
import androidx.core.app.NotificationCompat

import android.R
import android.app.NotificationChannel

import android.graphics.BitmapFactory

import android.media.RingtoneManager

import android.app.NotificationManager
import android.os.Build
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception


class AlarmService : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("Test Alarm", "Start")
//        var mediaPlayer : MediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI)
//        mediaPlayer.start()
        if (context != null) {
        val phoneNumberWithCountryCode = "+6281333169082"
        val message = "Hallo"

//        var intent = Intent(
//            Intent.ACTION_VIEW,
//            Uri.parse(
//                String.format(
//                    "https://api.whatsapp.com/send?phone=%s&text=%s",
//                    phoneNumberWithCountryCode,
//                    message
//                )
//            )
//        )
//        intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK
            showNotification(context, "Test", "Test Alarm", null)
        }


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