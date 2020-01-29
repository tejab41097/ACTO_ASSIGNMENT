package com.example.photoapplication.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.photoapplication.data.images.Pic

@Dao
interface PicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePicToDb(pic: Pic)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPicsToDb(pics: List<Pic>)

    @Query("SELECT * FROM pic where albumId = :albumId")
    fun getPicsByAlbumId(albumId : String) : List<Pic>

    @Query("SELECT * FROM pic")
    fun getAllPics() : List<Pic>
}