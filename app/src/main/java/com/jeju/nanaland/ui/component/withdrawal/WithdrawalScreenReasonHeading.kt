package com.jeju.nanaland.ui.component.withdrawal

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold

@Composable
fun WithdrawalScreenReasonHeading() {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = title02Bold.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append("서비스 탈퇴 사유 ")
            }
            withStyle(
                style = title02Bold.toSpanStyle().copy(
                    color = getColor().main
                )
            ) {
                append("(필수)")
            }
        }
    )
}