package com.example.imagesearchapp.presentation.search_images

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.imagesearchapp.presentation.search_images.components.ImageThumbnail

@Composable
fun SearchImagesScreen(
    viewModel: SearchImagesViewModel = hiltViewModel(),
) {
    // ViewModelが管理しているStateを参照する
    val state = viewModel.state.value
    // リスト表示を作成する
    LazyColumn {
        items(state.images) { image ->
            ImageThumbnail(image = image, onClick = {})
        }
    }
}
