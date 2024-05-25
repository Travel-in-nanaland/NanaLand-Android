package com.jeju.nanaland.ui.component.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun TagChip1(
    text: String?
) {
    Box(
        modifier = Modifier
            .height(20.dp)
            .background(
                color = getColor().main10,
                shape = RoundedCornerShape(50)
            )
            .padding(start = 10.dp, top = 2.dp, end = 10.dp, bottom = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text ?: "",
            color = getColor().main,
            style = caption01
        )
    }
}

@ComponentPreview
@Composable
private fun ThumbnailTagPreview() {
    NanaLandTheme {
        TagChip1(
            text = "서귀포시"
        )
    }
}