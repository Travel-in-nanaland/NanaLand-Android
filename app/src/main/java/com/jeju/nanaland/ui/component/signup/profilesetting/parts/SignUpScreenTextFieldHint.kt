package com.jeju.nanaland.ui.component.signup.profilesetting.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun SignUpScreenTextFieldHint() {
    Text(
        text = getString(R.string.sign_up_profile_setting_hint),
        color = getColor().gray01,
        style = body02
    )
}