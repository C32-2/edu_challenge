package com.example.edu_challenge.repository

import com.example.edu_challenge.model.QuizQuestion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizQuestionRepository : JpaRepository<QuizQuestion, Long> {
    fun findAllByQuizId(quizId: Long): List<QuizQuestion>
}