package com.example.edu_challenge.controller

import com.example.edu_challenge.model.Topic
import com.example.edu_challenge.service.TopicService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/topics")
class TopicController(private val topicService: TopicService) {

    @GetMapping("/{id}")
    fun getTopicById(@PathVariable id: Long): ResponseEntity<Topic> {
        val topic = topicService.getTopicById(id)
        return if (topic != null) ResponseEntity.ok(topic)
        else ResponseEntity.notFound().build()
    }

    @PostMapping
    @PreAuthorize("hasRole('EDITOR')")
    fun createTopic(@RequestBody topic: Topic): ResponseEntity<Topic> {
        val created = topicService.createTopic(topic)
        return ResponseEntity.ok(created)
    }

    @GetMapping
    fun getAllTopics(): ResponseEntity<List<Topic>> {
        val topics = topicService.getAllTopics()
        return ResponseEntity.ok(topics)
    }
}
