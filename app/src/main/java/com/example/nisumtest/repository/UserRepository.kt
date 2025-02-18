package com.example.nisumtest.repository

import com.example.nisumtest.model.UserResponse
import com.example.nisumtest.network.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: UserService) {
    suspend fun getUsers(count: Int): UserResponse = api.getUsers(count)
}