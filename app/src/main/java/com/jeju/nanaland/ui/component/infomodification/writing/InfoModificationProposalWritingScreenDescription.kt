package com.jeju.nanaland.ui.component.infomodification.writing

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun InfoModificationProposalWritingScreenDescription() {
    Text(
        text = "정보 수정 제안 결과를 받을 이메일을 입력해주세요",
        color = getColor().gray01,
        style = body02
    )
}