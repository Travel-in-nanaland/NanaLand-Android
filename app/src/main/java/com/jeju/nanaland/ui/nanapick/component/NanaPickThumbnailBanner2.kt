package com.jeju.nanaland.ui.nanapick.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.caption02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NanaPickThumbnailBanner2(
    item: NanaPickBannerData,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.clickableNoEffect { onClick(item.id) }
    ) {
        Box {
            GlideImage(
                modifier = Modifier.aspectRatio(3/4f),
                imageModel = { item.firstImage.thumbnailUrl },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop)
            )

            item.newest?.let {
                if (item.newest) {
                    NewTag(padding = 8)
                }
            }
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = item.heading,
            color = getColor().black,
            style = body02Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = item.subHeading,
            color = getColor().black,
            style = caption02
        )
    }
}