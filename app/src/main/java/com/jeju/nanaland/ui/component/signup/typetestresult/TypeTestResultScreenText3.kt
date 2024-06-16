package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.jeju.nanaland.R
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
import com.jeju.nanaland.util.resource.getString

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
                    TYPE_GAMGYUL_ICECREAM -> getString(R.string.type_감귤_아이스크림_description)
                    TYPE_GAMGYUL_RICECAKE -> getString(R.string.type_감귤_찹쌀떡_description)
                    TYPE_GAMGYUL -> getString(R.string.type_감귤_description)
                    TYPE_GAMGYUL_CIDER -> getString(R.string.type_감귤_사이다_description)
                    TYPE_GAMGYUL_AFFOKATO -> getString(R.string.type_감귤_아포가토_description)
                    TYPE_GAMGYUL_HANGWA -> getString(R.string.type_감귤_한과_description)
                    TYPE_GAMGYUL_JUICE -> getString(R.string.type_감귤_주스_description)
                    TYPE_GAMGYUL_CHOCOLATE -> getString(R.string.type_감귤_초콜릿_description)
                    TYPE_GAMGYUL_COCKTAIL -> getString(R.string.type_감귤_칵테일_description)
                    TYPE_TANGERINE_PEEL_TEA -> getString(R.string.type_귤피차_description)
                    TYPE_GAMGYUL_YOGURT -> getString(R.string.type_감귤_요거트_description)
                    TYPE_GAMGYUL_FLATCCINO -> getString(R.string.type_감귤_플랫치노_description)
                    TYPE_GAMGYUL_LATTE -> getString(R.string.type_감귤_라떼_description)
                    TYPE_GAMGYUL_SIKHYE -> getString(R.string.type_감귤_식혜_description)
                    TYPE_GAMGYUL_ADE -> getString(R.string.type_감귤_에이드_description)
                    TYPE_GAMGYUL_BUBBLE_TEA -> getString(R.string.type_감귤_버블티_description)
                    else -> ""
                })
            }
            withStyle(
                style = bodyBold.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append("\n\n" + getString(R.string.type_test_screen_text6))
            }
        },
        textAlign = TextAlign.Center
    )
}