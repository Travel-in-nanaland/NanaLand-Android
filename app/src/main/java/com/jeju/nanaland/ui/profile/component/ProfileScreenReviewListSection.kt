package com.jeju.nanaland.ui.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.review.MemberReviewDetail
import com.jeju.nanaland.ui.profile.component.parts.ProfileReviewRow

@Composable
fun ProfileScreenReviewListSection(
    reviews: List<MemberReviewDetail>,
    onClick: (Int) -> Unit
) {
    val verticalItemSpacing = 16
    val data = reviews.take(calculateMaximumItemCount(
        reviews.map {
            if(it.images.isNotEmpty()) 2
            else 1
        }
    ))
    val height = calculateFinalHeight(
        data.map {
            if(it.images.isNotEmpty()) 210
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
private fun calculateMaximumItemCount(cnts: List<Int>): Int{
    val MAX = 12
    var sum = 0
    cnts.forEachIndexed { index, c ->
        if(MAX < sum + c)
            return index
        sum += c
    }
    return MAX
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
