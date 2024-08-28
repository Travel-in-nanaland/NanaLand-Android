package com.jeju.nanaland.ui.component.review.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun ReviewCountText(count: Int) {
    Text(
        text = getString(R.string.common_리뷰) + " ${count}",
        color = getColor().black,
        style = caption01
    )
}