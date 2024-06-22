package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.NanaLandTheme
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
                else -> R.drawable.ic_phone_outlined
            }),
            contentDescription = null,
            colorFilter = ColorFilter.tint(getColor().gray01)
        )

        Spacer(Modifier.width(8.dp))

//        Text(
//            text = "${infoKey ?: ""} : ${infoValue ?: ""}",
//            color = getColor().gray01,
//            style = body02
//        )

        if (infoEmoji == "WEBSITE" || infoEmoji == "RESERVATION_LINK") {
            Text(
                modifier = Modifier
                    .clickableNoEffect {
                        uriHandler.openUri(infoValue ?: "")
                    },
                text = buildAnnotatedString {
                    append("${infoKey ?: ""} : ${infoValue ?: ""}".useNonBreakingSpace())
                    addStringAnnotation(
                        tag = "URL",
                        annotation = infoValue ?: "",
                        start = 0,
                        end = infoValue?.length ?: 0
                    )
                },
                color = getColor().gray01,
                style = body02,
                textAlign = TextAlign.Justify
            )
        } else {
            Text(
                text = "${infoKey ?: ""} : ${infoValue ?: ""}".useNonBreakingSpace(),
                color = getColor().gray01,
                style = body02,
                textAlign = TextAlign.Justify
            )
        }
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