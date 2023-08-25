package com.example.myhouse.ui.screens.camera

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
) {
    val swipeableState = rememberSwipeableState(
        initialValue = 0,
        confirmStateChange = { false },
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
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
            )
            {
                CameraBanner(
                    image = image,
                    isFavorite = isFavorite,
                    isRecorded = true,
                )

                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp,
                            vertical = 26.dp,
                        ),
                    text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    )
}
