package com.jeju.nanaland.ui.component.infomodification.category

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun InfoModificationProposalScreenText2() {
    Text(
        text = "장소 제안 시 첨부한 사진이 장소 정보 확인에\n도움이 되면 혜택을 받을 수 있어요.",
        color = getColor().black,
        style = body02
    )
}