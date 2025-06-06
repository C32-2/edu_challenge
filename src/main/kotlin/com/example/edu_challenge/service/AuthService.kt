package com.example.edu_challenge.service

import com.example.edu_challenge.model.AppUser
import com.example.edu_challenge.model.Role
import com.example.edu_challenge.repository.AppUserRepository
import com.example.edu_challenge.util.security.JwtUtil
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val appUserRepository: AppUserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
) {

    fun register(username: String, email: String, rawPassword: String): AppUser {
        val encryptedPassword = passwordEncoder.encode(rawPassword)

        val newUser = AppUser(
            username = username,
            email = email,
            password = encryptedPassword,
            role = Role.USER,
            level = 1,
            exp = 0,
            quizzesSolved = 0
        )

        return appUserRepository.save(newUser)
    }

    fun authenticate(email: String, rawPassword: String): String {
        val user = appUserRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("User with email '$email' not found")

        if (!passwordEncoder.matches(rawPassword, user.password)) {
            throw BadCredentialsException("Invalid password")
        }

        return jwtUtil.generateToken(user)
    }
}