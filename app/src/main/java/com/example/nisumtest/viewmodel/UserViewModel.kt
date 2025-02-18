package com.example.nisumtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nisumtest.model.User
import com.example.nisumtest.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    fun fetchUsers(count: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = ""
            try {
                _users.value = repository.getUsers(count).results

            } catch (e: IOException) {
                _error.value = "Network error. Please check your connection."
            } catch (e: HttpException) {
                _error.value = "Server error. Please try again later."
            } catch (e: Exception) {
                _error.value = "Failed to load users"
            }finally {
                _isLoading.value = false
            }
        }
    }
}