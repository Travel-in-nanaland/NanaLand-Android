package com.jeju.nanaland.ui.component.review

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.review.ReviewData
import com.jeju.nanaland.ui.component.review.parts.ReviewContent
import com.jeju.nanaland.ui.component.review.parts.ReviewCountText
import com.jeju.nanaland.ui.component.review.parts.ReviewFavoriteButton
import com.jeju.nanaland.ui.component.review.parts.ReviewProfileImage
import com.jeju.nanaland.ui.component.review.parts.ReviewProfileName
import com.jeju.nanaland.ui.component.review.parts.ReviewRatingText
import com.jeju.nanaland.ui.component.review.parts.ReviewTags
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.caption02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.jeju.nanaland.util.ui.drawColoredShadow
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ReviewCard(
    data: ReviewData,
    toggleReviewFavorite: (Int) -> Unit,
    onImageClick: (String) -> Unit,
    onProfileClick: (Int?) -> Unit,
    onReport: () -> Unit,
    onEdit: (ReviewData) -> Unit,
    onRemove: (ReviewData) -> Unit,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val isExpanded = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .drawColoredShadow(
                color = Color.Black,
                alpha = 0.1f,
                shadowRadius = 12.dp
            )
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = getColor().white,
                shape = RoundedCornerShape(12.dp)
            )
            .animateContentSize()
            .heightIn(max = 1000.dp)
//            .then(
//                if (isExpanded.value) {
//                    Modifier.height(IntrinsicSize.Max)
//                } else {
//                    Modifier.height(if (data.images.isEmpty()) 200.dp else 300.dp)
//                }
//            )
            .padding(16.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(Modifier.clickableNoEffect { onProfileClick(
                    if(data.myReview) null
                    else data.memberId)
                }) {
                    ReviewProfileImage(imageUrl = data.profileImage.originUrl)

                    Spacer(Modifier.width(8.dp))

                    Column {
                        ReviewProfileName(name = data.nickname)

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ReviewCountText(count = data.memberReviewCount)

                            Spacer(Modifier.width(4.dp))

                            Box(
                                Modifier
                                    .width(1.dp)
                                    .height(12.dp)
                                    .background(getColor().black))

                            Spacer(Modifier.width(4.dp))

                            ReviewRatingText(rating = data.rating)
                        }
                    }
                }

                Spacer(Modifier.weight(1f))

                if(data.myReview){
                    Row {
                        Text(
                            modifier = Modifier
                                .background(
                                    color = getColor().gray03,
                                    shape = RoundedCornerShape(30.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                                .clickableNoEffect { onEdit(data) },
                            text = getString(R.string.common_수정),
                            color = getColor().gray01,
                            style = caption01
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            modifier = Modifier
                                .background(
                                    color = getColor().gray03,
                                    shape = RoundedCornerShape(30.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                                .clickableNoEffect { onRemove(data) },
                            text = getString(R.string.common_삭제),
                            color = getColor().gray01,
                            style = caption01
                        )
                    }
                } else {
                    ReviewFavoriteButton(
                        isFavorite = data.reviewHeart,
                        favoriteCount = data.heartCount,
                        toggleFavorite = { toggleReviewFavorite(data.id) }
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            if (data.images.isNotEmpty()) {
                LazyRow(
                    modifier = Modifier.verticalScroll(scrollState)
                ) {
                    itemsIndexed(data.images) { idx, item ->
                        GlideImage(
                            modifier = Modifier
                                .size(70.dp)
                                .clickableNoEffect { onImageClick(item.originUrl) }
                                .clip(RoundedCornerShape(8.dp)),
                            imageModel = { item.thumbnailUrl }
                        )

                        Spacer(Modifier.width(8.dp))
                    }
                }

                Spacer(Modifier.height(12.dp))
            }

            ReviewContent(
                text = data.content,
                onExpanded = { isExpanded.value = it }
            )

            Spacer(Modifier.height(12.dp))

            ReviewTags(tags = data.reviewTypeKeywords)

            Spacer(Modifier.height(24.dp))
        }

        Row(
            modifier = Modifier.align(Alignment.BottomEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(!data.myReview)
                Text(
                    modifier = Modifier.clickableNoEffect(onReport),
                    text = getString(R.string.common_신고하기),
                    color = getColor().gray01,
                    style = caption02
                )
            Spacer(Modifier.weight(1f))
            Text(
                text = data.createdAt.replace("-", "."),
                color = getColor().gray01,
                style = caption01
            )

        }
    }
}