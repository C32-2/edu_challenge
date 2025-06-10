package com.example.edu_challenge.dto.profile

import com.example.edu_challenge.model.Role
import java.time.LocalDateTime

data class UserProfileDTO(
    val id: Long,
    val username: String,
    val createdDate: LocalDateTime?,
    val level: Int,
    val exp: Int,
    val role: Role,
    val quizzesSolved: Int
)