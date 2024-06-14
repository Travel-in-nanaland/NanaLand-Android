package com.jeju.nanaland.ui.component.withdrawal

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.resource.getString

@Composable
fun WithdrawalScreenGuideLineHeading() {
    Text(
        text = getString(R.string.withdrawal_screen_heading1),
        color = getColor().black,
        style = title02Bold
    )
}