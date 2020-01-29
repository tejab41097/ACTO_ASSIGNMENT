package com.example.photoapplication.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.photoapplication.data.Dao.AlbumDao
import com.example.photoapplication.data.Dao.PicDao
import com.example.photoapplication.data.Dao.UserDao
import com.example.photoapplication.data.album.Album
import com.example.photoapplication.data.images.Pic
import com.example.photoapplication.data.user.User
import retrofit2.Response

class DatabaseRepository(context: Context) : BaseDbRepository {
    private var userDao: UserDao
    private var albumDao: AlbumDao
    private var picDao: PicDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseRepository? = null

        fun getInstance(applicationContext: Context): DatabaseRepository {
            return INSTANCE ?: DatabaseRepository(applicationContext)
        }
    }

    init {
        val database: FullDb? = FullDb(context)
        userDao = database!!.userDao()
        albumDao = database!!.albumDao()
        picDao = database!!.picDao()
    }


    override suspend fun getAllUsersFromDb(): List<User> {
        return userDao.getAllUsersFromDb()
    }

    override suspend fun saveAllUsersToDb(users: List<User>) {
        userDao.saveAllUsersToDb(users)
    }

    override suspend fun getAlbumsByUserIdFromDb(userId: String): List<Album> {
        return albumDao.getAlbumsByUserIdFromDb(userId)
    }

    override suspend fun saveAllAlbumsToDb(albums: List<Album>) {
        albumDao.saveAllAlbumsToDb(albums)
    }

    override suspend fun getPicsByAlbumId(albumId: String): List<Pic> {
        return picDao.getPicsByAlbumId(albumId)
    }

    override suspend fun saveAllPicsToDb(pics: List<Pic>) {
        Log.e("saveAllPicsToDb","Done")
        picDao.saveAllPicsToDb(pics)
    }


}