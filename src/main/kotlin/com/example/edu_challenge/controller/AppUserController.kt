package com.example.edu_challenge.controller

import com.example.edu_challenge.model.AppUser
import com.example.edu_challenge.model.dto.*
import com.example.edu_challenge.service.AppUserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users")
class AppUserController(private val appUserService: AppUserService) {

    @PostMapping("/register")
    fun registerUser(@RequestBody request: RegisterRequest): AppUser {
        return appUserService.register(
            username = request.username,
            email = request.email,
            request.rawPassword
        )
    }

    @GetMapping("/{id}")
    fun getUserProfile(@PathVariable id: Long): UserProfileDTO {
        val user = appUserService.findById(id)
        return UserProfileDTO(
            id = user.id,
            username = user.username,
            createdDate = user.createdAt,
            level = user.level,
            exp = user.exp
        )
    }
}