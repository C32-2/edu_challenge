package com.example.edu_challenge.service

import com.example.edu_challenge.model.AppUser
import com.example.edu_challenge.model.Role
import com.example.edu_challenge.dto.UserProfileDTO
import com.example.edu_challenge.repository.AppUserRepository
import com.example.edu_challenge.util.security.JwtUtil
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.security.auth.login.CredentialNotFoundException

@Service
class AuthService(
    private val appUserRepository: AppUserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
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

    fun authenticate(email: String, password: String): String {
        val user: AppUser = appUserRepository.findByEmail(email) ?:
        throw UsernameNotFoundException("User not found!")

        if (!passwordEncoder.matches(password, user.password)) {
            throw BadCredentialsException("Invalid password!")
        }

        return jwtUtil.generateToken(user)
    }
}