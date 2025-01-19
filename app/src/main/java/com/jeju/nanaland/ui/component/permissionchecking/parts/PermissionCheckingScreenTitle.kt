package com.jeju.nanaland.ui.component.permissionchecking.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun PermissionCheckingScreenTitle(
    text: String,
    isNecessary: Boolean
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                body02.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append(text)
            }
            withStyle(
                body02.toSpanStyle().copy(
                    color = if (isNecessary) getColor().main else getColor().black
                )
            ) {
                append(" " + if (isNecessary) getString(R.string.common_필수) else getString(R.string.common_선택))
            }
        }
    )
}