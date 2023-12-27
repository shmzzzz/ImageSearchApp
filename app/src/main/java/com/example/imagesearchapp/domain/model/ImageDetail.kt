package com.example.imagesearchapp.domain.model

data class ImageDetail(
    val description: String?,
    val likes: Int?,
    val imageUrl: String,
    val photographer: String?,
    val camera: String?,
    val location: String?,
    val downloads: Int?,
)
