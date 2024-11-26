package com.jeju.nanaland.ui.profile.root.component.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.bodySemiBold
import com.jeju.nanaland.ui.theme.dropShadow
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun ProfileScreenTopPart (
    reviewSize: Int,
    moveToReviewScreen: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .dropShadow(
                positionX = 0,
                positionY = -3,
                blur = 10,
                spread = 0,
                color = 0x262627,
                alpha = 10,
                shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)
            )
            .background(
                getColor().white,
                shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)
            )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = getString(R.string.common_후기),
                color = getColor().black,
                style = bodyBold,
            )
            Spacer(Modifier.weight(1f))

            Row(
                modifier = Modifier.clickableNoEffect(moveToReviewScreen),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = reviewSize.toString(),
                    color = getColor().main,
                    style = bodySemiBold,
                )
                Text(
                    text = getString(R.string.mypage_screen_모두보기),
                    color = getColor().black,
                    style = body02SemiBold,
                )
                Image(
                    modifier = Modifier.size(12.dp),
                    painter = painterResource(R.drawable.ic_arrow_right),
                    contentDescription = null,
                )
            }
        }
    }
}