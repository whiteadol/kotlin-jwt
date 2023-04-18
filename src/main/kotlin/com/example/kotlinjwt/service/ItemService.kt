package com.example.kotlinjwt.service

import com.example.kotlinjwt.model.Item
import com.example.kotlinjwt.model.User
import com.example.kotlinjwt.repository.ItemRepo
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ItemService(
    private val itemRepo: ItemRepo
) {
    fun findById(id: Long): Item? {
        return itemRepo.findByIdOrNull(id)
    }

    fun findByUser(user: User): List<Item> {
        return itemRepo.findByUser(user)
    }

    fun findByNameAndUser(name: String, user: User): Item? {
        return itemRepo.findByNameAndUser(name, user)
    }

    fun existsByNameAndUser(name: String, user: User): Boolean {
        return itemRepo.existsByNameAndUser(name, user)
    }

    fun save(item: Item): Item {
        return itemRepo.save(item)
    }

    fun delete(item: Item) {
        return itemRepo.delete(item)
    }
}