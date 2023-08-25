package com.example.myhouse.ui.screens.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhouse.data.network.util.collectAsResult
import com.example.myhouse.domain.CameraUseCase
import com.example.myhouse.domain.model.Camera
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val getCameraUseCase: CameraUseCase
) : ViewModel() {
    private val _cameraUiState = MutableStateFlow(CameraUiState())
    val cameraUiState = _cameraUiState.asStateFlow()

    init {
        getCameraList()
    }

    fun getCameraList(refresh: Boolean = false) {
        viewModelScope.launch {
            getCameraUseCase(refresh).collectAsResult(
                onSuccess = { cameraList ->
                    _cameraUiState.update { currentState ->
                        currentState.copy(
                            cameraList = cameraList.toPersistentList(),
                            isLoading = false,
                            error = null,
                        )
                    }
                },
                onError = { ex ->
                    _cameraUiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            error = ex.toString(),
                        )
                    }
                },
                onLoading = {
                    _cameraUiState.update { currentState ->
                        currentState.copy(
                            isLoading = true,
                            error = null,
                        )
                    }
                },
            )
        }
    }
}

data class CameraUiState(
    val cameraList: PersistentList<Camera> = persistentListOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
