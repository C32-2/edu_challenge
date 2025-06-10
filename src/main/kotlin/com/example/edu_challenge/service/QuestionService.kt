package com.example.edu_challenge.service

import com.example.edu_challenge.dto.answer.AnswerDTO
import com.example.edu_challenge.dto.question.QuestionCreateDTO
import com.example.edu_challenge.dto.question.QuestionDTO
import com.example.edu_challenge.model.Answer
import com.example.edu_challenge.model.Question
import com.example.edu_challenge.repository.QuestionRepository
import com.example.edu_challenge.repository.TopicRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val topicRepository: TopicRepository
) {

    @Transactional
    fun addQuestion(dto: QuestionCreateDTO): Question {
        val topic = topicRepository.findById(dto.topicId)
            .orElseThrow { IllegalArgumentException("Topic not found") }

        val question = Question(
            text = dto.text,
            topic = topic
        )

        val answers = dto.answers.map { answerDto ->
            Answer().apply {
                text = answerDto.text
                isCorrect = answerDto.isCorrect
                this.question = question
            }
        }

        question.answers = answers.toMutableList()

        return questionRepository.save(question)
    }

    fun getQuestionById(id: Long): Question? =
        questionRepository.findById(id).orElse(null)

    fun search(topicId: Long?, query: String?): List<QuestionDTO> {
            val questions = when {
            topicId != null && query != null ->
                questionRepository.findByTopicIdAndTextContainingIgnoreCase(topicId, query)
            topicId != null ->
                questionRepository.findByTopicId(topicId)
            query != null ->
                questionRepository.findByTextContainingIgnoreCase(query)
            else ->
                questionRepository.findAll()
        }

        return questions.map { toDTO(it) }
    }


    fun toDTO(question: Question): QuestionDTO {
        return QuestionDTO(
            id = question.id,
            text = question.text,
            topicId = question.topic.id,
            answers = question.answers.map { answer ->
                AnswerDTO(
                    id = answer.id,
                    text = answer.text,
                    isCorrect = answer.isCorrect
                )
            }.toMutableList()
        )
    }
}
