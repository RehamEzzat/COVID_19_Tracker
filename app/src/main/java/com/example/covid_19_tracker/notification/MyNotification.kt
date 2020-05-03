package com.example.covid_19_tracker.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color.RED
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.covid_19_tracker.R
import com.example.covid_19_tracker.view.MainActivity

class MyNotification : AppCompatActivity() {

    private var notificationManager: NotificationManager? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        createNotificationChannel(      "com.ITI.covid_19_tracker.news",
            "Covid_19_Tracker",
            "New Information For Covid_19_Tracker")

    }


    private fun createNotificationChannel(id: String, name: String, description: String) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(id, name, importance)
            channel.description = description
            channel.enableLights(true)
            channel.lightColor = RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            notificationManager?.createNotificationChannel(channel)
        }
    }


    fun sendNotification(view: View) {

        val notificationID = 101
        val intent = Intent(this , MainActivity::class.java)
        val pendingIntent  = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT )
        val channelID = "com.ITI.covid_19_tracker.news"
        val notification:Notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             notification = Notification.Builder(
                this@MyNotification,
                channelID)
                .setContentTitle("New Information")
                .setContentText("New Information Related to cases at COVID_19")
                .setSmallIcon(R.drawable.ic_virus)
                .setChannelId(channelID)
                .setContentIntent(pendingIntent)
                .build()
        } else {
             notification = Notification.Builder(this)
                .setContentTitle("New Information")
                .setContentText("New Information Related to cases at COVID_19")
                .setSmallIcon(R.drawable.ic_virus)

                .setContentIntent(pendingIntent)
                .build()
        }

        notificationManager?.notify(notificationID, notification)






    }
}