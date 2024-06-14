package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.BottomOkButtonOutlined
import com.jeju.nanaland.util.resource.getString

@Composable
fun TypeTestResultScreenBottomButton1(onClick: () -> Unit) {
    BottomOkButtonOutlined(
        text = getString(R.string.type_test_screen_button1),
        onClick = onClick
    )
}