package com.jeju.nanaland.ui.component.infomodification.complete

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.util.resource.getString

@Composable
fun InfoModificationProposalCompleteScreenBottomButton2(onClick: () -> Unit,) {
    BottomOkButton(
        text = getString(R.string.info_modification_proposal_button2),
        isActivated = true,
        onClick = onClick
    )
}