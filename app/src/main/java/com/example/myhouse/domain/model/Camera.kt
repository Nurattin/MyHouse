package com.example.myhouse.domain.model

import com.example.myhouse.data.local.model.CameraEntity

data class Camera(
    val name: String,
    val snapshot: String,
    val room: String?,
    val id: Int,
    val favorites: Boolean,
    val rec: Boolean
)

fun Camera.toCameraEntity() = CameraEntity().apply {
    _id = id
    name = this@toCameraEntity.name
    snapshot = this@toCameraEntity.snapshot
    room = this@toCameraEntity.room
    favorites = this@toCameraEntity.favorites
    rec = this@toCameraEntity.rec
}
