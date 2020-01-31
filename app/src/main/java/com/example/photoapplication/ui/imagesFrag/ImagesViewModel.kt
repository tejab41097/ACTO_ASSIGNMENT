package com.example.photoapplication.ui.imagesFrag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoapplication.Coroutines.CoroutinesObject
import com.example.photoapplication.data.OfflineDB.DatabaseRepository
import com.example.photoapplication.data.images.Pic
import com.example.photoapplication.repository.ImageRepository
import kotlinx.coroutines.Job

class ImagesViewModel (
    private val repository: ImageRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {
    private lateinit var job: Job
    private val _pics = MutableLiveData<List<Pic>>()
    val pics: LiveData<List<Pic>>
        get() = _pics

    fun getImagesByAlbumId(albumId: String) {
        job = CoroutinesObject.ioToMain(
            { repository.getImagesByAlbumId(albumId) },
            { _pics.value = it })
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }


    fun saveAllPicsToDb(pics : List<Pic>){
        job = CoroutinesObject.ioToMain(
            { databaseRepository.saveAllPicsToDb(pics) },
            {  })
    }

    fun getPicsByAlbumId(albumId: String){
        job = CoroutinesObject.ioToMain(
            { databaseRepository.getPicsByAlbumId(albumId) },
            {_pics.value = it})
    }
}