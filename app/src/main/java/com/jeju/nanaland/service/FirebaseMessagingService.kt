package com.jeju.nanaland.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.toIcon
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.jeju.nanaland.MainActivity
import com.jeju.nanaland.R

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        notify(message)

        Log.e("onMessageReceived", "messageId: ${message.messageId}")
        Log.e("onMessageReceived", "data: ${message.data}")
        Log.e("onMessageReceived", "messageType: ${message.messageType}")
        Log.e("onMessageReceived", "from: ${message.from}")
        Log.e("onMessageReceived", "collapseKey: ${message.collapseKey}")
        Log.e("onMessageReceived", "title: ${message.notification?.title}")
        Log.e("onMessageReceived", "body: ${message.notification?.body}")
        Log.e("onMessageReceived", "imageUrl: ${message.notification?.imageUrl}")
        Log.e("onMessageReceived", "originalPriority: ${message.originalPriority}")
        Log.e("onMessageReceived", "priority: ${message.priority}")
        Log.e("onMessageReceived", "rawData: ${message.rawData}")
        Log.e("onMessageReceived", "senderId: ${message.senderId}")
        Log.e("onMessageReceived", "sentTime: ${message.sentTime}")
        Log.e("onMessageReceived", "to: ${message.to}")
        Log.e("onMessageReceived", "ttl: ${message.ttl}")
    }

    private fun notify(msg: RemoteMessage) {
        val channelId = resources.getString(R.string.default_notification_channel_id)

        getNotificationChannel(channelId).notify(
            channelId.toInt(),
            getNotification(msg, channelId)
        )
    }

    private fun getNotificationChannel(channelId: String): NotificationManager{
        val channel = NotificationChannel(
            channelId,
            resources.getString(R.string.app_name),
            NotificationManager.IMPORTANCE_HIGH
        )

        return (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).apply {
            createNotificationChannel(channel)
        }
    }

    private fun getNotification(msg: RemoteMessage, channelId: String): Notification {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )

        return NotificationCompat.Builder(
            this,
            channelId
        )
            .setSmallIcon(R.drawable.ic_logo)
            .setColor(getColor(R.color.main))
            .setContentTitle(msg.notification?.title)
            .setContentText(msg.notification?.body)
            .apply {
                msg.notification?.imageUrl?.let {
                    setLargeIcon(it.toIcon())
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        setStyle(
                            NotificationCompat.BigPictureStyle()
                                .bigPicture(it.toIcon())
                                .bigLargeIcon(null as Icon?)
                        )
                    }
                }
            }
            .setContentIntent(pendingIntent)
            .setGroupSummary(true)
            .setAutoCancel(true)
            .build()
    }

}