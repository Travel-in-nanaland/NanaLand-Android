package com.jeju.nanaland.ui.component.withdrawal.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.caption01SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun WithdrawalScreenDialogDescription() {
    Text(
        text = getString(R.string.withdrawal_dialog_text2),
        color = getColor().gray01,
        style = caption01SemiBold,
        textAlign = TextAlign.Center
    )
}