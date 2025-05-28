package com.example.edu_challenge.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Quiz(
    @Id @GeneratedValue val id: Long = 0,

    val title: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    val topic: Topic,

    val createdAt: LocalDateTime = LocalDateTime.now()
)


