package com.jeju.nanaland.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.jeju.nanaland.R


val appleSdGothicNeo = FontFamily(
    Font(R.font.apple_neo_t, FontWeight.Thin),
    Font(R.font.apple_neo_ul, FontWeight.ExtraLight),
    Font(R.font.apple_neo_l, FontWeight.Light),
    Font(R.font.apple_neo_r, FontWeight.Normal),
    Font(R.font.apple_neo_m, FontWeight.Medium),
    Font(R.font.apple_neo_sb, FontWeight.SemiBold),
    Font(R.font.apple_neo_b, FontWeight.Bold),
    Font(R.font.apple_neo_eb, FontWeight.ExtraBold),
    Font(R.font.apple_neo_h, FontWeight.Black)
)

val nanumPen = FontFamily(
    Font(R.font.nanum_pen_regular, FontWeight.Normal),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
    displayMedium = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
    displaySmall = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
    headlineLarge = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
    headlineMedium = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
    headlineSmall = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
    titleLarge = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
    titleMedium = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
    titleSmall = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
    bodyLarge = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
    bodyMedium = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
    bodySmall = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
    labelLarge = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
    labelMedium = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
    labelSmall = TextStyle(
        fontFamily = appleSdGothicNeo,
        platformStyle = PlatformTextStyle(false)
    ),
)

private const val baseFontSize = 10
val largeTitle01 = TextStyle(
    fontSize = (baseFontSize + 18).sp,
    fontWeight = FontWeight.Bold,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val largeTitle02 = TextStyle(
    fontSize = (baseFontSize + 12).sp,
    fontWeight = FontWeight.Bold,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 26).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val title01Bold = TextStyle(
    fontSize = (baseFontSize + 10).sp,
    fontWeight = FontWeight.Bold,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val title02Bold = TextStyle(
    fontSize = (baseFontSize + 8).sp,
    fontWeight = FontWeight.Bold,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 20).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val title02 = TextStyle(
    fontSize = (baseFontSize + 8).sp,
    fontWeight = FontWeight.Medium,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 20).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val bodyBold = TextStyle(
    fontSize = (baseFontSize + 6).sp,
    fontWeight = FontWeight.Bold,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 16).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val bodySemiBold = TextStyle(
    fontSize = (baseFontSize + 6).sp,
    fontWeight = FontWeight.SemiBold,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 16).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val body01 = TextStyle(
    fontSize = (baseFontSize + 6).sp,
    fontWeight = FontWeight.Medium,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 16).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val body02Bold = TextStyle(
    fontSize = (baseFontSize + 4).sp,
    fontWeight = FontWeight.Bold,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 12).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val body02SemiBold = TextStyle(
    fontSize = (baseFontSize + 4).sp,
    fontWeight = FontWeight.SemiBold,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 12).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val body02 = TextStyle(
    fontSize = (baseFontSize + 4).sp,
    fontWeight = FontWeight.Medium,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 12).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val caption01 = TextStyle(
    fontSize = (baseFontSize + 2).sp,
    fontWeight = FontWeight.Medium,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 10).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val caption01SemiBold = TextStyle(
    fontSize = (baseFontSize + 2).sp,
    fontWeight = FontWeight.SemiBold,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 10).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val caption02 = TextStyle(
    fontSize = (baseFontSize + 0).sp,
    fontWeight = FontWeight.Normal,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 6).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val caption02SemiBold = TextStyle(
    fontSize = (baseFontSize + 0).sp,
    fontWeight = FontWeight.SemiBold,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 6).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val largeTitle02Regular = TextStyle(
    fontSize = (baseFontSize + 12).sp,
    fontWeight = FontWeight.Normal,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 26).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val largeTitle02Medium = TextStyle(
    fontSize = (baseFontSize + 12).sp,
    fontWeight = FontWeight.Medium,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 26).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
val searchText = TextStyle(
    fontSize = (baseFontSize + 2).sp,
    fontWeight = FontWeight.Medium,
    fontFamily = appleSdGothicNeo,
    platformStyle = PlatformTextStyle(false),
    lineHeight = (baseFontSize + 5).sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)