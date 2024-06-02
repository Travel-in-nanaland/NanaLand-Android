package com.jeju.nanaland.ui.component.infomodification.complete

import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.component.common.BottomOkButtonOutlined

@Composable
fun InfoModificationProposalCompleteScreenBottomButton1(onClick: () -> Unit,) {
    BottomOkButtonOutlined(
        text = "콘텐츠 다시 보러 가기",
        onClick = onClick
    )
}