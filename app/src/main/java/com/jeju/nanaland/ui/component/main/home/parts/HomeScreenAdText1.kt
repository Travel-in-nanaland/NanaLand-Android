package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun HomeScreenAdText1(idx: Int) {
    Text(
        text = when (idx % 4) {
            0 -> getString(R.string.home_screen_ad1_title)
            1 -> getString(R.string.home_screen_ad2_title)
            2 -> getString(R.string.home_screen_ad3_title)
            else -> getString(R.string.home_screen_ad4_title)
        },
        color = when (idx % 4) {
            1 -> getColor().white
            else -> getColor().black
        },
        style = bodyBold
    )
}