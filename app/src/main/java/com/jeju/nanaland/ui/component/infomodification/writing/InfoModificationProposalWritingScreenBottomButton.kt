package com.jeju.nanaland.ui.component.infomodification.writing

import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.component.common.BottomOkButton

@Composable
fun InfoModificationProposalWritingScreenBottomButton(
    isActivated: Boolean,
    onClick: () -> Unit,
) {
    BottomOkButton(
        text = "보내기",
        isActivated = isActivated,
        onClick = onClick
    )
}