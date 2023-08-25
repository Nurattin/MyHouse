package com.example.myhouse.data.local

import com.example.myhouse.data.local.model.CameraEntity
import com.example.myhouse.data.local.model.DoorEntity

interface MyHouseMongoRepository {

    suspend fun changeDoorName(id: Int, newName: String)

    suspend fun getDoors(): List<DoorEntity>

    suspend fun saveDoors(listDoor: List<DoorEntity>)

    suspend fun getCameras(): List<CameraEntity>

    suspend fun saveCameras(listCamera: List<CameraEntity>)
}
