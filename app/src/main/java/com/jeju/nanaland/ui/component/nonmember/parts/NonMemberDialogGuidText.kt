package com.jeju.nanaland.ui.component.nonmember.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.util.resource.getString

@Composable
fun NonMemberDialogGuidText() {
    Text(
        text = getString(R.string.non_member_dialog_guide),
        color = getColor().black,
        style = title01Bold,
        textAlign = TextAlign.Center
    )
}