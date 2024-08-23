package com.jeju.nanaland.ui.nanapick.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.jeju.nanaland.util.ui.drawColoredShadow
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NanaPickThumbnailBanner1(
    item: NanaPickBannerData,
    onClick: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .drawColoredShadow(
                alpha = 0.1f,
                shadowRadius = 6.dp
            )
            .clip(RoundedCornerShape(8.dp))
            .background(getColor().white)
            .clickableNoEffect { onClick(item.id) }
    ) {
        Row() {
            Box(
                modifier = Modifier
                    .width(72.dp)
                    .fillMaxHeight()
            ) {
                GlideImage(
                    modifier = Modifier.fillMaxSize(),
                    imageModel = { item.firstImage.thumbnailUrl },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop)
                )

                item.newest?.let {
                    if (it) {
                        NewTag(padding = 3)
                    }
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.heading,
                    color = getColor().black,
                    style = bodyBold
                )

                Text(
                    text = item.version,
                    color = getColor().black,
                    style = caption01
                )
            }
        }

        Image(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp)
                .width(24.dp),
            painter = painterResource(R.drawable.ic_right_arrow_3),
            contentDescription = null
        )
    }
}