package com.jeju.nanaland.ui.profile.root.component.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.review.MemberReviewDetail
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.jeju.nanaland.util.ui.conditional
import com.jeju.nanaland.util.ui.drawColoredShadow
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileReviewRow(
    data: MemberReviewDetail,
    onClick: (Int) -> Unit,
    onMenuClick: (MemberReviewDetail) -> Unit,
) {
    val haveImg = data.images.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (haveImg) 210.dp else 105.dp)
            .clickableNoEffect { onClick(data.id) }
            .drawColoredShadow(
                color = Color.Black,
                shadowRadius = 8.dp,
                heightControl = (-5).dp,
                widthControl = (-5).dp,
            )
            .clip(
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = getColor().white,
            )
    ) {
        if(haveImg)
            GlideImage (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp,8.dp,0.dp,0.dp)),
                imageModel = { data.images.firstOrNull()?.originUrl }
            )

        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp)
                .conditional(!haveImg) { weight(1f) },
            text = data.placeName,
            color = getColor().black,
            style = body02Bold,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = data.createdAt.replace('-','.'),
                color = getColor().black,
                style = caption01,
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = getColor().main
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = data.heartCount.toString(),
                color = getColor().black,
                style = caption01,
            )
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .clickableNoEffect { onMenuClick(data) },
                painter = painterResource(R.drawable.ic_more_vert),
                contentDescription = null,
                colorFilter = ColorFilter.tint(getColor().gray01)
            )
        }

    }
}
