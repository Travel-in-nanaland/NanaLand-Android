package com.jeju.nanaland.ui.main.home.search

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.search.SearchResultData
import com.jeju.nanaland.globalvalue.constant.getActivityKeywordList
import com.jeju.nanaland.globalvalue.constant.getCultureArtKeywordList
import com.jeju.nanaland.globalvalue.constant.getLocationList
import com.jeju.nanaland.globalvalue.constant.getRestaurantKeywordList
import com.jeju.nanaland.globalvalue.type.SearchCategoryType
import com.jeju.nanaland.ui.component.common.dialog.BottomSheetFilterDialog
import com.jeju.nanaland.ui.component.common.dialog.BottomSheetFilterDialogType
import com.jeju.nanaland.ui.component.listscreen.filter.DateLocationFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.KeywordLocationFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.LocationFilterTopBar
import com.jeju.nanaland.ui.component.main.searchresult.SearchResultScreenCategorySelectionTab
import com.jeju.nanaland.ui.component.main.searchresult.SearchResultScreenSearchResult
import com.jeju.nanaland.ui.main.home.HomeViewModel
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState

@Composable
fun SearchResultContent(
    moveToCategoryContentScreen: (Int, String?, Boolean) -> Unit,
    moveToSignInScreen: () -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val inputText = homeViewModel.inputText.collectAsState().value
    val selectedCategory = searchViewModel.selectedCategory.collectAsState().value
    val allSearchResultList = searchViewModel.allSearchResultList.collectAsState().value
    val categorizedSearchResultList = searchViewModel.categorizedSearchResultList.collectAsState().value
    SearchResultContent(
        searchViewModel = searchViewModel,
        inputText = inputText,
        getSearchResult = searchViewModel::getSearchResult,
        selectedCategory = selectedCategory,
        allSearchResultList = allSearchResultList,
        categorizedSearchResultList = categorizedSearchResultList,
        updateSelectedCategory = searchViewModel::updateSelectedCategoryType,
        toggleAllSearchResultFavorite = searchViewModel::toggleAllSearchResultFavorite,
        toggleSearchResultFavorite = searchViewModel::toggleSearchResultFavorite,
        onPostClick = moveToCategoryContentScreen,
        moveToSignInScreen = moveToSignInScreen,
    )
}

@Composable
private fun SearchResultContent(
    searchViewModel: SearchViewModel,
    inputText: String,
    getSearchResult: (String) -> Unit,
    selectedCategory: SearchCategoryType,
    allSearchResultList: UiState<Map<String, SearchResultData>>,
    categorizedSearchResultList: UiState<SearchResultData>,
    updateSelectedCategory: (SearchCategoryType) -> Unit,
    toggleAllSearchResultFavorite: (Int, String?) -> Unit,
    toggleSearchResultFavorite: (Int, String?) -> Unit,
    onPostClick: (Int, String?, Boolean) -> Unit,
    moveToSignInScreen: () -> Unit,

) {
    SearchResultScreenCategorySelectionTab(
        getSearchResult = { getSearchResult(inputText) },
        selectedCategory = selectedCategory,
        updateSelectedCategory = updateSelectedCategory
    )


    if(selectedCategory == SearchCategoryType.All)
        Spacer(Modifier.height(24.dp))
    else if(selectedCategory == SearchCategoryType.NanaPick)
        Spacer(Modifier.height(16.dp))
    else
        SearchResultFilterLayout(
            searchViewModel,
            selectedCategory
        ) { searchViewModel.getSearchResult(inputText, true, false) }

    SearchResultScreenSearchResult(
        selectedCategory = selectedCategory,
        allSearchResultList = allSearchResultList,
        categorizedSearchResultList = categorizedSearchResultList,
        getSearchResult = { getSearchResult(inputText) },
        updateSearchCategoryType = updateSelectedCategory,
        toggleAllSearchResultFavorite = toggleAllSearchResultFavorite,
        toggleSearchResultFavorite = toggleSearchResultFavorite,
        onPostClick = onPostClick,
        moveToSignInScreen = moveToSignInScreen,
    )
}





@Composable
private fun SearchResultFilterLayout(
    vm: SearchViewModel,
    selectedCategory: SearchCategoryType,
    updateFilter: ()->Unit
) {
    if(selectedCategory == SearchCategoryType.All || selectedCategory == SearchCategoryType.NanaPick)
        return

    val startCalendar = vm.startCalendar.collectAsState().value
    val endCalendar = vm.endCalendar.collectAsState().value

    var isDateFilterShowing by remember { mutableStateOf(false) }
    var isLocationFilterShowing by remember { mutableStateOf(false) }
    var isActivityFilterShowing by remember { mutableStateOf(false) }
    var isArtFilterShowing by remember { mutableStateOf(false) }
    var isRestaurantFilterShowing by remember { mutableStateOf(false) }

    val locationList = remember { getLocationList() }
    val activityKeywordList = remember { getActivityKeywordList() }
    val artKeywordList = remember { getCultureArtKeywordList() }
    val restaurantKeywordList = remember { getRestaurantKeywordList() }

/** 날짜, 지역 **/
    if(selectedCategory == SearchCategoryType.Festival)
        DateLocationFilterTopBar(
            selectedLocationList = vm.selectedLocationList,
            locationList = locationList,
            openDateFilterDialog = { isDateFilterShowing = true },
            openLocationFilterDialog = { isLocationFilterShowing = true },
            startCalendar = startCalendar,
            endCalendar = endCalendar,
        )

/** 지역 **/
    else if(selectedCategory == SearchCategoryType.Nature || selectedCategory == SearchCategoryType.Market)
        LocationFilterTopBar(
            selectedLocationList = vm.selectedLocationList,
            locationList = locationList,
            openLocationFilterDialog = { isLocationFilterShowing = true },
        )

/** 키워드, 지역(액티비티, 문화예술, 맛집) **/
    else if(selectedCategory == SearchCategoryType.Activity || selectedCategory == SearchCategoryType.Art || selectedCategory == SearchCategoryType.Restaurant) {
        val text: String
        val selectedKeywordList: SnapshotStateList<Boolean>
        val keywordList: List<String>
        val openKeywordFilterDialog: () -> Unit

        when (selectedCategory) {
            SearchCategoryType.Activity -> {
                text = getString(R.string.review_write_keyword_title)
                selectedKeywordList = vm.selectedActivityKeywordList
                keywordList = activityKeywordList
                openKeywordFilterDialog = { isActivityFilterShowing = true }
            }
            SearchCategoryType.Art -> {
                text = getString(R.string.review_write_keyword_title)
                selectedKeywordList = vm.selectedCultureArtKeywordList
                keywordList = artKeywordList
                openKeywordFilterDialog = { isArtFilterShowing = true }
            }
            else -> {
                text = getString(R.string.common_종류)
                selectedKeywordList = vm.selectedRestaurantKeywordList
                keywordList = restaurantKeywordList
                openKeywordFilterDialog = { isRestaurantFilterShowing = true }
            }
        }

        KeywordLocationFilterTopBar(
            text = text,
            selectedKeywordList = selectedKeywordList,
            keywordList = keywordList,
            selectedLocationList = vm.selectedLocationList,
            locationList = locationList,
            openKeywordFilterDialog = openKeywordFilterDialog,
            openLocationFilterDialog = { isLocationFilterShowing = true }
        )
    }


/** 날짜 바텀시트 **/
    if(isDateFilterShowing)
        BottomSheetFilterDialog(
            onDismiss = { isDateFilterShowing = false },
            startCalendar = startCalendar,
            endCalendar = endCalendar,
            updateStartCalendar = vm::updateStartCalendar,
            updateEndCalendar = vm::updateEndCalendar,
            updateList = updateFilter,
            clearList = {}
        )
/** 그 외 바텀시트 **/
    else if (isLocationFilterShowing || isActivityFilterShowing || isArtFilterShowing || isRestaurantFilterShowing) {
        val type: BottomSheetFilterDialogType
        val stringList: List<String>
        val selectedList: SnapshotStateList<Boolean>

        if (isLocationFilterShowing) {
            type = BottomSheetFilterDialogType.Location
            stringList = locationList
            selectedList = vm.selectedLocationList
        } else if (isActivityFilterShowing) {
            type = BottomSheetFilterDialogType.Activity
            stringList = activityKeywordList
            selectedList = vm.selectedActivityKeywordList
        } else if (isArtFilterShowing) {
            type = BottomSheetFilterDialogType.Art
            stringList = artKeywordList
            selectedList = vm.selectedCultureArtKeywordList
        } else {
            type = BottomSheetFilterDialogType.Restaurant
            stringList = restaurantKeywordList
            selectedList = vm.selectedRestaurantKeywordList
        }

        BottomSheetFilterDialog(
            type = type,
            onDismiss = {
                isLocationFilterShowing = false
                isActivityFilterShowing = false
                isArtFilterShowing = false
                isRestaurantFilterShowing = false
            },
            stringList = stringList,
            selectedList = selectedList,
            updateList = updateFilter,
            clearList = {}
        )
    }
}