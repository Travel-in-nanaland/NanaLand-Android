package com.jeju.nanaland.ui.component.infomodification.complete

import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.component.common.BottomOkButton

@Composable
fun InfoModificationProposalCompleteScreenBottomButton2(onClick: () -> Unit,) {
    BottomOkButton(
        text = "다른 항목 추가하기",
        isActivated = true,
        onClick = onClick
    )
}