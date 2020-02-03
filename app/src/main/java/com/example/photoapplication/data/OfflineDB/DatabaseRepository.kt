package com.example.photoapplication.data.OfflineDB

import android.content.Context
import com.example.photoapplication.data.Dao.AlbumDao
import com.example.photoapplication.data.Dao.PicDao
import com.example.photoapplication.data.Dao.UserDao
import com.example.photoapplication.data.album.Album
import com.example.photoapplication.data.images.Pic
import com.example.photoapplication.data.join.PicAlbumUser
import com.example.photoapplication.data.user.User

class DatabaseRepository(context: Context) :
        BaseDbRepository {
    private var userDao: UserDao
    private var albumDao: AlbumDao
    private var picDao: PicDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseRepository? = null

        fun getInstance(applicationContext: Context): DatabaseRepository {
            return INSTANCE
                    ?: DatabaseRepository(
                            applicationContext
                    )
        }
    }

    init {
        val database: FullDb? =
                FullDb(context)
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
        picDao.saveAllPicsToDb(pics)
    }

    override suspend fun getImageDetailFromDb(imageId: String): PicAlbumUser {
        return picDao.getPicInfoByIdFromDb(imageId)
    }


}