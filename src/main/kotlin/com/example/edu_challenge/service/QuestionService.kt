package com.example.edu_challenge.service

import com.example.edu_challenge.model.Question
import com.example.edu_challenge.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(private val questionRepository: QuestionRepository){
    fun createQuestion(question: Question) : Question {
        return questionRepository.save(question);
    }

    fun getQuestion(id: Long): Question? {
        return questionRepository.findById(id).orElse(null);
    }
}