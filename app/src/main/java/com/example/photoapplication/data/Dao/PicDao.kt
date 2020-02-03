package com.example.photoapplication.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.photoapplication.data.images.Pic
import com.example.photoapplication.data.join.PicAlbumUser

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

    @Query("SELECT p.title,a.albumtitle,u.name,p.url from pic p,User u,album a where p.albumId = a.id AND a.userId = u.id AND p.id = :imageId")
    fun getPicInfoByIdFromDb(imageId: String) : PicAlbumUser
}