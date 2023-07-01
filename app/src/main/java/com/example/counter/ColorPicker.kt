package com.example.counter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorPicker(
    state: CounterState,
    onEvent: (CounterEvents) -> Unit,
    modifier: Modifier = Modifier
){
    var array = arrayOf(0,1,2,3,4,5)
    var idx by remember {
        mutableStateOf(0)
    }
    Row(modifier = Modifier
        .size(width = 300.dp, height = 40.dp)
        .padding(top = 10.dp, start = 5.dp, end = 5.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Box(modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .clickable {
                run { onEvent(CounterEvents.SelectColor(255, 255, 255)) }
                idx = 0
            }
            .background(color = Color.White)){
            if(idx==0){
                SelectedColor(red = 255, green = 255, blue = 255)
            }
        }
        Box(modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .clickable {
                run { onEvent(CounterEvents.SelectColor(247, 149, 45)) }
                idx = 1 }
            .background(color = Color(247, 149, 45))){
            if(idx == 1){

                SelectedColor(red = 247, green = 249, blue = 45)
            }
        }
        Box(modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .clickable {
                run { onEvent(CounterEvents.SelectColor(111, 159, 247)) }
                idx = 2 }
            .background(color = Color(111, 159, 247))){
            if(idx==2){
                SelectedColor(111, 159, 247)}
        }
        Box(modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .clickable {
                run { onEvent(CounterEvents.SelectColor(67, 232, 130)) }
                idx = 3 }
            .background(color = Color(67, 232, 130))){
            if(idx==3){
                SelectedColor(67, 232, 130)}
        }
        Box(modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .clickable {
                run { onEvent(CounterEvents.SelectColor(245, 44, 44)) }
                idx = 4 }
            .background(color = Color(245, 44, 44))){
            if(idx==4){
                SelectedColor(245, 44, 44)}
        }
        Box(modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .clickable {
                run { onEvent(CounterEvents.SelectColor(111, 247, 227)) }
                idx = 5 }
            .background(color = Color(111, 247, 227))){
           if(idx==5) { SelectedColor(111, 247, 227) }
        }

    }

}

@Composable
fun SelectedColor(red:Int,green:Int,blue:Int) {
    var col = Color(red, green, blue)
    Box(
        modifier = Modifier
            .size(22.dp)
            .clip(CircleShape)
            .background(color = col),
        contentAlignment = Alignment.Center
    )
    {
        Box(
            modifier = Modifier
                .size(18.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(color = col)
            )
        }
    }
}
