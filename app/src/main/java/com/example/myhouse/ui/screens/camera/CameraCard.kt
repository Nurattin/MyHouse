package com.example.myhouse.ui.screens.camera

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EmergencyRecording
import androidx.compose.material.icons.rounded.PlayCircleOutline
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myhouse.R
import com.example.myhouse.ui.theme.GoldenrodYellowDark
import com.example.myhouse.ui.theme.GoldenrodYellowLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraCard(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    name: String,
    image: String,
    confirmValueChange: () -> Unit,
) {
    val swipeState = rememberDismissState(
        confirmValueChange = {
            when (it) {
                DismissValue.DismissedToEnd -> {
                    confirmValueChange()
                    false
                }

                else -> {
                    false
                }
            }
        },
        positionalThreshold = { 0.3f }
    )
    SwipeToDismiss(
        modifier = modifier,
        state = swipeState,
        directions = setOf(DismissDirection.EndToStart),
        background = {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = CircleShape,
                        )
                        .padding(5.dp),
                ) {
                    Icon(
                        modifier = Modifier,
                        contentDescription = null,
                        imageVector = if (isFavorite) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                        tint = if (isSystemInDarkTheme()) GoldenrodYellowDark else GoldenrodYellowLight,
                    )
                }
            }
        },
        dismissContent = {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp,
                ),
            )
            {
                CameraImageBanner(
                    image = image,
                    isFavorite = isFavorite,
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

@Composable
private fun CameraImageBanner(
    modifier: Modifier = Modifier,
    image: String,
    isFavorite: Boolean,
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(343 / 205f),
            model = image,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.place_holder),
            error = painterResource(id = R.drawable.place_holder),
            contentScale = ContentScale.Crop,
        )
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .size(60.dp),
            imageVector = Icons.Rounded.PlayCircleOutline,
            contentDescription = null,
            tint = Color.White,
        )
        Icon(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(5.dp),
            imageVector = Icons.Rounded.EmergencyRecording,
            contentDescription = null,
            tint = Color.Red,
        )
        if (isFavorite) {
            Icon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(5.dp),
                contentDescription = null,
                imageVector = Icons.Rounded.Star,
                tint = if (isSystemInDarkTheme()) GoldenrodYellowDark else GoldenrodYellowLight,
            )
        }
    }
}
