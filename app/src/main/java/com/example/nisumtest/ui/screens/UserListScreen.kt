package com.example.nisumtest.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.nisumtest.viewmodel.UserViewModel
import com.example.nisumtest.ui.components.UserCard
import java.net.URLEncoder

@Composable
fun UserListScreen(navController: NavHostController, viewModel: UserViewModel = hiltViewModel()) {
    val users by viewModel.users.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    var userCount by remember { mutableStateOf("10") } // Default to 10 users

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Users List") },
                backgroundColor = Color.Blue,
                contentColor = Color.White
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)) {
            // Input Field to Enter Number of Users
            OutlinedTextField(
                value = userCount,
                onValueChange = { userCount = it },
                label = { Text("Enter number of users") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    val count = userCount.toIntOrNull() ?: 10 // Default to 10 if invalid input
                    viewModel.fetchUsers(count)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Fetch Users")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                when {
                    isLoading -> CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    error.isNotEmpty() -> Text(
                        text = error,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )

                    else -> LazyColumn {
                        items(users) { user ->
                            UserCard(user) {
                                navController.navigate(
                                    "user_detail/${
                                        URLEncoder.encode(user.name.first, "UTF-8")
                                    }/${
                                        URLEncoder.encode(user.name.last, "UTF-8")
                                    }/${
                                        URLEncoder.encode(user.picture.large, "UTF-8")
                                    }/${
                                        URLEncoder.encode(user.email, "UTF-8")
                                    }/${
                                        URLEncoder.encode(user.phone, "UTF-8")
                                    }/${
                                        URLEncoder.encode(user.gender, "UTF-8")
                                    }/${
                                        URLEncoder.encode(user.dob.date, "UTF-8")
                                    }/${
                                        URLEncoder.encode(" ${user.location.city}, ${user.location.state}, ${user.location.country}", "UTF-8")
                                    }"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
