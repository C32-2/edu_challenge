package com.example.edu_challenge.service

import com.example.edu_challenge.dto.QuizCreateRequest
import com.example.edu_challenge.dto.QuizDTO
import com.example.edu_challenge.model.Quiz
import com.example.edu_challenge.model.QuizQuestion
import com.example.edu_challenge.repository.*
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class QuizService(
    private val quizRepository: QuizRepository,
    private val topicRepository: TopicRepository,
    private val questionRepository: QuestionRepository,
    private val quizQuestionRepository: QuizQuestionRepository
) {

    @Transactional
    fun createQuiz(dto: QuizCreateRequest): Quiz {
        val topic = topicRepository.findById(dto.topicId)
            .orElseThrow { IllegalArgumentException("Topic with id ${dto.topicId} not found") }

        val quiz = quizRepository.save(
            Quiz(title = dto.title, topic = topic)
        )

        dto.questionIds.forEachIndexed { index, questionId ->
            val question = questionRepository.findById(questionId)
                .orElseThrow { IllegalArgumentException("Question with id $questionId not found") }

            val quizQuestion = QuizQuestion(
                quiz = quiz,
                question = question,
                position = index
            )

            quizQuestionRepository.save(quizQuestion)
        }

        return quiz
    }

    fun getAllQuizzes(): List<QuizDTO> =
        quizRepository.findAll().map {
            QuizDTO(
                id = it.id,
                title = it.title,
                topicId = it.topic.id,
                createdAt = it.createdAt
            )
        }

    fun getQuizById(id: Long): QuizDTO? {
        val quiz = quizRepository.findById(id).orElse(null) ?: return null

        return QuizDTO(
            id = quiz.id,
            title = quiz.title,
            topicId = quiz.topic.id,
            createdAt = quiz.createdAt
        )
    }
}


