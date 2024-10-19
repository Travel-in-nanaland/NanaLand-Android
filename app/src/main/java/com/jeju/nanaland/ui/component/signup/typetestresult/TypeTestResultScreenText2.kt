package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.TravelType
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01
import com.jeju.nanaland.util.resource.getString

//@Composable
//fun TypeTestResultScreenText2(travelType: TravelType,) {
//    Text(
//        text = when (travelType) {
//            TravelType.GAMGYUL_ICECREAM -> getString(R.string.type_감귤_아이스크림)
//            TravelType.GAMGYUL_RICECAKE -> getString(R.string.type_감귤_찹쌀떡)
//            TravelType.GAMGYUL -> getString(R.string.type_감귤)
//            TravelType.GAMGYUL_CIDER -> getString(R.string.type_감귤_사이다)
//            TravelType.GAMGYUL_AFFOKATO -> getString(R.string.type_감귤_아포가토)
//            TravelType.GAMGYUL_HANGWA -> getString(R.string.type_감귤_한과)
//            TravelType.GAMGYUL_JUICE -> getString(R.string.type_감귤_주스)
//            TravelType.GAMGYUL_CHOCOLATE -> getString(R.string.type_감귤_초콜릿)
//            TravelType.GAMGYUL_COCKTAIL -> getString(R.string.type_감귤_칵테일)
//            TravelType.TANGERINE_PEEL_TEA -> getString(R.string.type_귤피차)
//            TravelType.GAMGYUL_YOGURT -> getString(R.string.type_감귤_요거트)
//            TravelType.GAMGYUL_FLATCCINO -> getString(R.string.type_감귤_플랫치노)
//            TravelType.GAMGYUL_LATTE -> getString(R.string.type_감귤_라떼)
//            TravelType.GAMGYUL_SIKHYE -> getString(R.string.type_감귤_식혜)
//            TravelType.GAMGYUL_ADE -> getString(R.string.type_감귤_에이드)
//            TravelType.GAMGYUL_BUBBLE_TEA -> getString(R.string.type_감귤_버블티)
//        },
//        color = getColor().main,
//        style = largeTitle01
//    )
//}