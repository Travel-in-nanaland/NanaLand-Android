package com.jeju.nanaland.ui.component.recommendedspot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.recommendedspot.parts.RecommendedSpotScreenLogoStamp
import com.jeju.nanaland.ui.component.recommendedspot.parts.RecommendedSpotScreenLogoTextStamp
import com.jeju.nanaland.ui.component.recommendedspot.parts.RecommendedSpotScreenSpotDescription
import com.jeju.nanaland.ui.component.recommendedspot.parts.RecommendedSpotScreenSpotName
import com.jeju.nanaland.util.ui.TicketShape
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun RecommendedSpotScreenItem(
    imageUri: String?,
    title: String,
    description: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(480.dp)
            .graphicsLayer {
                shadowElevation = 6.dp.toPx()
                shape = TicketShape(
                    ovalWidth = 86.dp.toPx(),
                    ovalHeight = 72.dp.toPx(),
                )
                clip = true
            }
    ) {
        GlideImage(
            modifier = Modifier.fillMaxSize(),
            imageModel = { imageUri },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop)
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 24.dp, top = 24.dp)
        ) {
            RecommendedSpotScreenLogoStamp()
        }

        Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
            Spacer(Modifier.weight(1f))

            RecommendedSpotScreenSpotName(text = title)

            Spacer(Modifier.height(4.dp))

            RecommendedSpotScreenSpotDescription(text = description)

            Spacer(Modifier.height(60.dp))
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp)
        ) {
            RecommendedSpotScreenLogoTextStamp()
        }
    }
}