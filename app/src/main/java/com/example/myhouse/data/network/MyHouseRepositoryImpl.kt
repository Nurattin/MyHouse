package com.example.myhouse.data.network

import com.example.myhouse.data.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MyHouseRepositoryImpl @Inject constructor(
    val api: MyHouseApi,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
) : MyHouseRepository {

}
