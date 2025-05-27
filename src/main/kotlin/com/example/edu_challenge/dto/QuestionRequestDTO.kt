package com.example.edu_challenge.dto

import java.time.LocalDateTime

data class QuestionRequestDTO(
    val id: Long,
    val text: String,
    val difficulty: Int,
    val createdAt: LocalDateTime?,
    val explanation: String?,
    val authorId: Long,
    val topicId: Long
)