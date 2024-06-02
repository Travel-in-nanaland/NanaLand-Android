package com.jeju.nanaland.ui.component.withdrawal.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun WithdrawalScreenDialogDescription() {
    Text(
        text = "*90일 이내에 재가입 시,\n기존 계정으로 로그인이 됩니다.",
        color = getColor().gray01,
        style = body01,
        textAlign = TextAlign.Center
    )
}