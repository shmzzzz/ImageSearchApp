package com.example.imagesearchapp.data.repository

import com.example.imagesearchapp.data.dao.ImageHistoryDatabaseDao
import com.example.imagesearchapp.domain.model.ImageHistory
import com.example.imagesearchapp.domain.repository.ImageHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageHistoryRepositoryImpl @Inject constructor(
    private val imageHistoryDao: ImageHistoryDatabaseDao
) : ImageHistoryRepository {

    override suspend fun getAll(): Flow<List<ImageHistory>> {
        return imageHistoryDao.getAll()
    }

    override suspend fun getHistoryById(imageId: String): ImageHistory {
        return imageHistoryDao.getHistoryById(imageId)
    }

    override suspend fun insert(imageHistory: ImageHistory) {
        return imageHistoryDao.insert(imageHistory)
    }

    override suspend fun update(imageHistory: ImageHistory) {
        return imageHistoryDao.update(imageHistory)
    }

    override suspend fun deleteAll(imageHistory: ImageHistory) {
        return imageHistoryDao.deleteAll()
    }

    override suspend fun deleteHistoryById(imageId: String) {
        return imageHistoryDao.deleteHistoryById(imageId)
    }

}
