package com.example.counter

import androidx.compose.ui.graphics.Color

data class CounterState(
    val counters : List<Counter> = emptyList(),
    val counterName:String = "",
    val inc:String = "",
    val dec:String = "",
    val count:String = "",
//    val col: Color = Color(255,255,255),
    val red:Int = 255,
    val green:Int = 255,
    val blue:Int = 255,
    val isAdding:Boolean = false
)
