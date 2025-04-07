package com.jeju.nanaland.ui.main.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.globalvalue.constant.SYSTEM_STATUS_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.type.HomeScreenViewType
import com.jeju.nanaland.globalvalue.type.SearchCategoryType
import com.jeju.nanaland.ui.component.main.home.HomeScreenTopBar
import com.jeju.nanaland.ui.main.home.search.SearchResultContent
import com.jeju.nanaland.ui.main.home.search.SearchViewModel
import com.jeju.nanaland.ui.main.home.search.SearchingContent
import com.jeju.nanaland.util.ui.ScreenPreview

@Composable
fun HomeScreen(
    moveToNotificationScreen: () -> Unit,
    moveToCategoryContentScreen: (Int, String?, Boolean) -> Unit,
    moveToRestaurantListScreen: () -> Unit,
    moveToNatureListScreen: (String?) -> Unit,
    moveToFestivalListScreen: (String?) -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToActivityListScreen: () -> Unit,
    moveToArtListScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    updateHomeScreenViewType: (HomeScreenViewType) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val inputText = homeViewModel.inputText.collectAsState().value
    val viewType = homeViewModel.viewType.collectAsState().value
    HomeScreen(
        inputText = inputText,
        viewType = viewType,
        updateInputText = homeViewModel::updateInputText,
        getSearchResult = { searchViewModel.getSearchResult(it, true) },
        addRecentSearch = searchViewModel::addRecentSearch,
        updateHomeScreenViewType = {
            homeViewModel.updateHomeScreenViewType(it)
            updateHomeScreenViewType(it)
        },
        updateSearchCategoryType = searchViewModel::updateSelectedCategoryType,
        moveToNotificationScreen = moveToNotificationScreen,
        moveToCategoryContentScreen = moveToCategoryContentScreen,
        moveToRestaurantListScreen = moveToRestaurantListScreen,
        moveToNatureListScreen = moveToNatureListScreen,
        moveToFestivalListScreen = moveToFestivalListScreen,
        moveToMarketListScreen = moveToMarketListScreen,
        moveToActivityListScreen = moveToActivityListScreen,
        moveToArtListScreen = moveToArtListScreen,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun HomeScreen(
    inputText: String,
    viewType: HomeScreenViewType,
    updateInputText: (String) -> Unit,
    getSearchResult: (String) -> Unit,
    addRecentSearch: (String) -> Unit,
    updateHomeScreenViewType: (HomeScreenViewType) -> Unit,
    updateSearchCategoryType: (SearchCategoryType) -> Unit,
    moveToNotificationScreen: () -> Unit,
    moveToCategoryContentScreen: (Int, String?, Boolean) -> Unit,
    moveToRestaurantListScreen: () -> Unit,
    moveToNatureListScreen: (String?) -> Unit,
    moveToFestivalListScreen: (String?) -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToActivityListScreen: () -> Unit,
    moveToArtListScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    val homeScreenTopBar: @Composable ()->Unit = {
        if(viewType != HomeScreenViewType.Home)
            Spacer(Modifier.height(SYSTEM_STATUS_BAR_HEIGHT.dp))
        HomeScreenTopBar(
            inputText = inputText,
            onValueChange = updateInputText,
            currentViewType = viewType,
            updateHomeScreenViewType = updateHomeScreenViewType,
            updateSearchCategoryType = updateSearchCategoryType,
            getSearchResult = getSearchResult,
            addRecentSearch = addRecentSearch,
            moveToNotificationScreen = moveToNotificationScreen
        )
        Spacer(Modifier.height(10.dp))
    }
        Column {
            if(viewType != HomeScreenViewType.Home)
                homeScreenTopBar()

            when (viewType) {
                HomeScreenViewType.Home -> {
                    HomeContent(
                        moveToCategoryContentScreen = moveToCategoryContentScreen,
                        moveToRestaurantListScreen = moveToRestaurantListScreen,
                        moveToNatureListScreen = moveToNatureListScreen,
                        moveToFestivalListScreen = moveToFestivalListScreen,
                        moveToMarketListScreen = moveToMarketListScreen,
                        moveToActivityListScreen = moveToActivityListScreen,
                        moveToArtListScreen = moveToArtListScreen,
                        moveToSignInScreen = moveToSignInScreen,
                        homeScreenTopBar = homeScreenTopBar
                    )
                }
                HomeScreenViewType.Searching -> {
                    val focusManager = LocalFocusManager.current
                    BackHandler {
                        updateInputText("")
                        focusManager.clearFocus()
                        updateHomeScreenViewType(HomeScreenViewType.Home)
                    }
                    SearchingContent(
                        moveToCategoryContentScreen = moveToCategoryContentScreen,
                        moveToSignInScreen = moveToSignInScreen,
                    )
                }
                HomeScreenViewType.SearchResult -> {
                    val focusManager = LocalFocusManager.current
                    BackHandler {
                        updateInputText("")
                        focusManager.clearFocus()
                        updateHomeScreenViewType(HomeScreenViewType.Home)
                        updateSearchCategoryType(SearchCategoryType.All)
                    }
                    SearchResultContent(
                        moveToCategoryContentScreen = moveToCategoryContentScreen,
                        moveToSignInScreen = moveToSignInScreen,
                    )
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