package com.example.edu_challenge.service

import com.example.edu_challenge.model.Topic
import com.example.edu_challenge.repository.TopicRepository
import org.springframework.stereotype.Service

@Service
class TopicService(private val topicRepository: TopicRepository) {
    fun getTopic(id: Long): Topic? {
        return topicRepository.findById(id).orElse(null)
    }

    fun createTopic(topic: Topic): Topic {
        return topicRepository.save(topic)
    }

    fun getAllTopics(): List<Topic> {
        return topicRepository.findAll()
    }
}