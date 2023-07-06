package com.example.counter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncreaseBroadcastReceiver: BroadcastReceiver() {
//    val id = "22"
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("hello","hello world")

        val scope = CoroutineScope(Dispatchers.IO)
        val database = MainActivity.database
        val counterDao = database.dao

        var id : String? = p1?.getStringExtra("id")
//        var inc : String? = p1?.getStringExtra("inc")
//        var cnt : String? = p1?.getStringExtra("cnt")
        Log.d("id",id.toString())
//        Log.d("inc",inc.toString())
//        Log.d("cnt",cnt.toString())



//        var ans = (cnt!!.toDouble()) + (inc!!.toDouble())

//        val counter = Counter(
//            id = id.toInt(),
//            counterName = "isdsidj",
//            count = "934",
//            inc = "78",
//            dec = "23",
//            red = 222,
//            green = 21,
//            blue = 29
//        )
//        Log.d("ans",ans.toString())
//        scope.launch {
//            counterDao.incrementCounter(ans.toString(),id!!.toInt())
//        }

    }
}