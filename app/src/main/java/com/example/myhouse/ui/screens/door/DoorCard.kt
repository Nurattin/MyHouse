package com.example.myhouse.ui.screens.door

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.myhouse.ui.components.AdditionalFeaturesButton
import com.example.myhouse.ui.components.CameraBanner
import com.example.myhouse.ui.components.MyHouseSwipeableCard
import com.example.myhouse.ui.theme.GoldenrodYellow
import com.example.myhouse.ui.theme.TealBlue
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoorCard(
    modifier: Modifier = Modifier,
    image: String?,
    isFavorite: Boolean,
    name: String,
) {
    var canChangeText by remember {
        mutableStateOf(false)
    }
    val focusRequest = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()
    val swipeableState = rememberSwipeableState(
        initialValue = 0,
    )

    MyHouseSwipeableCard(
        modifier = modifier,
        swipeableState = swipeableState,
        background = {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AdditionalFeaturesButton(
                    icon = Icons.Rounded.Edit,
                    onClick = {
                        canChangeText = true
                        coroutineScope.launch {
                            swipeableState.animateTo(0)
                            focusRequest.requestFocus()
                        }
                    },
                    color = TealBlue,
                )
                AdditionalFeaturesButton(
                    icon = if (isFavorite) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                    onClick = { /*TODO*/ },
                    color = GoldenrodYellow,
                )
            }
        },
        halfContent = {
            if (!image.isNullOrBlank()) {
                CameraBanner(
                    image = image,
                    isFavorite = isFavorite,
                    isRecorded = false,
                )
            }
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
                BasicTextField(
                    modifier = Modifier
                        .focusRequester(focusRequest)
                        .onFocusChanged {
                            if (!it.isFocused) {
                                canChangeText = false
                            }
                        },
                    value = name,
                    onValueChange = {},
                    enabled = canChangeText,
                    textStyle = MaterialTheme.typography.bodyLarge
                        .copy(color = MaterialTheme.colorScheme.onSurface),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            canChangeText = false
                        }
                    )
                )
                Icon(
                    modifier = Modifier
                        .size(30.dp),
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = null,
                    tint = TealBlue,
                )
            }
        }
    )
}
