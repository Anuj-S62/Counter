package com.example.counter

//import androidx.compose.ui.text.font.FontFamily
import android.content.Intent
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CounterCoard(counter:Counter,onEvent:(CounterEvents)->Unit,fontFamily:androidx.compose.ui.text.font.FontFamily){
        var expand by remember {
            mutableStateOf(false)
        }
    val context = LocalContext.current

        val cnt = counter.count
        val ans = Normalize(cnt)

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.Black)
            .animateContentSize()
            .clickable { expand = !expand }
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                   , horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(horizontalAlignment = Alignment.Start, modifier = Modifier
                        .fillMaxHeight()) {
                        Text(counter.counterName,modifier = Modifier
                            .padding(start = 15.dp,top = 10.dp,),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            fontFamily = fontFamily,
                            fontSize = 25.sp,
//                            fontStyle = FontStyle.Italic
                        )
                        Text(ans,modifier = Modifier
                            .padding(start = 15.dp,top = 10.dp, bottom = 10.dp),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            fontFamily = fontFamily,
                            fontSize = 35.sp,
//                            fontStyle = FontStyle.Italic,
                            color = Color(counter.red,counter.green,counter.blue)
                        )
                    }
                    Column(modifier = Modifier
                        .fillMaxHeight()
//                        .background(color = Color.White)
                        .padding(end = 2.dp), verticalArrangement = Arrangement.SpaceBetween,)
                    {
                        Box(modifier = Modifier
                            .size(132.dp)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {onEvent(CounterEvents.Increment(counter))}
                            .background(color = Color(counter.red,counter.green,counter.blue)))
                        {
//
                            Box(modifier = Modifier.size(125.dp)
                                .padding(4.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(color = Color.Black),
                                contentAlignment = Alignment.Center)
                            {
                                Text("+"+ counter.inc,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = fontFamily,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    color = Color(counter.red,counter.green,counter.blue)
                                )
                            }
                        }
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    if(expand){
                        IconButton(onClick = {
                            expand = !expand
                            onEvent(CounterEvents.DeleteCounter(counter))},modifier = Modifier.size(70.dp)) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                        }
                        IconButton(onClick =
                        {
//                            Intent(context,AppService::class.java).also {
//                                it.action = AppService.Actions.STOP.toString()
//                                context.startService(it)
//                            }
                            Log.d("adadma","app Service")

                            Intent(context,AppService::class.java).also {
                                it.action = AppService.Actions.START.toString()
                                it.putExtra("cName", counter.counterName)
                                it.putExtra("cnt", ans)
                                it.putExtra("id",counter.id.toString())
                                it.putExtra("inc", counter.inc)
                                it.putExtra("dec", counter.dec)
                                context.startService(it)
                            }

                        },modifier = Modifier.size(70.dp)) {
                            Icon(imageVector = Icons.Default.Notifications, contentDescription = "")
                        }
                        IconButton(onClick =
                        {
                            Log.d("adadma","app Service")

                            Intent(context,AppService::class.java).also {
                                it.action = AppService.Actions.START.toString()
                                it.putExtra("cName", counter.counterName)
                                it.putExtra("cnt", ans)
                                it.putExtra("id",counter.id.toString())
                                it.putExtra("inc", counter.inc)
                                it.putExtra("dec", counter.dec)
                                context.startService(it)
                            }
                            Intent(context,AppService::class.java).also {
                                it.action = AppService.Actions.STOP.toString()
                                context.stopService(it)
                            }

                        },modifier = Modifier.size(70.dp)) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = "")
                        }
                        IconButton(onClick = {onEvent(CounterEvents.Decrement(counter))},modifier = Modifier.size(70.dp)) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                        }

                    }
                }
            }

        }
}


fun Normalize(cnt : String): String {
    var i = 0
    var ans:String = ""
    while(i<cnt.length){
        if(cnt[i]=='.') break
        ans += cnt[i]
        i++
    }
    var afterPoint:String = ""
    i++
    while(i<cnt.length){
        afterPoint+=cnt[i]
        i++
    }
//        var temp:Int = afterPoint.toInt()
    if(afterPoint.length>0 && afterPoint!="0"){
        ans += '.'
        ans += afterPoint[0]
        var j = 1
        var exp : String = ""
        var flag : Boolean = false
        while(j<afterPoint.length){
            if(afterPoint[j]=='E' || flag){
                flag = true
                exp += afterPoint[j]
            }
            j++
        }
        ans += exp
    }
    return ans
}
