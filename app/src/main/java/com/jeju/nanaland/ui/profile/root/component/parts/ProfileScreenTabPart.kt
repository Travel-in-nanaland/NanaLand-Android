package com.jeju.nanaland.ui.profile.root.component.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.drawColoredShadow

@Composable
fun ProfileScreenTabPart (
    isReviewList: Boolean,
    reviewSize: Int,
    moveToReviewScreen: () -> Unit,
    toggleReviewNoticeTab: (() -> Unit)? = null,
) {
    Box(
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
}