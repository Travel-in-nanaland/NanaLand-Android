package com.jeju.nanaland.ui.component.signup.profilesetting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun SignUpScreenCharacterCount(
    count: Int,
    maxCount: Int = 12,
) {
    Text(
        text = "$count / $maxCount " + getString(R.string.common_자),
        color = if (count > maxCount) getColor().warning else getColor().gray01,
        style = caption01
    )
}