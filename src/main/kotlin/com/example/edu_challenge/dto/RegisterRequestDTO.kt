package com.example.edu_challenge.dto

data class RegisterRequestDTO(
    val username: String,
    val email: String,
    val rawPassword: String
)