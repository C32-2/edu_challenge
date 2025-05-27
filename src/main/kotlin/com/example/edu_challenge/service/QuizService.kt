package com.example.edu_challenge.service

import com.example.edu_challenge.model.Quiz
import com.example.edu_challenge.repository.*
import org.springframework.stereotype.Service

class QuizService(
    private val questionRepository: QuestionRepository,
    private val quizRepository: QuizRepository,
    private val quizQuestionRepository: QuizQuestionRepository
) {

}