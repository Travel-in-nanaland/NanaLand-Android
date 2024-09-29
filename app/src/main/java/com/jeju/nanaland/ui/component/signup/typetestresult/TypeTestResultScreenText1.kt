package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun TypeTestResultScreenText1() {
    Text(
        text = getString(R.string.type_test_screen_text_result_header, UserData.nickname),
        color = getColor().black,
        style = body01
    )
}