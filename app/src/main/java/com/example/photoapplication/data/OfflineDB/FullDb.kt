package com.example.photoapplication.data.OfflineDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.photoapplication.data.Dao.AlbumDao
import com.example.photoapplication.data.Dao.PicDao
import com.example.photoapplication.data.Dao.UserDao
import com.example.photoapplication.data.album.Album
import com.example.photoapplication.data.images.Pic
import com.example.photoapplication.data.user.User

@Database(
    entities = [Pic::class,Album::class,User::class],
    version = 1
)
abstract class FullDb: RoomDatabase() {

    abstract  fun userDao(): UserDao

    abstract fun albumDao(): AlbumDao

    abstract fun picDao(): PicDao

    companion object {
        @Volatile private var instance : FullDb? = null
        private val Lock = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(Lock){
            instance
                ?: buildDb(
                    context
                ).also { instance = it }
        }

        private fun buildDb(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                FullDb::class.java,"photodb.db")
                .build()
    }
}