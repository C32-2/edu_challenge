package com.example.edu_challenge.repository

import com.example.edu_challenge.model.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AppUserRepository : JpaRepository<AppUser, Long> {
    fun findByUsernameContainingIgnoreCase(usernamePart: String): List<AppUser>
}