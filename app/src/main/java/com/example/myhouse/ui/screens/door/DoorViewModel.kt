package com.example.myhouse.ui.screens.door

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhouse.data.network.util.collectAsResult
import com.example.myhouse.domain.DoorChangeNameUseCase
import com.example.myhouse.domain.DoorUseCase
import com.example.myhouse.domain.model.Door
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
class DoorViewModel @Inject constructor(
    private val getDoorUseCase: DoorUseCase,
    private val changeNameUseCase: DoorChangeNameUseCase,
) : ViewModel() {
    private val _doorUiState = MutableStateFlow(DoorUiState())
    val doorUiState = _doorUiState.asStateFlow()

    init {
        getDoorList()
    }

    fun changeDoorName(doorId: Int, newName: String) {
        _doorUiState.update { currentState ->
            currentState.copy(
                doorList = currentState.doorList.mapValues { doors ->
                    doors.value.map { door ->
                        door.takeIf { it.id == doorId }?.copy(name = newName) ?: door
                    }
                }.toPersistentMap()
            )
        }
        viewModelScope.launch {
            changeNameUseCase(doorId, newName)
        }
    }

    fun getDoorList(refresh: Boolean = false) {
        viewModelScope.launch {
            getDoorUseCase(refresh).collectAsResult(
                onSuccess = { doorList ->
                    _doorUiState.update { currentState ->
                        currentState.copy(
                            doorList = doorList.groupBy {
                                it.room ?: "Undefended"
                            }.toPersistentMap(),
                            isLoading = false,
                            error = null,
                        )
                    }
                },
                onError = { ex ->
                    _doorUiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            error = ex.toString(),
                        )
                    }
                },
                onLoading = {
                    _doorUiState.update { currentState ->
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

data class DoorUiState(
    val doorList: PersistentMap<String, List<Door>> = persistentMapOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
)