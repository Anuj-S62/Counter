package com.example.counter

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.getActivity
import android.app.PendingIntent.getBroadcast
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class AppService:Service(){

    val notificationManager : NotificationManager? = null

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val id = intent?.getStringExtra("id").toString()
        val cName = intent?.getStringExtra("cName").toString()
        val cnt = intent?.getStringExtra("cnt").toString()
        val inc = intent?.getStringExtra("inc").toString()
        val dec = intent?.getStringExtra("dec").toString()

        when(intent?.action){
            Actions.START.toString() -> start(cName,cnt,id,inc,dec)
            Actions.STOP.toString() -> stopSelf()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun start(title:String,count:String,id:String,inc:String,dec:String){

        showNotification(title,count,id,inc,dec,notificationManager)
    }
    fun showNotification(title:String,count:String,id:String,inc:String,dec:String,notificationManager: NotificationManager? = this.notificationManager){

        val intent = Intent(applicationContext, MainActivity:: class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        getActivity(applicationContext, 0, intent, FLAG_IMMUTABLE)
        val increaseIntent = Intent(applicationContext, IncreaseBroadcastReceiver::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("id",id)
            putExtra("cnt",count)
            putExtra("inc",inc)
            putExtra("dec",dec)
            putExtra("title",title)
        }
        val increasePendingIntent = getBroadcast(
            applicationContext,
            id.toInt(),
            increaseIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE
        )
        val increaseAction: NotificationCompat.Action = NotificationCompat.Action(1, "+$inc",increasePendingIntent)

        val decreaseIntent = Intent(applicationContext, DecreaseBroadcastReceiver::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("id",id)
            putExtra("cnt",count)
            putExtra("inc",inc)
            putExtra("dec",dec)
            putExtra("title",title)
        }
        val decreasePendingIntent = getBroadcast(
            applicationContext,
            id.toInt(),
            decreaseIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE
        )

        val notification: Notification =
            NotificationCompat.Builder(applicationContext, "running_channel")
                .setSmallIcon(R.mipmap.ic_launcher_counter_foreground)
                .setContentTitle("$title")
                .setAutoCancel(true)
                .addAction(increaseAction)
                .addAction(R.drawable.ic_launcher_foreground,"-$dec",decreasePendingIntent)
                .setContentText(Normalize(count))
                .build()
        if(notificationManager==null){
            startForeground(123,notification)

        }
        else notificationManager.notify(123,notification)
    }

    enum class Actions {
        START,
        STOP
    }
}

