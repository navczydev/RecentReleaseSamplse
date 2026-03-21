package com.example.recentreleasesamplse

/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PlainTooltipSample() {
    TooltipBox(
        positionProvider =
            TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Above),
        tooltip = { PlainTooltip { Text("Add to favorites") } },
        state = rememberTooltipState(),
    ) {
        IconButton(onClick = { /* Icon button's click event */ }) {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Localized Description")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PlainTooltipWithManualInvocationSample() {
    val tooltipState = rememberTooltipState()
    val scope = rememberCoroutineScope()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TooltipBox(
            positionProvider =
                TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Above),
            tooltip = { PlainTooltip { Text("Add to list") } },
            state = tooltipState,
        ) {
            IconButton(onClick = { /* Icon button's click event */ }) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Localized Description",
                )
            }
        }
        Spacer(Modifier.requiredHeight(30.dp))
        OutlinedButton(onClick = { scope.launch { tooltipState.show() } }) {
            Text("Display tooltip")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PlainTooltipWithCaret() {
    TooltipBox(
        positionProvider =
            TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Above),
        tooltip = {
            PlainTooltip(caretShape = TooltipDefaults.caretShape()) { Text("Add to favorites") }
        },
        state = rememberTooltipState(),
    ) {
        IconButton(onClick = { /* Icon button's click event */ }) {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Localized Description")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PlainTooltipWithCaretBelowAnchor() {
    TooltipBox(
        positionProvider =
            TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Below),
        tooltip = {
            PlainTooltip(caretShape = TooltipDefaults.caretShape()) { Text("Add to favorites") }
        },
        state = rememberTooltipState(),
    ) {
        IconButton(onClick = { /* Icon button's click event */ }) {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Localized Description")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PlainTooltipWithCaretLeftOfAnchor() {
    TooltipBox(
        positionProvider =
            TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Left),
        tooltip = {
            PlainTooltip(caretShape = TooltipDefaults.caretShape()) { Text("Add to favorites") }
        },
        state = rememberTooltipState(),
    ) {
        IconButton(onClick = { /* Icon button's click event */ }) {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Localized Description")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlainTooltipWithCaretRightOfAnchor() {
    TooltipBox(
        positionProvider =
            TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Above),
        tooltip = {
            PlainTooltip(caretShape = TooltipDefaults.caretShape()) { Text("Add to favorites") }
        },
        state = rememberTooltipState(),
    ) {
        IconButton(onClick = { /* Icon button's click event */ }) {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Localized Description")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlainTooltipWithCaretStartOfAnchor() {
    TooltipBox(
        positionProvider =
            TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Start),
        tooltip = {
            PlainTooltip(caretShape = TooltipDefaults.caretShape()) { Text("Add to favorites") }
        },
        state = rememberTooltipState(),
    ) {
        IconButton(onClick = { /* Icon button's click event */ }) {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Localized Description")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PlainTooltipWithCaretEndOfAnchor() {
    TooltipBox(
        positionProvider =
            TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.End),
        tooltip = {
            PlainTooltip(caretShape = TooltipDefaults.caretShape()) { Text("Add to favorites") }
        },
        state = rememberTooltipState(),
    ) {
        IconButton(onClick = { /* Icon button's click event */ }) {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Localized Description")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PlainTooltipWithCustomCaret() {
    TooltipBox(
        positionProvider =
            TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Above),
        tooltip = {
            PlainTooltip(caretShape = TooltipDefaults.caretShape(DpSize(24.dp, 12.dp))) {
                Text("Add to favorites")
            }
        },
        state = rememberTooltipState(),
    ) {
        IconButton(onClick = { /* Icon button's click event */ }) {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Localized Description")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RichTooltipSample() {
    val tooltipState = rememberTooltipState(isPersistent = true)
    val scope = rememberCoroutineScope()
    TooltipBox(
        positionProvider =
            TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Above),
        tooltip = {
            RichTooltip(
                title = { Text(richTooltipSubheadText) },
                action = {
                    TextButton(onClick = { scope.launch { tooltipState.dismiss() } }) {
                        Text(richTooltipActionText)
                    }
                },
            ) {
                Text(richTooltipText)
            }
        },
        hasAction = true,
        state = tooltipState,
    ) {
        IconButton(onClick = { /* Icon button's click event */ }) {
            Icon(imageVector = Icons.Filled.Info, contentDescription = "Localized Description")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RichTooltipWithManualInvocationSample() {
    val tooltipState = rememberTooltipState(isPersistent = true)
    val scope = rememberCoroutineScope()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TooltipBox(
            positionProvider =
                TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Below),
            tooltip = {
                RichTooltip(
                    title = { Text(richTooltipSubheadText) },
                    action = {
                        TextButton(onClick = { scope.launch { tooltipState.dismiss() } }) {
                            Text(richTooltipActionText)
                        }
                    },
                    caretShape = TooltipDefaults.caretShape(),
                ) {
                    Text(richTooltipText)
                }
            },
            hasAction = true,
            state = tooltipState,
        ) {
            Column {
                Text("Add to favorites")
                Spacer(Modifier.size(80.dp))
                IconButton(onClick = { /* Icon button's click event */ }) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Localized Description"
                    )
                }
                Spacer(Modifier.size(80.dp))
                Text("Add to favorites")
            }
        }
        Spacer(Modifier.requiredHeight(30.dp))
        OutlinedButton(onClick = { scope.launch { tooltipState.show() } }) {
            Text("Display tooltip")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RichTooltipWithCaretSample() {
    val tooltipState = rememberTooltipState(isPersistent = true)
    val scope = rememberCoroutineScope()
    TooltipBox(
        positionProvider =
            TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Above),
        tooltip = {
            RichTooltip(
                title = { Text(richTooltipSubheadText) },
                action = {
                    TextButton(onClick = { scope.launch { tooltipState.dismiss() } }) {
                        Text(richTooltipActionText)
                    }
                },
                caretShape = TooltipDefaults.caretShape(),
            ) {
                Text(richTooltipText)
            }
        },
        hasAction = true,
        state = tooltipState,
    ) {
        IconButton(onClick = { /* Icon button's click event */ }) {
            Icon(imageVector = Icons.Filled.Info, contentDescription = "Localized Description")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RichTooltipWithCustomCaretSample() {
    val tooltipState = rememberTooltipState(isPersistent = true)
    val scope = rememberCoroutineScope()
    TooltipBox(
        positionProvider =
            TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Above),
        tooltip = {
            RichTooltip(
                title = { Text(richTooltipSubheadText) },
                action = {
                    TextButton(onClick = { scope.launch { tooltipState.dismiss() } }) {
                        Text(richTooltipActionText)
                    }
                },
                caretShape = TooltipDefaults.caretShape(DpSize(32.dp, 16.dp)),
            ) {
                Text(richTooltipText)
            }
        },
        hasAction = true,
        state = tooltipState,
    ) {
        IconButton(onClick = { /* Icon button's click event */ }) {
            Icon(imageVector = Icons.Filled.Info, contentDescription = "Localized Description")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicPositionTooltipSample() {
    val tooltipState = rememberTooltipState()

    var anchorPosition by remember { mutableStateOf(Offset.Zero) }
    var anchorSize by remember { mutableStateOf(IntSize.Zero) }

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    // Example tooltip size guess in pixels (adjust as needed)
    val tooltipHeightDp = 40.dp
    val tooltipWidthDp = 120.dp

    val tooltipHeightPx = with(density) { tooltipHeightDp.toPx() }
    val tooltipWidthPx = with(density) { tooltipWidthDp.toPx() }

    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }
    val screenWidthPx = with(density) { configuration.screenWidthDp.dp.toPx() }

    // Calculate tooltip anchor position dynamically based on available space
    val tooltipAnchorPosition = remember(anchorPosition, anchorSize) {
        when {
            // Enough space below the anchor
            (anchorPosition.y + anchorSize.height + tooltipHeightPx) < screenHeightPx -> TooltipAnchorPosition.Below
            // Otherwise, enough space above anchor
            (anchorPosition.y - tooltipHeightPx) > 0 -> TooltipAnchorPosition.Above
            // Otherwise, check horizontal space (right side)
            (anchorPosition.x + anchorSize.width + tooltipWidthPx) < screenWidthPx -> TooltipAnchorPosition.End
            else -> TooltipAnchorPosition.Start
        }
    }

        TooltipBox(
            modifier = Modifier.onGloballyPositioned { coordinates ->
                anchorPosition = coordinates.positionInWindow()
                anchorSize = coordinates.size
            },
            state = tooltipState,
            positionProvider = TooltipDefaults.rememberTooltipPositionProvider(tooltipAnchorPosition),
            tooltip = {
                PlainTooltip(caretShape = TooltipDefaults.caretShape()) {
                    Text("Dynamic Tooltip Position")
                }
            }
        ) {
            val scope = rememberCoroutineScope()
            IconButton(onClick = {
                scope.launch {
                    tooltipState.show()
                }
            }) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite")
            }
        }
}


const val richTooltipSubheadText = "Permissions"
const val richTooltipText =
    "Configure permissions for selected service accounts. " +
            "You can add and remove service account members and assign roles to them. " +
            "Visit go/permissions for details"
const val richTooltipActionText = "Request Access"