package com.example.kotlinjwtjpa.dto

import com.example.kotlinjwtjpa.model.Item

fun Item.toDto(): ItemDto {
    return ItemDto(id, name, count, note)
}