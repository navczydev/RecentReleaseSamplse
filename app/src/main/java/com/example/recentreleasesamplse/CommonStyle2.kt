@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.example.recentreleasesamplse

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.style.CommonStyle
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Fill
import androidx.compose.foundation.style.StyleResolver
import androidx.compose.foundation.style.appearance
import androidx.compose.foundation.style.stylePropertyOf
import androidx.compose.foundation.style.styleResolver
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val CardFill = stylePropertyOf<Fill>("CardFill") { Fill.None }
val CardRadius = stylePropertyOf<Float>("CardRadius") { 16f }
val CardTextColor = stylePropertyOf<Color>("CardTextColor") { Color.Black }

val ButtonFill = stylePropertyOf<Fill>("ButtonFill") { Fill.None }
val ButtonRadius = stylePropertyOf<Float>("ButtonRadius") { 12f }
val ButtonTextColor = stylePropertyOf<Color>("ButtonTextColor") { Color.White }

val DialogFill = stylePropertyOf<Fill>("DialogFill") { Fill.None }
val DialogTitleColor = stylePropertyOf<Color>("DialogTitleColor") { Color.Black }

val DefaultCardStyle: CommonStyle = CommonStyle {
    CardFill.provide(Fill(Color.White))
    CardRadius.provide(16f)
    CardTextColor.provide(Color.Black)
}

val DefaultButtonStyle: CommonStyle = CommonStyle {
    ButtonFill.provide(Fill(Color(0xFF6200EE)))
    ButtonRadius.provide(12f)
    ButtonTextColor.provide(Color.White)
}

val DefaultDialogStyle: CommonStyle = CommonStyle {
    DialogFill.provide(Fill(Color(0xFFF5F5F5)))
    DialogTitleColor.provide(Color(0xFF1C1B1F))
}

data class AppTheme(
    val card: CommonStyle = DefaultCardStyle,
    val button: CommonStyle = DefaultButtonStyle,
    val dialog: CommonStyle = DefaultDialogStyle
)

val DefaultTheme = AppTheme()

@Composable
fun CardView(
    title: String,
    theme: AppTheme = DefaultTheme,
    modifier: Modifier = Modifier
) {
    val resolver = remember(theme.card) { StyleResolver(theme.card) }

    Box(
        modifier = modifier
            .styleResolver(resolver)
            .appearance {
                background = resolver.resolve { CardFill.value }
            }
    ) {
        Text(
            text = title,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ButtonView(
    label: String,
    theme: AppTheme = DefaultTheme,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val resolver = remember(theme.button) { StyleResolver(theme.button) }

    Button(
        onClick = onClick,
        modifier = modifier
            .styleResolver(resolver)
            .appearance {
                background = resolver.resolve { ButtonFill.value }
            }
    ) {
        Text(
            text = label,
            color = Color.White
        )
    }
}

@Composable
fun DialogView(
    title: String,
    theme: AppTheme = DefaultTheme,
    modifier: Modifier = Modifier
) {
    val resolver = remember(theme.dialog) { StyleResolver(theme.dialog) }

    Surface(
        modifier = modifier
            .styleResolver(resolver)
            .appearance {
                background = resolver.resolve { DialogFill.value }
            }
    ) {
        Text(
            text = title,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_5")
@Composable
fun DemoScreen() {
    val theme = DefaultTheme

    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            CardView(title = "Card example", theme = theme)
            ButtonView(label = "Click me", theme = theme)
            DialogView(title = "Dialog example", theme = theme)
        }
    }
}