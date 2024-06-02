package com.jeju.nanaland.ui.main.home.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import com.jeju.nanaland.domain.entity.search.HotPostThumbnailData
import com.jeju.nanaland.globalvalue.type.HomeScreenViewType
import com.jeju.nanaland.ui.component.main.searching.SearchingScreenHotPosts
import com.jeju.nanaland.ui.component.main.searching.SearchingScreenHotPostsText
import com.jeju.nanaland.ui.component.main.searching.SearchingScreenTopKeywords
import com.jeju.nanaland.ui.component.main.searching.SearchingScreenTopKeywordsText
import com.jeju.nanaland.ui.component.main.searching.SearchingScreenRecentSearchContent
import com.jeju.nanaland.ui.main.home.HomeViewModel
import com.jeju.nanaland.util.ui.UiState

@Composable
fun SearchingContent(
    moveToCategoryContentScreen: (Long, String?, Boolean) -> Unit,
    moveToSignInScreen: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
    viewModel: SearchViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getTopKeywords()
        viewModel.getHotPosts()
        viewModel.getAllRecentSearches()
    }
    val topKeywordList = viewModel.topKeywordList.collectAsState().value
    val hotPostList = viewModel.hotPostList.collectAsState().value
    val recentSearchList = viewModel.recentSearchList.collectAsState().value
    SearchingContent(
        topKeywordList = topKeywordList,
        hotPostList = hotPostList,
        recentSearchList = recentSearchList,
        deleteAllRecentSearches = viewModel::deleteAllRecentSearches,
        deleteRecentSearch = viewModel::deleteRecentSearch,
        searchKeyword = viewModel::getSearchResult,
        toggleHotPostFavorite = viewModel::toggleHotPostFavorite,
        updateViewType = homeViewModel::updateHomeScreenViewType,
        updateInputText = homeViewModel::updateInputText,
        addRecentSearch = viewModel::addRecentSearch,
        onHotPostClick = moveToCategoryContentScreen,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun SearchingContent(
    topKeywordList: UiState<List<String>>,
    hotPostList: UiState<List<HotPostThumbnailData>>,
    recentSearchList: List<Pair<String, String>>,
    deleteAllRecentSearches: () -> Unit,
    deleteRecentSearch: (String) -> Unit,
    searchKeyword: (String) -> Unit,
    toggleHotPostFavorite: (Long, String?) -> Unit,
    updateViewType: (HomeScreenViewType) -> Unit,
    updateInputText: (String) -> Unit,
    addRecentSearch: (String) -> Unit,
    onHotPostClick: (Long, String?, Boolean) -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    Column(
        modifier = Modifier
//            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            SearchingScreenRecentSearchContent(
                recentSearchList = recentSearchList,
                deleteAllRecentSearches = deleteAllRecentSearches,
                deleteRecentSearch = deleteRecentSearch,
                searchKeyword = searchKeyword,
                updateViewType = updateViewType,
                updateInputText = updateInputText,
                addRecentSearch = addRecentSearch,
            )

            Spacer(Modifier.height(20.dp))

            SearchingScreenTopKeywordsText()

            Spacer(Modifier.height(32.dp))

            SearchingScreenTopKeywords(
                topKeywordList = topKeywordList,
                searchKeyword = searchKeyword,
                updateViewType = updateViewType,
                updateInputText = updateInputText,
                addRecentSearch = addRecentSearch,
            )

            Spacer(Modifier.height(20.dp))

            SearchingScreenHotPostsText()

            Spacer(Modifier.height(20.dp))

            SearchingScreenHotPosts(
                hotPosts = hotPostList,
                onFavoriteButtonClick = toggleHotPostFavorite,
                onPostClick = onHotPostClick,
                moveToSignInScreen = moveToSignInScreen,
            )

            Spacer(Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
private fun SearchingContentPreview() {

}