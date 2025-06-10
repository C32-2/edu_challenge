package com.example.edu_challenge.service

import com.example.edu_challenge.model.Topic
import com.example.edu_challenge.repository.TopicRepository
import org.springframework.stereotype.Service

@Service
class TopicService(private val topicRepository: TopicRepository) {

    fun getTopicById(id: Long): Topic =
        topicRepository.findById(id)
            .orElseThrow { NoSuchElementException("Topic with id $id not found") }

    fun createTopic(topic: Topic): Topic =
        topicRepository.save(topic)

    fun getAllTopics(): List<Topic> =
        topicRepository.findAll()
}