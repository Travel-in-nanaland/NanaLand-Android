package com.nanaland.ui.nanapick

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nanaland.domain.entity.nanapick.NanaPickThumbnail
import com.nanaland.ui.theme.bodyBold
import com.nanaland.ui.theme.caption01SemiBold
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NanaPickThumbnail(
    item: NanaPickThumbnail,
    height: Int = 180,
    onClick: (Long) -> Unit
) {
    NanaPickThumbnail(
        item = item,
        height = height,
        onClick = onClick,
        tmp = true
    )
}

@Composable
private fun NanaPickThumbnail(
    item: NanaPickThumbnail,
    height: Int,
    onClick: (Long) -> Unit,
    tmp: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .clickableNoEffect { onClick(item.id) }
    ) {
        GlideImage(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF7C7C7C)),
            imageModel = { item.thumbnailUrl }
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = item.subHeading ?: "",
                color = getColor().white,
                style = bodyBold
            )
            Text(
                text = item.heading ?: "",
                color = getColor().white
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            text = "나나's Pick vol.${item.version}",
            color = getColor().white,
            style = caption01SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NanaPickItemPreview() {
    NanaPickThumbnail(
        item = NanaPickThumbnail(
            id = 0,
            thumbnailUrl = "",
            version = null,
            subHeading = null,
            heading = null
        ),
        height = 180,
        onClick = {},
        tmp = true
    )
}