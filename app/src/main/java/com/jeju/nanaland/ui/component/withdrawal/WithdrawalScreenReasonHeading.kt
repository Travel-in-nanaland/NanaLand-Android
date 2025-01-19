package com.jeju.nanaland.ui.component.withdrawal

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun WithdrawalScreenReasonHeading() {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = bodyBold.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append(getString(R.string.withdrawal_screen_heading2))
            }
            withStyle(
                style = bodyBold.toSpanStyle().copy(
                    color = getColor().main
                )
            ) {
                append(" " + getString(R.string.common_필수))
            }
        }
    )
}