package com.example.kotlinjwtjpa.config

import com.example.kotlinjwtjpa.model.User
import org.springframework.security.core.Authentication

fun Authentication.toUser(): User {
    return principal as User
}