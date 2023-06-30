package com.example.counter

import android.annotation.SuppressLint
import android.graphics.fonts.FontFamily
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CounterCoard(counter:Counter,onEvent:(CounterEvents)->Unit,fontFamily:androidx.compose.ui.text.font.FontFamily){
        var expand by remember {
            mutableStateOf(false)
        }


        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.Black)
            .animateContentSize()
            .clickable { expand = !expand }
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                   , horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(horizontalAlignment = Alignment.Start, modifier = Modifier
                        .fillMaxHeight()) {
                        Text(counter.counterName,modifier = Modifier.padding(start = 15.dp,top = 10.dp,),fontFamily = fontFamily, fontSize = 25.sp, fontStyle = FontStyle.Italic)
                        Text(counter.count.toString(),modifier = Modifier.padding(start = 15.dp,top = 10.dp, bottom = 10.dp),fontFamily = fontFamily, fontSize = 35.sp,fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.primary)
                    }
                    Column(modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 2.dp), verticalArrangement = Arrangement.SpaceAround) {
                        IconButton(onClick = {
                            onEvent(CounterEvents.Increment(counter))
                        },modifier = Modifier.size(70.dp)
                        ) {
                            Icon(modifier = Modifier.size(40.dp),imageVector = Icons.Default.Add, contentDescription = "Increase Count")

                        }
                        IconButton(onClick = {onEvent(CounterEvents.Decrement(counter))},modifier = Modifier.size(70.dp)) {
                            Icon(modifier = Modifier.size(40.dp),imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "")
                        }
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    if(expand){
                        IconButton(onClick = {onEvent(CounterEvents.DeleteCounter(counter))}) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                        }
                    }
                }
            }

        }
}

