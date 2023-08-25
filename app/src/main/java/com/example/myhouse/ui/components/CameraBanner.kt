package com.example.myhouse.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EmergencyRecording
import androidx.compose.material.icons.rounded.PlayCircleOutline
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myhouse.R
import com.example.myhouse.ui.theme.GoldenrodYellow

@Composable
fun CameraBanner(
    modifier: Modifier = Modifier,
    image: String?,
    isRecorded: Boolean,
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
        if (isRecorded) {
            Icon(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(5.dp),
                imageVector = Icons.Rounded.EmergencyRecording,
                contentDescription = null,
                tint = Color.Red,
            )
        }
        if (isFavorite) {
            Icon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(5.dp),
                contentDescription = null,
                imageVector = Icons.Rounded.Star,
                tint = GoldenrodYellow,
            )
        }
    }
}
