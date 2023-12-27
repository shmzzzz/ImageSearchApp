package com.example.imagesearchapp.presentation.search_images

import com.example.imagesearchapp.domain.model.Image

data class SearchImagesState(
    val isLoading: Boolean = false,
    val images: List<Image> = emptyList(),
    val error: String? = null,
)
