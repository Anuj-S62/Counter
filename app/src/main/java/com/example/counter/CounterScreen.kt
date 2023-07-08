package com.example.counter

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterScreen(
    state:CounterState,
    onEvent:(CounterEvents)->Unit,
    fontFamily:FontFamily = FontFamily()
){
    Box(modifier = Modifier.fillMaxSize().background(color = Color.DarkGray)) {

    }

    Scaffold(

        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color(247, 170, 111),
                contentColor = Color(143, 98, 64),
                onClick = {
                onEvent(CounterEvents.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add counter"
                )
            }
        },
        containerColor = Color(20,20,20),
        contentColor = Color.White
    ) {
        if(state.isAdding) {
            AddCounterDialog(state = state, onEvent = onEvent)
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(0.dp)
        ){
            item {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color(247, 170, 111),
                                fontSize = 35.sp
                            )
                        ) {
                            append("  C")
                        }
                        append("ounter  ")
                    },
                    fontSize = 30.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
//                    fontStyle = FontStyle.Italic,
                )
            }
            items(state.counters){counter->

                CounterCoard(counter = counter,onEvent,fontFamily)
            }
        }
    }
}
