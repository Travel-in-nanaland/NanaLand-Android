package com.jeju.nanaland.ui.profile.root.component.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.review.MemberReviewDetail
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.caption02
import com.jeju.nanaland.ui.theme.dropShadow
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
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
            .height(if (haveImg) 214.dp else 103.dp)
            .clickableNoEffect { onClick(data.id) }
            .dropShadow(
                positionX = 0,
                positionY = 0,
                blur = 5,
                spread = 0,
                color = 0x262627,
                alpha = 15,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = getColor().white,
                shape = RoundedCornerShape(8.dp)
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

        Spacer(Modifier.height(8.dp))

        Box {
            Image(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(20.dp)
                    .clickableNoEffect { onMenuClick(data) },
                painter = painterResource(R.drawable.ic_more_vert),
                contentDescription = null,
                colorFilter = ColorFilter.tint(getColor().gray01)
            )

            Column {
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .background(
                            color = getColor().main10,
                            shape = RoundedCornerShape(30.dp)
                        ).padding(horizontal = 6.dp),
                    text = when(data.category) {
                        ReviewCategoryType.ACTIVITY -> getString(R.string.common_액티비티)
                        ReviewCategoryType.ART -> getString(R.string.common_문화예술)
                        ReviewCategoryType.RESTAURANT -> getString(R.string.common_제주_맛집)
                        else -> "    "
                    },
                    style = caption02,
                    color = getColor().main
                )

                Spacer(Modifier.height(2.dp))

                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    text = data.placeName,
                    color = getColor().black,
                    style = body02SemiBold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if(haveImg) 1 else 2
                )

                if(!haveImg)
                    Spacer(Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = data.createdAt.replace('-','.'),
                        color = getColor().gray01,
                        style = caption02,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = getColor().main
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = data.heartCount.toString(),
                        color = getColor().gray01,
                        style = caption02,
                    )
                }
            }
        }
    }
}
