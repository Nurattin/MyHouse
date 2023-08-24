package com.example.myhouse.ui.base

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.myhouse.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class MyHouseScreens(
    @StringRes val tab: Int,
) {
    Cameras(R.string.cameras),
    Doors(R.string.doors),
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun rememberMyHouseAppState(
    pagerState: PagerState = rememberPagerState(
        pageCount = MyHouseScreens.entries::size,
    ),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): MyHouseAppState {
    return remember(pagerState, coroutineScope) {
        MyHouseAppState(
            pagerState = pagerState,
            coroutineScope = coroutineScope,
        )
    }
}

@Stable
@OptIn(ExperimentalFoundationApi::class)
class MyHouseAppState constructor(
    val pagerState: PagerState,
    private val coroutineScope: CoroutineScope,
) {
    val listPages = MyHouseScreens.entries
    val currentPage: Int
        get() = pagerState.currentPage

    fun scrollToPage(page: Int) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(page)
        }
    }
}
