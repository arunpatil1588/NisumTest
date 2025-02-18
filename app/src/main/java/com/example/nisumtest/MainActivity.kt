package com.example.nisumtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nisumtest.ui.theme.NisumTestTheme
import com.example.nisumtest.ui.screens.UserDetailScreen
import com.example.nisumtest.ui.screens.UserListScreen
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder
import java.net.URLEncoder
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "user_list") {
                composable("user_list") { UserListScreen(navController) }
                composable(
                    "user_detail/{firstName}/{lastName}/{imageUrl}/{email}/{phone}/{gender}/{dob}/{address}"
                ) { backStackEntry ->
                    val firstName = URLDecoder.decode(backStackEntry.arguments?.getString("firstName") ?: "", "UTF-8")
                    val lastName = URLDecoder.decode(backStackEntry.arguments?.getString("lastName") ?: "", "UTF-8")
                    val imageUrl = URLDecoder.decode(backStackEntry.arguments?.getString("imageUrl") ?: "", "UTF-8")
                    val email = URLDecoder.decode(backStackEntry.arguments?.getString("email") ?: "", "UTF-8")
                    val phone = URLDecoder.decode(backStackEntry.arguments?.getString("phone") ?: "", "UTF-8")
                    val gender = URLDecoder.decode(backStackEntry.arguments?.getString("gender") ?: "", "UTF-8")
                    val dob = URLDecoder.decode(backStackEntry.arguments?.getString("dob") ?: "", "UTF-8")
                    val address = URLDecoder.decode(backStackEntry.arguments?.getString("address") ?: "", "UTF-8")

                    UserDetailScreen(
                        firstName, lastName, imageUrl, email, phone, gender, dob, address, navController
                    )
                }
            }
        }
    }
}
