package com.jeju.nanaland.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
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
}