package com.nanaland.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

@Composable
@ReadOnlyComposable
fun getColor() = LocalColor.current

val LocalColor = staticCompositionLocalOf {
    customLightColorScheme()
}

fun customLightColorScheme(
    surface: Color = Color(0xFFFFFFFF),
    main: Color = Color(0xFF583FF5),
    main10: Color = Color(0x19583FF5),
    black: Color = Color(0xFF262627),
    black25: Color = Color(0x3F151515),
    white: Color = Color(0xFFFFFFFF),
    gray01: Color = Color(0xFF7F7F7F),
    gray02: Color = Color(0xFFD9D9D9),
    skyblue: Color = Color(0xFFDCEEF8),
    warning: Color = Color(0xFFFF1305),
    main50: Color = Color(0x7F583FF5),
    white50: Color = Color(0x7FFFFFFF),
    skeleton: Color = Color(0xFFE6E6E6)
): CustomColorScheme =
    CustomColorScheme(
        surface = surface,
        main = main,
        main10 = main10,
        black = black,
        black25 = black25,
        white = white,
        gray01 = gray01,
        gray02 = gray02,
        skyblue = skyblue,
        warning = warning,
        main50 = main50,
        white50= white50,
        skeleton = skeleton
    )

fun customDarkColorScheme(
    surface: Color = Color(0xFFFFFFFF),
    main: Color = Color(0xFF583FF5),
    main10: Color = Color(0x19583FF5),
    black: Color = Color(0xFF151515),
    black25: Color = Color(0x3F151515),
    white: Color = Color(0xFFFFFFFF),
    gray01: Color = Color(0xFF7F7F7F),
    gray02: Color = Color(0xFFD9D9D9),
    skyblue: Color = Color(0xFFDCEEF8),
    warning: Color = Color(0xFFFF1305),
    main50: Color = Color(0x7F583FF5),
    white50: Color = Color(0x7FFFFFFF),
    skeleton: Color = Color(0xFFE6E6E6)
): CustomColorScheme =
    CustomColorScheme(
        surface = surface,
        main = main,
        main10 = main10,
        black = black,
        black25 = black25,
        white = white,
        gray01 = gray01,
        gray02 = gray02,
        skyblue = skyblue,
        warning = warning,
        main50 = main50,
        white50= white50,
        skeleton = skeleton
    )

@Stable
class CustomColorScheme(
    surface: Color,
    main: Color,
    main10: Color,
    black: Color,
    black25: Color,
    white: Color,
    gray01: Color,
    gray02: Color,
    skyblue: Color,
    warning: Color,
    main50: Color,
    white50: Color,
    skeleton: Color
) {
    var surface by mutableStateOf(surface, structuralEqualityPolicy())
    var main by mutableStateOf(main, structuralEqualityPolicy())
    var main10 by mutableStateOf(main10, structuralEqualityPolicy())
    var black by mutableStateOf(black, structuralEqualityPolicy())
    var black25 by mutableStateOf(black25, structuralEqualityPolicy())
    var white by mutableStateOf(white, structuralEqualityPolicy())
    var gray01 by mutableStateOf(gray01, structuralEqualityPolicy())
    var gray02 by mutableStateOf(gray02, structuralEqualityPolicy())
    var skyblue by mutableStateOf(skyblue, structuralEqualityPolicy())
    var warning by mutableStateOf(warning, structuralEqualityPolicy())
    var main50 by mutableStateOf(main50, structuralEqualityPolicy())
    var white50 by mutableStateOf(white50, structuralEqualityPolicy())
    var skeleton by mutableStateOf(skeleton, structuralEqualityPolicy())
}