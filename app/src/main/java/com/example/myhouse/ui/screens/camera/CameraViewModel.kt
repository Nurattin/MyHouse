package com.example.myhouse.ui.screens.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhouse.data.network.util.collectAsResult
import com.example.myhouse.domain.CameraUseCase
import com.example.myhouse.domain.model.Camera
import com.example.myhouse.util.mapNestedList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toPersistentMap
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

    fun setFavorite(doorId: Int) {
        _cameraUiState.update { currentState ->
            currentState.copy(
                cameraList = currentState.cameraList.mapNestedList { camera ->
                    camera.takeIf { it.id == doorId }?.copy(favorites = !camera.favorites) ?: camera
                }.toPersistentMap()
            )
        }
    }

    fun getCameraList(refresh: Boolean = false) {
        viewModelScope.launch {
            getCameraUseCase(refresh).collectAsResult(
                onSuccess = { cameraList ->
                    _cameraUiState.update { currentState ->
                        currentState.copy(
                            cameraList = cameraList.groupBy {
                                it.room ?: "Undefended"
                            }.toPersistentMap(),
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
    val cameraList: PersistentMap<String, List<Camera>> = persistentMapOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
