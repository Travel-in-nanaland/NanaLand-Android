package com.jeju.nanaland.ui.component.infomodification.complete

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.type.getCategoryType

@Composable
fun InfoModificationProposalCompleteScreenText2() {
    Text(
        text = getString(R.string.info_modification_proposal_text4),
        color = getColor().black,
        style = title02,
        textAlign = TextAlign.Center
    )
}