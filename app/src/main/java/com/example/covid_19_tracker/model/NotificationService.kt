package com.example.covid_19_tracker.model

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.core.app.NotificationCompat
import com.example.covid_19_tracker.R
import com.example.covid_19_tracker.view.MainActivity

class NotificationService(var context: Context) {

    private var notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val NOTIFICATION_CHANNEL_ID = "com.ITI.covid_19_tracker.news"
    private val NOTIFICATION_CHANNEL_NAME = "Covid_19_Tracker"
    private val NOTIFICATION_CHANNEL_DESCRIPTION = "New Information For Covid_19_Tracker"

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW)

            channel.description = NOTIFICATION_CHANNEL_DESCRIPTION
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

            notificationManager?.createNotificationChannel(channel)
        }
    }


    fun sendNotification() {
        val notificationID = 101

        val intent = Intent(context , MainActivity::class.java)
        val pendingIntent  = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT )


        val notification: Notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("New Information")
                .setContentText("New Information Related to cases at COVID_19")
                .setSmallIcon(R.drawable.ic_virus)
                .setChannelId(NOTIFICATION_CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                //.setStyle(NotificationCompat.BigTextStyle().bigText("list of changed countries"))
                .build()
        } else {
            notification = Notification.Builder(context)
                .setContentTitle("New Information")
                .setContentText("New Information Related to cases at COVID_19")
                .setSmallIcon(R.drawable.ic_virus)
                .setContentIntent(pendingIntent)
                .build()
        }
        notificationManager?.notify(notificationID, notification)
    }
}