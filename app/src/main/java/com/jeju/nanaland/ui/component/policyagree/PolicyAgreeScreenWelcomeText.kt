package com.jeju.nanaland.ui.component.policyagree

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01
import com.jeju.nanaland.util.resource.getString

@Composable
fun PolicyAgreeScreenWelcomeText() {
    Text(
        text = getString(R.string.policy_agree_screen_heading),
        color = getColor().black,
        style = largeTitle01
    )
}