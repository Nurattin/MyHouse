package com.example.myhouse.data.local

import com.example.myhouse.data.di.IoDispatcher
import com.example.myhouse.data.local.model.CameraEntity
import com.example.myhouse.data.local.model.DoorEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MyHouseMongoRepositoryImpl(
    private val realm: Realm,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
) : MyHouseMongoRepository {
    override suspend fun changeDoorName(id: Int, newName: String) {
        withContext(ioDispatcher) {
            realm.write {
                val queriedDoor = query<DoorEntity>(query = "_id = $0", id).first().find()
                queriedDoor?.name = newName
            }
        }
    }

    override suspend fun getDoors(): List<DoorEntity> {
        return realm.query<DoorEntity>().find().toList()
    }

    override suspend fun saveDoors(listDoor: List<DoorEntity>) {
        realm.write {
            listDoor.forEach {
                copyToRealm(
                    instance = it,
                    updatePolicy = UpdatePolicy.ALL,
                )
            }
        }
    }

    override suspend fun getCameras(): List<CameraEntity> {
        return realm.query<CameraEntity>().find().toList()
    }

    override suspend fun saveCameras(listCamera: List<CameraEntity>) {
        realm.write {
            listCamera.forEach {
                copyToRealm(
                    instance = it,
                    updatePolicy = UpdatePolicy.ALL,
                )
            }
        }
    }
}
