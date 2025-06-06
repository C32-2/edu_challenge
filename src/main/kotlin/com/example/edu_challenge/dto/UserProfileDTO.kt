package com.example.edu_challenge.dto

import java.time.LocalDateTime
import com.example.edu_challenge.model.Role

data class UserProfileDTO(
    val id: Long,
    val username: String,
    val createdDate: LocalDateTime?,
    val level: Int,
    val exp: Int,
    val role: Role,
    val quizzesSolved: Int
)