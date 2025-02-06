package com.jeju.nanaland.ui.component.withdrawal

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun WithdrawalScreenGuideLineHeading(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = getString(R.string.withdrawal_screen_heading1),
        color = getColor().black,
        style = bodyBold
    )
}