package com.jeju.nanaland.ui.experience

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.domain.entity.experience.ExperienceThumbnailData
import com.jeju.nanaland.globalvalue.constant.getKeywordList
import com.jeju.nanaland.globalvalue.constant.getLocationList
import com.jeju.nanaland.globalvalue.type.ExperienceCategoryType
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.component.listscreen.category.ExperienceCategoryListTab
import com.jeju.nanaland.ui.component.listscreen.filter.KeywordLocationFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.LocationFilterBottomDialog
import com.jeju.nanaland.ui.component.listscreen.filter.getLocationAnchoredDraggableState
import com.jeju.nanaland.ui.component.listscreen.list.ExperienceThumbnailList
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun ExperienceListScreen(
    moveToBackScreen: () -> Unit,
    moveToExperienceContentScreen: (Int) -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: ExperienceListViewModel = hiltViewModel()
) {
    val selectedCategoryType = viewModel.selectedCategoryType.collectAsState().value
    val selectedLocationList = viewModel.selectedLocationList
    val selectedKeywordList = viewModel.selectedKeywordList
    val experienceThumbnailList = viewModel.experienceThumbnailList.collectAsState().value
    val experienceThumbnailCount = viewModel.experienceThumbnailCount.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.getExperienceList("ACTIVITY")
    }
    ExperienceListScreen(
        selectedCategoryType = selectedCategoryType,
        updateSelectedCategoryType = viewModel::updateSelectedCategoryType,
        selectedLocationList = selectedLocationList,
        selectedKeywordList = selectedKeywordList,
        experienceThumbnailDataList = experienceThumbnailList,
        experienceThumbnailCount = experienceThumbnailCount,
        getExperienceList = viewModel::getExperienceList,
        clearExperienceList = viewModel::clearExperienceList,
        toggleFavorite = viewModel::toggleFavorite,
        moveToBackScreen = moveToBackScreen,
        moveToExperienceContentScreen = moveToExperienceContentScreen,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ExperienceListScreen(
    selectedCategoryType: ExperienceCategoryType,
    updateSelectedCategoryType: (ExperienceCategoryType) -> Unit,
    selectedLocationList: SnapshotStateList<Boolean>,
    selectedKeywordList: SnapshotStateList<Boolean>,
    experienceThumbnailDataList: UiState<List<ExperienceThumbnailData>>,
    experienceThumbnailCount: UiState<Int>,
    getExperienceList: (String) -> Unit,
    clearExperienceList: () -> Unit,
    toggleFavorite: (Int) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToExperienceContentScreen: (Int) -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyGridState = rememberLazyGridState()
    val locationList = remember { getLocationList() }
    val isDimBackgroundShowing = remember { mutableStateOf(false) }
    val locationFilterDialogAnchoredDraggableState = remember { getLocationAnchoredDraggableState() }

    CustomSurface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CustomTopBar(
                    title = "이색 체험",
                    onBackButtonClicked = moveToBackScreen
                )

                Spacer(Modifier.height(16.dp))

                ExperienceCategoryListTab(
                    selectedCategoryType = selectedCategoryType,
                    updateSelectedCategoryType = updateSelectedCategoryType
                )

                KeywordLocationFilterTopBar(
                    count = experienceThumbnailCount,
                    selectedKeywordList = selectedKeywordList,
                    keywordList = getKeywordList(),
                    selectedLocationList = selectedLocationList,
                    locationList = getLocationList(),
                    openKeywordFilterDialog = {},
                    openLocationFilterDialog = {},
                    showDimBackground = {}
                )

                ExperienceThumbnailList(
                    listState = lazyGridState,
                    thumbnailList = experienceThumbnailDataList,
                    toggleFavorite = toggleFavorite,
                    moveToExperienceContentScreen = moveToExperienceContentScreen,
                    moveToSignInScreen = moveToSignInScreen
                )
            }

            LocationFilterBottomDialog(
                locationList = locationList,
                hideDimBackground = { isDimBackgroundShowing.value = false },
                anchoredDraggableState = locationFilterDialogAnchoredDraggableState,
                selectedLocationList = selectedLocationList,
                updateList = {
                    getExperienceList(when(selectedCategoryType) {
                        ExperienceCategoryType.Activity -> {
                            "ACTIVITY"
                        }
                        ExperienceCategoryType.CultureArt -> {
                            "CULTURE_AND_ARTS"
                        }
                    })

                },
                clearList = {
                    clearExperienceList()
                    coroutineScope.launch { lazyGridState.scrollToItem(0) }
                }
            )
        }
    }
}

@ScreenPreview
@Composable
private fun ExperienceListScreenPreview() {

}