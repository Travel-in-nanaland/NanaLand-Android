package com.jeju.nanaland.ui.main.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.favorite.FavoriteThumbnailData
import com.jeju.nanaland.globalvalue.type.MainScreenViewType
import com.jeju.nanaland.globalvalue.type.SearchCategoryType
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.component.favorite.FavoriteScreenCategorySelectionTab
import com.jeju.nanaland.ui.component.favorite.FavoriteScreenFavoritePosts
import com.jeju.nanaland.ui.component.main.searchresult.parts.SearchResultScreenItemCount
import com.jeju.nanaland.ui.component.nonmember.NonMemberGuideDialog
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState

@Composable
fun FavoriteScreen(
    prevViewType: MainScreenViewType,
    updateMainScreenViewType: (MainScreenViewType) -> Unit,
    moveToCategoryContentScreen: (Int, String?, Boolean) -> Unit,
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
    favoriteThumbnailCount: UiState<Int>,
    favoriteThumbnailList: UiState<List<FavoriteThumbnailData>>,
    updateSelectedCategory: (SearchCategoryType) -> Unit,
    getFavoriteList: () -> Unit,
    toggleFavorite: (Int, String?) -> Unit,
    onPostClick: (Int, String?, Boolean) -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    Column {
        TopBarCommon(title = getString(R.string.common_찜))

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
                    count = favoriteThumbnailCount.data
                )
            }
            is UiState.Failure -> {}
        }

        Spacer(Modifier.height(16.dp))

        when (favoriteThumbnailList) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                if(favoriteThumbnailList.data.isEmpty())
                    EmptyScreen()
                else
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

@Composable
private fun EmptyScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(80.dp),
            painter = painterResource(id = R.drawable.img_info_heart),
            contentDescription = null
        )

        Spacer(Modifier.height(15.dp))

        Text(
            text = getString(R.string.favorite_screen_empty_list),
            color = getColor().gray01,
            style = body01,
            textAlign = TextAlign.Center
        )
    }
}