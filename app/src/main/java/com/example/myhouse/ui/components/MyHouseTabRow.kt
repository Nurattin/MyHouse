package com.example.myhouse.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.example.myhouse.ui.base.MyHouseScreens
import com.example.myhouse.ui.theme.TealBlueDark
import com.example.myhouse.ui.theme.TealBlueLight

@Composable
fun MyHouseTabRow(
    selectedTabIndex: Int,
    onClickTab: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = MaterialTheme.colorScheme.background,
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .clip(CircleShape),
                    color = if (isSystemInDarkTheme()) TealBlueDark else TealBlueLight
                )
            }
        },
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        MyHouseScreens.entries.fastForEachIndexed { index, page ->
            Tab(
                text = {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 14.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        text = stringResource(id = page.tab)
                    )
                },
                selected = selectedTabIndex == index,
                onClick = {
                    onClickTab(index)
                },
            )
        }
    }
}
