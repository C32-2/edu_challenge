package com.example.edu_challenge.controller

import com.example.edu_challenge.dto.quiz.QuizCreateRequest
import com.example.edu_challenge.dto.quiz.QuizDTO
import com.example.edu_challenge.model.Quiz
import com.example.edu_challenge.service.QuizService

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/quizzes")
class QuizController(private val quizService: QuizService) {

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    fun createQuiz(@RequestBody dto: QuizCreateRequest): ResponseEntity<Quiz> {
        val created = quizService.createQuiz(dto)
        return ResponseEntity.ok(created)
    }

    @GetMapping("/{id}")
    fun getQuizById(@PathVariable id: Long): ResponseEntity<QuizDTO> {
        val quiz = quizService.getQuizById(id)
        return if (quiz != null) ResponseEntity.ok(quiz)
        else ResponseEntity.notFound().build()
    }

    @GetMapping
    fun getQuizzes(@RequestParam(required = false) topicId: Long?): ResponseEntity<List<QuizDTO>> {
        val quizzes = if (topicId != null)
            quizService.getQuizzesByTopicId(topicId)
        else
            quizService.getAllQuizzes()

        return ResponseEntity.ok(quizzes)
    }
}

