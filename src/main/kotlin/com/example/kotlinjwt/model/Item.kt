package com.example.kotlinjwtjpa.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator

@Entity
data class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq")
    @SequenceGenerator(name = "item_id_seq", allocationSize = 1)
    var id: Long = 0,

    @ManyToOne
    var user: User = User(),

    @Column
    var name: String = "",

    @Column
    var count: Int = 0,

    @Column
    var note: String? = null,
)