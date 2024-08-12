package com.jeju.nanaland.ui.component.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.TOP_BAR_HEIGHT
import com.jeju.nanaland.ui.component.detailscreen.other.parts.description.DetailScreenFavoriteButton
import com.jeju.nanaland.ui.component.detailscreen.other.parts.description.DetailScreenFavoriteButton2
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.jeju.nanaland.util.ui.drawColoredShadow

@Composable
fun ReviewBottomBar(
    isFavorite: Boolean,
    toggleFavorite: () -> Unit,
    moveToReviewWritingScreen: () -> Unit,
    moveToSignInScreen: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_BAR_HEIGHT.dp)
            .clip(
                GenericShape { size, _ ->
                    moveTo(0f, -100f)
                    lineTo(size.width, -100f)
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                }
            )
            .zIndex(10f)
            .drawColoredShadow(
                color = getColor().black,
                alpha = 0.1f,
                shadowRadius = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp
            )
            .background(getColor().white)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(16.dp))

        DetailScreenFavoriteButton2(
            isFavorite = isFavorite,
            onClick = toggleFavorite,
            moveToSignInScreen = moveToSignInScreen
        )

        Spacer(Modifier.width(10.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .background(
                    color = getColor().main,
                    shape = RoundedCornerShape(50)
                )
                .clickableNoEffect { moveToReviewWritingScreen() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "리뷰 작성하기",
                color = getColor().white,
                style = bodyBold
            )
        }

        Spacer(Modifier.width(16.dp))
    }
}