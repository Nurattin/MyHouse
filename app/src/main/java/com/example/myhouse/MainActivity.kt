package com.example.myhouse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.example.myhouse.ui.base.MyHouseScreens
import com.example.myhouse.ui.base.rememberMyHouseAppState
import com.example.myhouse.ui.components.MyHouseTopAppBar
import com.example.myhouse.ui.screens.camera.CameraScreen
import com.example.myhouse.ui.screens.door.DoorScreen
import com.example.myhouse.ui.theme.MyHouseTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        setContent {
            MyHouseTheme {
                val systemUiController = rememberSystemUiController()
                val isNightMode = isSystemInDarkTheme()

                val appState = rememberMyHouseAppState()

                // Не использовал LaunchedEffect потому что не нужен coroutine scope
                DisposableEffect(systemUiController) {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isNightMode,
                    )
                    systemUiController.setNavigationBarColor(Color.Black)

                    onDispose { }
                }

                Scaffold(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxSize(),
                    topBar = {
                        MyHouseTopAppBar(
                            selectedTabIndex = appState.currentPage,
                            onClickTab = appState::scrollToPage,
                        )
                    }
                ) { innerPadding ->

                    HorizontalPager(
                        modifier = Modifier
                            .padding(innerPadding),
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
            }
        }
    }
}
