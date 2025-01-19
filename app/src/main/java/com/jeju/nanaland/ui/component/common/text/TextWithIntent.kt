package com.jeju.nanaland.ui.component.common.text

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun TextWithIntent(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
//    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
) {
    val context = LocalContext.current
    var intentType by remember { mutableStateOf<String?>(null) }
    val intent = remember(text) {
        when {
            Patterns.PHONE.matcher(text).matches() -> {
                intentType = "tel"
                Intent(Intent.ACTION_DIAL, text.toUriWithAddIfNotStarts("tel:"))
            }
            Patterns.WEB_URL.matcher(text).matches() -> {
                intentType = "web"
                Intent(Intent.ACTION_VIEW, text.toUriWithAddIfNotStarts("http://", "https://"))
            }
            else -> null
        }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = text,
            modifier = modifier.clickableNoEffect {
                try {
                    intent?.let(context::startActivity)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            },
            color = color,
            fontSize = fontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            style = style,
            textDecoration = if (intentType == "web") TextDecoration.Underline else TextDecoration.None
        )
        if(intentType == "tel") {
            Spacer(Modifier.width(2.dp))
            Image(
                modifier = Modifier.size(12.dp),
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color)
            )
        }

    }
}

private fun String.toUriWithAddIfNotStarts(vararg ifNot: String): Uri {
    return Uri.parse(
        if (ifNot.any { startsWith(it) }) this
        else ifNot.first() + this
    )
}


/* 테스트 코드
fun testInternetAddressValidation() {
    val testUrls = listOf(
        "example.com",                 // 도메인만
        "www.example.com",             // 프로토콜 없는 웹 주소
        "http://example.com",          // HTTP URL
        "https://www.example.com/path",// HTTPS URL + 경로
        "ftp://files.example.com",     // FTP URL
        "example..com",                // 잘못된 도메인
        "https://",                    // 프로토콜만 있음
        "not a url",                   // URL 아님
        "http://123.123.123.123",      // IP 주소
        "123.123.123.123"              // IP 주소 (프로토콜 없음)
    )

    Log.d("asd@@@","Patterns.DOMAIN_NAME Validation:")
    testUrls.forEach { url ->
        Log.d("asd@@@","$url -> ${Patterns.DOMAIN_NAME.matcher(url).matches()}")
    }

    Log.d("asd@@@","\nPatterns.WEB_URL Validation:")
    testUrls.forEach { url ->
        Log.d("asd@@@","$url -> ${Patterns.WEB_URL.matcher(url).matches()}")
    }

    Log.d("asd@@@","\nURLUtil.isValidUrl Validation:")
    testUrls.forEach { url ->
        Log.d("asd@@@","$url -> ${URLUtil.isValidUrl(url)}")
    }
}

fun testPhoneNumberValidation() {
    val testPhoneNumbers = listOf(
        "+1234567890",        // 국제 전화번호
        "+82-10-1234-5678",   // 국제 + 국가 코드 + 번호
        "010-1234-5678",      // 국내 전화번호
        "123-456-7890",       // 지역 번호 포함
        "1234567890",         // 숫자만
        "abcd-1234",          // 잘못된 입력
        "+1 (234) 567-8900",  // 괄호와 공백 포함
        "+821012345678",      // 한국 국제 번호
        "112",                // 긴급 번호
        "02-123-4567",         // 지역 번호 포함 (한국)
        "1123-4567",         // 지역 번호 포함 (한국)
        "123-4567",         // 지역 번호 포함 (한국)
    )

    Log.d("asd","Patterns.PHONE Validation:")
    testPhoneNumbers.forEach { phone ->
        Log.d("asd","$phone -> ${Patterns.PHONE.matcher(phone).matches()}")
    }

    Log.d("asd","\nPhoneNumberUtils.isGlobalPhoneNumber Validation:")
    testPhoneNumbers.forEach { phone ->
        Log.d("asd","$phone -> ${PhoneNumberUtils.isGlobalPhoneNumber(phone)}")
    }
}
 */