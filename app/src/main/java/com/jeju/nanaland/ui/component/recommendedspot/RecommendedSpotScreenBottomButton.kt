package com.jeju.nanaland.ui.component.recommendedspot

import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.component.common.BottomOkButton

@Composable
fun RecommendedSpotScreenBottomButton(onClick: () -> Unit,) {
    BottomOkButton(
        text = "메인 화면 바로가기",
        isActivated = true,
        onClick = onClick
    )
}