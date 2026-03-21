package com.example.recentreleasesamplse

import android.R.attr.name
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.InspectorInfo

object BrandGradientOverlayElement : ModifierNodeElement<BrandGradientOverlayNode>() {

    override fun create(): BrandGradientOverlayNode = BrandGradientOverlayNode()

    override fun update(node: BrandGradientOverlayNode) {
        // No properties to update, so this stays empty
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "brandGradientOverlay"
    }

    // Standard Kotlin referential equality check
    override fun equals(other: Any?): Boolean = (this === other)

    // Using the class hash is a very "Kotlin-native" way to handle this
    override fun hashCode(): Int = javaClass.hashCode()
}

class BrandGradientOverlayNode :
    Modifier.Node(),
    DrawModifierNode,
    CompositionLocalConsumerModifierNode {

    override fun ContentDrawScope.draw() {
        val colorScheme = currentValueOf(MaterialTheme.LocalMaterialTheme).colorScheme

        val gradient = Brush.linearGradient(
            listOf(colorScheme.primary, colorScheme.secondary)
        )

        drawContent()
        drawRect(brush = gradient, alpha = 0.4f)
    }
}