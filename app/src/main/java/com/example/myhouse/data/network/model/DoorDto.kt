package com.example.myhouse.data.network.model

import com.example.myhouse.domain.model.Door
import kotlinx.serialization.Serializable

@Serializable
data class DoorDto(
    val name: String,
    val room: String?,
    val id: Int,
    val favorites: Boolean,
)

fun DoorDto.toDoor() = Door(
    name = name,
    room = room,
    id = id,
    favorites = favorites,
)
