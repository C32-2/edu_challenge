package com.example.edu_challenge.service

import com.example.edu_challenge.model.AppUser
import com.example.edu_challenge.model.Role
import com.example.edu_challenge.dto.UserProfileDTO
import com.example.edu_challenge.repository.AppUserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthService(
    private val appUserRepository: AppUserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun register(username: String, email: String, rawPassword: String): AppUser {
        val encryptedPassword = passwordEncoder.encode(rawPassword)
        val user = AppUser(
            username = username,
            email = email,
            password = encryptedPassword,
            role = Role.USER,
            level = 1,
            exp = 0
        )
        return appUserRepository.save(user)
    }
}