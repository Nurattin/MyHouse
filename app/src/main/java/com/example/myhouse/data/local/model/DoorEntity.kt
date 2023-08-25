package com.example.myhouse.data.local.model

import com.example.myhouse.domain.model.Door
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class DoorEntity : RealmObject {
    @PrimaryKey
    var _id: Int = -1
    var name: String = ""
    var room: String? = null
    var snapshot: String? = null
    var favorites: Boolean = false
}

fun DoorEntity.toDoor() = Door(
    id = _id,
    name = name,
    snapshot = snapshot,
    room = room,
    favorites = favorites,
)
