package com.example.imagesearchapp.presentation.search_images.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.imagesearchapp.domain.model.Image
import com.example.imagesearchapp.presentation.components.CountLabel
import com.example.imagesearchapp.presentation.ui.theme.ImageSearchAppTheme

@Composable
fun ImageThumbnail(
    image: Image,
    onClick: (Image) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(Color.Black)
            // ローディング前後で高さが違いすぎないように、高さの最小値を設定する
            .heightIn(min = 200.dp)
            .clickable { onClick(image) },
        contentAlignment = Alignment.BottomCenter,
    ) {
        // 先にローディングが表示される
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        // AsyncImageが実行されるときはローディング画面が非表示になる
        AsyncImage(
            model = image.imageUrl,
            contentDescription = image.description,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(10.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(
                    text = image.description ?: "No description",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                )
                Text(
                    text = image.photographer ?: "Unknown photographer",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            CountLabel(
                imageVector = Icons.Default.Favorite,
                count = image.likes ?: 0,
                iconTint = Color(0xFFF50B5B),
                color = Color.White,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewImageThumbnail() {
    val image = Image(
        imageId = "",
        description = "Image description",
        likes = 100,
        imageUrl = "https://images.unsplash.com/photo-1587620962725-abab7fe55159?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cHJvZ3JhbW1pbmd8ZW58MHx8MHx8fDA%3D",
        photographer = "Surface",
    )
    ImageSearchAppTheme {
        ImageThumbnail(image = image, onClick = {})
    }
}
