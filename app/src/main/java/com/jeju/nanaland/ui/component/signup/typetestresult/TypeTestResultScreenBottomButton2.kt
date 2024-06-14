package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.util.resource.getString

@Composable
fun TypeTestResultScreenBottomButton2(onClick: () -> Unit) {
    BottomOkButton(
        text = getString(R.string.type_test_screen_button2),
        isActivated = true,
        onClick = onClick
    )
}