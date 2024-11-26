package com.jeju.nanaland.ui.component.infomodification.category

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun InfoModificationProposalScreenText2() {
    Text(
        text = getString(R.string.info_modification_proposal_text1),
        color = getColor().gray01,
        style = caption01
    )
}