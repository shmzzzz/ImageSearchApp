package com.example.imagesearchapp.presentation

sealed class ScreenRoute(val route: String) {
    object SearchImagesScreen: ScreenRoute("search_images_screen")
    object ImageDetailScreen: ScreenRoute("image_detail_screen")
}
