package com.example.myhouse.domain

import com.example.myhouse.data.network.MyHouseRepository
import javax.inject.Inject

class DoorUseCase @Inject constructor(
    private val repo: MyHouseRepository,
) {
    operator fun invoke(refresh: Boolean = false) = repo.getDoors(refresh)
}
