package com.example.photoapplication.ui.viewImageFrag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoapplication.Coroutines.CoroutinesObject
import com.example.photoapplication.data.OfflineDB.DatabaseRepository
import com.example.photoapplication.data.images.Pic
import com.example.photoapplication.data.join.PicAlbumUser
import com.example.photoapplication.repository.ImageRepository
import kotlinx.coroutines.Job

class ViewImageViewModel(
        private val repository: ImageRepository,
        private val databaseRepository: DatabaseRepository
) : ViewModel() {
    private lateinit var job: Job
    private val _pic = MutableLiveData<Pic>()
    val pic: LiveData<Pic>
        get() = _pic
    private val _picDetails = MutableLiveData<PicAlbumUser>()
    val picDetails: LiveData<PicAlbumUser>
        get() = _picDetails

    fun getImageDetailFromDb(imageId: String) {
        job = CoroutinesObject.ioToMain(
                { databaseRepository.getImageDetailFromDb(imageId) },
                { _picDetails.value = it })
    }
}
