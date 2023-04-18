package com.example.kotlinjwt.repository

import com.example.kotlinjwt.model.Item
import com.example.kotlinjwt.model.User
import org.springframework.data.repository.CrudRepository

interface ItemRepo : CrudRepository<Item, Long> {
    fun findByUser(user: User): List<Item>
    fun findByNameAndUser(name: String, user: User): Item?
    fun existsByNameAndUser(name: String, user: User): Boolean
}