package com.nanaland.ui.component.home.main.parts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.util.ui.ComponentPreview

@Composable
fun BoxScope.HomeScreenTopBannerPageIndicator(
    itemCnt: Int,
    pageNum: Int
) {
    Box(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(end = 20.dp, bottom = 20.dp)
            .width(40.dp)
            .height(20.dp)
            .background(
                color = Color(0x7F151515),
                shape = RoundedCornerShape(50)
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = Color(0x7FFFFFFF)
                ),
                shape = RoundedCornerShape(50)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.width(30.dp)
        ) {
            HomeScreenTopBannerPageIndicatorText(text = "${(pageNum % itemCnt) + 1}")

            HomeScreenTopBannerPageIndicatorText(text = "/")

            HomeScreenTopBannerPageIndicatorText(text = "$itemCnt")
        }
    }
}

@ComponentPreview
@Composable
private fun HomeScreenTopBannerPageIndicatorPreview() {
    NanaLandTheme {
        Box {
            HomeScreenTopBannerPageIndicator(
                itemCnt = 4,
                pageNum = 4
            )
        }
    }
}