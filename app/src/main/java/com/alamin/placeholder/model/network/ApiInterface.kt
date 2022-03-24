package com.alamin.placeholder.model.network

import com.alamin.placeholder.model.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("users/{id}")
    suspend fun getUserById(@Path(value = "id") id: Int): Response<User>;
}