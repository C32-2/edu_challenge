package com.example.edu_challenge.controller

import com.example.edu_challenge.model.Topic
import com.example.edu_challenge.service.TopicService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/topics")
class TopicController(private val topicService: TopicService) {

    @GetMapping("/{id}")
    fun getTopic(@PathVariable id: Long) : Topic? {
        return topicService.getTopic(id)
    }

    @PostMapping
    @PreAuthorize("hasRole('EDITOR')")
    fun createTopic(@RequestBody topic: Topic): Topic {
        return topicService.createTopic(topic)
    }

    @GetMapping
    fun getAllTopics(): List<Topic> {
        return topicService.getAllTopics()
    }
}