package com.example.myhouse.domain.model

import com.example.myhouse.data.local.model.DoorEntity

data class Door(
    val name: String,
    val room: String?,
    val id: Int,
    val snapshot: String?,
    val favorites: Boolean,
)

fun Door.toDoorEntity() = DoorEntity().apply {
    _id = id
    name = this@toDoorEntity.name
    snapshot = this@toDoorEntity.snapshot
    room = this@toDoorEntity.room
    favorites = this@toDoorEntity.favorites
}
