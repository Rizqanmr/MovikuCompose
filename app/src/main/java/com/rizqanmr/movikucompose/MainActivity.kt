package com.rizqanmr.movikucompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.movikucompose.R
import com.rizqanmr.movikucompose.ui.components.TabWithPager
import com.rizqanmr.movikucompose.ui.components.TopAppBar
import com.rizqanmr.movikucompose.ui.theme.MovikuComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovikuComposeTheme {
                val appName = R.string.app_name
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        TopAppBar(appName, onThemeSwitch = {})
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