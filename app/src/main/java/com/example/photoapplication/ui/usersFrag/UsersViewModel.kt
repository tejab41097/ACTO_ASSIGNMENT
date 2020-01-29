package com.example.photoapplication.ui.usersFrag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoapplication.Coroutines.CoroutinesObject
import com.example.photoapplication.data.DatabaseRepository
import com.example.photoapplication.data.user.User
import com.example.photoapplication.repository.UserRepository
import kotlinx.coroutines.Job

class UsersViewModel(
    private val repository: UserRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {
    private lateinit var job: Job
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    fun getUsers() {
        job = CoroutinesObject.ioToMain(
            { repository.getUsers() },
            { _users.value = it })
    }

    fun saveAllUsersToDb(users :List<User>){
        job = CoroutinesObject.ioToMain(
            { databaseRepository.saveAllUsersToDb(users) },
            { })
    }

    fun getAllUsersFromDb(){
        job = CoroutinesObject.ioToMain(
            { databaseRepository.getAllUsersFromDb() },
            { _users.value = it })
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
