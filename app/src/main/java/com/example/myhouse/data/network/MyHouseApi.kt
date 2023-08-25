package com.example.myhouse.data.network

import com.example.myhouse.data.network.model.CameraDto
import com.example.myhouse.data.network.model.DoorDto

interface MyHouseApi {
    suspend fun getCameras(): List<CameraDto>
    suspend fun getDoors(): List<DoorDto>
}

