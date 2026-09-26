/*
@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.example.recentreleasesamplse

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.style.CommonStyle
import androidx.compose.foundation.style.CommonStyleScope
import androidx.compose.foundation.style.CustomStyle
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Fill
import androidx.compose.foundation.style.Shadows
import androidx.compose.foundation.style.StyleResolver
import androidx.compose.foundation.style.appearance
import androidx.compose.foundation.style.styleLocalOf
import androidx.compose.foundation.style.stylePropertyOf
import androidx.compose.foundation.style.styleResolver
import androidx.compose.foundation.style.then
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// 1) Regular style properties (not inherited)
val CardRadius = stylePropertyOf<Float>("CardRadius") { 16f }
val CardFill = stylePropertyOf<Fill>("CardFill") { Fill.None }
val CardShadow = stylePropertyOf<Shadows>("CardShadow") { Shadows.None }

// 2) Local style property (inherits down the tree)
val CardTextColorLocal = styleLocalOf("CardTextColorLocal") { Color.Black }

// 3) CustomStyle: typed style wrapper, converted to CommonStyle
fun interface CardStyle : CustomStyle<CommonStyleScope> {
    override fun CommonStyleScope.applyStyle()
}

fun CardStyle.toCommonStyle(): CommonStyle = CommonStyle {
    with(this) { applyStyle() }
}

// 4) Example base style built from CommonStyle directly
val BaseCardStyle: CommonStyle = CommonStyle {
    CardFill.provide(Fill.Color(Color.Red))
    CardRadius.provide(16f)
    */
/*CardShadow.provide(Shadows(
        Shadow(
            color = Color(0x33000000),
            offset = Offset(0f, 2f),
            blurRadius = 4f
        )
    )!!)*//*

    CardTextColorLocal.provide(Color(0xFF1F1F1F))
}

// 5) Example custom style built from CustomStyle
val AccentCardStyle: CardStyle = CardStyle {
    CardFill.provide(Fill(Color(0xFFEEF2FF)))
    CardRadius.provide(20f)
    CardShadow.provide(Shadows(
        Shadow(
            color = Color(0x22000000),
            offset = Offset(0f, 6f),
            blurRadius = 12f
        )
    )!!)
    CardTextColorLocal.provide(Color.Blue)
}

// 6) Merge custom/common style and resolve values
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_5")
@Composable
fun CardSample() {
    val resolver = remember { StyleResolver(BaseCardStyle) }

    Box(
        modifier = Modifier
            .styleResolver(resolver)
            .appearance{
                background = resolver.resolve { CardFill.value }
            }
    ) {
        Text("Fill",
        */
/*    modifier = Modifier.styleResolver(resolver).appearance{
                this.foreground = resolver.resolve { CardTextColorLocal.value}*//*

            )
    }
}*/
