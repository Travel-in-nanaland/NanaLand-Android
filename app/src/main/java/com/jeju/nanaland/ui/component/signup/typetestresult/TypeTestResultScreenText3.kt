package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
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
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun TypeTestResultScreenText3(travelType: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = bodyBold.toSpanStyle().copy(
                    color = getColor().main
                )
            ) {
                append(when (travelType) {
                    TYPE_GAMGYUL_ICECREAM -> "부드러우면서도, 달콤한 기억을 선사해주는 감귤아이스크림 유형"
                    TYPE_GAMGYUL_RICECAKE -> "통통튀는 즐거움을 메모장에 하나씩 수 놓는, 감귤 찹쌀떡 유형"
                    TYPE_GAMGYUL -> "가장 기본적이면서도 유명한 곳으로만! 감귤유형"
                    TYPE_GAMGYUL_CIDER -> "톡톡쏘면서 상큼한 감귤사이다유형"
                    TYPE_GAMGYUL_AFFOKATO -> "열정적이면서 활발한 당신에게 창의성이 가득한 감귤 아포카토 유형"
                    TYPE_GAMGYUL_HANGWA -> "다채로운 제주의 맛을 담은 명품 감귤한과"
                    TYPE_GAMGYUL_JUICE -> "가장 기본적이면서도 유명한 곳으로만! 감귤주스 유형"
                    TYPE_GAMGYUL_CHOCOLATE -> "취향이 확고하고 많은 것보다는 좋은 것이 중요한 감귤 초콜릿 유형"
                    TYPE_GAMGYUL_COCKTAIL -> "화려하고 익스트림한 감귤 칵테일 유형"
                    TYPE_TANGERINE_PEEL_TEA -> "고즈넉하게 풍경을 바라보는 귤피차 유형"
                    TYPE_GAMGYUL_YOGURT -> "있는 그대로의 맛! 부드럽고 톡 쏘는 감귤요거트 유형"
                    TYPE_GAMGYUL_FLATCCINO -> "각종 과일들의 상큼함과 얼음의 차가움을 한번에, 창의적인 감귤 플랫치노 유형"
                    TYPE_GAMGYUL_LATTE -> "따뜻한 햇살 밑에서 유유자적 하는 감귤 라떼 유형!"
                    TYPE_GAMGYUL_SIKHYE -> "풍미 가득한! 느리지만, 깊은 감귤식혜유형!"
                    TYPE_GAMGYUL_ADE -> "톡 쏘는 탄산수에 싱그러운 과일이 퐁당! 청량감이 느껴지는 감귤에이드"
                    TYPE_GAMGYUL_BUBBLE_TEA -> "호기심 가득한 당신! 먹을수록 반전인 감귤 버블티 유형!"
                    else -> ""
                })
            }
            withStyle(
                style = bodyBold.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append("\n\n" + "nanaland in Jeju가 당신을 위해\n맞춤 여행 주스를 만들었어요!")
            }
        },
        textAlign = TextAlign.Center
    )
}