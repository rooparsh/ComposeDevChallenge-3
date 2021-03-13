/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.screen.HomeScreen
import com.example.androiddevchallenge.ui.screen.LoginScreen
import com.example.androiddevchallenge.ui.screen.WelcomeScreen
import com.example.androiddevchallenge.ui.theme.BloomTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            BloomTheme {
                ProvideWindowInsets {
                    BloomApp()
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun BloomApp() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation.WelcomeScreen.title) {
        composable(Navigation.WelcomeScreen.title) {
            WelcomeScreen(
                onLoginClick = {
                    navController.navigate(Navigation.LoginScreen.title)
                },
                onRegisterClick = {}
            )
        }

        composable(Navigation.LoginScreen.title) {
            LoginScreen(onLoginClick = { navController.navigate(Navigation.HomeScreen.title) })
        }

        composable(Navigation.HomeScreen.title) {
            HomeScreen()
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    BloomTheme {
        BloomApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    BloomTheme(darkTheme = true) {
        BloomApp()
    }
}
