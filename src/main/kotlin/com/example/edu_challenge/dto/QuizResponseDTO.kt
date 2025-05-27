package com.example.edu_challenge.dto

data class QuizResponseDTO(
    val id: Long,
    val title: String,
    val topic: String,
    val questions: List<QuestionDTO>? = null
)
