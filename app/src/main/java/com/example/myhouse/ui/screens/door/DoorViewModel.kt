package com.example.myhouse.ui.screens.door

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhouse.data.network.util.collectAsResult
import com.example.myhouse.domain.DoorUseCase
import com.example.myhouse.domain.model.Door
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
class DoorViewModel @Inject constructor(
    private val getDoorUseCase: DoorUseCase,
) : ViewModel() {
    private val _doorUiState = MutableStateFlow(DoorUiState())
    val doorUiState = _doorUiState.asStateFlow()

    init {
        getCameraList()
    }

    fun getCameraList() {
        viewModelScope.launch {
            getDoorUseCase().collectAsResult(
                onSuccess = { doorList ->
                    _doorUiState.update { currentState ->
                        currentState.copy(
                            doorList = doorList.toPersistentList(),
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
    val doorList: PersistentList<Door> = persistentListOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
)