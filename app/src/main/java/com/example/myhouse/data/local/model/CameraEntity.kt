package com.example.myhouse.data.local.model

import com.example.myhouse.domain.model.Camera
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class CameraEntity : RealmObject {
    @PrimaryKey
    var _id: Int = -1
    var name: String = ""
    var snapshot: String = ""
    var room: String? = null
    var favorites: Boolean = false
    var rec: Boolean = false
}

fun CameraEntity.toCamera() = Camera(
    id = _id,
    name = name,
    snapshot = snapshot,
    rec = rec,
    room = room,
    favorites = favorites,
)
