package com.jeju.nanaland.ui.component.withdrawal

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.util.resource.getString

@Composable
fun WithdrawalScreenLogo() {
    Text(
        text = getString(R.string.app_name),
        color = getColor().main,
        style = largeTitle02
    )
}