package com.example.imagesearchapp.presentation

sealed class ScreenRoute(val route: String) {
    object SearchImageScreen: ScreenRoute("search_images_screen")
    object ImageDetailScreen: ScreenRoute("image_detail_screen")
}
