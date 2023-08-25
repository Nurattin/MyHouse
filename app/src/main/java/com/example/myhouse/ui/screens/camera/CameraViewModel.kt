package com.example.myhouse.ui.screens.camera

import androidx.lifecycle.ViewModel
import com.example.myhouse.domain.CameraUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val getCameraUseCase: CameraUseCase
) : ViewModel() {

}
