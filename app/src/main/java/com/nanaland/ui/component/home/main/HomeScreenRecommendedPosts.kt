package com.nanaland.ui.component.home.main

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.domain.entity.member.RecommendedPostData
import com.nanaland.ui.component.thumbnail.MainHomeThumbnail
import com.nanaland.util.ui.UiState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreenRecommendedPosts(
    recommendedPosts: UiState<List<RecommendedPostData>>,
) {
    when (recommendedPosts) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            FlowRow {
                repeat(recommendedPosts.data.size) { idx ->
                    val item = recommendedPosts.data[idx]
                    MainHomeThumbnail(
                        imageUri = item.thumbnailUrl,
                        title = item.title,
                        subTitle = item.intro,
                        onClick = {}
                    )

                    if (idx % 2 == 0) Spacer(Modifier.width(8.dp))
                }
            }
        }
        is UiState.Failure -> {}
    }
}