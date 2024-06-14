package com.jeju.nanaland.ui.component.signup.typetestcompletion

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.util.resource.getString

@Composable
fun TypeTestCompletionScreenBottomButton(onClick: () -> Unit) {
    BottomOkButton(
        text = getString(R.string.type_test_screen_lets_go),
        isActivated = true,
        onClick = onClick
    )
}