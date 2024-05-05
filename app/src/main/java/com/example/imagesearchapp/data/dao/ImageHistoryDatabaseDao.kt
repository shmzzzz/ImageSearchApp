package com.example.imagesearchapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.imagesearchapp.domain.model.ImageHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageHistoryDatabaseDao {
    @Query("SELECT * FROM image_history_table ORDER BY date DESC")
    fun getAll(): Flow<List<ImageHistory>>

    @Query("SELECT * FROM image_history_table WHERE imageId = :imageId")
    suspend fun getHistoryById(imageId: String): ImageHistory

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imageHistory: ImageHistory)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(imageHistory: ImageHistory)

    @Query("DELETE FROM image_history_table")
    suspend fun deleteAll()

    @Query("DELETE FROM image_history_table WHERE imageId = :imageId")
    suspend fun deleteHistoryById(imageId: String)
}
