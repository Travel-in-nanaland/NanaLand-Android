package com.nanaland.ui.festival

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.domain.entity.festival.FestivalThumbnailData
import com.nanaland.globalvalue.type.FestivalCategoryType
import com.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.nanaland.ui.component.common.CustomSurface
import com.nanaland.ui.component.common.CustomTopBarWithShadow
import com.nanaland.ui.component.listscreen.category.CategoryListTab
import com.nanaland.ui.component.listscreen.filter.DateFilterBottomDialog
import com.nanaland.ui.component.listscreen.filter.FilterDialogDimBackground
import com.nanaland.ui.component.listscreen.filter.LocationFilterBottomDialog
import com.nanaland.ui.component.listscreen.filter.DateLocationFilterTopBar
import com.nanaland.ui.component.listscreen.filter.LocationFilterTopBar
import com.nanaland.ui.component.listscreen.filter.SeasonFilterTopBar
import com.nanaland.ui.component.listscreen.filter.getDateAnchoredDraggableState
import com.nanaland.ui.component.listscreen.filter.getLocationAnchoredDraggableState
import com.nanaland.ui.component.listscreen.list.FestivalThumbnailList
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.util.ui.ScreenPreview
import com.nanaland.util.ui.UiState
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
        festivalThumbnailList = festivalThumbnailList,
        festivalThumbnailCount = festivalThumbnailCount,
        getMonthlyFestivalList = viewModel::getMonthlyFestivalList,
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
    startCalendar: Calendar?,
    updateStartCalendar: (Calendar?) -> Unit,
    endCalendar: Calendar?,
    updateEndCalendar: (Calendar?) -> Unit,
    selectedLocationList: SnapshotStateList<Boolean>,
    festivalThumbnailList: UiState<List<FestivalThumbnailData>>,
    festivalThumbnailCount: UiState<Long>,
    getMonthlyFestivalList: () -> Unit,
    moveToBackScreen: () -> Unit,
    moveToFestivalContentScreen: (Long) -> Unit,
    isContent: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    val dateFilterDialogAnchoredDraggableState = remember { getDateAnchoredDraggableState() }
    val locationFilterDialogAnchoredDraggableState = remember { getLocationAnchoredDraggableState() }
    val locationList = listOf("전체", "제주시", "애월", "서귀포시", "성산", "한림", "조천", "구좌", "한경", "대정", "안덕", "남원", "표선", "우도")
    val isDimBackgroundShowing = remember { mutableStateOf(false) }
    CustomSurface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CustomTopBarWithShadow(
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
                        )
                    }
                }

                FestivalThumbnailList(
                    thumbnailList = festivalThumbnailList,
                    moveToFestivalContentScreen = moveToFestivalContentScreen
                )
            }

            if (isDimBackgroundShowing.value) {
                FilterDialogDimBackground(
                    isDimBackgroundShowing = isDimBackgroundShowing,
                    dateAnchoredDraggableState = dateFilterDialogAnchoredDraggableState,
                    locationAnchoredDraggableState = locationFilterDialogAnchoredDraggableState
                )
            }

            DateFilterBottomDialog(
                anchoredDraggableState = dateFilterDialogAnchoredDraggableState,
                hideDimBackground = { isDimBackgroundShowing.value = false },
                startCalendar = startCalendar,
                endCalendar = endCalendar,
                updateStartCalendar = updateStartCalendar,
                updateEndCalendar = updateEndCalendar,
                getMonthlyFestivalList = getMonthlyFestivalList,
            )

            LocationFilterBottomDialog(
                locationList = locationList,
                hideDimBackground = { isDimBackgroundShowing.value = false },
                anchoredDraggableState = locationFilterDialogAnchoredDraggableState,
                selectedLocationList = selectedLocationList,
                updateList = {},
                clearList = {}
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