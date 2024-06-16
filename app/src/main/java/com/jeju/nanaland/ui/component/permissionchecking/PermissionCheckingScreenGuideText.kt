package com.jeju.nanaland.ui.component.permissionchecking

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.resource.getString

@Composable
fun PermissionCheckingScreenGuideText() {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = title02.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append(getString(R.string.permission_checking_screen_guide1))
            }
            withStyle(
                style = title02Bold.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append(getString(R.string.permission_checking_screen_guide2))
            }
            withStyle(
                style = title02.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append(getString(R.string.permission_checking_screen_guide3))
            }
        },
    )
}