package com.jeju.nanaland.ui.restaurant

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.restaurant.RestaurantThumbnailData
import com.jeju.nanaland.globalvalue.constant.PAGING_THRESHOLD
import com.jeju.nanaland.globalvalue.constant.getLocationList
import com.jeju.nanaland.globalvalue.constant.getRestaurantKeywordList
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.bottombar.MainNavigationBar
import com.jeju.nanaland.ui.component.common.dialog.BottomSheetFilterDialog
import com.jeju.nanaland.ui.component.common.dialog.BottomSheetFilterDialogType
import com.jeju.nanaland.ui.component.common.icon.GoToUpInList
import com.jeju.nanaland.ui.component.common.layoutSet.ListEmptyByFilter
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.component.listscreen.filter.KeywordLocationFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.list.RestaurantThumbnailList
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun RestaurantListScreen(
    moveToBackScreen: () -> Unit,
    moveToRestaurantContentScreen: (Int) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToSearchScreen: () -> Unit,
    toHome: () -> Unit,
    toFavorite: () -> Unit,
    toNana: () -> Unit,
    toProfile: () -> Unit,
    viewModel: RestaurantListViewModel = hiltViewModel()
) {
    val restaurantThumbnailCount = viewModel.restaurantThumbnailCount.collectAsState().value
    val restaurantThumbnailList = viewModel.restaurantThumbnailDataList.collectAsState().value
    val selectedRestaurantKeywordList = viewModel.selectedRestaurantKeywordList
    val selectedLocationList = viewModel.selectedLocationList
    RestaurantListScreen(
        restaurantThumbnailCount = restaurantThumbnailCount,
        restaurantThumbnailList = restaurantThumbnailList,
        selectedRestaurantKeywordList = selectedRestaurantKeywordList,
        selectedLocationList = selectedLocationList,
        getRestaurantList = viewModel::getRestaurantList,
        toggleFavorite = viewModel::toggleFavorite,
        clearRestaurantList = viewModel::clearExperienceList,
        moveToBackScreen = moveToBackScreen,
        moveToRestaurantContentScreen = moveToRestaurantContentScreen,
        moveToSignInScreen = moveToSignInScreen,
        moveToSearchScreen = moveToSearchScreen,
        toHome = toHome,
        toFavorite = toFavorite,
        toNana = toNana,
        toProfile = toProfile,
        isContent = true
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RestaurantListScreen(
    restaurantThumbnailCount: UiState<Int>,
    restaurantThumbnailList: UiState<List<RestaurantThumbnailData>>,
    selectedRestaurantKeywordList: SnapshotStateList<Boolean>,
    selectedLocationList: SnapshotStateList<Boolean>,
    getRestaurantList: () -> Unit,
    toggleFavorite: (Int) -> Unit,
    clearRestaurantList: () -> Unit,
    moveToBackScreen: () -> Unit,
    moveToRestaurantContentScreen: (Int) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToSearchScreen: () -> Unit,
    toHome: () -> Unit,
    toFavorite: () -> Unit,
    toNana: () -> Unit,
    toProfile: () -> Unit,
    isContent: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyGridState = rememberLazyGridState()
    val locationList = remember { getLocationList() }
    val keywordList = remember { getRestaurantKeywordList() }
    var isLocationFilterShowing by remember { mutableStateOf(false) }
    var isRestaurantFilterShowing by remember { mutableStateOf(false) }

    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = lazyGridState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
            lastVisibleItemIndex > (totalItemsNumber - PAGING_THRESHOLD)
        }
    }

    LaunchedEffect(loadMore.value) {
        if (loadMore.value) {
            getRestaurantList()
        }
    }

    CustomSurface {
        Scaffold(
            containerColor = getColor().surface,
            bottomBar = { MainNavigationBar(toHome,toFavorite,toNana,toProfile) },
            floatingActionButton = { GoToUpInList(lazyGridState) },
        ) {
            it
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TopBarCommon(
                    title = getString(R.string.common_제주_맛집),
                    onBackButtonClicked = moveToBackScreen,
                    menus = arrayOf(R.drawable.ic_search_normal to moveToSearchScreen)
                )

                Spacer(Modifier.height(16.dp))

                KeywordLocationFilterTopBar(
                    text = getString(R.string.common_종류),
                    selectedKeywordList = selectedRestaurantKeywordList,
                    keywordList = getRestaurantKeywordList(),
                    selectedLocationList = selectedLocationList,
                    locationList = getLocationList(),
                    openKeywordFilterDialog = { isRestaurantFilterShowing = true },
                    openLocationFilterDialog = { isLocationFilterShowing = true },
                )
                if (//if filter on
                    (
                        !(selectedLocationList.all { it } || selectedLocationList.all { !it }) || // if location filter on
                        !(selectedRestaurantKeywordList.all { it } || selectedRestaurantKeywordList.all { !it }) // if keyword filter on
                    ) &&
                    (restaurantThumbnailList is UiState.Success && restaurantThumbnailList.data.isEmpty()) // and list is empty
                )
                    ListEmptyByFilter {
                        selectedLocationList.forEachIndexed { i, _ ->
                            selectedLocationList[i] = false
                        }
                        selectedRestaurantKeywordList.forEachIndexed { i, _ ->
                            selectedRestaurantKeywordList[i] = false
                        }
                        clearRestaurantList()
                        getRestaurantList()
                    }
                else
                    RestaurantThumbnailList(
                        listState = lazyGridState,
                        thumbnailList = restaurantThumbnailList,
                        toggleFavorite = toggleFavorite,
                        moveToRestaurantContentScreen = moveToRestaurantContentScreen,
                        moveToSignInScreen = moveToSignInScreen
                    )
            }

            if (isLocationFilterShowing || isRestaurantFilterShowing)
                BottomSheetFilterDialog(
                    type = if(isLocationFilterShowing) BottomSheetFilterDialogType.Location else BottomSheetFilterDialogType.Restaurant,
                    onDismiss = {
                        isLocationFilterShowing = false
                        isRestaurantFilterShowing = false
                    },
                    stringList = if(isLocationFilterShowing) locationList else keywordList,
                    selectedList = if(isLocationFilterShowing) selectedLocationList else selectedRestaurantKeywordList,
                    updateList = getRestaurantList,
                    clearList = {
                        clearRestaurantList()
                        coroutineScope.launch { lazyGridState.scrollToItem(0) }
                    }
                )
        }
    }
}