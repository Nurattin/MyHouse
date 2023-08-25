package com.example.myhouse.data.network

import com.example.myhouse.data.network.model.CameraItem
import com.example.myhouse.data.network.model.DoorItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MyHouseApiImpl(
    private val api: HttpClient
) : MyHouseApi {
    override suspend fun getCameras(): CameraItem {
        return api.get(urlString = "http://cars.cprogroup.ru/api/rubetek/cameras/").body()
    }

    override suspend fun getDoors(): DoorItem {
        return api.get(urlString = "http://cars.cprogroup.ru/api/rubetek/doors/").body()
    }
}
