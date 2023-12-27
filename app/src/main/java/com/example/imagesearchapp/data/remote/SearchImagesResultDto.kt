package com.example.imagesearchapp.data.remote


import com.example.imagesearchapp.domain.model.Image
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchImagesResultDto(
    val results: List<Result>?,
    val total: Int?,
    @Json(name = "total_pages")
    val totalPages: Int?
)

fun SearchImagesResultDto.toImages(): List<Image> {
    return results!!.map {
        Image(
            imageId = it.id!!,
            description = it.description,
            likes = it.likes,
            imageUrl = it.urls!!.raw!!,
            photographer = it.user?.name,
        )
    }
}
