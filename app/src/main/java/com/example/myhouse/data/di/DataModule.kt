package com.example.myhouse.data.di

import com.example.myhouse.data.local.MyHouseMongoRepository
import com.example.myhouse.data.local.MyHouseMongoRepositoryImpl
import com.example.myhouse.data.local.model.CameraEntity
import com.example.myhouse.data.local.model.DoorEntity
import com.example.myhouse.data.network.MyHouseApi
import com.example.myhouse.data.network.MyHouseApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                DoorEntity::class,
                CameraEntity::class,
            ),
        )
            .schemaVersion(1)
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }

    @Singleton
    @Provides
    fun provideMongoRepository(
        real: Realm,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): MyHouseMongoRepository {
        return MyHouseMongoRepositoryImpl(
            ioDispatcher = ioDispatcher,
            realm = real,
        )
    }

    @Provides
    @Singleton
    fun provideApi(): MyHouseApi {
        return MyHouseApiImpl(
            api = HttpClient(Android) {
                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                            coerceInputValues = true
                        }
                    )
                }

                install(HttpTimeout) {
                    requestTimeoutMillis = 15000L
                    connectTimeoutMillis = 15000L
                    socketTimeoutMillis = 15000L
                }
            }
        )
    }
}
