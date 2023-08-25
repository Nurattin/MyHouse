package com.example.myhouse.ui.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MyHouseSwipeableCard(
    modifier: Modifier = Modifier,
    background: @Composable BoxScope.() -> Unit,
    halfContent: @Composable () -> Unit,
    swipeableState: SwipeableState<Int>,
) {
    val coroutineScope = rememberCoroutineScope()
    val halfScreenWidth = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.toPx() / 4
    }
    val anchors = mapOf(-halfScreenWidth to 1, 0f to 0)

    Box(
        modifier = modifier
    ) {
        background()
        Card(
            modifier = modifier
                .offset {
                    IntOffset(
                        x = swipeableState.offset.value.roundToInt(),
                        y = 0
                    )
                }
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(.4f) },
                    orientation = Orientation.Horizontal,
                )
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
            ),
            onClick = {
                coroutineScope.launch {
                    swipeableState.animateTo(0)
                }
            }
        )
        {
            halfContent()
        }
    }
}
