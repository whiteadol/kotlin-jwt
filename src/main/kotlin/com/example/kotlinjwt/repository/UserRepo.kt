package com.example.kotlinjwtjpa.repository

import com.example.kotlinjwtjpa.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepo : CrudRepository<User, Long> {
    fun findByName(name: String): User?
    fun existsByName(name: String): Boolean
}