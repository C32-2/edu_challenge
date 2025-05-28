package com.example.edu_challenge.dto

data class CreateQuizDTO(
    val title: String,
    val topicId: Long,
    val questionIds: List<Long>
)
