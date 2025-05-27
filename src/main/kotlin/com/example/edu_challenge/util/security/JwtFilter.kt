package com.example.edu_challenge.security

import com.example.edu_challenge.repository.AppUserRepository
import com.example.edu_challenge.util.security.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean

@Component
class JwtFilter(
    private val jwtUtil: JwtUtil,
    private val userRepository: AppUserRepository
) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val authHeader = httpRequest.getHeader("Authorization")

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring(7)

            try {
                val email = jwtUtil.extractUsername(token)
                val user = userRepository.findByEmail(email)

                if (user != null && jwtUtil.isTokenValid(token, user)) {
                    val authorities = listOf(SimpleGrantedAuthority("ROLE_${user.role.name}"))
                    val auth = UsernamePasswordAuthenticationToken(user, null, authorities)
                    SecurityContextHolder.getContext().authentication = auth
                }
            } catch (ex: Exception) {
                (response as HttpServletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token")
                return
            }
        }

        chain.doFilter(request, response)
    }
}
