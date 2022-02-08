package com.example.androidcalendarbaseexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidcalendarbaseexample.ui.theme.AndroidCalendarBaseExampleTheme
import com.naim.android_calendar_core.TestFactory
import com.naim.android_calendar_core.config.calendar_config.CalendarConfig
import com.naim.android_calendar_core.viewmodel.CalendarViewModel
import com.naim.android_calendar_core.viewmodel.factory.CalendarViewModelFactory


import java.util.*

class MainActivity : ComponentActivity() {
    private val viewModel: CalendarViewModel by viewModels() {
        CalendarViewModelFactory(
            CalendarConfig().apply {
                date = Date()
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(TestFactory.test())
        setContent {
            AndroidCalendarBaseExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidCalendarBaseExampleTheme {
        Greeting("Android")
    }
}