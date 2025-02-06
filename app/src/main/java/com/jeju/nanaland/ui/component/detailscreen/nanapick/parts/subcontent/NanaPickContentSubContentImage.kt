package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.common.ImageUrl
import com.jeju.nanaland.ui.component.common.dialog.FullImageDialog
import com.jeju.nanaland.ui.theme.caption02SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NanaPickContentSubContentImage(imageUri: List<ImageUrl>) {
    val pageCount = remember(imageUri) { imageUri.size }
    val pagerState = rememberPagerState(
        pageCount = { pageCount }
    )
    var fullImageUrl by remember { mutableStateOf<String?>(null) }

    fullImageUrl?.let {
        FullImageDialog(it) { fullImageUrl = null }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(217.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        HorizontalPager(state = pagerState) { page ->
            GlideImage(
                modifier = Modifier.fillMaxSize().clickableNoEffect { fullImageUrl = imageUri[page].originUrl },
                imageModel = { imageUri[page].thumbnailUrl },
            )
        }
        if(pageCount > 1) {
            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(50))
                    .background(getColor().black50)
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = getColor().white50,
                        ),
                        shape = RoundedCornerShape(50)
                    )
                    .padding(horizontal = 9.dp, vertical = 1.dp),
                text = "${pagerState.currentPage + 1} / $pageCount",
                style = caption02SemiBold,
                color = getColor().white
            )
        }
    }
}