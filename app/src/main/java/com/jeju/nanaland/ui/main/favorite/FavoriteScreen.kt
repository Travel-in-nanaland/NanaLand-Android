package com.jeju.nanaland.ui.main.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.favorite.FavoriteThumbnailData
import com.jeju.nanaland.globalvalue.type.MainScreenViewType
import com.jeju.nanaland.globalvalue.type.SearchCategoryType
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.component.common.CustomTopBarNoBackButton
import com.jeju.nanaland.ui.component.favorite.FavoriteScreenCategorySelectionTab
import com.jeju.nanaland.ui.component.favorite.FavoriteScreenFavoritePosts
import com.jeju.nanaland.ui.component.main.searchresult.parts.SearchResultScreenItemCount
import com.jeju.nanaland.ui.component.nonmember.NonMemberGuideDialog
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState

@Composable
fun FavoriteScreen(
    prevViewType: MainScreenViewType,
    updateMainScreenViewType: (MainScreenViewType) -> Unit,
    moveToCategoryContentScreen: (Long, String?, Boolean) -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val selectedCategory = viewModel.selectedCategory.collectAsState().value
    val favoriteThumbnailCount = viewModel.favoriteThumbnailCount.collectAsState().value
    val favoriteThumbnailList = viewModel.favoriteThumbnailList.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.getFavoriteList()
    }
    FavoriteScreen(
        prevViewType = prevViewType,
        updateMainScreenViewType = updateMainScreenViewType,
        selectedCategory = selectedCategory,
        favoriteThumbnailCount = favoriteThumbnailCount,
        favoriteThumbnailList = favoriteThumbnailList,
        updateSelectedCategory = viewModel::updateSelectedCategoryType,
        getFavoriteList = viewModel::getFavoriteList,
        toggleFavorite = viewModel::toggleFavorite,
        onPostClick = moveToCategoryContentScreen,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun FavoriteScreen(
    prevViewType: MainScreenViewType,
    updateMainScreenViewType: (MainScreenViewType) -> Unit,
    selectedCategory: SearchCategoryType,
    favoriteThumbnailCount: UiState<Long>,
    favoriteThumbnailList: UiState<List<FavoriteThumbnailData>>,
    updateSelectedCategory: (SearchCategoryType) -> Unit,
    getFavoriteList: () -> Unit,
    toggleFavorite: (Long, String?) -> Unit,
    onPostClick: (Long, String?, Boolean) -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    Column {
        CustomTopBarNoBackButton(title = getString(R.string.common_찜))

        Spacer(Modifier.height(16.dp))

        FavoriteScreenCategorySelectionTab(
            getFavoriteList = getFavoriteList,
            selectedCategory = selectedCategory,
            updateSelectedCategory = updateSelectedCategory
        )

        Spacer(Modifier.height(24.dp))

        when (favoriteThumbnailCount) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                SearchResultScreenItemCount(
                    modifier = Modifier.padding(start = 16.dp),
                    count = when (selectedCategory) {
                        SearchCategoryType.JejuStory,
                        SearchCategoryType.NanaPick,
                        SearchCategoryType.Experience -> 0
                        else -> favoriteThumbnailCount.data
                    }
                )
            }
            is UiState.Failure -> {}
        }

        Spacer(Modifier.height(16.dp))

        when (favoriteThumbnailList) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                FavoriteScreenFavoritePosts(
                    favoriteThumbnailList = favoriteThumbnailList.data,
                    getFavoriteList = getFavoriteList,
                    onFavoriteButtonClick = toggleFavorite,
                    onPostClick = onPostClick,
                    moveToSignInScreen = moveToSignInScreen,
                )
            }
            is UiState.Failure -> {}
        }
    }

    if (UserData.provider == "GUEST") {
        NonMemberGuideDialog(
            onCloseClick = { updateMainScreenViewType(prevViewType) },
            moveToSignInScreen = moveToSignInScreen
        )
    }
}

@Preview
@Composable
private fun FavoriteScreenPreview() {

}