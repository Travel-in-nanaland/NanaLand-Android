package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.util.ui.ComponentPreviewBlack
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun HomeScreenNanaPickBanner(
    item: NanaPickBannerData,
    height: Int = 220,
    onClick: (Int) -> Unit,
) {
    val brush = remember { Brush.verticalGradient(listOf(Color.Transparent, Color(0x99262627))) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .clickableNoEffect { onClick(item.id) }
    ) {
        HomeScreenNanaPickBannerImage(imageUri = item.firstImage?.thumbnailUrl)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height.dp)
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
        HomeScreenNanaPickBannerVersion(text = item.version)
    }
}

@ComponentPreviewBlack
@Composable
private fun NanaPickItemPreview() {
    NanaLandTheme {
    }
}