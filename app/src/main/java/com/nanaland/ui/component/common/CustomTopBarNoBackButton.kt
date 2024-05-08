package com.nanaland.ui.component.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nanaland.R
import com.nanaland.globalvalue.constant.TOP_BAR_HEIGHT
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.title01Bold
import com.nanaland.util.ui.ScreenPreview
import com.nanaland.util.ui.clickableNoEffect
import com.nanaland.util.ui.drawColoredShadow

@Composable
fun CustomTopBarNoBackButton(
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_BAR_HEIGHT.dp)
            .clip(
                GenericShape { size, _ ->
                    moveTo(0f, 0f)
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height + 100f)
                    lineTo(0f, size.height + 100f)
                }
            )
            .zIndex(10f)
            .drawColoredShadow(
                color = getColor().black,
                alpha = 0.1f,
                shadowRadius = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp
            )
            .background(getColor().white)
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = getColor().black,
            style = title01Bold
        )
    }
}

@ScreenPreview
@Composable
private fun CustomTopBarNoBackButtonPreview() {
    NanaLandTheme {
        CustomTopBarNoBackButton(
            title = "나나 Pick",
        )
    }
}