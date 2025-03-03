package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.common.ImageUrl
import com.jeju.nanaland.domain.entity.nanapick.NanaPickSubContentAdditionalInfoData
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.component.detailscreen.nanapick.NanaPickContentAttractivePoint
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentDescription
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentImage
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentNumber
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentSubTitle
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentTag
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentTitle
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformation
import com.jeju.nanaland.util.string.useNonBreakingSpace

@Composable
fun NanaPickContentSubContent(
    id: Int,
    number: Int,
    index: Int,
    subTitle: String?,
    title: String?,
    imageUri: List<ImageUrl>,
    content: String?,
    additionalInfoList: List<NanaPickSubContentAdditionalInfoData>,
    tagList: List<String?>,
    moveToMap: (ROUTE.Content.Map)-> Unit,
    attractivePointOnClick: (String) -> Unit
) {
    Column {
        NanaPickContentSubContentSubTitle(text = subTitle)

        Spacer(Modifier.height(4.dp))

        Row {
            NanaPickContentSubContentNumber(index = index)

            Spacer(Modifier.width(8.dp))

            NanaPickContentSubContentTitle(text = title)
        }

        Spacer(Modifier.height(20.dp))

        NanaPickContentSubContentImage(imageUri = imageUri)

        Spacer(Modifier.height(16.dp))

        NanaPickContentSubContentDescription(text = content)

        Spacer(Modifier.height(24.dp))

        additionalInfoList.forEach { info ->
            if (info.infoEmoji != "SPECIAL") {
                DetailScreenInformation(
                    drawableId = when (info.infoEmoji) {
                        "ADDRESS" -> R.drawable.ic_location_outlined
                        "PARKING" -> R.drawable.ic_car_outlined
                        "SNS" -> R.drawable.ic_sns
                        "AMENITY" -> R.drawable.ic_warning_outlined
                        "WEBSITE" -> R.drawable.ic_clip_outlined
                        "RESERVATION_LINK" -> R.drawable.ic_ticket_outlined
                        "AGE" -> R.drawable.ic_age
                        "TIME" -> R.drawable.ic_clock_outlined
                        "FEE" -> R.drawable.ic_fee
                        "DATE" -> R.drawable.ic_calendar_outlined
                        "DESCRIPTION" -> R.drawable.ic_speech_bubble
                        "ETC" -> R.drawable.ic_smile
                        "CALL" -> R.drawable.ic_phone_outlined
                        else -> return@forEach
                    },
                    title = info.infoKey,
                    content = info.infoValue.useNonBreakingSpace(),
                    moveToMap = if(info.infoEmoji != "ADDRESS") null else {{
                        moveToMap(ROUTE.Content.Map(
                            name = title.toString(),
                            localLocate = info.infoValue,
                            id = id,
                            category = "NANA_CONTENT",
                            number = number
                        ))
                    }}
                )
                Spacer(Modifier.height(8.dp))
            }
        }

        Spacer(Modifier.height(8.dp))
        
        Row {
            tagList.forEach { tag ->
                NanaPickContentSubContentTag(text = tag)

                Spacer(Modifier.width(8.dp))
            }
        }

        Spacer(Modifier.height(16.dp))

        additionalInfoList.firstOrNull { it.infoEmoji == "SPECIAL" }?.let {
            it.infoValue?.let {
                NanaPickContentAttractivePoint {
                    attractivePointOnClick(it)
                }
            }
        }
    }
}