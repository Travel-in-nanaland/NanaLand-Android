package com.jeju.nanaland.ui.component.review

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.ui.theme.title02Bold

@Composable
fun TotalReviewCountText(count: Int) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "후기",
            color = getColor().black,
            style = title01Bold
        )

        Spacer(Modifier.width(4.dp))

        Text(
            text = count.toString(),
            color = getColor().main,
            style = title02Bold
        )
    }
}