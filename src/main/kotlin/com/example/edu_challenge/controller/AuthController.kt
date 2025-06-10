package com.example.edu_challenge.controller

import com.example.edu_challenge.dto.auth.LoginDTO
import com.example.edu_challenge.dto.auth.RegisterDTO
import com.example.edu_challenge.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun registerUser(@RequestBody request: RegisterDTO): ResponseEntity<Void> {
        authService.register(
            username = request.username,
            email = request.email,
            rawPassword = request.password
        )
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody request: LoginDTO) : ResponseEntity<Map<String, String>> {
        val token: String = authService.authenticate(request.email, request.password)
        return ResponseEntity.ok(mapOf("token" to token))
    }
}