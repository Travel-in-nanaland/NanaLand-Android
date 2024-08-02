package com.jeju.nanaland.ui.component.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.review.ReviewData
import com.jeju.nanaland.ui.component.review.parts.ReviewContent
import com.jeju.nanaland.ui.component.review.parts.ReviewCountText
import com.jeju.nanaland.ui.component.review.parts.ReviewFavoriteButton
import com.jeju.nanaland.ui.component.review.parts.ReviewImage
import com.jeju.nanaland.ui.component.review.parts.ReviewProfileImage
import com.jeju.nanaland.ui.component.review.parts.ReviewProfileName
import com.jeju.nanaland.ui.component.review.parts.ReviewRatingText
import com.jeju.nanaland.ui.component.review.parts.ReviewTags
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.drawColoredShadow

@Composable
fun ReviewCard(
    data: ReviewData
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
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
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ReviewProfileImage(imageUrl = data.profileImage.thumbnailUrl)

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
                            .height(20.dp)
                            .background(getColor().black))
                    
                    Spacer(Modifier.width(4.dp))
                    
                    ReviewRatingText(rating = data.rating)
                }
            }

            Spacer(Modifier.weight(1f))

            ReviewFavoriteButton(
                isFavorite = data.reviewHeart,
                favoriteCount = data.heartCount
            )
        }

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            data.images.forEach {
                ReviewImage(imageUrl = it.thumbnailUrl)

                Spacer(Modifier.width(8.dp))
            }
        }

        Spacer(Modifier.height(12.dp))

        ReviewContent(text = data.content)

        Spacer(Modifier.height(12.dp))

        ReviewTags(tags = data.reviewTypeKeywords)
    }
}