package com.example.recentreleasesamplse

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.min

fun Modifier.psOverlay(): Modifier = composed {
    val colorScheme = MaterialTheme.colorScheme  // Captured here
    drawWithCache {
        val brandGradient = Brush.linearGradient(
            0f to colorScheme.primary,
            1f to colorScheme.secondary
        )
        onDrawWithContent {
            drawContent()
            drawRect(brush = brandGradient, alpha = 0.9f)
        }
    }
}

fun Modifier.themedBorder(): Modifier = composed {
    val color = MaterialTheme.colorScheme.error

    border(2.dp, color)
}

/*fun Modifier.brandGradientOverlay(): Modifier = modifierNode(
    nodeFactory = {
        androidx.compose.ui.node.CompositionLocalConsumerModifierNode(
            factory = {
                // This node runs outside composable scope
            }
        ) { node ->
            // Access full theme subsystems
            val subsystems = currentValueOf(MaterialTheme.LocalMaterialTheme)

            // Build theme-aware gradient
            val brandGradient = Brush.linearGradient(
                0f to subsystems.colorScheme.primary,
                1f to subsystems.colorScheme.secondary
            )

            // Use in drawing/layout
            drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawRect(brush = brandGradient)
                }
            }
        }
    }
)*/

@Composable
fun CompositionLocalConsumingModifierSample() {
    val localBackgroundColor = compositionLocalOf { Color.White }

    class BackgroundColor :
        Modifier.Node(), DrawModifierNode, CompositionLocalConsumerModifierNode {
        override fun ContentDrawScope.draw() {
            val backgroundColor = currentValueOf(localBackgroundColor)
            drawRect(backgroundColor)
            drawContent()
        }
    }

    val backgroundColorElement =
        object : ModifierNodeElement<BackgroundColor>() {
            override fun create() = BackgroundColor()

            override fun update(node: BackgroundColor) {}

            override fun hashCode() = System.identityHashCode(this)

            override fun equals(other: Any?) = (other === this)

            override fun InspectorInfo.inspectableProperties() {
                name = "backgroundColor"
            }
        }

    fun Modifier.backgroundColor() = this then backgroundColorElement
    Box(Modifier.backgroundColor()) { Text("Hello, world!") }
}


@Preview(showSystemUi = true)
@Composable
fun Sample() {
    MaterialTheme {
        Card(
            modifier = Modifier
                .padding(48.dp)
        ) {
            Box(
                modifier = Modifier
                    .brandGradientOverlay()
                    .heightIn(300.dp)
                    .padding(16.dp),
                Alignment.Center
            ) {
                Text("Sample LocalMaterialTheme")
            }
        }
    }
}

fun Modifier.brandGradientOverlay(): Modifier =
    this then BrandGradientOverlayElement


fun Modifier.brandGradientOverlay(
    primaryColor: Color,
    secondaryColor: Color  // <- Manual prop from parent composable
): Modifier = drawWithCache {
    val brandGradient = Brush.linearGradient(
        0f to primaryColor,
        1f to secondaryColor
    )
    onDrawWithContent {
        drawContent()
        drawRect(brush = brandGradient)
    }
}