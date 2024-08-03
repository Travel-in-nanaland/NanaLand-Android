package com.jeju.nanaland.ui.theme

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
    main90: Color = Color(0xE6583FF5),
    main50: Color = Color(0x7F583FF5),
    main10: Color = Color(0x19583FF5),
    main5: Color = Color(0x0D583FF5),
    black: Color = Color(0xFF262627),
    black50: Color = Color(0x80262627),
    black25: Color = Color(0x3F262627),
    white: Color = Color(0xFFFFFFFF),
    white50: Color = Color(0x7FFFFFFF),
    gray01: Color = Color(0xFF7F7F7F),
    gray02: Color = Color(0xFFD9D9D9),
    gray03: Color = Color(0xFFF2F2F2),
    warning: Color = Color(0xFFFF1305),
    pink: Color = Color(0xFFF7C2BC),
    yellow: Color = Color(0xFFFFBC11),
    skyblue: Color = Color(0xFFDCEEF8),
    shadow: Color = Color(0xB3262627),
    skeleton: Color = Color(0xFFE6E6E6),
    transparent: Color = Color(0x00000000)
): CustomColorScheme =
    CustomColorScheme(
        surface = surface,
        main = main,
        main90 = main90,
        main50 = main50,
        main10 = main10,
        main5 = main5,
        black = black,
        black50 = black50,
        black25 = black25,
        white = white,
        white50 = white50,
        gray01 = gray01,
        gray02 = gray02,
        gray03 = gray03,
        warning = warning,
        pink = pink,
        yellow = yellow,
        skyblue = skyblue,
        shadow = shadow,
        skeleton = skeleton,
        transparent = transparent
    )

fun customDarkColorScheme(
    surface: Color = Color(0xFFFFFFFF),
    main: Color = Color(0xFF583FF5),
    main90: Color = Color(0xE6583FF5),
    main50: Color = Color(0x7F583FF5),
    main10: Color = Color(0x19583FF5),
    main5: Color = Color(0x0D583FF5),
    black: Color = Color(0xFF262627),
    black50: Color = Color(0x80262627),
    black25: Color = Color(0x3F262627),
    white: Color = Color(0xFFFFFFFF),
    white50: Color = Color(0x7FFFFFFF),
    gray01: Color = Color(0xFF7F7F7F),
    gray02: Color = Color(0xFFD9D9D9),
    gray03: Color = Color(0xFFF2F2F2),
    warning: Color = Color(0xFFFF1305),
    pink: Color = Color(0xFFF7C2BC),
    yellow: Color = Color(0xFFFFBC11),
    skyblue: Color = Color(0xFFDCEEF8),
    shadow: Color = Color(0xB3262627),
    skeleton: Color = Color(0xFFE6E6E6),
    transparent: Color = Color(0x00000000)
): CustomColorScheme =
    CustomColorScheme(
        surface = surface,
        main = main,
        main90 = main90,
        main50 = main50,
        main10 = main10,
        main5 = main5,
        black = black,
        black50 = black50,
        black25 = black25,
        white = white,
        white50 = white50,
        gray01 = gray01,
        gray02 = gray02,
        gray03 = gray03,
        warning = warning,
        pink = pink,
        yellow = yellow,
        skyblue = skyblue,
        shadow = shadow,
        skeleton = skeleton,
        transparent = transparent
    )

@Stable
class CustomColorScheme(
    surface: Color,
    main: Color,
    main90: Color,
    main50: Color,
    main10: Color,
    main5: Color,
    black: Color,
    black50: Color,
    black25: Color,
    white: Color,
    white50: Color,
    gray01: Color,
    gray02: Color,
    gray03: Color,
    warning: Color,
    pink: Color,
    yellow: Color,
    skyblue: Color,
    shadow: Color,
    skeleton: Color,
    transparent: Color,
) {
    var surface by mutableStateOf(surface, structuralEqualityPolicy())
    var main by mutableStateOf(main, structuralEqualityPolicy())
    var main90 by mutableStateOf(main90, structuralEqualityPolicy())
    var main50 by mutableStateOf(main50, structuralEqualityPolicy())
    var main10 by mutableStateOf(main10, structuralEqualityPolicy())
    var main5 by mutableStateOf(main5, structuralEqualityPolicy())
    var black by mutableStateOf(black, structuralEqualityPolicy())
    var black50 by mutableStateOf(black50, structuralEqualityPolicy())
    var black25 by mutableStateOf(black25, structuralEqualityPolicy())
    var white by mutableStateOf(white, structuralEqualityPolicy())
    var white50 by mutableStateOf(white50, structuralEqualityPolicy())
    var gray01 by mutableStateOf(gray01, structuralEqualityPolicy())
    var gray02 by mutableStateOf(gray02, structuralEqualityPolicy())
    var gray03 by mutableStateOf(gray03, structuralEqualityPolicy())
    var warning by mutableStateOf(warning, structuralEqualityPolicy())
    var pink by mutableStateOf(pink, structuralEqualityPolicy())
    var yellow by mutableStateOf(yellow, structuralEqualityPolicy())
    var skyblue by mutableStateOf(skyblue, structuralEqualityPolicy())
    var shadow by mutableStateOf(shadow, structuralEqualityPolicy())
    var skeleton by mutableStateOf(skeleton, structuralEqualityPolicy())
    var transparent by mutableStateOf(transparent, structuralEqualityPolicy())
}