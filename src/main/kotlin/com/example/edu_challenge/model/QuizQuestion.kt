package com.example.edu_challenge.model

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
data class QuizQuestion(
    @Id
    @GeneratedValue
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    val quiz: Quiz,

    @ManyToOne(fetch = FetchType.LAZY)
    val question: Question,

    val position: Int
)
