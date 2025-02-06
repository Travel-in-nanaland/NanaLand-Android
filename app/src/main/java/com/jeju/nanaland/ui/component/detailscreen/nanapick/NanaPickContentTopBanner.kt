package com.jeju.nanaland.ui.component.detailscreen.nanapick

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.nanapick.NanaPickContentData
import com.jeju.nanaland.ui.component.common.dialog.FullImageDialog
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerFavoriteButton
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerShareButton
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerSubTitle
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerTitle
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.intent.goToShare
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NanaPickContentTopBanner(
    height: Int,
    isEllipsis: Boolean,
    contentId: Int?,
    nanaPickContent: UiState.Success<NanaPickContentData>,
    onBackButtonClicked: () -> Unit,
    toggleFavorite: () -> Unit,
    moveToSignInScreen: () -> Unit,
) {
    val context = LocalContext.current
    val brush = remember { Brush.verticalGradient(listOf(Color.Transparent, Color(0x99262627))) }

    var fullImageUrl by remember { mutableStateOf<String?>(null) }

    fullImageUrl?.let {
        FullImageDialog(it) { fullImageUrl = null }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .background(getColor().skeleton)
    ) {
        GlideImage(
            modifier = Modifier.fillMaxSize().clickableNoEffect { fullImageUrl = nanaPickContent.data.firstImage.originUrl },
            imageModel = { nanaPickContent.data.firstImage.originUrl }
        )

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
            Modifier.padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .clickableNoEffect { onBackButtonClicked() },
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = null,
                    tint = getColor().white
                )

                Spacer(modifier = Modifier.weight(1f))

                NanaPickContentTopBannerFavoriteButton(
                    isFavorite = nanaPickContent.data.favorite,
                    onClick = { toggleFavorite() },
                    moveToSignInScreen = moveToSignInScreen,
                )

                Spacer(Modifier.width(8.dp))

                NanaPickContentTopBannerShareButton(
                    onClick = { goToShare(context, "nanapick",contentId) }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            NanaPickContentTopBannerSubTitle(text = nanaPickContent.data.subHeading, isEllipsis)
            NanaPickContentTopBannerTitle(text = nanaPickContent.data.heading, isEllipsis)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@ScreenPreview
@Composable
private fun NanaPickContentTopBannerPreview() {

}