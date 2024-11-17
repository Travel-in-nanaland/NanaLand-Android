package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent

import android.webkit.URLUtil
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.string.useNonBreakingSpace
import com.jeju.nanaland.util.ui.ComponentPreview
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun NanaPickContentSubContentAdditionalInfo(
    infoEmoji: String?,
    infoKey: String?,
    infoValue: String?
) {
    val uriHandler = LocalUriHandler.current
    Row {
        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(when (infoEmoji) {
                "ADDRESS" -> R.drawable.ic_location_outlined
                "PARKING" -> R.drawable.ic_car_outlined
                "AMENITY" -> R.drawable.ic_warning_outlined
                "WEBSITE" -> R.drawable.ic_home_filled
                "RESERVATION_LINK" -> R.drawable.ic_clip_outlined_2
                "AGE" -> R.drawable.ic_age
                "TIME" -> R.drawable.ic_clock_filled
                "FEE" -> R.drawable.ic_fee
                "DATE" -> R.drawable.ic_calendar_filled
                "DESCRIPTION" -> R.drawable.ic_speech_bubble
                "ETC" -> R.drawable.ic_smile
                else -> R.drawable.ic_phone_outlined
            }),
            contentDescription = null,
            colorFilter = ColorFilter.tint(getColor().gray01)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = "${infoKey ?: ""} : ".useNonBreakingSpace(),
            color = getColor().gray01,
            style = body02,
            textAlign = TextAlign.Justify
        )
        Text(
            modifier = Modifier
                .clickableNoEffect {
                    if(URLUtil.isValidUrl(infoValue)) {
                        uriHandler.openUri(infoValue ?: "")
                    }
                },
            text = infoValue.useNonBreakingSpace(),
            color = getColor().gray01,
            style = body02,
            textAlign = TextAlign.Justify,
            textDecoration = if(URLUtil.isValidUrl(infoValue)) TextDecoration.Underline else TextDecoration.None
        )
    }
}

@ComponentPreview
@Composable
private fun NanaPickContentSubContentAdditionalInfoPreview() {
    NanaLandTheme {
//        NanaPickContentSubContentAdditionalInfo(
//            drawableId = R.drawable.ic_car_outlined,
//            infoKey = "key",
//            infoValue = "value"
//        )
    }
}