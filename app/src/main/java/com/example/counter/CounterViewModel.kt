package com.example.counter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterViewModel(private val dao:CounterDao):ViewModel() {
    private val _counter = dao.getAllCounter()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(),
        emptyList()
    )
    private val _state = MutableStateFlow(CounterState())
    val state = combine(_state,_counter) { state, counter ->
        state.copy(
            counters = counter
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CounterState())
    fun onEvent(event:CounterEvents){
        when(event){

            is CounterEvents.Decrement -> {
                var cnt = event.Counter.count.toDouble() - event.Counter.dec.toDouble()
                val counter = Counter(
                    id = event.Counter.id,
                    counterName = event.Counter.counterName,
                    count = cnt.toString(),
                    inc = event.Counter.inc,
                    dec = event.Counter.dec,
                    red = event.Counter.red,
                    green = event.Counter.green,
                    blue = event.Counter.blue
                )
                viewModelScope.launch {
                    dao.upsertCounter(
                        counter
                    )
                }
            }
            is CounterEvents.DeleteCounter -> {
                Log.d("yes","helo")

                viewModelScope.launch {
                    dao.deleteCounter(event.Counter)
                }
            }
            CounterEvents.HideDialog -> {
                _state.update {
                    it.copy(
                        isAdding = false
                    )
                }
            }
            is CounterEvents.Increment -> {
                var cnt : Double= event.Counter.count.toDouble() + event.Counter.inc.toDouble()
                val counter = Counter(
                    id = event.Counter.id,
                    counterName = event.Counter.counterName,
                    count = cnt.toString(),
                    inc = event.Counter.inc,
                    dec = event.Counter.dec,
                    red = event.Counter.red,
                    green = event.Counter.green,
                    blue = event.Counter.blue
                )
                viewModelScope.launch {
                    dao.upsertCounter(
                        counter
                    )
                }
            }
            CounterEvents.SaveCounter -> {
                var counterName = state.value.counterName
                var count = state.value.count
                var inc = state.value.inc
                var dec = state.value.dec
//                var col = state.value.col
                var red = state.value.red
                var green = state.value.green
                var blue = state.value.blue
                if(counterName.isBlank()){
                    return
                }
                if(count.isBlank()){
                    count = "0"
                }
                if(inc.isBlank()){
                    inc = "1"
                }
                if(dec.isBlank()){
                    dec = "1"
                }
                val counter = Counter(
                    counterName = counterName,
                    count = count,
                    inc = inc,
                    dec = dec,
//                    col = col
                    red = red,
                    green = green,
                    blue = blue
                )
                Log.d("cName",counterName)
                Log.d("count",count.toString())
                Log.d("inc",inc.toString())
                Log.d("cName",counterName)
                viewModelScope.launch {
                    dao.upsertCounter(
                        counter
                    )
                }
                _state.update {
                    it.copy(
                        isAdding = false,
                        count = "",
                        counterName = "",
                        inc = "",
                        dec = "",
                        red = 255,
                        green = 255,
                        blue =255
                    )

                }
            }
            is CounterEvents.SetCounterName -> {
                _state.update {
                    it.copy(
                        counterName = event.name
                    )
                }

            }
            is CounterEvents.SetDec -> {
                    _state.update {
                        it.copy(
                            dec = event.dec
                        )
                    }

            }
            is CounterEvents.SetInc -> {
                _state.update {
                    it.copy(
                        inc = event.inc
                    )
                }
            }
            CounterEvents.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAdding = true
                    )
                }
            }

            is CounterEvents.SetCount -> {
                _state.update {
                    it.copy(
                        count = event.count
                    )
                }
            }

            is CounterEvents.SelectColor -> {
                _state.update {
                    it.copy(
                        red = event.r,
                        green = event.g,
                        blue = event.b
                    )
                }
            }
        }
    }

}