package com.jeju.nanaland.ui.component.infomodification.complete

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02

@Composable
fun InfoModificationProposalCompleteScreenText1() {
    Text(
        text = "정보 수정 제안 감사드립니다",
        color = getColor().main,
        style = largeTitle02
    )
}