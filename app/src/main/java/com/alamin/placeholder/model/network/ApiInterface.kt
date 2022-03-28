package com.alamin.placeholder.model.network

import com.alamin.placeholder.model.data.Album
import com.alamin.placeholder.model.data.Photo
import com.alamin.placeholder.model.data.Post
import com.alamin.placeholder.model.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("users/{id}")
    suspend fun getUserById(@Path(value = "id") id: Int): Response<User>;

    @GET("/posts")
    suspend fun getAllPost(): Response<List<Post>>

    @GET("albums")
    suspend fun getAllAlbum(@Query(value = "userId") userId: Int): Response<List<Album>>

    @GET("photos")
    suspend fun getAllPhoto(@Query(value = "albumId") albumId: Int): Response<List<Photo>>
}