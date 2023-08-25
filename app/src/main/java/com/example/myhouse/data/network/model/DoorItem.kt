package com.example.myhouse.data.network.model


import androidx.annotation.Keep
import com.example.myhouse.domain.model.Door
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class DoorItem(
    @SerialName("data")
    val `data`: List<DoorDto>,
    @SerialName("success")
    val success: Boolean
) {
    @Keep
    @Serializable
    data class DoorDto(
        @SerialName("favorites")
        val favorites: Boolean,
        @SerialName("snapshot")
        val snapshot: String? = null,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("room")
        val room: String?,
    )
}

fun DoorItem.DoorDto.toDoor() = Door(
    name = name,
    room = room,
    id = id,
    snapshot = snapshot,
    favorites = favorites,
)