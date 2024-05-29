package com.jeju.nanaland.ui.component.permissionchecking

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02
import com.jeju.nanaland.ui.theme.title02Bold

@Composable
fun PermissionCheckingScreenGuideText() {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = title02.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append("나나랜드인제주 사용을 위해\n" +
                        "다음 ")
            }
            withStyle(
                style = title02Bold.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append("접근 권한 허용 ")
            }
            withStyle(
                style = title02.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append("필요합니다.")
            }
        },
    )
}