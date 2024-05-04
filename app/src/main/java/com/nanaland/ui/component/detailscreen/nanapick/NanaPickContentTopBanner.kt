package com.nanaland.ui.component.detailscreen.nanapick

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
import com.nanaland.domain.entity.nanapick.NanaPickContentData
import com.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerLikeButton
import com.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerShareButton
import com.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerSubTitle
import com.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerTitle
import com.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerVersion
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ScreenPreview
import com.nanaland.util.ui.UiState
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NanaPickContentTopBanner(
    nanaPickContent: UiState.Success<NanaPickContentData>
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

        NanaPickContentTopBannerLikeButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 56.dp),
            isLiked = nanaPickContent.data.favorite,
            onClick = {}
        )
    }
}

@ScreenPreview
@Composable
private fun NanaPickContentTopBannerPreview() {

}