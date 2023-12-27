package com.example.imagesearchapp.data.repository

import com.example.imagesearchapp.data.remote.ImageDetailDto
import com.example.imagesearchapp.data.remote.SearchImagesResultDto
import com.example.imagesearchapp.data.remote.UnsplashApi
import com.example.imagesearchapp.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val api: UnsplashApi,
): ImageRepository{

    override suspend fun searchImages(query: String): SearchImagesResultDto {
        return api.searchImages(query)
    }

    override suspend fun getImageById(imageId: String): ImageDetailDto {
        return api.getImageById(imageId)
    }
}
