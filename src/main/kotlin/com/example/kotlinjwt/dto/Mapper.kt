package com.example.kotlinjwt.dto

import com.example.kotlinjwt.model.Item

fun Item.toDto(): ItemDto {
    return ItemDto(id, name, count, note)
}