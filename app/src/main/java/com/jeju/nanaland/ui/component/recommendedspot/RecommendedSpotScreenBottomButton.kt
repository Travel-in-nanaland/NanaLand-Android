package com.jeju.nanaland.ui.component.recommendedspot

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.util.resource.getString

@Composable
fun RecommendedSpotScreenBottomButton(onClick: () -> Unit,) {
    BottomOkButton(
        text = getString(R.string.type_test_screen_button2),
        isActivated = true,
        onClick = onClick
    )
}