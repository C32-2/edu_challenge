package com.example.edu_challenge.controller

import com.example.edu_challenge.dto.RegisterRequestDTO
import com.example.edu_challenge.model.AppUser
import com.example.edu_challenge.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/users")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun registerUser(@RequestBody request: RegisterRequestDTO): AppUser {
        return authService.register(
            username = request.username,
            email = request.email,
            request.rawPassword
        )
    }
}