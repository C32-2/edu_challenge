package com.example.edu_challenge.dto

data class CreateQuizRequestDTO(
    val title: String,
    val topic: String,
    val questionIds: List<Long>
)
