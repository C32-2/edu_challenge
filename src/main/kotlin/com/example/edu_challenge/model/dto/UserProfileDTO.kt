package com.example.edu_challenge.model.dto

import java.time.LocalDateTime

data class UserProfileDTO(
    val id: Long,
    val username: String,
    val createdDate: LocalDateTime?,
    val level: Int,
    val exp: Int
)