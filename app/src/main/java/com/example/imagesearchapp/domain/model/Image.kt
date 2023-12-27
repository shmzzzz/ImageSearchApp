package com.example.imagesearchapp.domain.model

data class Image(
    val imageId: String,
    val description: String?,
    val likes: Int?,
    val imageUrl: String,
    val photographer: String?,
)
