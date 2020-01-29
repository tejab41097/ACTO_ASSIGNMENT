package com.example.photoapplication.repository

import com.example.photoapplication.network.ApiService
import com.example.photoapplication.network.safeApiRequest

class ImageRepository (
    private val api : ApiService
): safeApiRequest() {
    suspend fun getImagesByAlbumId(albumId: String) = userApiRequest{
        api.getImagesByAlbumId(albumId)
    }
}