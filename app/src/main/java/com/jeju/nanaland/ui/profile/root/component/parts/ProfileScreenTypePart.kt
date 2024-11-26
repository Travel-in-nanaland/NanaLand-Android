package com.jeju.nanaland.ui.profile.root.component.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.member.UserProfile
import com.jeju.nanaland.globalvalue.constant.TravelType
import com.jeju.nanaland.globalvalue.constant.toImageRes
import com.jeju.nanaland.globalvalue.constant.toViewString
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.caption02
import com.jeju.nanaland.ui.theme.dropShadow
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect


@Composable
fun ProfileScreenTypePart(
    profile: UserProfile,
    isMine: Boolean,
    moveToTypeTestScreen: () -> Unit,
    moveToTypeTestResultScreen: () -> Unit
) {
    Row(Modifier
        .fillMaxWidth()
        .height(76.dp)
        .padding(horizontal = 16.dp)
        .dropShadow(
            positionX = 0,
            positionY = 0,
            blur = 5,
            spread = 0,
            color = 0x262627,
            alpha = 15,
            shape = RoundedCornerShape(12.dp)
        )
        .background(
            color = getColor().white,
            shape = RoundedCornerShape(12.dp)
        )
        .padding(vertical = 12.dp)
        .padding(start = 12.dp)
    ) {
        if(profile.travelType != null) {
            Image(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clipToBounds()
                    .graphicsLayer {
                        scaleX = 1.23f
                        scaleY = 1.23f
                    }
                    .clickableNoEffect(moveToTypeTestResultScreen),
                painter = painterResource(profile.travelType.toImageRes()),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center

            )
            Spacer(modifier = Modifier.width(12.dp))

            Column(Modifier.fillMaxHeight()) {
                TypePart(profile.travelType)

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    profile.hashTags.forEach {
                        HashtagPart(text = it)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            if(isMine)
                TextWithArrow(getString(R.string.mypage_screen_다시하기), moveToTypeTestScreen)
            Spacer(modifier = Modifier.width(2.dp))

        } else {
            Column {
                TypePart(null)
                Spacer(modifier = Modifier.weight(1f))
                if(isMine)
                TextWithArrow(getString(R.string.mypage_screen_테스트_하기), moveToTypeTestScreen)
            }
        }
    }
}

@Composable
private fun TypePart(
    type: TravelType?
) {
    Text(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = getColor().main,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(horizontal = 12.dp, vertical = 2.dp),
        text = type?.toViewString() ?: getString(R.string.mypage_screen_없음),
        color = getColor().main,
        style = caption01
    )
}

@Composable
private fun HashtagPart(
    text: String
) {
    Text(
        text = "#$text",
        color = getColor().main,
        style = caption01
    )
}

@Composable
private fun TextWithArrow(
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.clickableNoEffect(onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = getColor().gray01,
            style = caption02
        )
        Icon(
            modifier = Modifier.size(10.dp),
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = null,
            tint = getColor().gray01
        )

        Spacer(modifier = Modifier.width(8.dp))
    }
}