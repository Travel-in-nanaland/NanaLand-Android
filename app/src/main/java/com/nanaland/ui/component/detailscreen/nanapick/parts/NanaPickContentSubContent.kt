package com.nanaland.ui.component.detailscreen.nanapick.parts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.domain.entity.nanapick.NanaPickSubContentAdditionalInfoData
import com.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentAdditionalInfo
import com.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentDescription
import com.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentImage
import com.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentNumber
import com.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentSubTitle
import com.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentTag
import com.nanaland.ui.component.detailscreen.nanapick.parts.subcontent.NanaPickContentSubContentTitle

@Composable
fun NanaPickContentSubContent(
    index: Int,
    subTitle: String?,
    title: String?,
    imageUri: String?,
    content: String?,
    additionalInfoList: List<NanaPickSubContentAdditionalInfoData>,
    tagList: List<String?>
) {
    Column {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            NanaPickContentSubContentNumber(index = index)

            Spacer(Modifier.width(8.dp))

            Column {
                NanaPickContentSubContentSubTitle(text = subTitle)

                Spacer(Modifier.height(4.dp))

                NanaPickContentSubContentTitle(text = title)
            }
        }

        Spacer(Modifier.height(8.dp))

        NanaPickContentSubContentImage(imageUri = imageUri)

        Spacer(Modifier.height(8.dp))

        NanaPickContentSubContentDescription(text = content)

        Spacer(Modifier.height(8.dp))

        additionalInfoList.forEach { info ->
            NanaPickContentSubContentAdditionalInfo(
                drawableId = R.drawable.ic_check,
                infoKey = info.infoKey,
                infoValue = info.infoValue
            )

            Spacer(Modifier.height(8.dp))
        }

        Spacer(Modifier.height(8.dp))
        
        Row {
            tagList.forEach { tag ->
                NanaPickContentSubContentTag(text = tag)

                Spacer(Modifier.width(8.dp))
            }
        }
    }
}