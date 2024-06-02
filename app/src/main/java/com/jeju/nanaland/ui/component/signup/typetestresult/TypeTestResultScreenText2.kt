package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_ADE
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_AFFOKATO
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_BUBBLE_TEA
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_CHOCOLATE
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_CIDER
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_COCKTAIL
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_FLATCCINO
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_HANGWA
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_ICECREAM
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_JUICE
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_LATTE
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_RICECAKE
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_SIKHYE
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_YOGURT
import com.jeju.nanaland.globalvalue.constant.TYPE_TANGERINE_PEEL_TEA
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01

@Composable
fun TypeTestResultScreenText2(travelType: String,) {
    Text(
        text = when (travelType) {
            TYPE_GAMGYUL_ICECREAM -> "감귤 아이스크림"
            TYPE_GAMGYUL_RICECAKE -> "감귤 찹쌀떡"
            TYPE_GAMGYUL -> "감귤"
            TYPE_GAMGYUL_CIDER -> "감귤사이다"
            TYPE_GAMGYUL_AFFOKATO -> "감귤 아포가토"
            TYPE_GAMGYUL_HANGWA -> "감귤한과"
            TYPE_GAMGYUL_JUICE -> "감귤주스"
            TYPE_GAMGYUL_CHOCOLATE -> "감귤 초콜릿"
            TYPE_GAMGYUL_COCKTAIL -> "감귤 칵테일"
            TYPE_TANGERINE_PEEL_TEA -> "귤피차"
            TYPE_GAMGYUL_YOGURT -> "감귤 요거트"
            TYPE_GAMGYUL_FLATCCINO -> "감귤 플랫치노"
            TYPE_GAMGYUL_LATTE -> "감귤 라떼"
            TYPE_GAMGYUL_SIKHYE -> "감귤식혜"
            TYPE_GAMGYUL_ADE -> "감귤에이드"
            TYPE_GAMGYUL_BUBBLE_TEA -> "감귤 버블티"
            else -> ""
        },
        color = getColor().main,
        style = largeTitle01
    )
}