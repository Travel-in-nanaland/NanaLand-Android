package com.nanaland.ui.main.home.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.domain.entity.search.HotPostThumbnailData
import com.nanaland.ui.component.home.searching.SearchingScreenHotPosts
import com.nanaland.ui.component.home.searching.SearchingScreenHotPostsText
import com.nanaland.ui.component.home.searching.SearchingScreenTopKeywords
import com.nanaland.ui.component.home.searching.SearchingScreenTopKeywordsText
import com.nanaland.ui.component.home.searching.SearchingScreenRecentSearchContent
import com.nanaland.util.ui.UiState

@Composable
fun SearchingContent(
    scaffoldPadding: PaddingValues,
    viewModel: SearchViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getTopKeywords()
        viewModel.getHotPosts()
    }
    val topKeywords = viewModel.topKeywords.collectAsState().value
    val hotPosts = viewModel.hotPosts.collectAsState().value
    SearchingContent(
        scaffoldPadding = scaffoldPadding,
        topKeywords = topKeywords,
        hotPosts = hotPosts,
        onLikeButtonClick = viewModel::toggleFavorite,
        onHotPostClick = {},
        isContent = true
    )
}

@Composable
private fun SearchingContent(
    scaffoldPadding: PaddingValues,
    topKeywords: UiState<List<String>>,
    hotPosts: UiState<List<HotPostThumbnailData>>,
    onLikeButtonClick: (Long, String?) -> Unit,
    onHotPostClick: (Long) -> Unit,
    isContent: Boolean
) {
    Column(
        modifier = Modifier
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            SearchingScreenRecentSearchContent()

            Spacer(Modifier.height(20.dp))

            SearchingScreenTopKeywordsText()

            Spacer(Modifier.height(20.dp))

            SearchingScreenTopKeywords(topKeywords = topKeywords)

            Spacer(Modifier.height(20.dp))

            SearchingScreenHotPostsText()

            Spacer(Modifier.height(20.dp))

            SearchingScreenHotPosts(
                hotPosts = hotPosts,
                onLikeButtonClick = onLikeButtonClick,
                onClick = onHotPostClick
            )
        }
        Spacer(Modifier.height(scaffoldPadding.calculateBottomPadding()))
    }
}

@Preview
@Composable
private fun SearchingContentPreview() {

}