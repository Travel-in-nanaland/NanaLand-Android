package com.jeju.nanaland.ui.restaurant

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.restaurant.RestaurantThumbnailData
import com.jeju.nanaland.globalvalue.constant.PAGING_THRESHOLD
import com.jeju.nanaland.globalvalue.constant.getLocationList
import com.jeju.nanaland.globalvalue.constant.getRestaurantKeywordList
import com.jeju.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.KeywordLocationFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.LocationFilterBottomDialog
import com.jeju.nanaland.ui.component.listscreen.filter.RestaurantFilterDialogDimBackground
import com.jeju.nanaland.ui.component.listscreen.filter.RestaurantKeywordFilterDialog
import com.jeju.nanaland.ui.component.listscreen.filter.getLocationAnchoredDraggableState
import com.jeju.nanaland.ui.component.listscreen.filter.getRestaurantKeywordAnchoredDraggableState
import com.jeju.nanaland.ui.component.listscreen.list.RestaurantThumbnailList
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun RestaurantListScreen(
    moveToBackScreen: () -> Unit,
    moveToRestaurantContentScreen: (Int) -> Unit,
    moveToSignInScreen: () -> Unit,
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
    isContent: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyGridState = rememberLazyGridState()
    val locationList = remember { getLocationList() }
    val keywordList = remember { getRestaurantKeywordList() }
    val isDimBackgroundShowing = remember { mutableStateOf(false) }
    val locationFilterDialogAnchoredDraggableState = remember { getLocationAnchoredDraggableState() }
    val restaurantKeywordFilterDialogAnchoredDraggableState = remember { getRestaurantKeywordAnchoredDraggableState() }

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
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CustomTopBar(
                    title = getString(R.string.common_제주_맛집),
                    onBackButtonClicked = moveToBackScreen
                )

                Spacer(Modifier.height(16.dp))

                KeywordLocationFilterTopBar(
                    text = getString(R.string.common_종류),
                    count = restaurantThumbnailCount,
                    selectedKeywordList = selectedRestaurantKeywordList,
                    keywordList = getRestaurantKeywordList(),
                    selectedLocationList = selectedLocationList,
                    locationList = getLocationList(),
                    openKeywordFilterDialog = {
                        coroutineScope.launch { restaurantKeywordFilterDialogAnchoredDraggableState.animateTo(
                            AnchoredDraggableContentState.Open) }
                    },
                    openLocationFilterDialog = {
                        coroutineScope.launch { locationFilterDialogAnchoredDraggableState.animateTo(
                        AnchoredDraggableContentState.Open) }
                    },
                    showDimBackground = { isDimBackgroundShowing.value = true }
                )

                RestaurantThumbnailList(
                    listState = lazyGridState,
                    thumbnailList = restaurantThumbnailList,
                    toggleFavorite = toggleFavorite,
                    moveToRestaurantContentScreen = moveToRestaurantContentScreen,
                    moveToSignInScreen = moveToSignInScreen
                )
            }

            if (isDimBackgroundShowing.value) {
                RestaurantFilterDialogDimBackground(
                    isDimBackgroundShowing = isDimBackgroundShowing,
                    locationAnchoredDraggableState = locationFilterDialogAnchoredDraggableState,
                    keywordAnchoredDraggableState = restaurantKeywordFilterDialogAnchoredDraggableState,
                )
            }

            LocationFilterBottomDialog(
                locationList = locationList,
                hideDimBackground = { isDimBackgroundShowing.value = false },
                anchoredDraggableState = locationFilterDialogAnchoredDraggableState,
                selectedLocationList = selectedLocationList,
                updateList = {
                    getRestaurantList()
                },
                clearList = {
                    clearRestaurantList()
                    coroutineScope.launch { lazyGridState.scrollToItem(0) }
                }
            )

            RestaurantKeywordFilterDialog(
                keywordList = keywordList,
                hideDimBackground = { isDimBackgroundShowing.value = false },
                anchoredDraggableState = restaurantKeywordFilterDialogAnchoredDraggableState,
                selectedKeywordList = selectedRestaurantKeywordList,
                updateList = {
                    getRestaurantList()
                },
                clearList = {
                    clearRestaurantList()
                    coroutineScope.launch { lazyGridState.scrollToItem(0) }
                }
            )
        }
    }
}