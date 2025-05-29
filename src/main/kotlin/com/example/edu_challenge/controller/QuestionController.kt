package com.example.edu_challenge.controller

import com.example.edu_challenge.dto.QuestionDTO
import com.example.edu_challenge.model.Question
import com.example.edu_challenge.service.QuestionService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/questions")
class QuestionController(private val questionService: QuestionService) {

    @PostMapping
    @PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
    fun createQuestion(@RequestBody dto: QuestionDTO): ResponseEntity<Question> {
        val saved = questionService.createQuestion(dto)
        return ResponseEntity.ok(saved)
    }

    @GetMapping("/{id}")
    fun getQuestionById(@PathVariable id: Long): ResponseEntity<Question> {
        val question = questionService.getQuestionById(id)
        return if (question != null) ResponseEntity.ok(question)
        else ResponseEntity.notFound().build()
    }

    @GetMapping("/by-topic/{topicId}")
    fun getQuestionsByTopic(@PathVariable topicId: Long): ResponseEntity<List<Question>> {
        val questions = questionService.getQuestionsByTopicId(topicId)
        return ResponseEntity.ok(questions)
    }
}

