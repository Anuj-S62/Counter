package com.example.counter

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncreaseBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {

        val scope = CoroutineScope(Dispatchers.IO)
        val database = MainActivity.database
        val counterDao = database.dao
//        val notificationManager = NotificationManagerCompat.from(p0!!)


        var id : String? = p1?.getStringExtra("id")
        var inc : String? = p1?.getStringExtra("inc")
        var cnt : String? = p1?.getStringExtra("cnt")

        var ans = (cnt!!.toDouble()) + (inc!!.toDouble())

        scope.launch {
            counterDao.updateCounter(ans.toString(),id!!.toInt())
        }

//        val counter : Counter = counterDao.getCounterById(id!!.toInt())

//        val notification: Notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            Log.d("noti","Notifi")
//            Notification.Builder(p0!!, "running_channel")
//                .setSmallIcon(R.mipmap.ic_launcher_counter_foreground)
//                .setContentText(counter.count)
//                .build()
//        } else {
//            TODO("VERSION.SDK_INT < O")
//        }
//        notificationManager.notify(123, notification)
    }
}