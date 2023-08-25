package com.example.myhouse.ui.screens.camera

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myhouse.ui.components.AdditionalFeaturesButton
import com.example.myhouse.ui.components.CameraBanner
import com.example.myhouse.ui.components.MyHouseSwipeableCard
import com.example.myhouse.ui.theme.GoldenrodYellow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CameraCard(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    name: String,
    image: String?,
    isShield: Boolean = false,
    onSetFavorite: () -> Unit,
) {
    val swipeableState = rememberSwipeableState(
        initialValue = 0,
        confirmStateChange = {
            if (it == 1) onSetFavorite()
            false
        },
    )

    MyHouseSwipeableCard(
        modifier = modifier,
        swipeableState = swipeableState,
        background = {
            AdditionalFeaturesButton(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                icon = if (isFavorite) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                onClick = { /*TODO*/ },
                color = GoldenrodYellow,
            )
        },
        halfContent = {
            Column {
                CameraBanner(
                    image = image,
                    isFavorite = isFavorite,
                    isRecorded = true,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 26.dp,
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier,
                        text = name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    if (isShield) {
                        Icon(
                            modifier = Modifier
                                .size(30.dp),
                            imageVector = Icons.Outlined.Shield,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            }
        }
    )
}
