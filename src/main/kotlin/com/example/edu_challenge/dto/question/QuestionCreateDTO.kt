package com.example.edu_challenge.dto.question
import com.example.edu_challenge.dto.answer.*

data class QuestionCreateDTO(
    val text: String,
    val topicId: Long,
    val answers: MutableList<AnswerCreateDTO>
)