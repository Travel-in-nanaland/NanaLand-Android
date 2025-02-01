package com.jeju.nanaland.ui.component.infomodification.writing

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun InfoModificationProposalWritingScreenDescription() {
    Text(
        text = getString(R.string.report_write_email_subtitle),
        color = getColor().gray01,
        style = caption01
    )
}