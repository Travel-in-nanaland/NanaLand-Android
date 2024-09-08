package com.jeju.nanaland.ui.nanapick.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.caption02SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun NewTag(
    padding: Int,
) {
    Box(
        modifier = Modifier
            .padding(start = padding.dp, top = padding.dp)
            .width(36.dp)
            .height(18.dp)
            .clip(RoundedCornerShape(50))
            .background(getColor().main),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = getString(R.string.nanapick_list_screen_new),
            color = getColor().white,
            style = caption02SemiBold
        )
    }
}