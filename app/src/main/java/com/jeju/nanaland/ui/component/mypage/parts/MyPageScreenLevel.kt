package com.jeju.nanaland.ui.component.mypage.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun BoxScope.MyPageScreenLevel(level: Int) {
    Box(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .width(44.dp)
            .height(20.dp)
            .background(
                color = getColor().main,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = getString(R.string.mypage_screen_level) + "$level",
            color = getColor().white,
            style = caption01
        )
    }
}