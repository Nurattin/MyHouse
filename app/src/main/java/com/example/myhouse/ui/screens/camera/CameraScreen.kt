package com.example.myhouse.ui.screens.camera

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
fun CameraScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: CameraViewModel = viewModel()
    val uiState by viewModel.cameraUiState.collectAsStateWithLifecycle()
    val pullState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = { viewModel.getCameraList(true) }
    )

    Box(
        modifier = modifier.pullRefresh(
            state = pullState,
        )
    ) {
        CameraScreenContent(
            modifier = Modifier,
            uiState = uiState,
            onRefreshClick = viewModel::getCameraList,
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
private fun CameraScreenContent(
    modifier: Modifier = Modifier,
    uiState: CameraUiState,
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
            uiState.cameraList.forEach { (room, cameraList) ->
                item {
                    Text(
                        text = room,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                items(
                    items = cameraList,
                    key = { camera ->
                        camera.id
                    },
                    contentType = {
                        MyHouseScreens.Cameras
                    }
                ) { camera ->
                    CameraCard(
                        modifier = Modifier,
                        isFavorite = camera.favorites,
                        image = camera.snapshot,
                        name = camera.name,
                        isShield = camera.rec,
                        onSetFavorite = {
                            onSetFavorite(camera.id)
                        }
                    )
                }
            }
        }
    }
}
