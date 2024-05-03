package com.nanaland.ui.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.nanaland.domain.entity.member.RecommendedPostData
import com.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.nanaland.ui.component.main.home.HomeScreenAdBanner
import com.nanaland.ui.component.main.home.HomeScreenCategoryButtons
import com.nanaland.ui.component.main.home.HomeScreenRecommendText
import com.nanaland.ui.component.main.home.HomeScreenRecommendedPosts
import com.nanaland.ui.component.main.home.HomeScreenTopBanner
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.util.ui.ScreenPreview
import com.nanaland.util.ui.UiState

@Composable
fun HomeContent(
    scaffoldPadding: PaddingValues,
    moveToCategoryContentScreen: (Long, String?) -> Unit,
    moveToNatureListScreen: () -> Unit,
    moveToFestivalListScreen: () -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceScreen: () -> Unit,
    moveToNanaPickListScreen: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homePreviewBanner = viewModel.homeBannerPreview.collectAsState().value
    val recommendedPosts = viewModel.recommendedPosts.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.getHomeBannerPreview()
        viewModel.getRecommendedPost()
    }
    HomeContent(
        scaffoldPadding = scaffoldPadding,
        homePreviewBanner = homePreviewBanner,
        recommendedPosts = recommendedPosts,
        moveToCategoryContentScreen = moveToCategoryContentScreen,
        moveToNatureListScreen = moveToNatureListScreen,
        moveToFestivalListScreen = moveToFestivalListScreen,
        moveToMarketListScreen = moveToMarketListScreen,
        moveToExperienceScreen = moveToExperienceScreen,
        moveToNanaPickListScreen = moveToNanaPickListScreen,
        isContent = true
    )
}

@Composable
private fun HomeContent(
    scaffoldPadding: PaddingValues,
    homePreviewBanner: UiState<List<NanaPickBannerData>>,
    recommendedPosts: UiState<List<RecommendedPostData>>,
    moveToCategoryContentScreen: (Long, String?) -> Unit,
    moveToNatureListScreen: () -> Unit,
    moveToFestivalListScreen: () -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceScreen: () -> Unit,
    moveToNanaPickListScreen: () -> Unit,
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
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
        ) {
            HomeScreenCategoryButtons(
                moveToNatureListScreen = moveToNatureListScreen,
                moveToFestivalListScreen = moveToFestivalListScreen,
                moveToMarketListScreen = moveToMarketListScreen,
                moveToExperienceScreen = moveToExperienceScreen,
                moveToNanaPickListScreen = moveToNanaPickListScreen,
            )

            Spacer(Modifier.height(20.dp))

            HomeScreenAdBanner()

            Spacer(Modifier.height(24.dp))

            HomeScreenRecommendText(text = "감자마케터")

            Spacer(Modifier.height(10.dp))

            HomeScreenRecommendedPosts(
                recommendedPosts = recommendedPosts,
                onClick = moveToCategoryContentScreen
            )
        }
        Spacer(Modifier.height(scaffoldPadding.calculateBottomPadding()))
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
            scaffoldPadding = PaddingValues(0.dp),
            homePreviewBanner = UiState.Success(data),
            recommendedPosts = UiState.Loading,
            moveToCategoryContentScreen = { _, _ ->},
            moveToNatureListScreen = { /*TODO*/ },
            moveToFestivalListScreen = { /*TODO*/ },
            moveToMarketListScreen = { /*TODO*/ },
            moveToExperienceScreen = { /*TODO*/ },
            moveToNanaPickListScreen = { /*TODO*/ },
            isContent = true
        )
    }
}