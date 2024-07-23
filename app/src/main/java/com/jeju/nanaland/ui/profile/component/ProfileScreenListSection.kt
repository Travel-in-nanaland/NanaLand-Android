package com.jeju.nanaland.ui.profile.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.profile.component.parts.ProfileNoticeRow
import com.jeju.nanaland.ui.profile.component.parts.ProfileReviewRow
import com.jeju.nanaland.ui.profile.component.parts.ProfileShowAll
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.caption01SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.jeju.nanaland.util.ui.drawColoredShadow
import java.util.Date

data class TempReviewData(
    val id:Int,
    val title: String,
    val img: String?,
    val date: Date,
    val like: Int,
)
data class TempNoticeData(
    val id:Int,
    val category: Int,
    val title: String,
    val date: Date,
)

@Composable
fun ProfileScreenListSection(
    reviews: List<TempReviewData>,
    notices: List<TempNoticeData>?,
    moveToReviewWriteScreen: () -> Unit,
    moveToReviewScreen: (Int?) -> Unit,
    moveToNoticeScreen: (Int?) -> Unit,
) {
    var isReviewList by remember { mutableStateOf(true) }
    val isMine = notices != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .drawColoredShadow(
                color = getColor().black,
                alpha = 0.1f,
                shadowRadius = 30.dp,
            )
            .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp))
            .background(getColor().white)
    ) {
        TabParts(
            isReviewList = isReviewList,
            reviewSize = reviews.size,
            moveToReviewScreen = { moveToReviewScreen(null) },
            toggleReviewNoticeTab = if(!isMine) null else {
                { isReviewList = !isReviewList }
            }
        )

        if(isMine)
            MoreInfoParts(
                isReviewList = isReviewList,
                listSize =if(isReviewList) reviews.size else notices?.size ?: 0,
                moveToListPage = {
                    if(isReviewList) moveToReviewScreen(null)
                    else moveToNoticeScreen(null)
                },
                moveToReviewWriteScreen = moveToReviewWriteScreen
            )
        else
            HorizontalDivider()

        if(isReviewList)
            Reviews(reviews) {
                moveToReviewScreen(it)
            }
        else if(notices != null)
            Notices(notices) {
                moveToReviewScreen(it)
            }
    }
}

@Composable
private fun TabParts (
    isReviewList: Boolean,
    reviewSize: Int,
    moveToReviewScreen: () -> Unit,
    toggleReviewNoticeTab: (() -> Unit)? = null,
) {
    if(toggleReviewNoticeTab == null){
        Row(
            modifier = Modifier
                .padding(16.dp)
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = getString(R.string.common_후기),
                color = getColor().black,
                style = bodyBold,
            )

            Spacer(modifier = Modifier.weight(1f))

            ProfileShowAll(reviewSize, moveToReviewScreen)
        }
    } else {
        TabRow(
            selectedTabIndex = 0,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier
                        .tabIndicatorOffset(
                            tabPositions[if (isReviewList) 0 else 1]
                        )
                        .height(2.dp),
                    color = getColor().main
                )
            },
            divider = {
                HorizontalDivider(
                    thickness = 2.dp,
                    color = getColor().gray02
                )
            }
        ) {
            Tab(
                text = {
                    Text(
                        text = getString(R.string.common_후기),
                        color = getColor().black,
                        style = body02SemiBold,
                    )
                },
                selected = isReviewList,
                onClick = {
                    if(!isReviewList) toggleReviewNoticeTab()
                }
            )
            Tab(
                text = {
                    Text(
                        text = getString(R.string.common_공지사항),
                        color = getColor().black,
                        style = body02SemiBold,
                    )
                },
                selected = !isReviewList,
                onClick = {
                    if(isReviewList) toggleReviewNoticeTab()
                }
            )
        }
    }
}

@Composable
private fun MoreInfoParts(
    isReviewList: Boolean,
    listSize: Int,
    moveToListPage: () -> Unit,
    moveToReviewWriteScreen: () -> Unit,
){
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileShowAll(
            cnt = listSize,
            onClick = moveToListPage
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

@Composable
private fun Reviews(
    data: List<TempReviewData>,
    onClick: (Int) -> Unit
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
                ProfileReviewRow(it, onClick)
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
    data: List<TempNoticeData>,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        data.forEach {
            ProfileNoticeRow(it, onClick)
        }
    }
}
