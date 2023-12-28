package com.example.imagesearchapp.presentation.search_images

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.imagesearchapp.presentation.search_images.components.ImageThumbnail
import com.example.imagesearchapp.presentation.search_images.components.SearchBar

@Composable
fun SearchImagesScreen(
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
        // リスト表示を作成する
        LazyColumn(modifier = Modifier.padding(paddingValue)) {
            items(state.images) { image ->
                ImageThumbnail(image = image, onClick = {})
            }
        }
    }

}
