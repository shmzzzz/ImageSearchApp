package com.example.imagesearchapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imagesearchapp.presentation.ui.theme.ImageSearchAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageSearchAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        // 画像検索画面を最初に表示する
                        startDestination = ScreenRoute.SearchImageScreen.route,
                    ) {
                        // NavHostBuilderは最後の引数かつ関数なのでNavHostBuilderは外に出せる
                        // 画像検索画面
                        composable(route = ScreenRoute.SearchImageScreen.route) {
                            // TODO
                        }
                        // 画像詳細画面
                        composable(route = ScreenRoute.ImageDetailScreen.route) {
                            // TODO
                        }
                    }
                }
            }
        }
    }
}
