package com.nanaland.ui.nature

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
import com.nanaland.domain.entity.nature.NatureThumbnailData
import com.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.nanaland.ui.component.common.CustomSurface
import com.nanaland.ui.component.common.CustomTopBarWithShadow
import com.nanaland.ui.component.listscreen.filter.FilterDialogDimBackground
import com.nanaland.ui.component.listscreen.filter.LocationFilterBottomDialog
import com.nanaland.ui.component.listscreen.filter.LocationFilterTopBar
import com.nanaland.ui.component.listscreen.filter.getLocationAnchoredDraggableState
import com.nanaland.ui.component.listscreen.list.NatureThumbnailList
import com.nanaland.util.ui.ScreenPreview
import com.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun NatureListScreen(
    moveToBackScreen: () -> Unit,
    moveToNatureContentScreen: (Long) -> Unit,
    viewModel: NatureListViewModel = hiltViewModel()
) {
    val selectedLocationList = viewModel.selectedLocationList
    val natureThumbnailCount = viewModel.natureThumbnailCount.collectAsState().value
    val natureThumbnailList = viewModel.natureThumbnailList.collectAsState().value
    NatureListScreen(
        selectedLocationList = selectedLocationList,
        natureThumbnailCount = natureThumbnailCount,
        natureThumbnailList = natureThumbnailList,
        getNatureList = viewModel::getNatureList,
        toggleFavorite = viewModel::toggleFavorite,
        clearNatureList = viewModel::clearNatureList,
        moveToBackScreen = moveToBackScreen,
        moveToNatureContentScreen = moveToNatureContentScreen,
        isContent = true
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NatureListScreen(
    selectedLocationList: SnapshotStateList<Boolean>,
    natureThumbnailCount: UiState<Long>,
    natureThumbnailList: UiState<List<NatureThumbnailData>>,
    toggleFavorite: (Long) -> Unit,
    clearNatureList: () -> Unit,
    getNatureList: () -> Unit,
    moveToBackScreen: () -> Unit,
    moveToNatureContentScreen: (Long) -> Unit,
    isContent: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    val locationFilterDialogAnchoredDraggableState = remember { getLocationAnchoredDraggableState() }
    val isDimBackgroundShowing = remember { mutableStateOf(false) }
    val locationList = listOf("전체", "제주시", "애월", "서귀포시", "성산", "한림", "조천", "구좌", "한경", "대정", "안덕", "남원", "표선", "우도")
    val lazyGridState = rememberLazyGridState()
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = lazyGridState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
            lastVisibleItemIndex > (totalItemsNumber - 1)
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
                CustomTopBarWithShadow(
                    title = "7대 자연",
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

                NatureThumbnailList(
                    listState = lazyGridState,
                    thumbnailList = natureThumbnailList,
                    toggleFavorite = toggleFavorite,
                    moveToNatureContentScreen = moveToNatureContentScreen
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