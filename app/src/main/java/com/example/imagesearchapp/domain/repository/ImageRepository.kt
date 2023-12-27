package com.example.imagesearchapp.domain.repository

import com.example.imagesearchapp.data.remote.SearchImagesResultDto

interface ImageRepository {

    suspend fun searchImages(query: String): SearchImagesResultDto
}
