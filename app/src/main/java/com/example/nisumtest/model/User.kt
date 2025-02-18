package com.example.nisumtest.model

data class User(
    val name: Name,
    val location: Location,
    val picture: Picture,
    val email: String,
    val phone: String,
    val gender: String,
    val dob: Dob
)