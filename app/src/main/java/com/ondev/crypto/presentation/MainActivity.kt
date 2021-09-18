package com.ondev.crypto.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ondev.crypto.Screen
import com.ondev.crypto.presentation.coin_detail.CoinDetailScreen
import com.ondev.crypto.presentation.coin_list.CoinListScreen
import com.ondev.crypto.presentation.ui.theme.CriptomonedasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            CriptomonedasTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(modifier = Modifier.padding(vertical = 50.dp),
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {
                        composable(route = Screen.CoinListScreen.route) {
                            CoinListScreen(navController = navController)
                        }
                        composable(route = Screen.CoinDetailScreen.route + "/{coinID}") {
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}