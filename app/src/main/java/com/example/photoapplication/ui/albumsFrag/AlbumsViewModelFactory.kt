package com.example.photoapplication.ui.albumsFrag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photoapplication.data.OfflineDB.DatabaseRepository
import com.example.photoapplication.repository.AlbumRepository


class AlbumsViewModelFactory (
    private val repository: AlbumRepository,
    private val databaseRepository: DatabaseRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumsViewModel(
            repository,
            databaseRepository
        ) as T
    }
}