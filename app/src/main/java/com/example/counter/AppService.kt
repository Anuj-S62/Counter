package com.example.counter

import android.app.Notification
import android.app.Notification.Action
import android.app.PendingIntent
import android.app.PendingIntent.*
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.channels.ActorScope
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context

class AppService:Service(){
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        var id = intent?.getStringExtra("id").toString()
        var cName = intent?.getStringExtra("cName").toString()
        var cnt = intent?.getStringExtra("cnt").toString()
        var inc = intent?.getStringExtra("inc").toString()
        var dec = intent?.getStringExtra("dec").toString()

        when(intent?.action){
            Actions.START.toString() -> start(cName,cnt,id,inc,dec)
            Actions.STOP.toString() -> stopSelf()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun start(title:String,count:String,id:String,inc:String,dec:String){
        Log.d("title",title)
        Log.d("count",count)
        val intent = Intent(applicationContext, MainActivity:: class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        // 3
        val pendingIntent = getActivity(applicationContext, 0, intent, FLAG_IMMUTABLE)
//        val increaseIntent = Intent(applicationContext, IncreaseBroadcastReceiver::class.java).apply{
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            putExtra("id",id)
//            Log.d("sidjsjdisj",id)
//            putExtra("cnt",count)
//            putExtra("inc",inc)
//        }
//        val increasePendingIntent = sendBroadcast(intent)
//        val increaseAction:Notification.Action? = Notification.Action(1,"+5",increasePendingIntent)

        val inten = Intent(applicationContext, IncreaseBroadcastReceiver::class.java)
        inten.action = "com.example.ACTION_INCREMENT_COUNTER"

// Add extra data to the Intent
        val incrementValue = 10 // Replace with your desired increment value
        inten.putExtra("id",id)

// Create a PendingIntent using getService()
        val increasePendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            0,
            inten,
            FLAG_IMMUTABLE
        )
        val increaseAction:Notification.Action? = Notification.Action(1,"+5",increasePendingIntent)

        val notification: Notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("noti","Notifi")
            Notification.Builder(applicationContext, "running_channel")
                .setSmallIcon(R.mipmap.ic_launcher_counter_foreground)
                .setContentTitle(title + " " + id)
                .setContentIntent(pendingIntent)
                .addAction(increaseAction)
                    .setContentText(count)
                    .build()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        startForeground(123,notification)

    }

    enum class Actions {
        START,
        STOP
    }
}

