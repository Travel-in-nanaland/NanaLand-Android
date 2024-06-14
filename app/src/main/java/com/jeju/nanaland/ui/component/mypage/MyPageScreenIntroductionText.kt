package com.jeju.nanaland.ui.component.mypage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun MyPageScreenIntroductionText() {
    Text(
        text = getString(R.string.common_소개),
        color = getColor().black,
        style = bodyBold
    )
}