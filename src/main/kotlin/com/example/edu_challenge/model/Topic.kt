package com.example.edu_challenge.model

import jakarta.persistence.*

@Entity
@Table(name = "topic")
class Topic(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, length = 255)
    var name: String
)
