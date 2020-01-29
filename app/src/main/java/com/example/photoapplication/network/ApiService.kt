package com.example.photoapplication.network

import com.example.photoapplication.data.album.Album
import com.example.photoapplication.data.images.Pic
import com.example.photoapplication.data.user.User
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("albums?")
    suspend fun getAlbumsByUserId(
        @Query("userId") userId: String
    ): Response<List<Album>>

    @GET("photos?")
    suspend fun getImagesByAlbumId(
        @Query("albumId") albumId: String
    ): Response<List<Pic>>


    companion object Factory {
        private val URL = "https://jsonplaceholder.typicode.com/"

        operator fun invoke(): ApiService {

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build()
            val retrofit = Retrofit.Builder()
                .client(OkHttpClient.Builder().build())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}