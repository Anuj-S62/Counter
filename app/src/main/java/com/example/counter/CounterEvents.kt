package com.example.counter

import androidx.compose.ui.graphics.Color

sealed interface CounterEvents{
    object SaveCounter:CounterEvents
    data class SetInc(val inc:String):CounterEvents
    data class SetDec(val dec:String):CounterEvents
    data class SetCount(val count:String):CounterEvents
    data class SetCounterName(val name:String):CounterEvents
    data class Increment(val Counter: Counter):CounterEvents
    data class Decrement(val Counter: Counter):CounterEvents
    object ShowDialog: CounterEvents
    object HideDialog: CounterEvents
    data class DeleteCounter(val Counter: Counter): CounterEvents
    data class SelectColor(val r:Int,val g:Int,val b:Int) : CounterEvents

}