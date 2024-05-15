package com.jeju.nanaland.ui.component.signin

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun SignInScreenGuestModeText(onClick: () -> Unit) {
    Text(
        modifier = Modifier.clickableNoEffect { onClick() },
        text = "로그인 없이 둘러보기",
        color = getColor().gray01,
        style = body02
    )
}