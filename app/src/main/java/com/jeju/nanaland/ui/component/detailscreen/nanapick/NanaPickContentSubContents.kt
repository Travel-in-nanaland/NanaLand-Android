package com.jeju.nanaland.ui.component.detailscreen.nanapick

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.nanapick.NanaPickContentData
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.NanaPickContentSubContent

@Composable
fun NanaPickContentSubContents(
    nanaPickContent: NanaPickContentData,
    moveToMap: (ROUTE.Content.Map)-> Unit,
    attractivePointOnClick: (String) -> Unit
) {
    nanaPickContent.nanaDetails.forEachIndexed { idx, details ->
        NanaPickContentSubContent(
            index = idx + 1,
            subTitle = details.subTitle,
            title = details.title,
            imageUri = details.images,
            content = details.content,
            additionalInfoList = details.nanaPickSubContentAdditionalInfoList,
            tagList = details.hashtags,
            attractivePointOnClick = attractivePointOnClick,
            moveToMap = moveToMap
        )
        if (idx != nanaPickContent.nanaDetails.size - 1) {
            Spacer(Modifier.height(64.dp))
        }
    }
}