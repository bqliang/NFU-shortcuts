package com.bqliang.nfushortcuts.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.tools.getStringFromSharedPreferences
import com.bqliang.nfushortcuts.tools.loginWIFI
import com.bqliang.nfushortcuts.tools.showToast
import kotlin.concurrent.thread

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel("foreground_service", "前台服务通知", NotificationManager.IMPORTANCE_MIN)
        notificationManager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(this, "foreground_service")
            .setContentTitle("Title")
            .setContentText("Text")
            .build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val request = NetworkRequest.Builder()
                //.addCapability(NET_CAPABILITY_CAPTIVE_PORTAL)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build()

            connectivityManager.requestNetwork(
                request,
                object: ConnectivityManager.NetworkCallback(){
                    override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    connectivityManager.bindProcessToNetwork(network)
                    val id = getStringFromSharedPreferences("id", null)
                    val password = getStringFromSharedPreferences("password", null)
                        if ( id.isNullOrBlank() || password.isNullOrBlank())
                            resources.getString(R.string.error_id_or_pw).showToast(Toast.LENGTH_LONG)
                        else loginWIFI(id, password)
                    connectivityManager.unregisterNetworkCallback(this)
                    }

                    override fun onUnavailable() {
                        super.onUnavailable()
                        resources.getString(R.string.login_unavailable).showToast(Toast.LENGTH_LONG)
                    }
                },
            2000)
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }
}