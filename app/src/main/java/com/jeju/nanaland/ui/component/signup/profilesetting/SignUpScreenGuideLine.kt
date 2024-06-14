package com.jeju.nanaland.ui.component.signup.profilesetting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01
import com.jeju.nanaland.util.resource.getString

@Composable
fun SignUpScreenGuideLine() {
    Text(
        text = getString(R.string.sign_up_profile_setting_guide),
        color = getColor().black,
        textAlign = TextAlign.Center,
        style = largeTitle01
    )
}