package com.example.myhouse.ui.screens.camera

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myhouse.ui.base.MyHouseScreens

@Composable
fun CameraScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: CameraViewModel = viewModel()
    val uiState by viewModel.cameraUiState.collectAsStateWithLifecycle()

    CameraScreenContent(
        modifier = modifier,
        uiState = uiState,
    )
}

@Composable
private fun CameraScreenContent(
    modifier: Modifier = Modifier,
    uiState: CameraUiState,
) {
    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (!uiState.error.isNullOrEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = uiState.error,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(all = 21.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(
                items = uiState.cameraList,
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
                )
            }
        }
    }
}
