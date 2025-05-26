package com.example.edu_challenge.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "app_user", uniqueConstraints = [
    UniqueConstraint(columnNames = ["username"]),
    UniqueConstraint(columnNames = ["email"])
])
class AppUser(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, length = 50)
    var username: String,

    @Column(nullable = false)
    var password: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var role: Role,

    @Column(nullable = false, length = 100)
    var email: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null,

    var level: Int,

    var exp: Int,
) {
    @PrePersist
    fun onCreate() {
        createdAt = LocalDateTime.now()
    }
}

enum class Role {
    USER, ADMIN, EDITOR
}
