package com.example.myhouse.data.network

import com.example.myhouse.data.di.IoDispatcher
import com.example.myhouse.data.network.model.CameraDto
import com.example.myhouse.data.network.model.DoorDto
import com.example.myhouse.data.network.model.toCamera
import com.example.myhouse.data.network.model.toDoor
import com.example.myhouse.domain.model.Camera
import com.example.myhouse.domain.model.Door
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MyHouseRepositoryImpl @Inject constructor(
    private val api: MyHouseApi,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
) : MyHouseRepository {
    override fun getCameras(): Flow<List<Camera>> = flow {
        emit(api.getCameras().map(CameraDto::toCamera))
    }.flowOn(ioDispatcher)

    override fun getDoors(): Flow<List<Door>> = flow {
        emit(api.getDoors().map(DoorDto::toDoor))
    }.flowOn(ioDispatcher)
}
