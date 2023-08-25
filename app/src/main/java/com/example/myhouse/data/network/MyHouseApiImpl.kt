package com.example.myhouse.data.network

import com.example.myhouse.data.network.model.CameraDto
import com.example.myhouse.data.network.model.DoorDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MyHouseApiImpl(
    private val api: HttpClient
) : MyHouseApi {
    override suspend fun getCameras(): List<CameraDto> {
        return api.get(urlString = "http://cars.cprogroup.ru/api/rubetek/cameras/").body()
    }

    override suspend fun getDoors(): List<DoorDto> {
        return api.get(urlString = "http://cars.cprogroup.ru/api/rubetek/doors/").body()
    }
}
