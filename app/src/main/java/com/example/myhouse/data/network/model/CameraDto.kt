package com.example.myhouse.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep
import com.example.myhouse.data.network.model.CameraItem.Data.CameraDto
import com.example.myhouse.domain.model.Camera

@Keep
@Serializable
data class CameraItem(
    @SerialName("data")
    val `data`: Data,
    @SerialName("success")
    val success: Boolean
) {
    @Keep
    @Serializable
    data class Data(
        @SerialName("cameras")
        val cameraDtos: List<CameraDto>,
        @SerialName("room")
        val room: List<String>
    ) {
        @Keep
        @Serializable
        data class CameraDto(
            @SerialName("favorites")
            val favorites: Boolean,
            @SerialName("id")
            val id: Int,
            @SerialName("name")
            val name: String,
            @SerialName("rec")
            val rec: Boolean,
            @SerialName("room")
            val room: String?,
            @SerialName("snapshot")
            val snapshot: String
        )
    }
}

fun CameraDto.toCamera() = Camera(
    name = name,
    snapshot = snapshot,
    room = room,
    id = id,
    favorites = favorites,
    rec = rec,
)
