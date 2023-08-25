package com.example.myhouse.ui.screens.door

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DoorScreen(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(all = 21.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ){

        items(
            count = 10
        ) {
            DoorCard(
                image = null,
                isFavorite = true,
                name = "Хасбик",
            )
        }
    }
}
