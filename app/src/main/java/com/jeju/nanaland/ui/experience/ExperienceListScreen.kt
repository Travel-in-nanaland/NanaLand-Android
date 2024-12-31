package com.jeju.nanaland.ui.experience

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
import com.jeju.nanaland.domain.entity.experience.ExperienceThumbnailData
import com.jeju.nanaland.globalvalue.constant.PAGING_THRESHOLD
import com.jeju.nanaland.globalvalue.constant.getActivityKeywordList
import com.jeju.nanaland.globalvalue.constant.getCultureArtKeywordList
import com.jeju.nanaland.globalvalue.constant.getLocationList
import com.jeju.nanaland.globalvalue.type.ExperienceCategoryType
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.bottombar.MainNavigationBar
import com.jeju.nanaland.ui.component.common.dialog.BottomSheetFilterDialog
import com.jeju.nanaland.ui.component.common.dialog.BottomSheetFilterDialogType
import com.jeju.nanaland.ui.component.common.icon.GoToUpInList
import com.jeju.nanaland.ui.component.common.layoutSet.ListEmptyByFilter
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.component.listscreen.filter.KeywordLocationFilterTopBar
import com.jeju.nanaland.ui.component.listscreen.list.ExperienceThumbnailList
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun ExperienceListScreen(
    isActivity: Boolean,
    moveToBackScreen: () -> Unit,
    moveToExperienceContentScreen: (Int, String) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToSearchScreen: () -> Unit,
    toHome: () -> Unit,
    toFavorite: () -> Unit,
    toNana: () -> Unit,
    toProfile: () -> Unit,
    viewModel: ExperienceListViewModel = hiltViewModel()
) {
    val selectedCategoryType = viewModel.selectedCategoryType.collectAsState().value
    val selectedLocationList = viewModel.selectedLocationList
    val selectedActivityKeywordList = viewModel.selectedActivityKeywordList
    val selectedCultureArtKeywordList = viewModel.selectedCultureArtKeywordList
    val experienceThumbnailList = viewModel.experienceThumbnailList.collectAsState().value
    val experienceThumbnailCount = viewModel.experienceThumbnailCount.collectAsState().value
    LaunchedEffect(isActivity) {
        viewModel.updateSelectedCategoryType(if(isActivity) ExperienceCategoryType.Activity else ExperienceCategoryType.CultureArt)
    }
    ExperienceListScreen(
        title = getString(if(isActivity) R.string.common_액티비티 else R.string.common_문화예술),
        selectedCategoryType = selectedCategoryType,
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
private fun ExperienceListScreen(
    title: String,
    selectedCategoryType: ExperienceCategoryType,
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
    moveToSearchScreen: () -> Unit,
    toHome: () -> Unit,
    toFavorite: () -> Unit,
    toNana: () -> Unit,
    toProfile: () -> Unit,
    isContent: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyGridState = rememberLazyGridState()
    val locationList = remember { getLocationList() }
    val activityKeywordList = remember { getActivityKeywordList() }
    val cultureArtKeywordList = remember { getCultureArtKeywordList() }
    var isLocationFilterShowing by remember { mutableStateOf(false) }
    var isActivityFilterShowing by remember { mutableStateOf(false) }
    var isArtFilterShowing by remember { mutableStateOf(false) }

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
                    title = title,
                    onBackButtonClicked = moveToBackScreen,
                    menus = arrayOf(R.drawable.ic_search_normal to moveToSearchScreen)
                )

                Spacer(Modifier.height(16.dp))


                KeywordLocationFilterTopBar(
                    selectedKeywordList = if (selectedCategoryType == ExperienceCategoryType.Activity) selectedActivityKeywordList else selectedCultureArtKeywordList,
                    keywordList = if (selectedCategoryType == ExperienceCategoryType.Activity) getActivityKeywordList() else getCultureArtKeywordList(),
                    selectedLocationList = selectedLocationList,
                    locationList = getLocationList(),
                    openKeywordFilterDialog = {
                        if (selectedCategoryType == ExperienceCategoryType.Activity) isActivityFilterShowing = true
                        else isArtFilterShowing = true
                    },
                    openLocationFilterDialog = { isLocationFilterShowing = true }
                )
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

            if(isLocationFilterShowing || isActivityFilterShowing || isArtFilterShowing) {
                BottomSheetFilterDialog(
                    type = if(isLocationFilterShowing) BottomSheetFilterDialogType.Location
                    else if(isActivityFilterShowing) BottomSheetFilterDialogType.Activity
                    else BottomSheetFilterDialogType.Art,
                    onDismiss = {
                        isLocationFilterShowing = false
                        isActivityFilterShowing = false
                        isArtFilterShowing = false
                    },
                    stringList = if(isLocationFilterShowing) locationList
                    else if(isActivityFilterShowing) activityKeywordList
                    else cultureArtKeywordList,
                    selectedList = if(isLocationFilterShowing) selectedLocationList
                    else if(isActivityFilterShowing) selectedActivityKeywordList
                    else selectedCultureArtKeywordList,
                    updateList = getExperienceList,
                    clearList = {
                        clearExperienceList()
                        coroutineScope.launch { lazyGridState.scrollToItem(0) }
                    }
                )
            }
        }
    }
}

@ScreenPreview
@Composable
private fun ExperienceListScreenPreview() {

}