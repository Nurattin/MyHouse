package com.example.myhouse.data.network.model

import com.example.myhouse.domain.model.Camera
import kotlinx.serialization.Serializable

@Serializable
data class CameraDto(
    val name: String,
    val snapshot: String,
    val room: String?,
    val id: Int,
    val favorites: Boolean,
    val rec: Boolean,
)

fun CameraDto.toCamera() = Camera(
    name = name,
    snapshot = snapshot,
    room = room,
    id = id,
    favorites = favorites,
    rec = rec,
)
