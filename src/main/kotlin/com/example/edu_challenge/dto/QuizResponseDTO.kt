package com.example.edu_challenge.dto

import java.time.LocalDateTime

data class QuizResponseDTO(
    val id: Long,
    val title: String,
    val topicName: String,
    val createdAt: LocalDateTime,
    val questionIds: List<Long>
)


