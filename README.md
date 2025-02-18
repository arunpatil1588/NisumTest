📌 Overview

This is a Native Android app built using Kotlin, MVVM architecture, and Jetpack Compose. It fetches and displays a list of random users from the Random User API. Users can enter a number to specify how many users to fetch. Clicking on a user card navigates to a details screen with additional information.

///// 🚀 Features //////

Fetch and display a list of random users.

Enter a number to specify how many users to retrieve.

Paging support for smooth scrolling and loading more users.

User details screen showing full profile information.

Error handling for API failures and network issues.

Unit tests for ViewModel and Repository.

🏗️//// Tech Stack ////

Kotlin (Native Android Development)

Jetpack Compose (UI)

MVVM Architecture

Retrofit (Networking)

Hilt (Dependency Injection)

StateFlow (Reactive Data Handling)

Paging 3 (Infinite Scrolling)

Coil (Image Loading)

JUnit & MockK (Unit Testing)

📲/// API Reference ////

Random User API Documentation

Base URL: https://randomuser.me/api/

Example: https://randomuser.me/api/?results=10

 Project Structure

app/
├── di/                 # Hilt Dependency Injection
├── model/              # Data Models
├── network/            # Retrofit API Service
├── repository/         # Data Handling Layer
├── ui/                 # Jetpack Compose Screens
│   ├── UserListScreen.kt
│   ├── UserDetailScreen.kt
├── viewmodel/          # ViewModel for UI State Management
├── MainActivity.kt      # Entry Point


