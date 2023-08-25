package com.example.myhouse.data.local

import com.example.myhouse.data.di.IoDispatcher
import io.realm.kotlin.Realm
import kotlinx.coroutines.CoroutineDispatcher

class MyHouseMongoRepositoryImpl(
    private val realm: Realm,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
) : MyHouseMongoRepository {

}