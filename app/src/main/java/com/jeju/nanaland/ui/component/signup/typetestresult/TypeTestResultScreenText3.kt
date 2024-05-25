package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun TypeTestResultScreenText3() {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = bodyBold.toSpanStyle().copy(
                    color = getColor().main
                )
            ) {
                append("부드러우면서도, 달콤한 기억")
            }
            withStyle(
                style = bodyBold.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append("을 선사해주는 감귤 아이스크림 유형인 당신!\n\n" +
                        "nanaland in Jeju가 당신을 위해\n맞춤 여행 주스를 만들었어요!")
            }
        },
        textAlign = TextAlign.Center
    )
}