package com.rizqanmr.movikucompose.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import com.example.movikucompose.R
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.rizqanmr.movikucompose.ui.screens.detail.DetailScreen
import com.rizqanmr.movikucompose.ui.screens.home.HomeScreen
import com.rizqanmr.movikucompose.ui.theme.MovikuComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val darkTheme = viewModel.isDarkTheme.observeAsState(false)
            MovikuComposeTheme(darkTheme = darkTheme.value) {
                val navController = rememberAnimatedNavController()

                AnimatedNavHost(
                    navController = navController,
                    startDestination = "home",
                    enterTransition = { fadeIn(animationSpec = tween(300)) },
                    exitTransition = { fadeOut(animationSpec = tween(300)) },
                    popEnterTransition = { fadeIn(animationSpec = tween(300)) },
                    popExitTransition = { fadeOut(animationSpec = tween(300)) }
                ) {
                    composable("home") {
                        HomeScreen(viewModel, R.string.app_name, navController)
                    }

                    composable(
                        "detail",
                        enterTransition = {
                            slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) +
                                    fadeIn(animationSpec = tween(300))
                        },
                        exitTransition = {
                            slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) +
                                    fadeOut(animationSpec = tween(300))
                        },
                        popEnterTransition = {
                            slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) +
                                    fadeIn(animationSpec = tween(300))
                        },
                        popExitTransition = {
                            slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) +
                                    fadeOut(animationSpec = tween(300))
                        }
                    ) {
                        DetailScreen(navController)
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