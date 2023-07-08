package com.example.counter

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncreaseBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {


        val id : String? = p1?.getStringExtra("id")
        val inc : String? = p1?.getStringExtra("inc")
        val cnt : String? = p1?.getStringExtra("cnt")
        val title : String?  = p1?.getStringExtra("title")
        val dec : String? = p1?.getStringExtra("dec")
        val notificationManager : NotificationManager = p0!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager



        val scope = CoroutineScope(Dispatchers.IO)
        val database = MainActivity.database
        val counterDao = database.dao


        var ans = ((cnt!!.toDouble()) + (inc!!.toDouble())).toString()

        scope.launch {
            counterDao.updateCounter(ans,id!!.toInt())
        }


        val intent = Intent(p0, MainActivity:: class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent =
            PendingIntent.getActivity(p0, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val increaseIntent = Intent(p0, IncreaseBroadcastReceiver::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("id",id)
            putExtra("cnt",ans)
            putExtra("inc",inc)
            putExtra("dec",dec)
            putExtra("title",title)
        }
        val increasePendingIntent = PendingIntent.getBroadcast(
            p0,
            id!!.toInt(),
            increaseIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val increaseAction: NotificationCompat.Action = NotificationCompat.Action(1, "+$inc",increasePendingIntent)

        val decreaseIntent = Intent(p0, DecreaseBroadcastReceiver::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("id",id)
            putExtra("cnt",ans)
            putExtra("inc",inc)
            putExtra("dec",dec)
            putExtra("title",title)
        }
        val decreasePendingIntent = PendingIntent.getBroadcast(
            p0,
            id.toInt(),
            decreaseIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val updatedNotification: NotificationCompat.Builder =
            NotificationCompat.Builder(p0, "running_channel")
                .setSmallIcon(R.mipmap.ic_launcher_counter_foreground)
                .setContentTitle("$title")
                .setOnlyAlertOnce(true)
                .addAction(increaseAction)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_launcher_foreground,"-$dec",decreasePendingIntent)
                .setContentText(Normalize(ans))

        notificationManager.notify(123,updatedNotification.build())

    }
}