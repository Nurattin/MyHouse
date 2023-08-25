package com.example.myhouse.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AdditionalFeaturesButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(.5f),
                shape = CircleShape,
            )
            .clickable(
                onClick = onClick,
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = rememberRipple(
                    color = Color.White,
                )
            )
            .padding(8.dp),
    ) {
        Icon(
            modifier = Modifier,
            contentDescription = null,
            imageVector = icon,
            tint = color
        )
    }
}
