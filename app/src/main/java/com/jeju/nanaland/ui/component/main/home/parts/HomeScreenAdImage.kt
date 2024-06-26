package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R

@Composable
fun HomeScreenAdImage(idx: Int) {
    Image(
        modifier = Modifier.width(when (idx) {
            1 -> 50
            2 -> 66
            3 -> 70
            else -> 70
        }.dp),
        painter = painterResource(when (idx % 4) {
            0 -> R.drawable.img_ad_1
            1 -> R.drawable.img_ad_2
            2 -> R.drawable.img_ad_3
            else -> R.drawable.img_ad_4
        }),
        contentDescription = null
    )
}