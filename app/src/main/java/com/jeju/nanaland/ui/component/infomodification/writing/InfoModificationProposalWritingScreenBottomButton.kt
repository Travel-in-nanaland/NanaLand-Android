package com.jeju.nanaland.ui.component.infomodification.writing

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.util.resource.getString

@Composable
fun InfoModificationProposalWritingScreenBottomButton(
    isActivated: Boolean,
    onClick: () -> Unit,
) {
    BottomOkButton(
        text = getString(R.string.info_modification_proposal_보내기),
        isActivated = isActivated,
        onClick = onClick
    )
}