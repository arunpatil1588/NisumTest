package com.example.nisumtest.network

import com.example.nisumtest.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api/")
    suspend fun getUsers(@Query("results") count: Int): UserResponse
}