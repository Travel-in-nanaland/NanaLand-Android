package com.jeju.nanaland.ui.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.member.HotPostData
import com.jeju.nanaland.domain.entity.member.RecommendedPostData
import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.jeju.nanaland.globalvalue.constant.SYSTEM_STATUS_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.component.main.home.HomeScreenCategoryButtons
import com.jeju.nanaland.ui.component.main.home.HomeScreenPopularPlaces
import com.jeju.nanaland.ui.component.main.home.HomeScreenRecommendedPosts
import com.jeju.nanaland.ui.component.main.home.HomeScreenTopBanner
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.shadowDivider
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState

@Composable
fun HomeContent(
    moveToCategoryContentScreen: (Int, String?, Boolean) -> Unit,
    moveToNatureListScreen: (String?) -> Unit,
    moveToFestivalListScreen: (String?) -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToActivityListScreen: () -> Unit,
    moveToArtListScreen: () -> Unit,
    moveToRestaurantListScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    homeScreenTopBar: @Composable () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homePreviewBanner = viewModel.homeBannerPreview.collectAsState().value
    val recommendedPosts = viewModel.recommendedPosts.collectAsState().value
    val hotPosts = viewModel.hotPosts.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.getHomeBannerPreview()
        viewModel.getRecommendedPost()
        viewModel.getHotPost()
    }
    HomeContent(
        homePreviewBanner = homePreviewBanner,
        recommendedPosts = recommendedPosts,
        hotPosts = hotPosts,
        toggleFavorite = viewModel::toggleFavorite,
        moveToCategoryContentScreen = moveToCategoryContentScreen,
        moveToNatureListScreen = moveToNatureListScreen,
        moveToFestivalListScreen = moveToFestivalListScreen,
        moveToMarketListScreen = moveToMarketListScreen,
        moveToActivityListScreen = moveToActivityListScreen,
        moveToArtListScreen = moveToArtListScreen,
        moveToRestaurantListScreen = moveToRestaurantListScreen,
        moveToSignInScreen = moveToSignInScreen,
        homeScreenTopBar = homeScreenTopBar,
        isContent = true
    )
}

@Composable
private fun HomeContent(
    homePreviewBanner: UiState<List<NanaPickBannerData>>,
    recommendedPosts: UiState<List<RecommendedPostData>>,
    hotPosts: UiState<List<HotPostData>>,
    toggleFavorite: (Int, String?) -> Unit,
    moveToCategoryContentScreen: (Int, String?, Boolean) -> Unit,
    moveToNatureListScreen: (String?) -> Unit,
    moveToFestivalListScreen: (String?) -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToActivityListScreen: () -> Unit,
    moveToArtListScreen: () -> Unit,
    moveToRestaurantListScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    homeScreenTopBar: @Composable () -> Unit,
    isContent: Boolean
) {
    Column(
        modifier = Modifier
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            HomeScreenTopBanner(
                topBanner = homePreviewBanner,
                onBannerClick = moveToCategoryContentScreen
            )
            Column {
                Spacer(Modifier.height(SYSTEM_STATUS_BAR_HEIGHT.dp))
                homeScreenTopBar()
            }
        }

        Spacer(Modifier.height(28.dp))

        HomeScreenCategoryButtons(
            moveToNatureListScreen = moveToNatureListScreen,
            moveToFestivalListScreen = moveToFestivalListScreen,
            moveToMarketListScreen = moveToMarketListScreen,
            moveToActivityListScreen = moveToActivityListScreen,
            moveToArtListScreen = moveToArtListScreen,
            moveToRestaurantListScreen = moveToRestaurantListScreen,
        )

        Spacer(
            modifier = Modifier
                .padding(top = 32.dp, bottom = 24.dp)
                .height(8.dp)
                .fillMaxWidth()
                .shadowDivider()
                .background(getColor().gray03)
        )

//        HomeScreenAdBanner(
//            moveToNatureListScreen = moveToNatureListScreen,
//            moveToFestivalListScreen = moveToFestivalListScreen,
//            moveToMarketListScreen = moveToMarketListScreen
//        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = getString(R.string.home_screen_recommend,
                if(UserData.nickname == "GUEST")
                    getString(R.string.home_screen_recommend_default_name)
                else
                    UserData.nickname
            ),
            color = getColor().black,
            style = bodyBold
        )
        Spacer(Modifier.height(12.dp))
        HomeScreenRecommendedPosts(
            recommendedPosts = recommendedPosts,
            onFavoriteButtonClick = toggleFavorite,
            onClick = moveToCategoryContentScreen,
            moveToSignInScreen = moveToSignInScreen,
        )

        Spacer(
            modifier = Modifier
                .padding(top = 32.dp, bottom = 24.dp)
                .height(8.dp)
                .fillMaxWidth()
                .shadowDivider()
                .background(getColor().gray03)
        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = getString(R.string.home_screen_popular),
            color = getColor().black,
            style = bodyBold
        )
        Spacer(Modifier.height(12.dp))
        HomeScreenPopularPlaces(
            hotPosts = hotPosts,
            onFavoriteButtonClick = toggleFavorite,
            onClick = moveToCategoryContentScreen,
            moveToSignInScreen = moveToSignInScreen,
        )
        Spacer(Modifier.height(24.dp))
    }
}

@ScreenPreview
@Composable
private fun HomeContentPreview() {
//    val data = listOf(NanaPickBannerData(
//        id = 0,
//        firstImage = null,
//        version = "Version",
//        subHeading = "SubHeading",
//        heading = "Heading"
//    ))
//    NanaLandTheme {
//        HomeContent(
//            homePreviewBanner = UiState.Success(data),
//            recommendedPosts = UiState.Loading,
//            toggleFavorite = { _, _ -> },
//            moveToCategoryContentScreen = { _, _, _->},
//            moveToNatureListScreen = { /*TODO*/ },
//            moveToFestivalListScreen = { /*TODO*/ },
//            moveToMarketListScreen = { /*TODO*/ },
//            moveToExperienceScreen = { /*TODO*/ },
//            moveToNanaPickListScreen = { /*TODO*/ },
//            moveToSignInScreen = {},
//            isContent = true
//        )
//    }
}