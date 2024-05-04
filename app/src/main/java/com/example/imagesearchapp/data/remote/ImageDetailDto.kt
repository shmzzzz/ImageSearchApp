package com.example.imagesearchapp.data.remote


import com.example.imagesearchapp.domain.model.ImageDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageDetailDto(
    @Json(name = "blur_hash")
    val blurHash: String?,
    val color: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "current_user_collections")
    val currentUserCollections: List<CurrentUserCollection>?,
    val description: String?,
    val downloads: Int?,
    val exif: Exif?,
    val height: Int?,
    val id: String?,
    @Json(name = "liked_by_user")
    val likedByUser: Boolean?,
    val likes: Int?,
    val links: LinksXX?,
    val location: Location?,
    @Json(name = "public_domain")
    val publicDomain: Boolean?,
    val tags: List<Tag>?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    val urls: UrlsX?,
    val user: UserX?,
    val width: Int?
)

fun ImageDetailDto.toImageDetail(): ImageDetail {
    return ImageDetail(
        description = description,
        likes = likes,
        imageUrl = urls!!.raw!!,
        photographer = user?.username ?: "Unknown Photographer",
        camera = exif?.name ?: "Unknown Camera",
        location = "${location?.city ?: "Unknown City"}, ${location?.country ?: "Unknown Country"}",
        downloads = downloads,
    )
}
