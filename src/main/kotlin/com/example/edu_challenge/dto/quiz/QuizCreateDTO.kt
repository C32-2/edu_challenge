package com.example.edu_challenge.dto.quiz

data class QuizCreateRequest(
    val title: String,
    val topicId: Long,
    val questionIds: List<Long>
)