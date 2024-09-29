package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.TravelType
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun TypeTestResultScreenText3(travelType: TravelType) {
    Text(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        text = buildAnnotatedString {
            withStyle(
                style = bodyBold.toSpanStyle().copy(
                    color = getColor().main
                )
            ) {
                append(when (travelType) {
                    TravelType.GAMGYUL_ICECREAM -> getString(R.string.type_감귤_아이스크림_description)
                    TravelType.GAMGYUL_RICECAKE -> getString(R.string.type_감귤_찹쌀떡_description)
                    TravelType.GAMGYUL -> getString(R.string.type_감귤_description)
                    TravelType.GAMGYUL_CIDER -> getString(R.string.type_감귤_사이다_description)
                    TravelType.GAMGYUL_AFFOKATO -> getString(R.string.type_감귤_아포가토_description)
                    TravelType.GAMGYUL_HANGWA -> getString(R.string.type_감귤_한과_description)
                    TravelType.GAMGYUL_JUICE -> getString(R.string.type_감귤_주스_description)
                    TravelType.GAMGYUL_CHOCOLATE -> getString(R.string.type_감귤_초콜릿_description)
                    TravelType.GAMGYUL_COCKTAIL -> getString(R.string.type_감귤_칵테일_description)
                    TravelType.TANGERINE_PEEL_TEA -> getString(R.string.type_귤피차_description)
                    TravelType.GAMGYUL_YOGURT -> getString(R.string.type_감귤_요거트_description)
                    TravelType.GAMGYUL_FLATCCINO -> getString(R.string.type_감귤_플랫치노_description)
                    TravelType.GAMGYUL_LATTE -> getString(R.string.type_감귤_라떼_description)
                    TravelType.GAMGYUL_SIKHYE -> getString(R.string.type_감귤_식혜_description)
                    TravelType.GAMGYUL_ADE -> getString(R.string.type_감귤_에이드_description)
                    TravelType.GAMGYUL_BUBBLE_TEA -> getString(R.string.type_감귤_버블티_description)
                })
            }
            withStyle(
                style = body01.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append("\n\n" + getString(R.string.type_test_screen_text_result_body))
            }
        },
        textAlign = TextAlign.Center
    )
}