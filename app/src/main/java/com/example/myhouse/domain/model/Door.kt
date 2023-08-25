package com.example.myhouse.domain.model

data class Door(
    val name: String,
    val room: String?,
    val id: Int,
    val snapshot: String?,
    val favorites: Boolean,
)
