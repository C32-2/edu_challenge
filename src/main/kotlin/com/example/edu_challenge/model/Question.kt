package com.example.edu_challenge.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Question(
    @Id
    @GeneratedValue
    val id: Long = 0,

    val text: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    val topic: Topic,

    @ElementCollection
    val options: List<String>,

    val correctIndex: Int
)

