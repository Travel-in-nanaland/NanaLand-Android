package com.nanaland.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

object CustomTheme {
    val colorScheme: CustomColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColor.current
}

val LocalColor = staticCompositionLocalOf {
    customLightColorScheme()
}

fun customLightColorScheme(
    red: Color = Color.Red,
    blue: Color = Color.Blue
): CustomColorScheme =
    CustomColorScheme(
        red = red,
        blue = blue
    )

fun customDarkColorScheme(
    red: Color = Color.Cyan,
    blue: Color = Color.Yellow
): CustomColorScheme =
    CustomColorScheme(
        red = blue,
        blue = red
    )

@Stable
class CustomColorScheme(
    red: Color,
    blue: Color
) {
    var red by mutableStateOf(red, structuralEqualityPolicy())
    var blue by mutableStateOf(blue, structuralEqualityPolicy())
}