package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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

@Composable
fun TypeTestResultScreenImage(travelType: String) {
    Image(
        modifier = Modifier
//            .fillMaxWidth(),
            .size(200.dp)
            .clip(CircleShape),
        painter = painterResource(when (travelType) {
            TYPE_GAMGYUL_ICECREAM -> R.drawable.img_type_gamgyul_icecream
            TYPE_GAMGYUL_RICECAKE -> R.drawable.img_type_gamgyul_ricecake
            TYPE_GAMGYUL -> R.drawable.img_type_gamgyul
            TYPE_GAMGYUL_CIDER -> R.drawable.img_type_gamgyul_cider
            TYPE_GAMGYUL_AFFOKATO -> R.drawable.img_type_gamgyul_affokato
            TYPE_GAMGYUL_HANGWA -> R.drawable.img_type_hangwa
            TYPE_GAMGYUL_JUICE -> R.drawable.img_type_gamgyul_juice
            TYPE_GAMGYUL_CHOCOLATE -> R.drawable.img_type_gamgyul_chocolate
            TYPE_GAMGYUL_COCKTAIL -> R.drawable.img_type_gamgyul_cocktail
            TYPE_TANGERINE_PEEL_TEA -> R.drawable.img_type_tangerine_peel_tea
            TYPE_GAMGYUL_YOGURT -> R.drawable.img_type_gamgyul_yogurt
            TYPE_GAMGYUL_FLATCCINO -> R.drawable.img_type_gamgyul_flatccino
            TYPE_GAMGYUL_LATTE -> R.drawable.img_type_gamgyul_latte
            TYPE_GAMGYUL_SIKHYE -> R.drawable.img_type_gamgyul_sikhye
            TYPE_GAMGYUL_ADE -> R.drawable.img_type_gamgyul_ade
            else -> R.drawable.img_type_gamgyul_bubble_tea
        }),
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    )
}