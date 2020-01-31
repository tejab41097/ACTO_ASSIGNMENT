package com.example.photoapplication.ui.albumsFrag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoapplication.Coroutines.CoroutinesObject
import com.example.photoapplication.data.OfflineDB.DatabaseRepository
import com.example.photoapplication.data.album.Album
import com.example.photoapplication.repository.AlbumRepository
import kotlinx.coroutines.Job

class AlbumsViewModel (
    private val repository: AlbumRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {
    private lateinit var job: Job
    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    fun getAlbumsByUserId(albumId: String) {
        job = CoroutinesObject.ioToMain(
            { repository.getAlbumsByUserId(albumId) },
            { _albums.value = it })
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }


    fun saveAllAlbumsToDb(albums: List<Album>){
        job = CoroutinesObject.ioToMain(
            { databaseRepository.saveAllAlbumsToDb(albums) },
            {  })
    }

    fun getAlbumsByUserIdFromDb(userId : String){

        job = CoroutinesObject.ioToMain(
            { databaseRepository.getAlbumsByUserIdFromDb(userId) },
            {  _albums.value = it  })
    }

}