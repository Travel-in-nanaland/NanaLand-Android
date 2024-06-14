package com.jeju.nanaland.ui.component.policyagree

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.util.resource.getString

@Composable
fun PolicyAgreeScreenBottomButton(
    isActivated: Boolean,
    onClick: () -> Unit
) {
    BottomOkButton(
        text = getString(R.string.common_확인),
        isActivated = isActivated,
        onClick = onClick
    )
}