package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01
import com.jeju.nanaland.util.resource.getString

@Composable
fun TypeTestResultScreenText2(travelType: String,) {
    Text(
        text = when (travelType) {
            TYPE_GAMGYUL_ICECREAM -> getString(R.string.type_감귤_아이스크림)
            TYPE_GAMGYUL_RICECAKE -> getString(R.string.type_감귤_찹쌀떡)
            TYPE_GAMGYUL -> getString(R.string.type_감귤)
            TYPE_GAMGYUL_CIDER -> getString(R.string.type_감귤_사이다)
            TYPE_GAMGYUL_AFFOKATO -> getString(R.string.type_감귤_아포가토)
            TYPE_GAMGYUL_HANGWA -> getString(R.string.type_감귤_한과)
            TYPE_GAMGYUL_JUICE -> getString(R.string.type_감귤_주스)
            TYPE_GAMGYUL_CHOCOLATE -> getString(R.string.type_감귤_초콜릿)
            TYPE_GAMGYUL_COCKTAIL -> getString(R.string.type_감귤_칵테일)
            TYPE_TANGERINE_PEEL_TEA -> getString(R.string.type_귤피차)
            TYPE_GAMGYUL_YOGURT -> getString(R.string.type_감귤_요거트)
            TYPE_GAMGYUL_FLATCCINO -> getString(R.string.type_감귤_플랫치노)
            TYPE_GAMGYUL_LATTE -> getString(R.string.type_감귤_라떼)
            TYPE_GAMGYUL_SIKHYE -> getString(R.string.type_감귤_식혜)
            TYPE_GAMGYUL_ADE -> getString(R.string.type_감귤_에이드)
            TYPE_GAMGYUL_BUBBLE_TEA -> getString(R.string.type_감귤_버블티)
            else -> ""
        },
        color = getColor().main,
        style = largeTitle01
    )
}