package com.example.myhouse.ui.screens.camera

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CameraScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(all = 21.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(
            count = 10,
        ) {
            CameraCard(
                modifier = Modifier,
                isFavorite = false,
                image = "https://cdn.forbes.ru/forbes-static/1040x669/new/2022/04/445-6269016c556dc.jpg",
                name = "Камера1",
                confirmValueChange = {},
            )
        }
    }
}