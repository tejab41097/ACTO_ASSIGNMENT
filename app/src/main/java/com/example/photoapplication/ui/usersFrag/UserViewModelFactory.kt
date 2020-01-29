package com.example.photoapplication.ui.usersFrag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photoapplication.data.DatabaseRepository
import com.example.photoapplication.repository.UserRepository

class UserViewModelFactory(
    private val repository: UserRepository,
    private val databaseRepository: DatabaseRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UsersViewModel(
            repository,
            databaseRepository
        ) as T
    }
}