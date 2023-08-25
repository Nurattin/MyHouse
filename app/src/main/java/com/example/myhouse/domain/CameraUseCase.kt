package com.example.myhouse.domain

import com.example.myhouse.data.network.MyHouseRepository
import javax.inject.Inject

class CameraUseCase @Inject constructor(
    private val repo: MyHouseRepository
) {
    operator fun invoke() = repo.getCameras()
}
