package com.example.photoapplication.repository

import com.example.photoapplication.network.ApiService
import com.example.photoapplication.network.safeApiRequest

class AlbumRepository(
    private val api : ApiService
): safeApiRequest() {
    suspend fun getAlbumsByUserId(userId: String) = userApiRequest{
        api.getAlbumsByUserId( userId)
    }
}