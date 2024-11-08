package com.jeju.nanaland.ui.market

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.market.MarketThumbnail
import com.jeju.nanaland.globalvalue.constant.PAGING_THRESHOLD
import com.jeju.nanaland.globalvalue.constant.getLocationList
import com.jeju.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.icon.GoToUpInList
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.FestivalFilterDialogDimBackground
import com.jeju.nanaland.ui.component.listscreen.filter.LocationFilterBottomDialog
import com.jeju.nanaland.ui.component.listscreen.filter.LocationFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.getLocationAnchoredDraggableState
import com.jeju.nanaland.ui.component.listscreen.list.MarketThumbnailList
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun MarketListScreen(
    moveToBackScreen: () -> Unit,
    moveToMarketContentScreen: (Int) -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: MarketListViewModel = hiltViewModel()
) {
    val selectedLocationList = viewModel.selectedLocationList
    val marketThumbnailCount = viewModel.marketThumbnailCount.collectAsState().value
    val marketThumbnailList = viewModel.marketThumbnailList.collectAsState().value
    MarketListScreen(
        selectedLocationList = selectedLocationList,
        marketThumbnailCount = marketThumbnailCount,
        marketThumbnailList = marketThumbnailList,
        getMarketList = viewModel::getMarketList,
        toggleFavorite = viewModel::toggleFavorite,
        clearMarketList = viewModel::clearMarketList,
        moveToBackScreen = moveToBackScreen,
        moveToMarketContentScreen = moveToMarketContentScreen,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MarketListScreen(
    selectedLocationList: SnapshotStateList<Boolean>,
    marketThumbnailCount: UiState<Int>,
    marketThumbnailList: UiState<List<MarketThumbnail>>,
    getMarketList: () -> Unit,
    toggleFavorite: (Int) -> Unit,
    clearMarketList: () -> Unit,
    moveToBackScreen: () -> Unit,
    moveToMarketContentScreen: (Int) -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    val locationFilterDialogAnchoredDraggableState = remember { getLocationAnchoredDraggableState() }
    val isDimBackgroundShowing = remember { mutableStateOf(false) }
    val locationList = remember { getLocationList() }
    val lazyGridState = rememberLazyGridState()
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
            getMarketList()
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
                    title = getString(R.string.common_전통시장),
                    onBackButtonClicked = moveToBackScreen
                )

                LocationFilterTopBar(
                    count = marketThumbnailCount,
                    selectedLocationList = selectedLocationList,
                    locationList = locationList,
                    openLocationFilterDialog = {
                        coroutineScope.launch {
                            locationFilterDialogAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Open)
                        }
                    },
                    showDimBackground = { isDimBackgroundShowing.value = true }
                )

                MarketThumbnailList(
                    listState = lazyGridState,
                    thumbnailList = marketThumbnailList,
                    toggleFavorite = toggleFavorite,
                    moveToMarketContentScreen = moveToMarketContentScreen,
                    moveToSignInScreen = moveToSignInScreen
                )
            }

            GoToUpInList(lazyGridState)

            if (isDimBackgroundShowing.value) {
                FestivalFilterDialogDimBackground(
                    isDimBackgroundShowing = isDimBackgroundShowing,
                    locationAnchoredDraggableState = locationFilterDialogAnchoredDraggableState
                )
            }

            LocationFilterBottomDialog(
                locationList = locationList,
                hideDimBackground = { isDimBackgroundShowing.value = false },
                anchoredDraggableState = locationFilterDialogAnchoredDraggableState,
                selectedLocationList = selectedLocationList,
                updateList = getMarketList,
                clearList = {
                    clearMarketList()
                    coroutineScope.launch { lazyGridState.scrollToItem(0) }
                }
            )
        }
    }
}

@ScreenPreview
@Composable
private fun MarketListScreenPreview() {

}