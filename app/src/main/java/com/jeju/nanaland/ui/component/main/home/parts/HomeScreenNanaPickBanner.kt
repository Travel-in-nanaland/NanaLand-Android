package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.jeju.nanaland.globalvalue.constant.SYSTEM_STATUS_BAR_HEIGHT
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.util.ui.ComponentPreviewBlack
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun HomeScreenNanaPickBanner(
    item: NanaPickBannerData,
    height: Int = 300,
    onClick: (Int) -> Unit,
) {
    val brush = remember { Brush.verticalGradient(listOf(Color.Transparent, Color(0x99262627))) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height((height + SYSTEM_STATUS_BAR_HEIGHT).dp)
            .clickableNoEffect { onClick(item.id) }
    ) {
        HomeScreenNanaPickBannerImage(imageUri = item.firstImage.originUrl)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRect(
                        brush = brush,
                        topLeft = Offset.Zero,
                        size = Size(this.size.width, this.size.height)
                    )
                }
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            HomeScreenNanaPickBannerSubTitle(text = item.heading)

            HomeScreenNanaPickBannerTitle(text = item.subHeading)
        }
//        HomeScreenNanaPickBannerVersion(text = item.version)
    }
}

@ComponentPreviewBlack
@Composable
private fun NanaPickItemPreview() {
    NanaLandTheme {
    }
}