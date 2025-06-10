package com.example.edu_challenge.controller

import com.example.edu_challenge.dto.question.QuestionCreateDTO
import com.example.edu_challenge.dto.question.QuestionDTO
import com.example.edu_challenge.model.Question
import com.example.edu_challenge.repository.QuestionRepository
import com.example.edu_challenge.service.QuestionService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/questions")
class QuestionController(private val questionService: QuestionService,
                         private val questionRepository: QuestionRepository
) {

    @PostMapping
    @PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
    fun createQuestion(@RequestBody dto: QuestionCreateDTO): ResponseEntity<QuestionDTO> {
        val savedQuestion = questionService.addQuestion(dto)
        val questionDTO = questionService.toDTO(savedQuestion)
        return ResponseEntity.ok(questionDTO)
    }


    @GetMapping("/{id}")
    fun getQuestionById(@PathVariable id: Long): ResponseEntity<QuestionDTO> {
        val question = questionService.getQuestionById(id)
        return if (question != null) {
            val dto = questionService.toDTO(question)
            ResponseEntity.ok(dto)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @RestController
    @RequestMapping("/api/questions")
    class QuestionController(
        private val questionService: QuestionService
    ) {
        @GetMapping
        fun getQuestions(
            @RequestParam(required = false) topicId: Long?,
            @RequestParam(required = false) query: String?
        ): ResponseEntity<List<QuestionDTO>> {
            val result = questionService.search(topicId, query)
            return ResponseEntity.ok(result)
        }
    }

}

