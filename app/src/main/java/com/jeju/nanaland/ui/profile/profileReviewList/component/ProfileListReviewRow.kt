package com.jeju.nanaland.ui.profile.profileReviewList.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.review.MemberReviewDetail
import com.jeju.nanaland.ui.component.common.ExpandableText
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.jeju.nanaland.util.ui.conditional
import com.jeju.nanaland.util.ui.drawColoredShadow
import com.skydoves.landscapist.glide.GlideImage
import kotlin.random.Random

/** 마이페이지 **/
@JvmName("rowIsMyPage")
@Composable
fun ProfileListReviewRow(
    data: MemberReviewDetail,
    onEdit: (MemberReviewDetail) -> Unit,
    onRemove: (MemberReviewDetail) -> Unit,
) {
    ProfileListReviewRow(
        data,
        onEdit = onEdit,
        onRemove = onRemove,

        onLike = null,
        onReport = null,
    )
}

/** 타인 프로필 **/
@JvmName("rowIsOthresProfile")
@Composable
fun ProfileListReviewRow(
    data: MemberReviewDetail,
    onLike: (Int, Boolean) -> Unit,
    onReport: (Int) -> Unit,
) {
    ProfileListReviewRow(
        data,
        onLike = onLike,
        onReport = onReport,

        onEdit = null,
        onRemove = null,
    )

}

@Composable
private fun ProfileListReviewRow(
    data: MemberReviewDetail,
    onEdit: ((MemberReviewDetail) -> Unit)? = null,
    onRemove: ((MemberReviewDetail) -> Unit)? = null,
    onLike: ((Int, Boolean) -> Unit)? = null,
    onReport: ((Int) -> Unit)? = null,
) {
    var likeCnt by remember { mutableIntStateOf(data.heartCount) }
    var isLike by remember { mutableStateOf(/*TODO*/ Random.nextBoolean()) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .drawColoredShadow(
                color = getColor().black,
                alpha = 0.15f,
                shadowRadius = 12.dp,
            )
            .clip(RoundedCornerShape(12.dp))
            .background(getColor().white)
            .padding(vertical = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(Modifier.padding(horizontal = 16.dp)) {
                Row {
                    Column(Modifier.weight(1f)) {
                        /** 가게명 **/
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.weight(1f, false),
                                text = data.placeName,
                                style = body02Bold,
                                color = getColor().black
                            )
                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(R.drawable.ic_arrow_right),
                                contentDescription = null,
                                tint = getColor().black
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        /** 별점 **/
                        Rating(data.rating.toInt())
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        /** 수정 **/
                        onEdit?.let {
                            TopRightBtn(isLike = null, text = getString(R.string.common_수정)) {
                                onEdit(data)
                            }
                        }
                        /** 삭제 **/
                        onRemove?.let {
                            TopRightBtn(isLike = null, text = getString(R.string.common_삭제)) {
                                onRemove(data)
                            }
                        }
                        /** 좋아요 **/
                        onLike?.let {
                            TopRightBtn(isLike = isLike, text = likeCnt.toString()) {
                                isLike = !isLike
                                likeCnt += if(isLike) 1 else -1
                                onLike(data.id, isLike)
                            }
                        }
                    }
                }
            }

            /** 사진 **/
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.width((16 - 8).dp))
                data.images.forEach { img ->
                    GlideImage(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        imageModel = { img.thumbnailUrl }
                    )
                }
                Spacer(modifier = Modifier.width((16 - 8).dp))
            }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                /** 본문 **/
                ExpandableText(
                    text = data.content,
                    style = body02.copy(color = getColor().black),
                    collapsedMaxLine = 2,
                    showMoreText = getString(R.string.detail_screen_common_더보기),
                    showMoreStyle = caption01.copy(color = getColor().gray01),
                    showLessText = getString(R.string.detail_screen_common_접기),
                )

                /** 키워드 **/
                Text(
                    text = data.reviewTypeKeywords.fold("") { total, word -> "$total #$word" },
                    style = caption01,
                    color = getColor().main
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    /** 좋아요 **/
                    if(onLike == null)
                        Like(true, data.heartCount)

                    Spacer(modifier = Modifier.weight(1f))

                    /** 작성일 **/
                    Text(
                        text = data.createdAt.replace('-','.'),
                        style = caption01,
                        color = getColor().gray01
                    )
                    /** 신고 버튼 **/
                    onReport?.let {
                        Icon(
                            modifier = Modifier
                                .size(20.dp)
                                .clickableNoEffect { onReport(data.id) },
                            painter = painterResource(R.drawable.ic_more_vert),
                            contentDescription = null,
                            tint = getColor().gray01
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Rating(r: Int) {
    Row {
        for (i in 0 until 5) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_star_rating),
                contentDescription = null,
                tint = if (i < r) getColor().yellow
                else getColor().gray02
            )
        }
    }
}

@Composable
private fun TopRightBtn(
    isLike: Boolean?,
    text: String,
    onClick: () -> Unit
) {
    val isMine = isLike == null
    val gray2 = getColor().gray02

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .conditional(!isMine) {
                border(1.dp, gray2, RoundedCornerShape(30.dp))
            }
            .background(if (isMine) getColor().gray03 else getColor().white)
            .clickableNoEffect { onClick() }
            .conditional(isMine) {
                padding(horizontal = 12.dp, vertical = 2.dp)
            }
            .conditional(!isMine) {
                padding(horizontal = 8.dp, vertical = 4.dp)
            }
    ) {
        if(isLike != null)
            Like(
                isLike = isLike,
                cnt = text.toInt()
            )
        else
            Text(
                text = text,
                style = caption01,
                color = getColor().gray01
            )
    }
}

@Composable
private fun Like(
    isLike: Boolean,
    cnt: Int
) {
    Row (
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            painter = painterResource(
                if (isLike) R.drawable.ic_heart_filled
                else R.drawable.ic_heart_outlined
            ),
            contentDescription = null,
            tint = getColor().main
        )

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = cnt.toString(),
            style = caption01,
            color = getColor().black,
        )
    }
}