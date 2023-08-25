package com.example.myhouse.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val TealBlueLight = Color(0xFF0087C7)
val TealBlueDark = Color(0xFF8dcdff)

val GoldenrodYellowLight = Color(0xFFAC8D00)
val GoldenrodYellowDark = Color(0xFFe6c43b)

val GoldenrodYellow: Color
    @Composable
    get() = if (isSystemInDarkTheme()) GoldenrodYellowDark else GoldenrodYellowLight

val TealBlue: Color
    @Composable
    get() = if (isSystemInDarkTheme()) TealBlueDark else TealBlueLight
