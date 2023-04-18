package com.example.kotlinjwtjpa.controller

import com.example.kotlinjwtjpa.config.toUser
import com.example.kotlinjwtjpa.dto.*
import com.example.kotlinjwtjpa.model.Item
import com.example.kotlinjwtjpa.service.ItemService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/items")
class ItemController(
    private val itemService: ItemService,
) {
    @GetMapping
    fun getItems(authentication: Authentication): List<ItemDto> {
        val authUser = authentication.toUser()

        return itemService.findByUser(authUser).map { item -> item.toDto() }
    }

    @PostMapping
    fun createItem(authentication: Authentication, @RequestBody payload: CreateItemDto) {
        val authUser = authentication.toUser()

        if (itemService.existsByNameAndUser(payload.name, authUser)) {
            throw ApiException(409, "Item name already exists")
        }

        val item = Item(
            user = authUser,
            name = payload.name,
            count = payload.count,
            note = payload.note,
        )

        itemService.save(item)
    }

    @PutMapping
    fun updateItem(authentication: Authentication, @RequestBody payload: UpdateItemDto) {
        val authUser = authentication.toUser()

        val item = itemService.findById(payload.id) ?: throw ApiException(404, "Item not found")
        if (item.user.id != authUser.id) {
            throw ApiException(403, "Not your item")
        }

        val existingItem = itemService.findByNameAndUser(payload.name, authUser)
        if (existingItem != null && existingItem.id != payload.id) {
            throw ApiException(409, "Item name already exists")
        }

        item.name = payload.name
        item.count = payload.count
        item.note = payload.note

        itemService.save(item)
    }

    @DeleteMapping
    fun deleteItem(authentication: Authentication, @RequestParam itemId: Long) {
        val authUser = authentication.toUser()
        val item = itemService.findById(itemId) ?: throw ApiException(404, "Item not found")
        if (item.user.id != authUser.id) {
            throw ApiException(403, "Not your item")
        }

        itemService.delete(item)
    }
}