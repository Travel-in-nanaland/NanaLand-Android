package com.jeju.nanaland.ui.component.infomodification.writing

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold

@Composable
fun InfoModificationProposalWritingScreenHeading(text: String) {
    Text(
        text = text,
        color = getColor().black,
        style = title02Bold
    )
}