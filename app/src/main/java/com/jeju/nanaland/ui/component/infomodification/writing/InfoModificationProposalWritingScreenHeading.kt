package com.jeju.nanaland.ui.component.infomodification.writing

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun InfoModificationProposalWritingScreenHeading(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = getColor().black,
            style = bodyBold
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = "*" + getString(R.string.info_modification_proposal_necessary),
            color = getColor().main,
            style = caption01
        )
    }
}