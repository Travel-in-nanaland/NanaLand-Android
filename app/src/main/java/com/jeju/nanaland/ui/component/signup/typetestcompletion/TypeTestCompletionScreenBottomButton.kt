package com.jeju.nanaland.ui.component.signup.typetestcompletion

import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.component.common.BottomOkButton

@Composable
fun TypeTestCompletionScreenBottomButton(onClick: () -> Unit) {
    BottomOkButton(
        text = "Let's Go!",
        isActivated = true,
        onClick = onClick
    )
}