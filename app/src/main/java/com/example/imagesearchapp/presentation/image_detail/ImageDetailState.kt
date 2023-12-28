package com.example.imagesearchapp.presentation.image_detail

import com.example.imagesearchapp.domain.model.ImageDetail

data class ImageDetailState(
    val isLoading: Boolean = false,
    val imageDetail: ImageDetail? = null,
    val error: String? = null,
)
