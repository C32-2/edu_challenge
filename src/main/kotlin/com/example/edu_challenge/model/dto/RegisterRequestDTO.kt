package com.example.edu_challenge.model.dto

data class RegisterRequest(
    val username: String,
    val email: String,
    val rawPassword: String
)