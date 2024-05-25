package com.jeju.nanaland.ui.component.signup.profilesetting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun SignUpScreenCharacterCount(count: Int) {
    Text(
        text = "${count} / 8 자",
        color = if (count > 8) getColor().warning else getColor().gray01,
        style = caption01
    )
}