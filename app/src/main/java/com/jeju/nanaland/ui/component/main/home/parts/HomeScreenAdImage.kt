package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.jeju.nanaland.R

@Composable
fun HomeScreenAdImage(idx: Int) {
    Image(
        painter = painterResource(when (idx % 4) {
            0 -> R.drawable.img_ad_1
            1 -> R.drawable.img_ad_2
            2 -> R.drawable.img_ad_3
            else -> R.drawable.img_ad_4
        }),
        contentDescription = null
    )
}