package com.example.edu_challenge.service

import com.example.edu_challenge.model.AppUser
import com.example.edu_challenge.dto.profile.UserProfileDTO
import com.example.edu_challenge.model.Role
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
        return appUserRepository.findById(id)
            .orElseThrow { NoSuchElementException("User with id $id not found") }
    }

    fun findAllByUsername(username: String): List<AppUser> {
        return appUserRepository.findByUsernameContainingIgnoreCase(username)
    }

    fun toDTO(appUser: AppUser): UserProfileDTO {
        return UserProfileDTO(
            id = appUser.id,
            username = appUser.username,
            createdDate = appUser.createdAt,
            level = appUser.level,
            exp = appUser.exp,
            role = appUser.role,
            quizzesSolved = appUser.quizzesSolved
        )
    }

    fun getLeaderboardTop30(): List<UserProfileDTO> {
        val topUsers = appUserRepository.findTop30ByOrderByQuizzesSolvedDesc()
        return topUsers.map { toDTO(it) }
    }

    fun updateUserRole(userId: Long, newRole: Role) {
        val user = findById(userId)
        user.role = newRole
        appUserRepository.save(user)
    }

    fun getAllUsers(): List<UserProfileDTO> {
        return appUserRepository.findAll().map { toDTO(it) }
    }
}