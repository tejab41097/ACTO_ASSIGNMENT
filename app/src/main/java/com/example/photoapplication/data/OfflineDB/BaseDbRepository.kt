package com.example.photoapplication.data.OfflineDB

import androidx.lifecycle.LiveData
import com.example.photoapplication.data.album.Album
import com.example.photoapplication.data.images.Pic
import com.example.photoapplication.data.user.User
import retrofit2.Response

interface BaseDbRepository {

    suspend fun getAllUsersFromDb(): List<User>

    suspend fun saveAllUsersToDb(users: List<User>)

    suspend fun getAlbumsByUserIdFromDb(userId: String): List<Album>

    suspend fun saveAllAlbumsToDb(albums: List<Album>)

    suspend fun getPicsByAlbumId(albumId : String) : List<Pic>

    suspend fun saveAllPicsToDb(pics: List<Pic>)

    suspend fun getPicInfoByIdFromDb(picId: String): Pic
}