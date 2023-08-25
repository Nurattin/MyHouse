package com.example.myhouse.data.network

import io.ktor.client.HttpClient

class MyHouseApiImpl(
    private val api: HttpClient
) : MyHouseApi {

}