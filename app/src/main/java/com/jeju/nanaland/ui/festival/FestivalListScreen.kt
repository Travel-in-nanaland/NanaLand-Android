package com.jeju.nanaland.ui.festival

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
import com.jeju.nanaland.domain.entity.festival.FestivalThumbnailData
import com.jeju.nanaland.globalvalue.constant.PAGING_THRESHOLD
import com.jeju.nanaland.globalvalue.constant.getLocationList
import com.jeju.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.jeju.nanaland.globalvalue.type.FestivalCategoryType
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.component.listscreen.category.CategoryListTab
import com.jeju.nanaland.ui.component.listscreen.filter.DateFilterBottomDialog
import com.jeju.nanaland.ui.component.listscreen.filter.DateLocationFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.FilterDialogDimBackground
import com.jeju.nanaland.ui.component.listscreen.filter.LocationFilterBottomDialog
import com.jeju.nanaland.ui.component.listscreen.filter.LocationFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.SeasonFilterBottomDialog
import com.jeju.nanaland.ui.component.listscreen.filter.SeasonFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.getDateAnchoredDraggableState
import com.jeju.nanaland.ui.component.listscreen.filter.getLocationAnchoredDraggableState
import com.jeju.nanaland.ui.component.listscreen.filter.getSeasonAnchoredDraggableState
import com.jeju.nanaland.ui.component.listscreen.list.FestivalThumbnailList
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch
import java.util.Calendar

@Composable
fun FestivalListScreen(
    moveToBackScreen: () -> Unit,
    moveToFestivalContentScreen: (Long) -> Unit,
    viewModel: FestivalListViewModel = hiltViewModel()
) {
    val selectedCategoryType = viewModel.selectedCategoryType.collectAsState().value
    val selectedLocationList = viewModel.selectedLocationList
    val selectedSeasonList = viewModel.selectedSeasonList
    val startCalendar = viewModel.startCalendar.collectAsState().value
    val endCalendar = viewModel.endCalendar.collectAsState().value
    val festivalThumbnailList = viewModel.festivalThumbnailList.collectAsState().value
    val festivalThumbnailCount = viewModel.festivalThumbnailCount.collectAsState().value
    FestivalListScreen(
        selectedCategoryType = selectedCategoryType,
        updateSelectedCategoryType = viewModel::updateSelectedCategoryType,
        startCalendar = startCalendar,
        updateStartCalendar = viewModel::updateStartCalendar,
        endCalendar = endCalendar,
        updateEndCalendar = viewModel::updateEndCalendar,
        selectedLocationList = selectedLocationList,
        selectedSeasonList = selectedSeasonList,
        festivalThumbnailList = festivalThumbnailList,
        festivalThumbnailCount = festivalThumbnailCount,
        toggleFavorite = viewModel::toggleFavorite,
        getMonthlyFestivalList = viewModel::getMonthlyFestivalList,
        getEndedFestivalList = viewModel::getEndedFestivalList,
        getSeasonalFestivalList = viewModel::getSeasonalFestivalList,
        clearFestivalList = viewModel::clearFestivalList,
        moveToBackScreen = moveToBackScreen,
        moveToFestivalContentScreen = moveToFestivalContentScreen,
        isContent = true
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FestivalListScreen(
    selectedCategoryType: FestivalCategoryType,
    updateSelectedCategoryType: (FestivalCategoryType) -> Unit,
    startCalendar: Calendar,
    updateStartCalendar: (Calendar) -> Unit,
    endCalendar: Calendar,
    updateEndCalendar: (Calendar) -> Unit,
    selectedLocationList: SnapshotStateList<Boolean>,
    selectedSeasonList: SnapshotStateList<Boolean>,
    festivalThumbnailList: UiState<List<FestivalThumbnailData>>,
    festivalThumbnailCount: UiState<Long>,
    toggleFavorite: (Long) -> Unit,
    getMonthlyFestivalList: () -> Unit,
    getEndedFestivalList: () -> Unit,
    getSeasonalFestivalList: () -> Unit,
    clearFestivalList: () -> Unit,
    moveToBackScreen: () -> Unit,
    moveToFestivalContentScreen: (Long) -> Unit,
    isContent: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    val dateFilterDialogAnchoredDraggableState = remember { getDateAnchoredDraggableState() }
    val locationFilterDialogAnchoredDraggableState = remember { getLocationAnchoredDraggableState() }
    val seasonFilterDialogAnchoredDraggableState = remember { getSeasonAnchoredDraggableState() }
    val locationList = remember { getLocationList() }
    val seasonList = remember { listOf("봄", "여름", "가을", "겨울") }
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
            when (selectedCategoryType) {
                FestivalCategoryType.Monthly -> { getMonthlyFestivalList() }
                FestivalCategoryType.Ended -> { getEndedFestivalList() }
                FestivalCategoryType.Seasonal -> { getSeasonalFestivalList() }
            }
        }
    }
    val isDimBackgroundShowing = remember { mutableStateOf(false) }
    CustomSurface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CustomTopBar(
                    title = "축제",
                    onBackButtonClicked = moveToBackScreen
                )

                Spacer(Modifier.height(16.dp))

                CategoryListTab(
                    selectedCategoryType = selectedCategoryType,
                    updateSelectedCategoryType = updateSelectedCategoryType
                )

                when (selectedCategoryType) {
                    FestivalCategoryType.Monthly -> {
                        DateLocationFilterTopBar(
                            count = festivalThumbnailCount,
                            selectedLocationList = selectedLocationList,
                            locationList = locationList,
                            openDateFilterDialog = { coroutineScope.launch { dateFilterDialogAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Open) } },
                            openLocationFilterDialog = { coroutineScope.launch { locationFilterDialogAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Open) } },
                            showDimBackground = { isDimBackgroundShowing.value = true },
                            startCalendar = startCalendar,
                            endCalendar = endCalendar,
                        )
                    }
                    FestivalCategoryType.Ended -> {
                        LocationFilterTopBar(
                            count = festivalThumbnailCount,
                            selectedLocationList = selectedLocationList,
                            locationList = locationList,
                            openLocationFilterDialog = { coroutineScope.launch { locationFilterDialogAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Open) } },
                            showDimBackground = { isDimBackgroundShowing.value = true }
                        )
                    }
                    FestivalCategoryType.Seasonal -> {
                        SeasonFilterTopBar(
                            count = festivalThumbnailCount,
                            selectedSeasonList = selectedSeasonList,
                            seasonList = seasonList,
                            openSeasonFilterDialog = { coroutineScope.launch { seasonFilterDialogAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Open) } },
                            showDimBackground = { isDimBackgroundShowing.value = true }
                        )
                    }
                }

                FestivalThumbnailList(
                    listState = lazyGridState,
                    thumbnailList = festivalThumbnailList,
                    toggleFavorite = toggleFavorite,
                    moveToFestivalContentScreen = moveToFestivalContentScreen
                )
            }

            if (isDimBackgroundShowing.value) {
                FilterDialogDimBackground(
                    isDimBackgroundShowing = isDimBackgroundShowing,
                    dateAnchoredDraggableState = dateFilterDialogAnchoredDraggableState,
                    locationAnchoredDraggableState = locationFilterDialogAnchoredDraggableState,
                    seasonAnchoredDraggableState = seasonFilterDialogAnchoredDraggableState
                )
            }

            DateFilterBottomDialog(
                anchoredDraggableState = dateFilterDialogAnchoredDraggableState,
                hideDimBackground = { isDimBackgroundShowing.value = false },
                startCalendar = startCalendar,
                endCalendar = endCalendar,
                updateStartCalendar = updateStartCalendar,
                updateEndCalendar = updateEndCalendar,
                updateList = { getMonthlyFestivalList() },
                clearList = { clearFestivalList() }
            )

            LocationFilterBottomDialog(
                locationList = locationList,
                hideDimBackground = { isDimBackgroundShowing.value = false },
                anchoredDraggableState = locationFilterDialogAnchoredDraggableState,
                selectedLocationList = selectedLocationList,
                updateList = {
                    when(selectedCategoryType) {
                        FestivalCategoryType.Monthly -> {
                            getMonthlyFestivalList()
                        }
                        FestivalCategoryType.Ended -> {
                            getEndedFestivalList()
                        }
                        FestivalCategoryType.Seasonal -> {}
                    }
                },
                clearList = {
                    clearFestivalList()
                    coroutineScope.launch { lazyGridState.scrollToItem(0) }
                }
            )

            SeasonFilterBottomDialog(
                seasonList = seasonList,
                hideDimBackground = { isDimBackgroundShowing.value = false },
                anchoredDraggableState = seasonFilterDialogAnchoredDraggableState,
                selectedSeasonList = selectedSeasonList,
                updateList = {
                    when(selectedCategoryType) {
                        FestivalCategoryType.Seasonal -> {
                            getSeasonalFestivalList()
                        }
                        else -> {}
                    }
                },
                clearList = {
                    clearFestivalList()
                    coroutineScope.launch { lazyGridState.scrollToItem(0) }
                },
            )
        }
    }
}


@ScreenPreview
@Composable
private fun FestivalListScreenPreview() {
    NanaLandTheme {

    }
}