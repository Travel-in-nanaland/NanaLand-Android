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

object CustomColor {
    @Composable
    @ReadOnlyComposable
    operator fun invoke() = LocalColor.current
//    val color: CustomColorScheme
//        @Composable
//        @ReadOnlyComposable
//        get() = LocalColor.current
}

val LocalColor = staticCompositionLocalOf {
    customLightColorScheme()
}

fun customLightColorScheme(
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
    white50: Color = Color(0x7FFFFFFF)
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
        white50= white50
    )

fun customDarkColorScheme(
    surface: Color = Color.White,
    main: Color = Color.White,
    main10: Color = Color.White,
    black: Color = Color.White,
    black25: Color = Color.White,
    white: Color = Color.White,
    gray01: Color = Color.White,
    gray02: Color = Color.White,
    skyblue: Color = Color.White,
    warning: Color = Color.White,
    main50: Color = Color.White,
    white50: Color = Color.White
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
        white50= white50
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
    white50: Color
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
}