package com.example.photoapplication.data.images


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pic(
    val albumId: Int,
    @PrimaryKey
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)