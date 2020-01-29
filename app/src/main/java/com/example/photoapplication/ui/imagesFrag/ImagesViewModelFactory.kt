package com.example.photoapplication.ui.imagesFrag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photoapplication.data.DatabaseRepository
import com.example.photoapplication.repository.ImageRepository


class ImagesViewModelFactory (
    private val repository: ImageRepository,
    private val databaseRepository: DatabaseRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImagesViewModel(
            repository,
            databaseRepository
        ) as T
    }
}