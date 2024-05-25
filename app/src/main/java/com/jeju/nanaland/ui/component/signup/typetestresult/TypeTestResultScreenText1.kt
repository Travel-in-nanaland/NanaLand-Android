package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun TypeTestResultScreenText1() {
    Text(
        text = "${UserData.nickname} 님의 여행 유형은",
        color = getColor().black,
        style = body01
    )
}