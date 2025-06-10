package com.example.edu_challenge.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "answer")
class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference
    lateinit var question: Question

    @Column(nullable = false, columnDefinition = "TEXT")
    var text: String = ""

    @Column(name = "is_correct", nullable = false)
    var isCorrect: Boolean = false
}
