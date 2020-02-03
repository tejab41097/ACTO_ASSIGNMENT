package com.example.photoapplication.data.join

import androidx.room.ColumnInfo

data class PicAlbumUser(
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "albumtitle")
        val albumName: String,
        @ColumnInfo(name = "name")
        val userName: String,
        @ColumnInfo(name = "url")
        val url: String
)