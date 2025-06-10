package com.example.edu_challenge.dto.question

import com.example.edu_challenge.dto.answer.AnswerDTO

data class QuestionDTO(
    val id: Long,
    val text: String,
    val topicId: Long,
    val answers: MutableList<AnswerDTO>
)
