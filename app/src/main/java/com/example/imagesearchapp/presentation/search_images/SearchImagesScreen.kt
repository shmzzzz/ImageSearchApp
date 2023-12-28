package com.example.imagesearchapp.presentation.search_images

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.imagesearchapp.presentation.ScreenRoute
import com.example.imagesearchapp.presentation.search_images.components.ImageThumbnail
import com.example.imagesearchapp.presentation.search_images.components.SearchBar

@Composable
fun SearchImagesScreen(
    navController: NavController,
    viewModel: SearchImagesViewModel = hiltViewModel(),
) {
    // ViewModelが管理しているStateを参照する
    val state = viewModel.state.value

    // 検索窓を表示する
    Scaffold(
        topBar = {
            SearchBar(
                searchText = viewModel.query,
                onSearchTextChanged = { viewModel.query = it },
                onDone = { viewModel.searchImages() },
            )
        }
    ) { paddingValue ->
        // 通信状態によって表示内容を切り替える
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.isLoading -> {
                    // ローディング
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                !state.error.isNullOrBlank() -> {
                    // エラー表示
                    Text(
                        text = state.error,
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error,
                    )
                }

                else -> {
                    // 通信成功しているのでリストを表示する
                    LazyColumn(modifier = Modifier.padding(paddingValue)) {
                        items(state.images) { image ->
                            ImageThumbnail(
                                image = image,
                                onClick = {
                                    // 指定したリンクへ遷移する
                                    navController.navigate(ScreenRoute.ImageDetailScreen.route + "/${image.imageId}")
                                },
                            )
                        }
                    }
                }
            }
        }
    }

}
