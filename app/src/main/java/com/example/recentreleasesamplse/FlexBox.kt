@file:OptIn(ExperimentalFlexBoxApi::class)

package com.example.recentreleasesamplse

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalFlexBoxApi
import androidx.compose.foundation.layout.FlexAlignItems
import androidx.compose.foundation.layout.FlexAlignSelf
import androidx.compose.foundation.layout.FlexBox
import androidx.compose.foundation.layout.FlexDirection
import androidx.compose.foundation.layout.FlexJustifyContent
import androidx.compose.foundation.layout.FlexWrap
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO_XL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@OptIn(ExperimentalFlexBoxApi::class)
@Preview(showSystemUi = true, device = PIXEL_9_PRO_XL)
@Composable
fun SimpleFlexBox() {
    // FlexBox defaults to a Row-like layout (FlexDirection.Row).
    // The children will be laid out horizontally.
    FlexBox(
        modifier = Modifier
            .fillMaxWidth()
            .safeDrawingPadding(),
        config = {
            direction(
                if (constraints.maxWidth < 400.dp.roundToPx()) FlexDirection.Column
                else FlexDirection.Row
            )
            wrap(FlexWrap.Wrap)
            alignItems(FlexAlignItems.Start)
            justifyContent(FlexJustifyContent.SpaceBetween)
            gap(8.dp)
        },
    ) {
        // This child has a fixed size and will not flex.
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color.Magenta),
            contentAlignment = Alignment.Center,
        ) {
            Text("Fixed")
        }
        // This child has a grow factor of 1. It will take up 1/3 of the remaining space.
        Box(
            modifier = Modifier
                .height(80.dp)
                .flex {
                    grow(1f)
                    order(-1)
                    alignSelf(FlexAlignSelf.End)
                }
                .background(Color.Yellow),
            contentAlignment = Alignment.Center,
        ) {
            Text("Grow = 1")
        }
        // This child has a grow factor of 2. It will take up 2/3 of the remaining space.
        Box(
            modifier = Modifier
                .height(80.dp)
                .flex { grow(2f) }
                .background(Color.Green),
            contentAlignment = Alignment.Center,
        ) {
            Text("Grow = 2")
        }
    }
}

@Preview(showSystemUi = true, device = PIXEL_9_PRO_XL)
@Composable
fun SimpleRowFlexBox() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Row", fontSize = 32.sp)
        FlexBoxRowDemo()
        Spacer(Modifier.height(24.dp))
        Text(text = "Wrap", fontSize = 32.sp)
        FlexBoxRowWrapDemo()
        Spacer(Modifier.height(24.dp))
        Text(text = "RowReverse", fontSize = 32.sp)
        FlexBoxRowReverseDemo()
        Spacer(Modifier.height(24.dp))
        Text(text = "WrapReverse", fontSize = 32.sp)
        FlexBoxRowWrapReverseDemo()
    }
}

@Preview(showSystemUi = true, device = PIXEL_9_PRO_XL)

@Composable
private fun FlexBoxRowDemo() {
    FlexBox(config = { direction(FlexDirection.Row) }, modifier = Modifier.fillMaxWidth()) {
        repeat(4) {
            Box(
                modifier =
                    Modifier
                        .size(50.dp)
                        .background(color = randomColor())
                        .border(1.dp, color = Color.Black)
            ) {
                Text(text = "$it", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showSystemUi = true, device = PIXEL_9_PRO_XL)

@Composable
private fun FlexBoxRowWrapDemo() {
    FlexBox(
        config = {
            direction(FlexDirection.Row)
            wrap(FlexWrap.Wrap)
        }
    ) {
        repeat(10) {
            Box(
                modifier =
                    Modifier
                        .size(50.dp)
                        .background(color = randomColor())
                        .border(1.dp, color = Color.Black)
            ) {
                Text(text = "$it", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showSystemUi = true, device = PIXEL_9_PRO_XL)
// RowReverse sample
@Composable
private fun FlexBoxRowReverseDemo() {
    FlexBox(config = { direction(FlexDirection.RowReverse) }, modifier = Modifier.fillMaxWidth()) {
        repeat(4) {
            Box(
                modifier =
                    Modifier
                        .size(50.dp)
                        .background(color = randomColor())
                        .border(1.dp, color = Color.Black)
            ) {
                Text(text = "$it", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

// WrapReverse sample
@Preview(showSystemUi = true, device = PIXEL_9_PRO_XL)
@Composable
private fun FlexBoxRowWrapReverseDemo() {
    FlexBox(
        config = {
            direction(FlexDirection.Row)
            wrap(FlexWrap.WrapReverse)
        }
    ) {
        repeat(10) {
            Box(
                modifier =
                    Modifier
                        .size(50.dp)
                        .background(color = randomColor())
                        .border(1.dp, color = Color.Black)
            ) {
                Text(text = "$it", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

private fun randomColor(): Color {
    val random = Random.Default
    return Color(
        red = random.nextInt(256),
        green = random.nextInt(256),
        blue = random.nextInt(256)
    )
}
