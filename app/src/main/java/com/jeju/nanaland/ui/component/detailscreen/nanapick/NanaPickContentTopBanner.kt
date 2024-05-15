package com.jeju.nanaland.ui.component.detailscreen.nanapick

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.nanapick.NanaPickContentData
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerSubTitle
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerTitle
import com.jeju.nanaland.util.ui.UiState
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerFavoriteButton
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerShareButton
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerVersion
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ScreenPreview
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NanaPickContentTopBanner(
    nanaPickContent: UiState.Success<NanaPickContentData>,
    toggleFavorite: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .background(getColor().skeleton)
    ) {
        GlideImage(
            modifier = Modifier.fillMaxSize(),
            imageModel = { nanaPickContent.data.originUrl }
        )

        NanaPickContentTopBannerVersion(
            modifier = Modifier.align(Alignment.TopStart),
            text = ""
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 20.dp)
        ) {
            NanaPickContentTopBannerSubTitle(text = nanaPickContent.data.subHeading)

            NanaPickContentTopBannerTitle(text = nanaPickContent.data.heading)
        }

        NanaPickContentTopBannerShareButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 16.dp),
            onClick = {}
        )

        NanaPickContentTopBannerFavoriteButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 56.dp),
            isFavorite = nanaPickContent.data.favorite,
            onClick = { toggleFavorite() }
        )
    }
}

@ScreenPreview
@Composable
private fun NanaPickContentTopBannerPreview() {

}