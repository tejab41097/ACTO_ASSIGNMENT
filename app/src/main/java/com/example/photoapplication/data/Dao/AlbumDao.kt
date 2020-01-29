package com.example.photoapplication.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.photoapplication.data.album.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAlbumToDb(album: Album)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllAlbumsToDb(albums: List<Album>)

    @Query("SELECT * FROM album")
    fun getAllAlbumsFromDb(): List<Album>

    @Query("SELECT * FROM album where userId = :userId")
    fun getAlbumsByUserIdFromDb(userId: String): List<Album>
}