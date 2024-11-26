package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformation
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.util.string.useNonBreakingSpace
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun NanaPickContentSubContentAdditionalInfo(
    infoEmoji: String?,
    infoKey: String?,
    infoValue: String?
) {

    DetailScreenInformation(
        drawableId = when (infoEmoji) {
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
        },
        title = "${infoKey ?: ""} : ".useNonBreakingSpace(),
        content = infoValue.useNonBreakingSpace()
    )

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