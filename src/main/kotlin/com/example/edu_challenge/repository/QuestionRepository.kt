package com.example.edu_challenge.repository

import com.example.edu_challenge.model.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question, Long> {
    fun findByTopicId(topicId: Long): List<Question>
    fun findByTextContainingIgnoreCase(text: String): List<Question>
    fun findByTopicIdAndTextContainingIgnoreCase(topicId: Long, text: String): List<Question>
}