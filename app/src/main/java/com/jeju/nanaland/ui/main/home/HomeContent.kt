package com.jeju.nanaland.ui.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.domain.entity.member.RecommendedPostData
import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.component.main.home.HomeScreenAdBanner
import com.jeju.nanaland.ui.component.main.home.HomeScreenCategoryButtons
import com.jeju.nanaland.ui.component.main.home.HomeScreenRecommendText
import com.jeju.nanaland.ui.component.main.home.HomeScreenRecommendedPosts
import com.jeju.nanaland.ui.component.main.home.HomeScreenTopBanner
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState

@Composable
fun HomeContent(
    moveToCategoryContentScreen: (Long, String?, Boolean) -> Unit,
    moveToNatureListScreen: () -> Unit,
    moveToFestivalListScreen: () -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceScreen: () -> Unit,
    moveToNanaPickListScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homePreviewBanner = viewModel.homeBannerPreview.collectAsState().value
    val recommendedPosts = viewModel.recommendedPosts.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.getHomeBannerPreview()
        viewModel.getRecommendedPost()
    }
    HomeContent(
        homePreviewBanner = homePreviewBanner,
        recommendedPosts = recommendedPosts,
        toggleFavorite = viewModel::toggleFavorite,
        moveToCategoryContentScreen = moveToCategoryContentScreen,
        moveToNatureListScreen = moveToNatureListScreen,
        moveToFestivalListScreen = moveToFestivalListScreen,
        moveToMarketListScreen = moveToMarketListScreen,
        moveToExperienceScreen = moveToExperienceScreen,
        moveToNanaPickListScreen = moveToNanaPickListScreen,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun HomeContent(
    homePreviewBanner: UiState<List<NanaPickBannerData>>,
    recommendedPosts: UiState<List<RecommendedPostData>>,
    toggleFavorite: (Long, String?) -> Unit,
    moveToCategoryContentScreen: (Long, String?, Boolean) -> Unit,
    moveToNatureListScreen: () -> Unit,
    moveToFestivalListScreen: () -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceScreen: () -> Unit,
    moveToNanaPickListScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    Column(
        modifier = Modifier
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {
        HomeScreenTopBanner(
            topBanner = homePreviewBanner,
            onBannerClick = moveToCategoryContentScreen
        )

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            HomeScreenCategoryButtons(
                moveToNatureListScreen = moveToNatureListScreen,
                moveToFestivalListScreen = moveToFestivalListScreen,
                moveToMarketListScreen = moveToMarketListScreen,
                moveToExperienceScreen = moveToExperienceScreen,
                moveToNanaPickListScreen = moveToNanaPickListScreen,
            )
        }

        Spacer(Modifier.height(32.dp))

        HomeScreenAdBanner(
            moveToNatureListScreen = moveToNatureListScreen,
            moveToFestivalListScreen = moveToFestivalListScreen,
            moveToMarketListScreen = moveToMarketListScreen
        )

        Spacer(Modifier.height(32.dp))

        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
        ) {
            HomeScreenRecommendText(text = UserData.nickname)

            Spacer(Modifier.height(10.dp))

            HomeScreenRecommendedPosts(
                recommendedPosts = recommendedPosts,
                onFavoriteButtonClick = toggleFavorite,
                onClick = moveToCategoryContentScreen,
                moveToSignInScreen = moveToSignInScreen,
            )
        }
    }
}

@ScreenPreview
@Composable
private fun HomeContentPreview() {
    val data = listOf(NanaPickBannerData(
        id = 0L,
        thumbnailUrl = null,
        version = "Version",
        subHeading = "SubHeading",
        heading = "Heading"
    ))
    NanaLandTheme {
        HomeContent(
            homePreviewBanner = UiState.Success(data),
            recommendedPosts = UiState.Loading,
            toggleFavorite = { _, _ -> },
            moveToCategoryContentScreen = { _, _, _->},
            moveToNatureListScreen = { /*TODO*/ },
            moveToFestivalListScreen = { /*TODO*/ },
            moveToMarketListScreen = { /*TODO*/ },
            moveToExperienceScreen = { /*TODO*/ },
            moveToNanaPickListScreen = { /*TODO*/ },
            moveToSignInScreen = {},
            isContent = true
        )
    }
}