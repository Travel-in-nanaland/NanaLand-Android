package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun HomeScreenAdText2(idx: Int) {
    Text(
        text = when (idx % 4) {
            0 -> getString(R.string.home_screen_ad1_content)
            1 -> getString(R.string.home_screen_ad2_content)
            2 -> getString(R.string.home_screen_ad3_content)
            else -> getString(R.string.home_screen_ad4_content)
        },
        color = when (idx % 4) {
            1 -> getColor().white
            else -> getColor().black
        },
        style = caption01
    )
}