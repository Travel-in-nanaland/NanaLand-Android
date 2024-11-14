package com.jeju.nanaland.ui.nature

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
import com.jeju.nanaland.domain.entity.nature.NatureThumbnail
import com.jeju.nanaland.globalvalue.constant.PAGING_THRESHOLD
import com.jeju.nanaland.globalvalue.constant.getLocationIdx
import com.jeju.nanaland.globalvalue.constant.getLocationList
import com.jeju.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.icon.GoToUpInList
import com.jeju.nanaland.ui.component.common.layoutSet.ListEmptyByFilter
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.FestivalFilterDialogDimBackground
import com.jeju.nanaland.ui.component.listscreen.filter.LocationFilterBottomDialog
import com.jeju.nanaland.ui.component.listscreen.filter.LocationFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.getLocationAnchoredDraggableState
import com.jeju.nanaland.ui.component.listscreen.list.NatureThumbnailList
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
            getNatureList()
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
                    title = getString(R.string.common_7대_자연),
                    onBackButtonClicked = moveToBackScreen
                )

                LocationFilterTopBar(
                    count = natureThumbnailCount,
                    selectedLocationList = selectedLocationList,
                    locationList = locationList,
                    openLocationFilterDialog = { coroutineScope.launch { locationFilterDialogAnchoredDraggableState.animateTo(
                        AnchoredDraggableContentState.Open) } },
                    showDimBackground = { isDimBackgroundShowing.value = true }
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