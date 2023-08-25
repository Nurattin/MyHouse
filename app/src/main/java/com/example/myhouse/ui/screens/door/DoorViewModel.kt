package com.example.myhouse.ui.screens.door

import androidx.lifecycle.ViewModel
import com.example.myhouse.domain.DoorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoorViewModel @Inject constructor(
    private val getDoorUseCase: DoorUseCase,
) : ViewModel() {

}
