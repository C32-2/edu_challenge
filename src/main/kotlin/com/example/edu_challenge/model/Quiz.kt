package com.example.edu_challenge.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Quiz(

    @Id @GeneratedValue val id: Long = 0,

    val title: String,

    val topic: String,

    val createdAt: LocalDateTime = LocalDateTime.now()
)

