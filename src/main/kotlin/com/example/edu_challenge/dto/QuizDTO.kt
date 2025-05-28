package com.example.edu_challenge.dto

import java.time.LocalDateTime

data class QuizDTO(
    val id: Long,
    val title: String,
    val topicId: Long,
    val createdAt: LocalDateTime
)
