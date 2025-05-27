package com.example.edu_challenge.config

import com.example.edu_challenge.security.JwtFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableMethodSecurity // ← позволяет использовать @PreAuthorize в контроллерах
class SecurityConfig(
    private val jwtFilter: JwtFilter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // отключаем CSRF (актуально для REST API)
            .authorizeHttpRequests { auth ->
                auth
                    // разрешаем login и register
                    .requestMatchers("/api/users/login", "/api/users/register").permitAll()
                    // всё остальное — только для аутентифицированных
                    .anyRequest().authenticated()
            }
            // добавляем фильтр JWT перед стандартной аутентификацией
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}


