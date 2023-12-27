package com.example.imagesearchapp.domain.repository

import com.example.imagesearchapp.data.remote.ImageDetailDto
import com.example.imagesearchapp.data.remote.SearchImagesResultDto

interface ImageRepository {

    suspend fun searchImages(query: String): SearchImagesResultDto

    suspend fun getImageById(imageId: String): ImageDetailDto
}
