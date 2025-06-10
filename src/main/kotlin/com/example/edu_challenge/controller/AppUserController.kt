package com.example.edu_challenge.controller

import com.example.edu_challenge.dto.profile.UserProfileDTO
import com.example.edu_challenge.model.Role
import com.example.edu_challenge.service.AppUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users")
class AppUserController(private val appUserService: AppUserService) {

    @GetMapping("/{id}")
    fun getUserProfile(@PathVariable id: Long): UserProfileDTO {
        val user = appUserService.findById(id)
        return appUserService.toDTO(user)
    }

    @GetMapping("/search")
    fun getAllByUsername(@RequestParam username: String): List<UserProfileDTO> {
        return appUserService.findAllByUsername(username).map { appUserService.toDTO(it) }
    }

    @GetMapping("/leaderboard")
    fun getLeaderboard(): List<UserProfileDTO> {
        return appUserService.getLeaderboardTop30()
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/change-role")
    fun changeRole(@RequestParam id: Long, @RequestParam role: Role) : ResponseEntity<Void> {
        if (role != Role.ADMIN) {
            appUserService.updateUserRole(id, role)
            return ResponseEntity.status(HttpStatus.ACCEPTED).build()
        } else if (role == Role.ADMIN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @GetMapping
    fun getAllUsers(): List<UserProfileDTO> {
        return appUserService.getAllUsers();
    }
}