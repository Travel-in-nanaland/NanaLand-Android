package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.common.ImageUrl
import com.jeju.nanaland.domain.entity.nanapick.NanaPickSubContentAdditionalInfoData
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.component.detailscreen.nanapick.NanaPickContentAttractivePoint
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentAdditionalInfo
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentDescription
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentImage
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentNumber
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentSubTitle
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentTag
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentTitle

@Composable
fun NanaPickContentSubContent(
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
        NanaPickContentSubContentSubTitle(text = title)

        Spacer(Modifier.height(4.dp))

        Row {
            NanaPickContentSubContentNumber(index = index)

            Spacer(Modifier.width(8.dp))

            NanaPickContentSubContentTitle(text = subTitle)
        }

        Spacer(Modifier.height(20.dp))

        NanaPickContentSubContentImage(imageUri = imageUri)

        Spacer(Modifier.height(16.dp))

        NanaPickContentSubContentDescription(text = content)

        Spacer(Modifier.height(24.dp))

        additionalInfoList.forEach { info ->
            if (info.infoEmoji != "SPECIAL") {
                NanaPickContentSubContentAdditionalInfo(
                    infoEmoji = info.infoEmoji,
                    infoKey = info.infoKey,
                    infoValue = info.infoValue,
                    moveToMap = if(info.infoEmoji != "ADDRESS") null else {{
                        moveToMap(ROUTE.Content.Map(
                            name = title.toString(),
                            localLocate = info.infoValue,
                            koreaLocate = info.infoValue,
                            lat = 33.359451, // TODO
                            lng = 126.545839,
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