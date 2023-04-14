package com.kachamba.zpt

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kachamba.zpt.network.ApiHelper
import com.kachamba.zpt.network.RetrofitBuilder
import java.net.HttpURLConnection
import java.net.URL

private const val TAG = "MyFirebaseMessagingService"

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class A4 : FirebaseMessagingService() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("LongLogTag")
    override fun handleIntent(intent: Intent) {
        //Log.d(TAG, "handleIntent = $intent")
        try {
            if (intent.extras != null) {
                val builder = RemoteMessage.Builder("MyFirebaseMessagingService")
                for (key in intent.extras!!.keySet()) {
                    builder.addData(key!!, intent.extras!![key].toString())
                }
                onMessageReceived(builder.build())
                //Log.d(TAG, "handleIntent 1")
            } else {
                super.handleIntent(intent)
                //Log.d(TAG, "handleIntent 2")
            }
        } catch (e: Exception) {
            super.handleIntent(intent)//if app close
            //Log.d(TAG, "handleIntent e: $e")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("LongLogTag", "UnspecifiedImmutableFlag", "ServiceCast",
        "LaunchActivityFromNotification", "DiscouragedApi"
    )
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        //Log.d(TAG, "onMessageReceived")
        //Log.d(TAG, "onMessageReceived: remoteMessage.notification = ${remoteMessage.notification?.imageUrl}")
        //Log.d(TAG, "onMessageReceived: remoteMessage.notification = ${remoteMessage.notification?.icon}")

        //Log.d(TAG, "onMessageReceived: remoteMessage.data = ${remoteMessage.data}")

        val a3 = A3(applicationContext)

        val sentPushId = remoteMessage.data["sent_push_id"].toString()
        val deeplink = remoteMessage.data["deeplink"].toString()
        val openUrl = remoteMessage.data["open_url"]
        a3.f0SSPI(sentPushId)
        a3.f4SD(deeplink)
        if (openUrl != null) {
            a3.f2SOU(openUrl)
        }
        //Log.d(TAG, "onMessageReceived sentPushId: $sentPushId")
        //Log.d(TAG, "onMessageReceived deeplink: $deeplink")
        //Log.d(TAG, "onMessageReceived openUrl: $openUrl")

        // get resId
        val packageName = applicationContext.packageName
        val mLauncher = "ic_launcher"
        val resId = resources.getIdentifier(mLauncher, "mipmap", packageName)
        //Log.d(TAG, "onMessageReceived resId: $resId")
        //Log.d(TAG, "onMessageReceived packageName: $packageName")

        val intent = packageManager.getLaunchIntentForPackage(packageName)
        //intent?.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent?.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK

        // Send pushData to intent
        intent?.putExtra("sent_push_id", remoteMessage.data["sent_push_id"])
        intent?.putExtra("deeplink", remoteMessage.data["deeplink"]).toString()
        intent?.putExtra("open_url", remoteMessage.data["open_url"])

        // Get image
        /*val largeIcon = remoteMessage
            .notification?.imageUrl.let { getBitmapFromUrl(it.toString()) }
        val smallIcon = remoteMessage.notification?.icon.let { getBitmapFromUrl(it) }*/
        val largeIcon = remoteMessage
            .data["image"]?.let { getBitmapFromUrl(it) }
        //Get icon
        val smallIcon = remoteMessage.data["icon"]?.let { getBitmapFromUrl(it) }
        //LOG: Get image
        //Log.d(TAG, "image: ${remoteMessage.data["image"]}")
        //Log.d(TAG, "smallIcon: ${remoteMessage.notification?.imageUrl}")

        val rnds = (1..1000).random()

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                this, rnds, intent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity(
                this, rnds, intent, PendingIntent.FLAG_ONE_SHOT)
        }

        val channelId = "Default"

        if (remoteMessage.notification?.imageUrl != null && remoteMessage.notification?.icon == null) {
            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(resId)
                .setContentTitle(remoteMessage.notification?.title)
                .setContentText(remoteMessage.notification?.body)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setChannelId(channelId)
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(largeIcon)
                    //.bigLargeIcon(largeIcon)
                )
                .setContentIntent(pendingIntent)

            val notificationManager = NotificationManagerCompat.from(this)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    "Default channel",
                    NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(0, builder.build())
        }

        if (remoteMessage.notification?.imageUrl != null && remoteMessage.notification?.icon != null) {
            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(resId)
                .setContentTitle(remoteMessage.notification?.title)
                .setContentText(remoteMessage.notification?.body)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setChannelId(channelId)
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(largeIcon)
                        .bigLargeIcon(smallIcon)
                )
                .setContentIntent(pendingIntent)

            val notificationManager = NotificationManagerCompat.from(this)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    "Default channel",
                    NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(0, builder.build())
        }

        if (remoteMessage.notification?.imageUrl == null && remoteMessage.notification?.icon != null) {
            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(resId)
                .setContentTitle(remoteMessage.notification?.title)
                .setContentText(remoteMessage.notification?.body)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setChannelId(channelId)
                .setLargeIcon(smallIcon)
                .setContentIntent(pendingIntent)

            val notificationManager = NotificationManagerCompat.from(this)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    "Default channel",
                    NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(0, builder.build())
        }

        if (remoteMessage.notification?.imageUrl == null
            && remoteMessage.notification?.icon == null) {
            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(resId)
                .setContentTitle(remoteMessage.notification?.title)
                .setContentText(remoteMessage.notification?.body)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setChannelId(channelId)
                //.setLargeIcon(icon)
                .setContentIntent(pendingIntent)

            val notificationManager = NotificationManagerCompat.from(this)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    "Default channel",
                    NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(0, builder.build())
        }
    }

    @SuppressLint("LongLogTag")
    private fun getBitmapFromUrl(imageUrl: String?): Bitmap {
        val url = URL(imageUrl)
        //Log.d(TAG, "getBitmapFromUrl: url = $url")
        val connection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val input = connection.inputStream
        return BitmapFactory.decodeStream(input)

    }

    @SuppressLint("LongLogTag")
    override fun onNewToken(p0: String) {
        //Log.d(TAG, "Refreshed token: $p0")
        val service = ApiHelper(RetrofitBuilder.apiService)
        val a6 = A6()

        service.updateRegistrationId(p0)
        a6.f6SORIP(p0)
    }
}
