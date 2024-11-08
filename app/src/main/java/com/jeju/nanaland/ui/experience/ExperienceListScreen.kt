package com.jeju.nanaland.ui.experience

import android.util.Log
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
import com.jeju.nanaland.domain.entity.experience.ExperienceThumbnailData
import com.jeju.nanaland.globalvalue.constant.PAGING_THRESHOLD
import com.jeju.nanaland.globalvalue.constant.getActivityKeywordList
import com.jeju.nanaland.globalvalue.constant.getCultureArtKeywordList
import com.jeju.nanaland.globalvalue.constant.getLocationList
import com.jeju.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.jeju.nanaland.globalvalue.type.ExperienceCategoryType
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.icon.GoToUpInList
import com.jeju.nanaland.ui.component.common.layoutSet.ListEmptyByFilter
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.component.listscreen.category.ExperienceCategoryListTab
import com.jeju.nanaland.ui.component.listscreen.filter.ActivityKeywordFilterDialog
import com.jeju.nanaland.ui.component.listscreen.filter.CultureArtKeywordFilterDialog
import com.jeju.nanaland.ui.component.listscreen.filter.ExperienceFilterDialogDimBackground
import com.jeju.nanaland.ui.component.listscreen.filter.KeywordLocationFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.filter.LocationFilterBottomDialog
import com.jeju.nanaland.ui.component.listscreen.filter.getActivityKeywordAnchoredDraggableState
import com.jeju.nanaland.ui.component.listscreen.filter.getCultureKeywordArtAnchoredDraggableState
import com.jeju.nanaland.ui.component.listscreen.filter.getLocationAnchoredDraggableState
import com.jeju.nanaland.ui.component.listscreen.list.ExperienceThumbnailList
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun ExperienceListScreen(
    moveToBackScreen: () -> Unit,
    moveToExperienceContentScreen: (Int, String) -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: ExperienceListViewModel = hiltViewModel()
) {
    val selectedCategoryType = viewModel.selectedCategoryType.collectAsState().value
    val selectedLocationList = viewModel.selectedLocationList
    val selectedActivityKeywordList = viewModel.selectedActivityKeywordList
    val selectedCultureArtKeywordList = viewModel.selectedCultureArtKeywordList
    val experienceThumbnailList = viewModel.experienceThumbnailList.collectAsState().value
    val experienceThumbnailCount = viewModel.experienceThumbnailCount.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.getExperienceList()
    }
    ExperienceListScreen(
        selectedCategoryType = selectedCategoryType,
        updateSelectedCategoryType = viewModel::updateSelectedCategoryType,
        selectedLocationList = selectedLocationList,
        selectedActivityKeywordList = selectedActivityKeywordList,
        selectedCultureArtKeywordList = selectedCultureArtKeywordList,
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
    selectedActivityKeywordList: SnapshotStateList<Boolean>,
    selectedCultureArtKeywordList: SnapshotStateList<Boolean>,
    experienceThumbnailDataList: UiState<List<ExperienceThumbnailData>>,
    experienceThumbnailCount: UiState<Int>,
    getExperienceList: () -> Unit,
    clearExperienceList: () -> Unit,
    toggleFavorite: (Int) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToExperienceContentScreen: (Int, String) -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyGridState = rememberLazyGridState()
    val locationList = remember { getLocationList() }
    val activityKeywordList = remember { getActivityKeywordList() }
    val cultureArtKeywordList = remember { getCultureArtKeywordList() }
    val isDimBackgroundShowing = remember { mutableStateOf(false) }
    val locationFilterDialogAnchoredDraggableState = remember { getLocationAnchoredDraggableState() }
    val activityKeywordFilterDialogAnchoredDraggableState = remember { getActivityKeywordAnchoredDraggableState() }
    val cultureArtKeywordFilterDialogAnchoredDraggableState = remember { getCultureKeywordArtAnchoredDraggableState() }

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
            getExperienceList()
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
                    title = getString(R.string.common_액티비티), // TODO!!변경
                    onBackButtonClicked = moveToBackScreen
                )

                Spacer(Modifier.height(16.dp))

                ExperienceCategoryListTab(
                    selectedCategoryType = selectedCategoryType,
                    updateSelectedCategoryType = updateSelectedCategoryType
                )

                KeywordLocationFilterTopBar(
                    count = experienceThumbnailCount,
                    selectedKeywordList = if (selectedCategoryType == ExperienceCategoryType.Activity) selectedActivityKeywordList else selectedCultureArtKeywordList,
                    keywordList = if (selectedCategoryType == ExperienceCategoryType.Activity) getActivityKeywordList() else getCultureArtKeywordList(),
                    selectedLocationList = selectedLocationList,
                    locationList = getLocationList(),
                    openKeywordFilterDialog = {
                        if (selectedCategoryType == ExperienceCategoryType.Activity) { coroutineScope.launch { activityKeywordFilterDialogAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Open) } }
                        else { coroutineScope.launch { cultureArtKeywordFilterDialogAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Open) } }
                    },
                    openLocationFilterDialog = { coroutineScope.launch { locationFilterDialogAnchoredDraggableState.animateTo(
                        AnchoredDraggableContentState.Open) } },
                    showDimBackground = { isDimBackgroundShowing.value = true }
                )
                Log.d("asd","""
!(${selectedLocationList.all { it }} || ${selectedLocationList.all { !it }}) || // if location filter
                        (${selectedCategoryType == ExperienceCategoryType.Activity} && !(${selectedActivityKeywordList.all { it }} || ${selectedActivityKeywordList.all { !it }})) ||  // if activity and  filter
                        (${selectedCategoryType != ExperienceCategoryType.Activity} && !(${selectedCultureArtKeywordList.all { it }} || ${selectedCultureArtKeywordList.all { !it }}))// if art and  filter
                        ) &&  ${(experienceThumbnailDataList as? UiState.Success)?.data}
                    (${experienceThumbnailDataList is UiState.Success && experienceThumbnailDataList.data.isEmpty()}) // and list is empty                
                """.trimIndent())
                if (
                    (// if filter on
                        !(selectedLocationList.all { it } || selectedLocationList.all { !it }) || // if location filter
                        (selectedCategoryType == ExperienceCategoryType.Activity && !(selectedActivityKeywordList.all { it } || selectedActivityKeywordList.all { !it })) ||  // if activity and  filter
                        (selectedCategoryType != ExperienceCategoryType.Activity && !(selectedCultureArtKeywordList.all { it } || selectedCultureArtKeywordList.all { !it }))// if art and  filter
                    ) &&
                    (experienceThumbnailDataList is UiState.Success && experienceThumbnailDataList.data.isEmpty()) // and list is empty
                )
                    ListEmptyByFilter {
                        selectedLocationList.forEachIndexed { i, _ ->
                            selectedLocationList[i] = false
                        }
                        clearExperienceList()
                        getExperienceList()
                    }
                else
                    ExperienceThumbnailList(
                        experienceCategory = selectedCategoryType.toString(),
                        listState = lazyGridState,
                        thumbnailList = experienceThumbnailDataList,
                        toggleFavorite = toggleFavorite,
                        moveToExperienceContentScreen = moveToExperienceContentScreen,
                        moveToSignInScreen = moveToSignInScreen
                    )
            }

            GoToUpInList(lazyGridState)

            if (isDimBackgroundShowing.value) {
                ExperienceFilterDialogDimBackground(
                    isDimBackgroundShowing = isDimBackgroundShowing,
                    locationAnchoredDraggableState = locationFilterDialogAnchoredDraggableState,
                    activityKeywordAnchoredDraggableState = activityKeywordFilterDialogAnchoredDraggableState,
                    cultureArtKeywordAnchoredDraggableState = cultureArtKeywordFilterDialogAnchoredDraggableState
                )
            }

            LocationFilterBottomDialog(
                locationList = locationList,
                hideDimBackground = { isDimBackgroundShowing.value = false },
                anchoredDraggableState = locationFilterDialogAnchoredDraggableState,
                selectedLocationList = selectedLocationList,
                updateList = {
                    getExperienceList()
                },
                clearList = {
                    clearExperienceList()
                    coroutineScope.launch { lazyGridState.scrollToItem(0) }
                }
            )

            ActivityKeywordFilterDialog(
                keywordList = activityKeywordList,
                hideDimBackground = { isDimBackgroundShowing.value = false },
                anchoredDraggableState = activityKeywordFilterDialogAnchoredDraggableState,
                selectedKeywordList = selectedActivityKeywordList,
                updateList = {
                    getExperienceList()
                },
                clearList = {
                    clearExperienceList()
                    coroutineScope.launch { lazyGridState.scrollToItem(0) }
                }
            )

            CultureArtKeywordFilterDialog(
                keywordList = cultureArtKeywordList,
                hideDimBackground = { isDimBackgroundShowing.value = false },
                anchoredDraggableState = cultureArtKeywordFilterDialogAnchoredDraggableState,
                selectedKeywordList = selectedCultureArtKeywordList,
                updateList = {
                    getExperienceList()
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