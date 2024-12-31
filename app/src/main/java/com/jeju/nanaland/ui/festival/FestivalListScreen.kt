package com.jeju.nanaland.ui.festival

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
import com.jeju.nanaland.domain.entity.festival.FestivalThumbnailData
import com.jeju.nanaland.globalvalue.constant.PAGING_THRESHOLD
import com.jeju.nanaland.globalvalue.constant.getLocationList
import com.jeju.nanaland.globalvalue.type.FestivalCategoryType
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.bottombar.MainNavigationBar
import com.jeju.nanaland.ui.component.common.dialog.BottomSheetFilterDialog
import com.jeju.nanaland.ui.component.common.dialog.BottomSheetFilterDialogType
import com.jeju.nanaland.ui.component.common.icon.GoToUpInList
import com.jeju.nanaland.ui.component.common.layoutSet.ListEmptyByFilter
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.component.listscreen.category.FestivalCategoryListTab
import com.jeju.nanaland.ui.component.listscreen.filter.DateLocationFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.LocationFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.SeasonFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.list.FestivalThumbnailList
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch
import java.util.Calendar

@Composable
fun FestivalListScreen(
    filter: String?,
    moveToBackScreen: () -> Unit,
    moveToFestivalContentScreen: (Int) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToSearchScreen: () -> Unit,
    toHome: () -> Unit,
    toFavorite: () -> Unit,
    toNana: () -> Unit,
    toProfile: () -> Unit,
    viewModel: FestivalListViewModel = hiltViewModel()
) {
    val selectedCategoryType = viewModel.selectedCategoryType.collectAsState().value
    val selectedLocationList = viewModel.selectedLocationList
    val selectedSeasonList = viewModel.selectedSeasonList
    val startCalendar = viewModel.startCalendar.collectAsState().value
    val endCalendar = viewModel.endCalendar.collectAsState().value
    val festivalThumbnailList = viewModel.festivalThumbnailList.collectAsState().value
    val festivalThumbnailCount = viewModel.festivalThumbnailCount.collectAsState().value

    LaunchedEffect(Unit) {
        if (filter != null) {
            val startYear = filter.split("~")[0].split(".")[0]
            val startMonth = filter.split("~")[0].split(".")[1]
            val startDate = filter.split("~")[0].split(".")[2]
            val endYear = filter.split("~")[1].split(".")[0]
            val endMonth = filter.split("~")[1].split(".")[1]
            val endDate = filter.split("~")[1].split(".")[2]
            viewModel.updateStartCalendar(Calendar.getInstance().apply {
                set(startYear.toInt(), startMonth.toInt() - 1, startDate.toInt())
            })
            viewModel.updateEndCalendar(Calendar.getInstance().apply {
                set(endYear.toInt(), endMonth.toInt() - 1, endDate.toInt())
            })
        }
        viewModel.getMonthlyFestivalList()
    }

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
    festivalThumbnailCount: UiState<Int>,
    toggleFavorite: (Int) -> Unit,
    getMonthlyFestivalList: () -> Unit,
    getEndedFestivalList: () -> Unit,
    getSeasonalFestivalList: () -> Unit,
    clearFestivalList: () -> Unit,
    moveToBackScreen: () -> Unit,
    moveToFestivalContentScreen: (Int) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToSearchScreen: () -> Unit,
    toHome: () -> Unit,
    toFavorite: () -> Unit,
    toNana: () -> Unit,
    toProfile: () -> Unit,
    isContent: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    var isDateFilterShowing by remember { mutableStateOf(false) }
    var isSeasonFilterShowing by remember { mutableStateOf(false) }
    var isLocationFilterShowing by remember { mutableStateOf(false) }
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
            when (selectedCategoryType) {
                FestivalCategoryType.Monthly -> { getMonthlyFestivalList() }
                FestivalCategoryType.Ended -> { getEndedFestivalList() }
                FestivalCategoryType.Seasonal -> { getSeasonalFestivalList() }
            }
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
                    title = getString(R.string.common_축제),
                    onBackButtonClicked = moveToBackScreen,
                    menus = arrayOf(R.drawable.ic_search_normal to moveToSearchScreen)
                )

                Spacer(Modifier.height(16.dp))

                FestivalCategoryListTab(
                    selectedCategoryType = selectedCategoryType,
                    updateSelectedCategoryType = updateSelectedCategoryType
                )

                when (selectedCategoryType) {
                    FestivalCategoryType.Monthly -> {
                        DateLocationFilterTopBar(
                            selectedLocationList = selectedLocationList,
                            locationList = locationList,
                            openDateFilterDialog = { isDateFilterShowing = true },
                            openLocationFilterDialog = { isLocationFilterShowing = true },
                            startCalendar = startCalendar,
                            endCalendar = endCalendar,
                        )
                    }
                    FestivalCategoryType.Ended -> {
                        LocationFilterTopBar(
                            selectedLocationList = selectedLocationList,
                            locationList = locationList,
                            openLocationFilterDialog = { isLocationFilterShowing = true },
                        )
                    }
                    FestivalCategoryType.Seasonal -> {
                        SeasonFilterTopBar(
                            selectedSeasonList = selectedSeasonList,
                            openSeasonFilterDialog = { isSeasonFilterShowing = true },
                        )
                    }
                }

                if(
                    !(selectedLocationList.all { it } || selectedLocationList.all { !it }) && // if filter on
                    (festivalThumbnailList is UiState.Success && festivalThumbnailList.data.isEmpty()) // and list is empty
                )
                    ListEmptyByFilter {
                        updateStartCalendar(Calendar.getInstance())
                        updateEndCalendar(Calendar.getInstance())
                        selectedLocationList.forEachIndexed { i, _ ->
                            selectedLocationList[i] = false
                        }
                        clearFestivalList()
                        when(selectedCategoryType) {
                            FestivalCategoryType.Monthly -> getMonthlyFestivalList()
                            FestivalCategoryType.Ended -> getEndedFestivalList()
                            FestivalCategoryType.Seasonal -> getSeasonalFestivalList()
                        }
                    }
                else
                    FestivalThumbnailList(
                        listState = lazyGridState,
                        thumbnailList = festivalThumbnailList,
                        toggleFavorite = toggleFavorite,
                        moveToFestivalContentScreen = moveToFestivalContentScreen,
                        moveToSignInScreen = moveToSignInScreen,
                    )
            }

            if(isLocationFilterShowing)
                BottomSheetFilterDialog(
                    type = BottomSheetFilterDialogType.Location,
                    onDismiss = { isLocationFilterShowing = false },
                    stringList = locationList,
                    selectedList = selectedLocationList,
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

            if(isDateFilterShowing)
                BottomSheetFilterDialog(
                    onDismiss = { isDateFilterShowing = false },
                    startCalendar = startCalendar,
                    endCalendar = endCalendar,
                    updateStartCalendar = updateStartCalendar,
                    updateEndCalendar = updateEndCalendar,
                    updateList = { getMonthlyFestivalList() },
                    clearList = { clearFestivalList() }
                )
            if(isSeasonFilterShowing)
                BottomSheetFilterDialog(
                    onDismiss = { isSeasonFilterShowing = false },
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