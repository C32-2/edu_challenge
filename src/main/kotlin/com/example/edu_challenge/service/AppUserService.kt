package com.example.edu_challenge.service

import com.example.edu_challenge.model.AppUser
import com.example.edu_challenge.model.Role
import com.example.edu_challenge.dto.UserProfileDTO
import com.example.edu_challenge.repository.AppUserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AppUserService(
    private val appUserRepository: AppUserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun findById(id: Long): AppUser {
        return appUserRepository.findById(id).get()
    }

    fun findAllByUsername(username: String): List<AppUser> {
        return appUserRepository.findByUsernameContainingIgnoreCase(username)
    }

    fun toDTO(appUser: AppUser): UserProfileDTO {
        return UserProfileDTO(
            id = appUser.id,
            username = appUser.username,
            createdDate = LocalDateTime.now(),
            level = appUser.level,
            exp = appUser.exp
        )
    }
}