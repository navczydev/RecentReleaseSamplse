package com.example.recentreleasesamplse


import android.text.Html
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.collapse
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.expand
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


private const val CARDS_NUMBER_WITHOUT_DECK = 1
private const val CARDS_STUBS_NUMBER_IN_STACKED_DECK = 2
private const val CARD_Y_OFFSET_IN_DECK_PX = 20
private const val CARD_SCALE_STEP_IN_DECK = 0.05f


@Composable
fun CardsDeck(
    cardYOffsetPX: Int,
    onBoxClicked: () -> Unit,
    body: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .clickable(role = Role.Button) { onBoxClicked() }
            // Blocks touch events to child, needed to block activityCard click
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    val event = awaitPointerEvent(PointerEventPass.Initial)
                    if (event.changes.any { it.pressed }) {
                        event.changes.forEach { it.consume() }
                        onBoxClicked()
                    }
                }
            }
            .semantics {
                contentDescription = body
                expand {
                    onBoxClicked()
                    true
                }
            }
    ) {
        Layout(
            content,
            modifier = modifier.clearAndSetSemantics {
                // Stops talkback from reading the first card when it's collapsed
            },
        ) { measurables, constraints ->

            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }
            val width =
                if (placeables.isNotEmpty()) {
                    placeables.last().width
                } else {
                    0
                }
            val height =
                if (placeables.isNotEmpty()) {
                    placeables.last().height + (cardYOffsetPX * (placeables.lastIndex))
                } else {
                    0
                }
            layout(
                width = width,
                height = height,
            ) {
                placeables.mapIndexed { index, placeable ->
                    placeable.place(
                        x = 0,
                        y = height - placeable.height - cardYOffsetPX * index,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PrevCardsDeck() {
    CardsDeck(
        cardYOffsetPX = CARD_Y_OFFSET_IN_DECK_PX,
        onBoxClicked = {  },
        body = "Sample body goes here......",
        content = {
            Text("Sample content....")
        }
    )
}


// Blocks touch events to child, needed to block activityCard click
/*
.pointerInput(Unit) {
    awaitPointerEventScope {
        if (awaitPointerEvent().changes.any { it.pressed }) {
            onBoxClicked()
        }
    }
}
*/

