package com.jeju.nanaland.ui.component.signup.typetest

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.util.resource.getString

@Composable
fun TypeTestingScreenBottomButton(
    isActivated: Boolean,
    onClick: () -> Unit
) {
    BottomOkButton(
        text = getString(R.string.type_test_screen_다음),
        isActivated = isActivated,
        onClick = onClick
    )
}