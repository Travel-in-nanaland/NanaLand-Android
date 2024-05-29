package com.jeju.nanaland.ui.component.permissionchecking.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun PermissionCheckingScreenTitle(
    text: String,
    isNecessary: Boolean
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                body02SemiBold.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append(text)
            }
            withStyle(
                body02SemiBold.toSpanStyle().copy(
                    color = if (isNecessary) getColor().main else getColor().black
                )
            ) {
                append(if (isNecessary) " (필수)" else " (선택)")
            }
        }
    )
}