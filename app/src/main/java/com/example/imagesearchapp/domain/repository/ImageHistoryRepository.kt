package com.example.imagesearchapp.domain.repository

import com.example.imagesearchapp.domain.model.ImageHistory
import kotlinx.coroutines.flow.Flow

interface ImageHistoryRepository {
    suspend fun getAll(): Flow<List<ImageHistory>>
    suspend fun getHistoryById(imageId: String): ImageHistory
    suspend fun insert(imageHistory: ImageHistory)
    suspend fun update(imageHistory: ImageHistory)
    suspend fun deleteAll(imageHistory: ImageHistory)
    suspend fun deleteHistoryById(imageId: String)
}
