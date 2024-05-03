package com.nanaland.ui.market

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.domain.entity.market.MarketThumbnailData
import com.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.nanaland.ui.component.common.CustomSurface
import com.nanaland.ui.component.common.CustomTopBarWithShadow
import com.nanaland.ui.component.listscreen.filter.FilterDialogDimBackground
import com.nanaland.ui.component.listscreen.filter.LocationFilterBottomDialog
import com.nanaland.ui.component.listscreen.filter.LocationFilterTopBar
import com.nanaland.ui.component.listscreen.filter.getLocationAnchoredDraggableState
import com.nanaland.ui.component.listscreen.list.MarketThumbnailList
import com.nanaland.util.ui.ScreenPreview
import com.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun MarketListScreen(
    moveToBackScreen: () -> Unit,
    moveToMarketContentScreen: (Long) -> Unit,
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
        moveToBackScreen = moveToBackScreen,
        moveToMarketContentScreen = moveToMarketContentScreen,
        isContent = true
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MarketListScreen(
    selectedLocationList: SnapshotStateList<Boolean>,
    marketThumbnailCount: UiState<Long>,
    marketThumbnailList: UiState<List<MarketThumbnailData>>,
    getMarketList: () -> Unit,
    toggleFavorite: (Long) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToMarketContentScreen: (Long) -> Unit,
    isContent: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    val locationFilterDialogAnchoredDraggableState = remember { getLocationAnchoredDraggableState() }
    val isDimBackgroundShowing = remember { mutableStateOf(false) }
    val locationList = listOf("전체", "제주시", "애월", "서귀포시", "성산", "한림", "조천", "구좌", "한경", "대정", "안덕", "남원", "표선", "우도")
    CustomSurface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CustomTopBarWithShadow(
                    title = "전통시장",
                    onBackButtonClicked = moveToBackScreen
                )

                LocationFilterTopBar(
                    count = marketThumbnailCount,
                    selectedLocationList = selectedLocationList,
                    locationList = locationList,
                    openLocationFilterDialog = { coroutineScope.launch { locationFilterDialogAnchoredDraggableState.animateTo(
                        AnchoredDraggableContentState.Open) } },
                    showDimBackground = { isDimBackgroundShowing.value = true }
                )

                MarketThumbnailList(
                    thumbnailList = marketThumbnailList,
                    toggleFavorite = toggleFavorite,
                    moveToMarketContentScreen = moveToMarketContentScreen
                )
            }

            if (isDimBackgroundShowing.value) {
                FilterDialogDimBackground(
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
                clearList = {}
            )
        }
    }
}

@ScreenPreview
@Composable
private fun MarketListScreenPreview() {

}