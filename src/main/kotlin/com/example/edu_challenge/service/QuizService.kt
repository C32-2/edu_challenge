package com.example.edu_challenge.service

import com.example.edu_challenge.dto.answer.AnswerDTO
import com.example.edu_challenge.dto.question.QuestionDTO
import com.example.edu_challenge.dto.quiz.QuizCreateRequest
import com.example.edu_challenge.dto.quiz.QuizDTO
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

        val quiz = quizRepository.save(Quiz(title = dto.title, topic = topic))

        val questions = questionRepository.findAllById(dto.questionIds.toSet())

        val quizQuestions = questions.mapIndexed { index, question ->
            QuizQuestion(
                quiz = quiz,
                question = question,
                position = index
            )
        }

        quizQuestionRepository.saveAll(quizQuestions)

        return quiz
    }

    fun getQuizById(id: Long): QuizDTO? {
        val quiz = quizRepository.findById(id).orElse(null)

        val quizQuestions = quizQuestionRepository.findByQuizIdOrderByPositionAsc(id)

        val questions = quizQuestions.map { qq ->
            val question = qq.question

            val answers = question.answers.map { answer ->
                AnswerDTO(
                    id = answer.id,
                    text = answer.text,
                    isCorrect = answer.isCorrect
                )
            }.toMutableList()

            QuestionDTO(
                id = question.id,
                text = question.text,
                topicId = question.topic.id,
                answers = answers
            )
        }

        return QuizDTO(
            id = quiz.id,
            title = quiz.title,
            topicId = quiz.topic.id,
            createdAt = quiz.createdAt,
            questions = questions
        )
    }

    fun getAllQuizzes(): List<QuizDTO> {
        return quizRepository.findAll().map { quiz ->
            val quizQuestions = quizQuestionRepository.findByQuizIdOrderByPositionAsc(quiz.id)

            val questionDTOs = quizQuestions.map { qq ->
                val q = qq.question
                val answerDTOs = q.answers.map { a ->
                    AnswerDTO(a.id, a.text, a.isCorrect)
                }
                QuestionDTO(q.id, q.text, q.topic.id, answerDTOs.toMutableList())
            }

            QuizDTO(
                id = quiz.id,
                title = quiz.title,
                topicId = quiz.topic.id,
                createdAt = quiz.createdAt,
                questions = questionDTOs
            )
        }
    }


    fun getQuizzesByTopicId(topicId: Long): List<QuizDTO> {
        return quizRepository.findAllByTopicId(topicId).map { quiz ->
            val quizQuestions = quizQuestionRepository.findByQuizIdOrderByPositionAsc(quiz.id)

            val questionDTOs = quizQuestions.map { qq ->
                val q = qq.question
                val answerDTOs = q.answers.map { a ->
                    AnswerDTO(a.id, a.text, a.isCorrect)
                }
                QuestionDTO(q.id, q.text, q.topic.id, answerDTOs.toMutableList())
            }

            QuizDTO(
                id = quiz.id,
                title = quiz.title,
                topicId = quiz.topic.id,
                createdAt = quiz.createdAt,
                questions = questionDTOs
            )
        }
    }
}


