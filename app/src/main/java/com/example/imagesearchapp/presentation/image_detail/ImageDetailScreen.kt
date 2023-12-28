package com.example.imagesearchapp.presentation.image_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.imagesearchapp.domain.model.ImageDetail

@Composable
fun ImageDetailScreen(
    viewModel: ImageDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            !state.error.isNullOrBlank() -> {
                Text(
                    text = state.error,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error,
                )
            }

            else -> {
                state.imageDetail?.let { imageDetail ->
                    AsyncImage(
                        model = imageDetail.imageUrl,
                        contentDescription = imageDetail.description,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Composable
fun ImageDetailContent(
    imageDetail: ImageDetail
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.heightIn(min = 200.dp)) {
            var isLoadingImage by remember { mutableStateOf(true) }
            if (isLoadingImage) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }

            AsyncImage(
                model = imageDetail.imageUrl,
                contentDescription = imageDetail.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStartPercent = 0,
                            topEndPercent = 0,
                            bottomStartPercent = 5,
                            bottomEndPercent = 5,
                        )
                    ),
                // 通信成功時はローディングを完了させる
                onSuccess = { isLoadingImage = false }
            )
        }
    }
}
