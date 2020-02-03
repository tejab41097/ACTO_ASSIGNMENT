package com.example.photoapplication.ui.viewImageFrag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photoapplication.data.OfflineDB.DatabaseRepository
import com.example.photoapplication.repository.ImageRepository
import com.example.photoapplication.repository.UserRepository
import com.example.photoapplication.ui.usersFrag.UsersViewModel

class ViewImageViewModelFactory(
        private val repository: ImageRepository,
        private val databaseRepository: DatabaseRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewImageViewModel(
                repository,
                databaseRepository
        ) as T
    }
}