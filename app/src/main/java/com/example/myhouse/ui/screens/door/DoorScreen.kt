package com.example.myhouse.ui.screens.door

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myhouse.ui.base.MyHouseScreens
import com.example.myhouse.ui.components.MyHouseError
import com.example.myhouse.ui.components.MyHouseLoading

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoorScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: DoorViewModel = viewModel()
    val uiState by viewModel.doorUiState.collectAsStateWithLifecycle()

    val pullState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = { viewModel.getDoorList(true) }
    )

    Box(
        modifier = modifier
            .pullRefresh(
                state = pullState,
            )
    ) {
        DoorScreenContent(
            modifier = Modifier,
            uiState = uiState,
            onDoorNameChange = viewModel::changeDoorName,
            onRefreshClick = viewModel::getDoorList,
            onSetFavorite = viewModel::setFavorite,
        )
        PullRefreshIndicator(
            refreshing = uiState.isLoading,
            state = pullState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun DoorScreenContent(
    modifier: Modifier = Modifier,
    uiState: DoorUiState,
    onDoorNameChange: (Int, String) -> Unit,
    onRefreshClick: () -> Unit,
    onSetFavorite: (Int) -> Unit,
) {
    if (uiState.isLoading) {
        MyHouseLoading()
    } else if (!uiState.error.isNullOrEmpty()) {
        MyHouseError(
            errorMessage = uiState.error,
            onRefreshClick = onRefreshClick,
        )
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(all = 21.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            uiState.doorList.forEach { (room, doors) ->
                item {
                    Text(
                        text = room,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                items(
                    items = doors,
                    key = { door ->
                        door.id
                    },
                    contentType = {
                        MyHouseScreens.Doors
                    }
                ) { door ->
                    DoorCard(
                        modifier = Modifier,
                        isFavorite = door.favorites,
                        image = door.snapshot,
                        name = door.name,
                        onNameChange = { newName ->
                            onDoorNameChange(door.id, newName)
                        },
                        onSetFavorite = {
                            onSetFavorite(door.id)
                        }
                    )
                }
            }
        }
    }
}
