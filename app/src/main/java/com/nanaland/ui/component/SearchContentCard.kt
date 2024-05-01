package com.nanaland.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.body02Bold
import com.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SearchContentCard(
    title: String?,
    imageUrl: String?
) {
    SearchContentCard(
        title = title,
        imageUrl = imageUrl,
        isContent = true
    )
}

@Composable
private fun SearchContentCard(
    title: String?,
    imageUrl: String?,
    isContent: Boolean
) {
    val isLiked = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 30.dp)
    ) {
        Column(
            modifier = Modifier.width(160.dp)
        ) {
            GlideImage(
                modifier = Modifier
                    .width(160.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(getColor().skyblue),
                imageModel = { imageUrl }
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = title ?: "",
                color = getColor().black,
                style = body02Bold
            )
        }
        Image(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
                .size(28.dp)
                .clickableNoEffect {
                    isLiked.value = !isLiked.value
                },
            painter = painterResource(id = if (isLiked.value) R.drawable.ic_heart_filled else R.drawable.ic_heart_outlined_white),
            contentDescription = null,
            colorFilter = if (isLiked.value) ColorFilter.tint(getColor().main) else null
        )
    }
}

@Preview
@Composable
private fun SearchContentCardPreview() {

}