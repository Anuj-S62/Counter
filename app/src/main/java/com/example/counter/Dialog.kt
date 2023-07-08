@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.counter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AddCounterDialog(
    state: CounterState,
    onEvent: (CounterEvents) -> Unit
) {
    AlertDialog(

        onDismissRequest = { onEvent(CounterEvents.HideDialog) },
        title = { Text(text = "Add counter") },
        text = {
               LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp),) {
                   item {
                       OutlinedTextField(value = state.counterName, label = {Text("Counter Name")},onValueChange = {onEvent(CounterEvents.SetCounterName(it))})
                   }
                   item {
                       OutlinedTextField(value = state.count,
                           onValueChange = {onEvent(CounterEvents.SetCount(it))},
                           label = {Text("Initial Count")},
                           keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                       )
                   }
                   item {
                       OutlinedTextField(value = state.inc,
                           onValueChange = {onEvent(CounterEvents.SetInc(it))},
                           label = {Text("Increment Value")},
                           keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                       )
                   }
                   item {
                       OutlinedTextField(value = state.dec,
                           label = {Text("Decrement Value")},
                           onValueChange = {onEvent(CounterEvents.SetDec(it))},
                           keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                       )
                   }
                   item {
                        ColorPicker(onEvent = onEvent)
                   }
               }
        },
        confirmButton = {
            TextButton(onClick = {onEvent(CounterEvents.SaveCounter)})
            { Text(text = "Save") }
        },
    )
    
}