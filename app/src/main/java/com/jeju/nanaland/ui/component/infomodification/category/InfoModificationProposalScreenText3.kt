package com.jeju.nanaland.ui.component.infomodification.category

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.util.resource.getString

@Composable
fun InfoModificationProposalScreenText3() {
    Text(
        text = getString(R.string.info_modification_proposal_heading2),
        color = getColor().black,
        style = title01Bold
    )
}