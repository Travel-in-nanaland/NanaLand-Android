package com.jeju.nanaland.ui.component.mypage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.mypage.parts.MyPageNoticeRow
import com.jeju.nanaland.ui.component.mypage.parts.MyPageReviewRow
import com.jeju.nanaland.ui.component.mypage.parts.MyPageShowAll
import com.jeju.nanaland.ui.theme.caption01SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import java.util.Date

data class TempReviewData(
    val title: String,
    val img: String?,
    val date: Date,
    val like: Int,
)
data class TempNoticeData(
    val category: Int,
    val title: String,
    val date: Date,
)

@Composable
fun MyPageScreenListSection(
    isReviewList: Boolean,
    reviews: List<TempReviewData>,
    notices: List<TempNoticeData>?,
    moveToReviewWriteScreen: () -> Unit,
    moveToReviewScreen: () -> Unit,
    moveToNoticeScreen: () -> Unit,
) {
    Column {
        if(notices == null){
            HorizontalDivider()
        }
        else {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MyPageShowAll(
                    cnt = if(isReviewList) reviews.size else notices.size,
                    onClick = if(isReviewList) moveToReviewScreen else moveToNoticeScreen
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .alpha(if (isReviewList) 1f else 0f)
                        .border(
                            border = BorderStroke(
                                width = 1.dp,
                                color = getColor().main,
                            ),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .clickableNoEffect {
                            if (isReviewList) moveToReviewWriteScreen()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.ic_pencil_outlined),
                        contentDescription = null,
                        tint = getColor().main
                    )
                    Text(
                        text = getString(R.string.mypage_screen_리뷰작성하기),
                        color = getColor().black,
                        style = caption01SemiBold,
                    )
                }
            }
        }

        if(isReviewList)
            Reviews(reviews)
        else if(notices != null)
            Notices(notices)
    }
}

@Composable
private fun Reviews(
    data: List<TempReviewData>
) {
    val verticalItemSpacing = 16

    val height = calculateFinalHeight(
        data.map {
            if(it.img != null) 210
            else 105
        },
        verticalItemSpacing
    )

    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .height((height + 24).dp)
            .padding(horizontal = 16.dp),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = verticalItemSpacing.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            items(data) {
                MyPageReviewRow(it) {
                    // TODO
                }
            }
        },
    )
}

private fun calculateFinalHeight(heights: List<Int>, verticalItemSpacing: Int): Int {
    val result = IntArray(2) { 0 }

    for (h in heights) {
        if(result[0] > result[1])
            result[1] += h + verticalItemSpacing
        else
            result[0] += h + verticalItemSpacing
    }

    return result.max()
}

@Composable
private fun Notices(
    data: List<TempNoticeData>
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        data.forEach {
            MyPageNoticeRow(it) {
                // TODO
            }
        }
    }
}
