package com.example.edu_challenge.controller

import com.example.edu_challenge.dto.UserProfileDTO
import com.example.edu_challenge.service.AppUserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users")
class AppUserController(private val appUserService: AppUserService) {

    @GetMapping("/{id}")
    fun getUserProfile(@PathVariable id: Long): UserProfileDTO {
        val user = appUserService.findById(id)
        return appUserService.toDTO(user)
    }

    @GetMapping
    fun getAllByUsername(@RequestParam username: String): List<UserProfileDTO> {
        return appUserService.findAllByUsername(username).map { appUserService.toDTO(it) }
    }
}