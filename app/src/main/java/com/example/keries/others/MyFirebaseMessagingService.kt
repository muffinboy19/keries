package com.example.keries.others

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.keries.R
import com.example.keries.activity.BaseHome
import  com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


const val channelId = "nfc"
private val TAG = "MyFirebaseMessaging"
const val channelName = "Effe"
class MyFirebaseMessagingService : FirebaseMessagingService() {


    override fun onMessageReceived(remoteMesage: RemoteMessage) {
        if(remoteMesage.notification !=null){
            generateNotfication(remoteMesage.notification!!.title!!,remoteMesage.notification!!.body!!
            )
        }
        Log.d(TAG, "From: ${remoteMesage.from}")

        // Check if message contains a data payload.
        if (remoteMesage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMesage.data}")
            Toast.makeText(this,"wow",Toast.LENGTH_SHORT).show()
        }

        // Check if message contains a notification payload.
        remoteMesage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")
    }

    fun getRemoteView(title: String,messge: String): RemoteViews {
        val remoteView = RemoteViews("com.example.keries", R.layout.notification)
        remoteView.setTextViewText(R.id.titlenoto,title)
        remoteView.setTextViewText(R.id.nessagenoto,messge)
        remoteView.setImageViewResource(R.id.logonoto, R.drawable.effesvghome)

        return remoteView

    }
    fun generateNotfication(title: String,messge: String){
        val intent = Intent(this, BaseHome::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)



        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext,
            channelId
        )

            .setSmallIcon(R.drawable.effesvghome)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(title,messge))

        val notificationManager  = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val notificationChannel = NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)

        }
        notificationManager.notify(0,builder.build())

    }

}