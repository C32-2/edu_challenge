package com.example.edu_challenge.dto.quiz

import com.example.edu_challenge.dto.question.QuestionDTO
import java.time.LocalDateTime

data class QuizDTO(
    val id: Long,
    val title: String,
    val topicId: Long,
    val createdAt: LocalDateTime,
    val questions: List<QuestionDTO>
)
