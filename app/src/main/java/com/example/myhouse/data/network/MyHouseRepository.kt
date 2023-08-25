package com.example.myhouse.data.network

import com.example.myhouse.domain.model.Camera
import com.example.myhouse.domain.model.Door
import kotlinx.coroutines.flow.Flow

interface MyHouseRepository {
    fun getCameras(refresh: Boolean): Flow<List<Camera>>

    fun getDoors(refresh: Boolean): Flow<List<Door>>
}
