package com.jeju.nanaland.ui.component.mypage

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.BottomOkButtonOutlined
import com.jeju.nanaland.util.resource.getString

@Composable
fun MyPageScreenBottomButton(onClick: () -> Unit) {
    BottomOkButtonOutlined(
        text = getString(R.string.mypage_screen_프로필_수정),
        onClick = onClick
    )
}