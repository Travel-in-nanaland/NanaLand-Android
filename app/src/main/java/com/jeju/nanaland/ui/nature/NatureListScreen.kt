package com.jeju.nanaland.ui.nature

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.nature.NatureThumbnail
import com.jeju.nanaland.globalvalue.constant.PAGING_THRESHOLD
import com.jeju.nanaland.globalvalue.constant.getLocationIdx
import com.jeju.nanaland.globalvalue.constant.getLocationList
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.bottombar.MainNavigationBar
import com.jeju.nanaland.ui.component.common.dialog.BottomSheetFilterDialog
import com.jeju.nanaland.ui.component.common.dialog.BottomSheetFilterDialogType
import com.jeju.nanaland.ui.component.common.icon.GoToUpInList
import com.jeju.nanaland.ui.component.common.layoutSet.ListEmptyByFilter
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.component.listscreen.filter.LocationFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.list.NatureThumbnailList
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun NatureListScreen(
    filter: String?,
    moveToBackScreen: () -> Unit,
    moveToNatureContentScreen: (Int) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToSearchScreen: () -> Unit,
    toHome: () -> Unit,
    toFavorite: () -> Unit,
    toNana: () -> Unit,
    toProfile: () -> Unit,
    viewModel: NatureListViewModel = hiltViewModel()
) {
    val selectedLocationList = viewModel.selectedLocationList
    val natureThumbnailCount = viewModel.natureThumbnailCount.collectAsState().value
    val natureThumbnailList = viewModel.natureThumbnailList.collectAsState().value

    LaunchedEffect(Unit) {
        if (filter != null) {
            selectedLocationList[getLocationIdx(filter)] = true
        }
    }

    NatureListScreen(
        selectedLocationList = selectedLocationList,
        natureThumbnailCount = natureThumbnailCount,
        natureThumbnailList = natureThumbnailList,
        getNatureList = viewModel::getNatureList,
        toggleFavorite = viewModel::toggleFavorite,
        clearNatureList = viewModel::clearNatureList,
        moveToBackScreen = moveToBackScreen,
        moveToNatureContentScreen = moveToNatureContentScreen,
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
private fun NatureListScreen(
    selectedLocationList: SnapshotStateList<Boolean>,
    natureThumbnailCount: UiState<Int>,
    natureThumbnailList: UiState<List<NatureThumbnail>>,
    toggleFavorite: (Int) -> Unit,
    clearNatureList: () -> Unit,
    getNatureList: () -> Unit,
    moveToBackScreen: () -> Unit,
    moveToNatureContentScreen: (Int) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToSearchScreen: () -> Unit,
    toHome: () -> Unit,
    toFavorite: () -> Unit,
    toNana: () -> Unit,
    toProfile: () -> Unit,
    isContent: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
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
            getNatureList()
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
                    title = getString(R.string.common_자연),
                    onBackButtonClicked = moveToBackScreen,
                    menus = arrayOf(R.drawable.ic_search_normal to moveToSearchScreen)
                )

                LocationFilterTopBar(
                    selectedLocationList = selectedLocationList,
                    locationList = locationList,
                    openLocationFilterDialog = { isLocationFilterShowing = true },
                )
                if (
                    !(selectedLocationList.all { it } || selectedLocationList.all { !it }) && // if filter on
                    (natureThumbnailList is UiState.Success && natureThumbnailList.data.isEmpty()) // and list is empty
                )
                    ListEmptyByFilter {
                        selectedLocationList.forEachIndexed { i, _ ->
                            selectedLocationList[i] = false
                        }
                        clearNatureList()
                        getNatureList()
                    }
                else
                    NatureThumbnailList(
                        listState = lazyGridState,
                        thumbnailList = natureThumbnailList,
                        toggleFavorite = toggleFavorite,
                        moveToNatureContentScreen = moveToNatureContentScreen,
                        moveToSignInScreen = moveToSignInScreen,
                    )
            }


            if (isLocationFilterShowing)
                BottomSheetFilterDialog(
                    type = BottomSheetFilterDialogType.Location,
                    onDismiss = { isLocationFilterShowing = false },
                    stringList = locationList,
                    selectedList = selectedLocationList,
                    updateList = getNatureList,
                    clearList = {
                        clearNatureList()
                        coroutineScope.launch { lazyGridState.scrollToItem(0) }
                    }
                )
        }
    }
}

@ScreenPreview
@Composable
private fun NatureListScreenPreview() {

}