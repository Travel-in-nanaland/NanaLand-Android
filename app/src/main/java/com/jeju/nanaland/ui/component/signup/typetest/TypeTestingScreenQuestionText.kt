package com.jeju.nanaland.ui.component.signup.typetest

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02

@Composable
fun TypeTestingScreenQuestionText(level: Int) {
    Text(
        text = when (level) {
            1 -> "여행 장소를\n고를 때 ${UserData.nickname} 님은?"
            2 -> "여행 계획을\n세울 때 ${UserData.nickname} 님은?"
            3 -> "여행 경비를\n정할 때 ${UserData.nickname} 님은?"
            4 -> "여행을 다닐 때\n${UserData.nickname} 님은?"
            else -> "여행에서 가장\n하고 싶은 것은?"
        },
        color = getColor().main,
        textAlign = TextAlign.Center,
        style = largeTitle02
    )
}