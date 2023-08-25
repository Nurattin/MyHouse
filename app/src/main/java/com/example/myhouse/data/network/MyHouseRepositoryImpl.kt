package com.example.myhouse.data.network

import com.example.myhouse.data.di.IoDispatcher
import com.example.myhouse.data.local.MyHouseMongoRepository
import com.example.myhouse.data.local.model.CameraEntity
import com.example.myhouse.data.local.model.DoorEntity
import com.example.myhouse.data.local.model.toCamera
import com.example.myhouse.data.local.model.toDoor
import com.example.myhouse.data.network.model.CameraItem.Data.CameraDto
import com.example.myhouse.data.network.model.DoorItem.DoorDto
import com.example.myhouse.data.network.model.toCamera
import com.example.myhouse.data.network.model.toDoor
import com.example.myhouse.domain.model.Camera
import com.example.myhouse.domain.model.Door
import com.example.myhouse.domain.model.toCameraEntity
import com.example.myhouse.domain.model.toDoorEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MyHouseRepositoryImpl @Inject constructor(
    private val api: MyHouseApi,
    private val db: MyHouseMongoRepository,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
) : MyHouseRepository {
    override fun getCameras(refresh: Boolean): Flow<List<Camera>> = flow {
        val result = if (refresh) {
            val networkData = fetchAndSaveCamerasFromNetwork()
            networkData
        } else {
            val localData = fetchCamerasFromLocal()
            localData.ifEmpty {
                val networkData = fetchAndSaveCamerasFromNetwork()
                networkData
            }
        }

        emit(result)
    }.flowOn(ioDispatcher)

    private suspend fun fetchAndSaveCamerasFromNetwork(): List<Camera> {
        val networkData = api.getCameras().data.cameraDtos.map(CameraDto::toCamera)
        db.saveCameras(networkData.map(Camera::toCameraEntity))
        return networkData
    }

    private suspend fun fetchCamerasFromLocal(): List<Camera> {
        return db.getCameras().map(CameraEntity::toCamera)
    }

    override fun getDoors(refresh: Boolean): Flow<List<Door>> = flow {
        val result = if (refresh) {
            val networkData = fetchAndSaveDoorsFromNetwork()
            networkData
        } else {
            val localData = fetchDoorsFromLocal()
            localData.ifEmpty {
                val networkData = fetchAndSaveDoorsFromNetwork()
                networkData
            }
        }

        emit(result)
    }.flowOn(ioDispatcher)

    private suspend fun fetchAndSaveDoorsFromNetwork(): List<Door> {
        val networkData = api.getDoors().data.map(DoorDto::toDoor)
        db.saveDoors(networkData.map(Door::toDoorEntity))
        return networkData
    }

    private suspend fun fetchDoorsFromLocal(): List<Door> {
        return db.getDoors().map(DoorEntity::toDoor)
    }
}
