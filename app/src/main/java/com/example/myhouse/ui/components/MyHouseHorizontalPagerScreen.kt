package com.example.myhouse.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myhouse.ui.base.MyHouseAppState
import com.example.myhouse.ui.base.MyHouseScreens
import com.example.myhouse.ui.screens.camera.CameraScreen
import com.example.myhouse.ui.screens.door.DoorScreen

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun MyHouseHorizontalPagerScreen(
    modifier: Modifier = Modifier,
    appState: MyHouseAppState
) {
    HorizontalPager(
        modifier = modifier,
        state = appState.pagerState,
        key = {
            appState.listPages[it].name
        },
    ) { currentPage ->
        when (appState.listPages[currentPage]) {
            MyHouseScreens.Cameras -> {
                CameraScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            MyHouseScreens.Doors -> {
                DoorScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}