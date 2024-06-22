package com.ku.bazar.login.data

data class RegisterRequest(
    val email: String,
    val username: String
)

data class User(
    val name: String,
    val id: String,
    val imageURL: String? // Allow nullable field
)