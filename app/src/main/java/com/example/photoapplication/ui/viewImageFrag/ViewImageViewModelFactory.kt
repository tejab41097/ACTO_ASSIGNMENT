package com.example.photoapplication.ui.viewImageFrag

import androidx.lifecycle.ViewModelProvider
import com.example.photoapplication.data.OfflineDB.DatabaseRepository
import com.example.photoapplication.repository.UserRepository

class ViewImageViewModelFactory(
        databaseRepository: DatabaseRepository,
        repository: UserRepository
):ViewModelProvider.NewInstanceFactory() {

}