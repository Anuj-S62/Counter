package com.example.counter

data class CounterState(
    val counters : List<Counter> = emptyList(),
    val counterName:String = "",
    val inc:String = "",
    val dec:String = "",
    val count:String = "",
    val isAdding:Boolean = false
)
