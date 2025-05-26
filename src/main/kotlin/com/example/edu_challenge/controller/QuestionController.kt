package com.example.edu_challenge.controller

import com.example.edu_challenge.model.Question
import com.example.edu_challenge.service.QuestionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/questions")
class QuestionController(private val questionService: QuestionService) {

    @PostMapping
    fun createQuestion(@RequestBody question: Question) : ResponseEntity<Question> {
        val createdQuestion = questionService.createQuestion(question)
        return ResponseEntity.ok(createdQuestion);
    }

    @GetMapping("/{id}")
    fun getQuestion(id: Long): ResponseEntity<Question> {
        val question = questionService.getQuestion(id)
        return if (question != null) {
            ResponseEntity.ok(question)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}