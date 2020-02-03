package com.example.photoapplication.data.album

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "albumtitle")
    val title: String,
    val userId: Int
)