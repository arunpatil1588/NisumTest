package com.example.nisumtest.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserDetailScreen(firstName: String,
                     lastName: String,
                     imageUrl: String,
                     email: String,
                     phone: String,
                     gender: String,
                     dob: String,
                     address: String,
                     navController: NavHostController) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("User Details") },
                    backgroundColor = Color.Blue,
                    contentColor = Color.White,
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(250.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "$firstName $lastName", fontSize = 24.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Email:", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = email, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Phone:", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = phone, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Gender: $gender", fontSize = 16.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Date of Birth: $dob", fontSize = 16.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Address: $address", fontSize = 16.sp, color = Color.DarkGray)
            }
        }
    }