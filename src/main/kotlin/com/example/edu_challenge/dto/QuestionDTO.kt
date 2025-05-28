package com.example.edu_challenge.dto

import com.example.edu_challenge.model.Topic

data class QuestionDTO(
    val text: String,
    val topicId: Long,
    val options: List<String>,
    val correctIndex: Int
)


