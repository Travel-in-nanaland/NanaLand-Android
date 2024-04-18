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
    surface: Color = Color.White,
): CustomColorScheme =
    CustomColorScheme(
        surface = surface,
    )

fun customDarkColorScheme(
    surface: Color = Color.White,
): CustomColorScheme =
    CustomColorScheme(
        surface = surface,
    )

@Stable
class CustomColorScheme(
    surface: Color,
) {
    var surface by mutableStateOf(surface, structuralEqualityPolicy())
}