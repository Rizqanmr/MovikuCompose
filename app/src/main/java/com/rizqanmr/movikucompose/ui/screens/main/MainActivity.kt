package com.rizqanmr.movikucompose.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import com.example.movikucompose.R
import com.rizqanmr.movikucompose.ui.screens.home.HomeScreen
import com.rizqanmr.movikucompose.ui.theme.MovikuComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val darkTheme = viewModel.isDarkTheme.observeAsState(false)
            MovikuComposeTheme(darkTheme = darkTheme.value) {
                val appName = R.string.app_name
                HomeScreen(viewModel, appName)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MovikuComposeTheme {
//        Greeting("Android")
//    }
//}