package com.jeju.nanaland.ui.component.infomodification.category

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.ui.theme.title01Bold

@Composable
fun InfoModificationProposalScreenText1() {
    Text(
        text = "수정해야 할 정보를 제안해 주세요!",
        color = getColor().black,
        style = title01Bold
    )
}