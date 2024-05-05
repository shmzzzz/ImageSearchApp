package com.example.imagesearchapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date

@Entity(tableName = "image_history_table")
data class ImageHistory(
    @PrimaryKey
    val imageId: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "likes")
    val likes: Int,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "photographer")
    val photographer: String,

    @ColumnInfo(name = "date")
    val date: Date = Date.from(Instant.now())
)
