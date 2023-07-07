package com.example.counter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DecreaseBroadcastReceiver:BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("hello","hello world")

        val scope = CoroutineScope(Dispatchers.IO)
        val database = MainActivity.database
        val counterDao = database.dao

        var id : String? = p1?.getStringExtra("id")
        var inc : String? = p1?.getStringExtra("dec")
        var cnt : String? = p1?.getStringExtra("cnt")
        Log.d("id",id.toString())
        Log.d("inc",inc.toString())
        Log.d("cnt",cnt.toString())



        var ans = (cnt!!.toDouble()) - (inc!!.toDouble())

        Log.d("ans",ans.toString())
        scope.launch {
            counterDao.updateCounter(ans.toString(),id!!.toInt())
        }

    }
}