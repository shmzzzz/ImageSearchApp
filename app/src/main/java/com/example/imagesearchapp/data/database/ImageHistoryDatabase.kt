package com.example.imagesearchapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.imagesearchapp.data.dao.ImageHistoryDatabaseDao
import com.example.imagesearchapp.domain.model.ImageHistory
import com.example.imagesearchapp.util.DateConverter

@Database(entities = [ImageHistory::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class ImageHistoryDatabase : RoomDatabase() {
    abstract fun imageHistoryDao(): ImageHistoryDatabaseDao
}
