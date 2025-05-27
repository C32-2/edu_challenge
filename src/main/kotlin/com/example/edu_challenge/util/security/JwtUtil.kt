package com.example.edu_challenge.util.security

import com.example.edu_challenge.model.AppUser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtUtil(@Value("\${jwt.secret}") private val secret: String) {

    private val key = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(user: AppUser): String {
        val token: String = Jwts.builder()
            .setSubject(user.email)
            .claim("role", user.role.name)
            .setIssuedAt(Date())
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
        return token
    }

    fun extractUsername(token: String): String {
        return Jwts.parserBuilder().setSigningKey(key).build()
            .parseClaimsJws(token)
            .body
            .subject
    }
    fun isTokenValid(token: String, user: AppUser): Boolean {
        val username = extractUsername(token)
        return username == user.email
    }
}