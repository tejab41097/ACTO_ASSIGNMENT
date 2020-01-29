package com.example.photoapplication.repository

import com.example.photoapplication.network.ApiService
import com.example.photoapplication.network.safeApiRequest

class UserRepository(
    private val api : ApiService
): safeApiRequest() {

    suspend fun getUsers() = userApiRequest{
        api.getUsers()
    }
}