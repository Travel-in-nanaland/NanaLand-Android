package com.jeju.nanaland.ui.component.infomodification.complete

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02
import com.jeju.nanaland.util.type.getCategoryType

@Composable
fun InfoModificationProposalCompleteScreenText2() {
    Text(
        text = "정보 수정 제안으로\n여행지의 매력도 함께 올라갔어요!\n\n" +
                "점점 기대될 것 같아요\uD83E\uDDDA\u200D♀\uFE0F",
        color = getColor().black,
        style = title02,
        textAlign = TextAlign.Center
    )
}