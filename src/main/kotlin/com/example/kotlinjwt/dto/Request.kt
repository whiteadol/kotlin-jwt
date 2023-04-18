package com.example.kotlinjwtjpa.dto

data class LoginDto(
    val name: String,
    val password: String,
)

data class RegisterDto(
    val name: String,
    val password: String,
)

data class CreateItemDto(
    val name: String,
    val count: Int,
    val note: String?,
)

data class UpdateItemDto(
    val id: Long,
    val name: String,
    val count: Int,
    val note: String?,
)
