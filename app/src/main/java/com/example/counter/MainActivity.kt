package com.example.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.counter.ui.theme.CounterTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            CounterDatabse::class.java,
            "counter.db"
        ).build()
    }
    private val viewModel by viewModels<CounterViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override  fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CounterViewModel(db.dao) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fontFamily = FontFamily(
            Font(R.font.notosans_light, FontWeight.Thin),
            Font(R.font.notosans_medium, FontWeight.Medium),
            Font(R.font.notosans_black, FontWeight.Black),
            Font(R.font.notosans_bold, FontWeight.Bold)
        )

        setContent {
            CounterTheme {
                val state by viewModel.state.collectAsState()
                CounterScreen(state = state, onEvent = viewModel::onEvent,fontFamily)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CounterTheme {
        Greeting("Android")
    }
}