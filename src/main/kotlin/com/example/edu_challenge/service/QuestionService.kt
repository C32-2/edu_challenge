package com.example.edu_challenge.service

import com.example.edu_challenge.dto.QuestionDTO
import com.example.edu_challenge.model.Question
import com.example.edu_challenge.repository.QuestionRepository
import com.example.edu_challenge.repository.TopicRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val topicRepository: TopicRepository
) {

    fun createQuestion(dto: QuestionDTO): Question {
        val topic = topicRepository.findById(dto.topicId)
            .orElseThrow { IllegalArgumentException("Topic with id ${dto.topicId} not found") }

        val question = Question(
            text = dto.text,
            topic = topic,
            options = dto.options,
            correctIndex = dto.correctIndex
        )

        return questionRepository.save(question)
    }

    fun getQuestionById(id: Long): Question? =
        questionRepository.findById(id).orElse(null)

    fun getQuestionsByTopicId(topicId: Long): List<Question> =
        questionRepository.findAllByTopicId(topicId)
}