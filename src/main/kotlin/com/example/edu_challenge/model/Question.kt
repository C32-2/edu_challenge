package com.example.edu_challenge.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "question")
class Question(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, columnDefinition = "TEXT")
    var text: String,

    @Column(nullable = false)
    var difficulty: Int,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(columnDefinition = "TEXT")
    var explanation: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    var author: AppUser,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    var topic: Topic,

    @ManyToMany(mappedBy = "questions")
    var questionSets: MutableSet<QuestionSet> = mutableSetOf()
) {
    @PrePersist
    fun prePersist() {
        createdAt = LocalDateTime.now()
    }
}
