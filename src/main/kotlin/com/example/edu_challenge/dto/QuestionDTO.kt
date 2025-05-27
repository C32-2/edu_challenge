package com.example.edu_challenge.dto

data class QuestionDTO(
    val id: Long,
    val text: String,
    val options: List<String>
)
