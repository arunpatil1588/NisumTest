package com.example.nisumtest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nisumtest.model.Dob
import com.example.nisumtest.model.Location
import com.example.nisumtest.model.Name
import com.example.nisumtest.model.Picture
import com.example.nisumtest.model.User
import com.example.nisumtest.model.UserResponse
import com.example.nisumtest.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class UserViewModelTest {

    // Rule for LiveData Testing
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Coroutine Test Dispatcher
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: UserViewModel
    private val repository: UserRepository = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher) // Set Main Dispatcher
        viewModel = UserViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset Dispatcher after test
    }

    @Test
    fun `fetchUsers() should update users list on success`() = runTest {
        // Given: Fake user data
        val fakeUsers = listOf(

        User(Name("Arun", "Patil"),
            Location("Pune","Maharashtra","India"),
            Picture("https://example.com/Arun.jpg"),
            "","","", Dob("",12))
        )
        coEvery { repository.getUsers(any()) } returns UserResponse(fakeUsers)

        // When: Fetching users
        viewModel.fetchUsers(1)
        advanceUntilIdle() // Simulate coroutine completion

        // Then: Verify correct behavior
        assertEquals(fakeUsers, viewModel.users.first())  // Ensure data is updated
        assertFalse(viewModel.isLoading.first())  // Ensure loading state is false
        assertTrue(viewModel.error.first().isEmpty())  // Ensure no error
    }

    @Test
    fun `fetchUsers() should set error message on failure`() = runTest {
        // Given: Simulate network error
        coEvery { repository.getUsers(any()) } throws Exception("Network Error")

        // When: Fetching users
        viewModel.fetchUsers(1)

        // **Explicitly collect from flow to ensure it updates**
        val errorMessage = viewModel.error.first { it.isNotEmpty() }

        // Then: Verify error handling
        assertEquals("Failed to load users", errorMessage)  // Check error message
        assertTrue(viewModel.users.first().isEmpty())  // Ensure no data is set
        assertFalse(viewModel.isLoading.first())  // Ensure loading is false
    }
}
