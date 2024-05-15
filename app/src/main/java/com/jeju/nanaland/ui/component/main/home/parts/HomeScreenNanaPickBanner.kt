package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.util.ui.ComponentPreviewBlack
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun HomeScreenNanaPickBanner(
    item: NanaPickBannerData,
    height: Int = 220,
    onClick: (Long) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .clickableNoEffect { onClick(item.id) }
    ) {
        HomeScreenNanaPickBannerImage(imageUri = item.thumbnailUrl)

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
        HomeScreenNanaPickBanner(
            item = NanaPickBannerData(
                id = 0,
                thumbnailUrl = "",
                version = "Version",
                subHeading = "Subheading",
                heading = "Heading"
            ),
            onClick = {}
        )
    }
}