package com.example.myhouse.domain

import com.example.myhouse.data.local.MyHouseMongoRepository
import javax.inject.Inject

class DoorChangeNameUseCase @Inject constructor(
    private val db: MyHouseMongoRepository
) {
    suspend operator fun invoke(doorId: Int, newName: String) {
        db.changeDoorName(doorId, newName)
    }
}
