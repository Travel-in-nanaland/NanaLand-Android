package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.TravelType

@Composable
fun TypeTestResultScreenImage(travelType: TravelType) {
    Image(
        modifier = Modifier
//            .fillMaxWidth(),
            .size(200.dp)
            .clip(CircleShape),
        painter = painterResource(when (travelType) {
            TravelType.GAMGYUL_ICECREAM -> R.drawable.img_type_gamgyul_icecream
            TravelType.GAMGYUL_RICECAKE -> R.drawable.img_type_gamgyul_ricecake
            TravelType.GAMGYUL -> R.drawable.img_type_gamgyul
            TravelType.GAMGYUL_CIDER -> R.drawable.img_type_gamgyul_cider
            TravelType.GAMGYUL_AFFOKATO -> R.drawable.img_type_gamgyul_affokato
            TravelType.GAMGYUL_HANGWA -> R.drawable.img_type_hangwa
            TravelType.GAMGYUL_JUICE -> R.drawable.img_type_gamgyul_juice
            TravelType.GAMGYUL_CHOCOLATE -> R.drawable.img_type_gamgyul_chocolate
            TravelType.GAMGYUL_COCKTAIL -> R.drawable.img_type_gamgyul_cocktail
            TravelType.TANGERINE_PEEL_TEA -> R.drawable.img_type_tangerine_peel_tea
            TravelType.GAMGYUL_YOGURT -> R.drawable.img_type_gamgyul_yogurt
            TravelType.GAMGYUL_FLATCCINO -> R.drawable.img_type_gamgyul_flatccino
            TravelType.GAMGYUL_LATTE -> R.drawable.img_type_gamgyul_latte
            TravelType.GAMGYUL_SIKHYE -> R.drawable.img_type_gamgyul_sikhye
            TravelType.GAMGYUL_ADE -> R.drawable.img_type_gamgyul_ade
            TravelType.GAMGYUL_BUBBLE_TEA -> R.drawable.img_type_gamgyul_bubble_tea
        }),
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    )
}