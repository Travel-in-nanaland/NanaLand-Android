package com.jeju.nanaland.ui.component.policyagree.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.bodySemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun PolicyAgreeScreenAgreeAllContentText() {
    Text(
        text = getString(R.string.policy_agree_screen_전체_동의),
        color = getColor().black,
        style = bodySemiBold
    )
}