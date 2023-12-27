package com.example.imagesearchapp.data.remote

import com.example.imagesearchapp.common.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Authorization: Client-ID ${Constants.API_KEY}")
    @GET("/search/photos")
    suspend fun searchImages(@Query("query") query: String): SearchImagesResultDto
}
