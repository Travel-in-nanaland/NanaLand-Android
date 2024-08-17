package com.jeju.nanaland.ui.component.signup.profilesetting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.NICKNAME_CONSTRAINT
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun SignUpScreenCharacterCount(
    count: Int,
    isError: Boolean,
    maxCount: Int = NICKNAME_CONSTRAINT,
) {
    Text(
        text = "$count / $maxCount " + getString(R.string.common_자),
        color = if (isError) getColor().warning else getColor().gray01,
        style = caption01
    )
}