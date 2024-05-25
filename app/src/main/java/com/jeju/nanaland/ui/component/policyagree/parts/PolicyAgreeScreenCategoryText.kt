package com.jeju.nanaland.ui.component.policyagree.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun PolicyAgreeScreenCategoryText(
    text: String,
    isNecessary: Boolean
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = body02.toSpanStyle().copy(
                    color = getColor().gray01
                )
            ) {
                append(text)
            }
            withStyle(
                style = body02.toSpanStyle().copy(
                    color = if (isNecessary) getColor().main else getColor().gray01
                )
            ) {
                append(if (isNecessary) " (필수)" else " (선택)")
            }
        }
    )
}