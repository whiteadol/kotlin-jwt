package com.example.kotlinjwt.config

import com.example.kotlinjwt.model.User
import org.springframework.security.core.Authentication

fun Authentication.toUser(): User {
    return principal as User
}