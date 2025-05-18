package com.rizqanmr.movikucompose.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.movikucompose.R
import com.rizqanmr.movikucompose.ui.components.TabWithPager
import com.rizqanmr.movikucompose.ui.components.TopAppBar
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        TopAppBar(appName, onThemeSwitch = {
                            viewModel.performAction(MainViewModel.Action.SwitchTheme)
                        })
                        TabWithPager()
                    }
                }
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