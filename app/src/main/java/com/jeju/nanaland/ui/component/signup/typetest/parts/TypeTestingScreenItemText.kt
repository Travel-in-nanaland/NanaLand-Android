package com.jeju.nanaland.ui.component.signup.typetest.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun TypeTestingScreenItemText(itemIdx: Int) {
    Text(
        text = when (itemIdx) {
            1 -> "활발한 관광 장소"
            2 -> "한적한 로컬 장소"
            3 -> "유동적"
            4 -> "완전 계획적"
            5 -> "가성비 여행"
            6 -> "럭셔리 여행"
            7 -> "남는건 사진뿐!"
            8 -> "눈으로 담아야지"
            9 -> "감성 장소"
            10 -> "전통 문화"
            11 -> "자연 경관"
            else -> "테마파크"
        },
        color = getColor().black,
        style = body02SemiBold
    )
}