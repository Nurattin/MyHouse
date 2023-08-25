package com.example.myhouse.data.network

import com.example.myhouse.domain.model.Camera
import com.example.myhouse.domain.model.Door
import kotlinx.coroutines.flow.Flow

interface MyHouseRepository {
    fun getCameras(): Flow<List<Camera>>

    fun getDoors(): Flow<List<Door>>
}
