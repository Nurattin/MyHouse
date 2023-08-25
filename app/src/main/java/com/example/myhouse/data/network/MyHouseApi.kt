package com.example.myhouse.data.network

import com.example.myhouse.data.network.model.CameraItem
import com.example.myhouse.data.network.model.DoorItem

interface MyHouseApi {
    suspend fun getCameras(): CameraItem
    suspend fun getDoors(): DoorItem
}

