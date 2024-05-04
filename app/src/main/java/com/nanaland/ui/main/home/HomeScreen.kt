package com.nanaland.ui.main.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.globalvalue.type.HomeScreenViewType
import com.nanaland.ui.component.main.home.HomeScreenTopBar
import com.nanaland.ui.main.home.search.SearchResultContent
import com.nanaland.ui.main.home.search.SearchViewModel
import com.nanaland.ui.main.home.search.SearchingContent
import com.nanaland.util.ui.ScreenPreview

@Composable
fun HomeScreen(
    scaffoldPadding: PaddingValues,
    moveToCategoryContentScreen: (Long, String?) -> Unit,
    moveToNanaPickListScreen: () -> Unit,
    moveToNatureListScreen: () -> Unit,
    moveToFestivalListScreen: () -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceScreen: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val inputText = homeViewModel.inputText.collectAsState().value
    val viewType = homeViewModel.viewType.collectAsState().value
    HomeScreen(
        scaffoldPadding = scaffoldPadding,
        inputText = inputText,
        viewType = viewType,
        updateInputText = homeViewModel::updateInputText,
        getSearchResult = searchViewModel::getSearchResult,
        addRecentSearch = searchViewModel::addRecentSearch,
        updateViewType = homeViewModel::updateViewType,
        moveToCategoryContentScreen = moveToCategoryContentScreen,
        moveToNanaPickListScreen = moveToNanaPickListScreen,
        moveToNatureListScreen = moveToNatureListScreen,
        moveToFestivalListScreen = moveToFestivalListScreen,
        moveToMarketListScreen = moveToMarketListScreen,
        moveToExperienceScreen = moveToExperienceScreen,
        isContent = true
    )
}

@Composable
private fun HomeScreen(
    scaffoldPadding: PaddingValues,
    inputText: String,
    viewType: HomeScreenViewType,
    updateInputText: (String) -> Unit,
    getSearchResult: (String) -> Unit,
    addRecentSearch: (String) -> Unit,
    updateViewType: (HomeScreenViewType) -> Unit,
    moveToCategoryContentScreen: (Long, String?) -> Unit,
    moveToNanaPickListScreen: () -> Unit,
    moveToNatureListScreen: () -> Unit,
    moveToFestivalListScreen: () -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceScreen: () -> Unit,
    isContent: Boolean
) {
    Column {
        HomeScreenTopBar(
            inputText = inputText,
            onValueChange = updateInputText,
            currentViewType = viewType,
            updateViewType = updateViewType,
            getSearchResult = getSearchResult,
            addRecentSearch = addRecentSearch
        )
        Spacer(Modifier.height(10.dp))
        when (viewType) {
            HomeScreenViewType.Home -> {
                HomeContent(
                    scaffoldPadding = scaffoldPadding,
                    moveToCategoryContentScreen = moveToCategoryContentScreen,
                    moveToNanaPickListScreen = moveToNanaPickListScreen,
                    moveToNatureListScreen = moveToNatureListScreen,
                    moveToFestivalListScreen = moveToFestivalListScreen,
                    moveToMarketListScreen = moveToMarketListScreen,
                    moveToExperienceScreen = moveToExperienceScreen,
                )
            }
            HomeScreenViewType.Searching -> {
                val focusManager = LocalFocusManager.current
                BackHandler {
                    updateInputText("")
                    focusManager.clearFocus()
                    updateViewType(HomeScreenViewType.Home)
                }
                SearchingContent(
                    scaffoldPadding = scaffoldPadding,
                    moveToCategoryContentScreen = moveToCategoryContentScreen
                )
            }
            HomeScreenViewType.SearchResult -> {
                val focusManager = LocalFocusManager.current
                BackHandler {
                    updateInputText("")
                    focusManager.clearFocus()
                    updateViewType(HomeScreenViewType.Home)
                }
                SearchResultContent(
                    scaffoldPadding = scaffoldPadding,
                    moveToCategoryContentScreen = moveToCategoryContentScreen
                )
            }
        }
        LazyColumn(
                modifier = Modifier.imePadding()
        ) {
            item {
                Spacer(Modifier.height(scaffoldPadding.calculateBottomPadding()))
            }
        }
    }
}

@ScreenPreview
@Composable
private fun HomeScreenPreview() {
//    NanaLandTheme {
//        HomeScreen(
//            scaffoldPadding = PaddingValues(0.dp),
//            inputText = "",
//            viewType = HomeScreenViewType.Home,
//            updateInputText = {},
//            updateViewType = {},
//            getSearchResult = {},
//            moveToNanaPickListScreen = {},
//            isContent = true
//        )
//    }
}