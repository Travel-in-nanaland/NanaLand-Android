package com.jeju.nanaland.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.SYSTEM_STATUS_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.constant.TOP_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.constant.TravelType
import com.jeju.nanaland.globalvalue.type.HomeScreenViewType
import com.jeju.nanaland.globalvalue.type.MainScreenViewType
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.globalvalue.type.SearchCategoryType
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.main.favorite.FavoriteScreen
import com.jeju.nanaland.ui.main.favorite.FavoriteViewModel
import com.jeju.nanaland.ui.main.home.HomeScreen
import com.jeju.nanaland.ui.main.home.HomeViewModel
import com.jeju.nanaland.ui.main.home.search.SearchViewModel
import com.jeju.nanaland.ui.nanapick.NanaPickListScreen
import com.jeju.nanaland.ui.profile.root.ProfileScreen
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.caption02SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.shadowBottomNav
import com.jeju.nanaland.util.intent.DeepLinkData
import com.jeju.nanaland.util.resource.getString
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun MainScreen(
    viewTypeByPopStack: MainScreenViewType?,
    retry:()->Unit,
    deepLinkData: DeepLinkData,
    moveToNotificationScreen: () -> Unit,
    moveToCategoryContentScreen: (Int, String?, Boolean) -> Unit,
    moveToRestaurantListScreen: () -> Unit,
    moveToNatureListScreen: (String?) -> Unit,
    moveToFestivalListScreen: (String?) -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToActivityListScreen: () -> Unit,
    moveToArtListScreen: () -> Unit,
    moveToSettingsScreen: () -> Unit,
    moveToProfileModificationScreen: (String?, String?, String?) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToTypeTestScreen: () -> Unit,
    moveToTypeTestResultScreen: (TravelType) -> Unit,
    moveToReviewWriteScreen: () -> Unit,
    moveToProfileNoticeListScreen: (Int?) -> Unit,
    moveToProfileReviewListScreen: (Int?) -> Unit,
    moveToNanaPickAllListScreen: () -> Unit,
    moveToReviewEditScreen: (Int, ReviewCategoryType) -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        if (deepLinkData.contentId != null) {
            val language = deepLinkData.language
            val category = deepLinkData.category
            val contentId = deepLinkData.contentId
            deepLinkData.language = null
            deepLinkData.category = null
            deepLinkData.contentId = null
            when (category) {
                "nature" -> {
                    moveToCategoryContentScreen(contentId ?: 0, "NATURE", false)
                }
                "festival" -> {
                    moveToCategoryContentScreen(contentId ?: 0, "FESTIVAL", false)
                }
                "market" -> {
                    moveToCategoryContentScreen(contentId ?: 0, "MARKET", false)
                }
                "experience" -> {//TODO
                    moveToCategoryContentScreen(contentId ?: 0, "EXPERIENCE", false)
                }
                "cultureAndArts" -> {//TODO
                    moveToCategoryContentScreen(contentId ?: 0, "CULTURE_AND_ARTS", false)
                }
                "activity" -> {//TODO
                    moveToCategoryContentScreen(contentId ?: 0, "ACTIVITY", false)
                }
                "restaurant" -> {
                    moveToCategoryContentScreen(contentId ?: 0, "RESTAURANT", false)
                }
                else -> {
                    moveToCategoryContentScreen(contentId ?: 0, "NANAPICK", false)
                }
            }
        }
    }
    val viewType = viewModel.viewType.collectAsState().value
    val homeScreenViewType = viewModel.homeScreenViewType.collectAsState().value
    val prevViewType = viewModel.prevViewType.collectAsState().value
    val navigationItemContentList = viewModel.getNavigationItemContentList()
    
    LaunchedEffect(viewTypeByPopStack) {
        viewTypeByPopStack?.let {
            viewModel.updateViewType(it)
            retry()
        }
    }
    MainScreen(
        homeScreenViewType = homeScreenViewType,
        onHomeScreenViewType = viewModel::updateHomeScreenViewType,
        viewType = viewType,
        prevViewType = prevViewType,
        navigationItemContentList = navigationItemContentList,
        updateMainScreenViewType = viewModel::updateViewType,
        initHomeScreen = {
            homeViewModel.updateHomeScreenViewType(HomeScreenViewType.Home)
            homeViewModel.updateInputText("")
            searchViewModel.updateSelectedCategoryType(SearchCategoryType.All)
        },
        initFavoriteScreen = {
            favoriteViewModel.updateSelectedCategoryType(SearchCategoryType.All)
        },
        moveToNotificationScreen = moveToNotificationScreen,
        moveToCategoryContentScreen = moveToCategoryContentScreen,
        moveToRestaurantListScreen = moveToRestaurantListScreen,
        moveToNatureListScreen = moveToNatureListScreen,
        moveToFestivalListScreen = moveToFestivalListScreen,
        moveToMarketListScreen = moveToMarketListScreen,
        moveToActivityListScreen = moveToActivityListScreen,
        moveToArtListScreen = moveToArtListScreen,
        moveToSettingsScreen = moveToSettingsScreen,
        moveToProfileModificationScreen = moveToProfileModificationScreen,
        moveToSignInScreen = moveToSignInScreen,
        moveToTypeTestScreen = moveToTypeTestScreen,
        moveToTypeTestResultScreen = moveToTypeTestResultScreen,
        moveToReviewWriteScreen = moveToReviewWriteScreen,
        moveToProfileNoticeListScreen = moveToProfileNoticeListScreen,
        moveToProfileReviewListScreen = moveToProfileReviewListScreen,
        moveToNanaPickAllListScreen = moveToNanaPickAllListScreen,
        moveToReviewEditScreen = moveToReviewEditScreen,
        isContent = true
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MainScreen(
    homeScreenViewType: HomeScreenViewType,
    onHomeScreenViewType: (HomeScreenViewType) -> Unit,
    viewType: MainScreenViewType,
    prevViewType: MainScreenViewType,
    navigationItemContentList: List<MainViewModel.NavigationItemContent>,
    updateMainScreenViewType: (MainScreenViewType) -> Unit,
    initHomeScreen: () -> Unit,
    initFavoriteScreen: () -> Unit,
    moveToNotificationScreen: () -> Unit,
    moveToCategoryContentScreen: (Int, String?, Boolean) -> Unit,
    moveToRestaurantListScreen: () -> Unit,
    moveToNatureListScreen: (String?) -> Unit,
    moveToFestivalListScreen: (String?) -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToActivityListScreen: () -> Unit,
    moveToArtListScreen: () -> Unit,
    moveToSettingsScreen: () -> Unit,
    moveToProfileModificationScreen: (String?, String?, String?) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToTypeTestScreen: () -> Unit,
    moveToTypeTestResultScreen: (TravelType) -> Unit,
    moveToReviewWriteScreen: () -> Unit,
    moveToProfileNoticeListScreen: (Int?) -> Unit,
    moveToProfileReviewListScreen: (Int?) -> Unit,
    moveToNanaPickAllListScreen: () -> Unit,
    moveToReviewEditScreen: (Int, ReviewCategoryType) -> Unit,
    isContent: Boolean
) {
    CustomSurface(false) { isImeKeyboardShowing ->
        Scaffold(
            topBar = {},
            bottomBar = {
                if (homeScreenViewType == HomeScreenViewType.Home) {
                    MainNavigationBar(
                        viewType,
                        navigationItemContentList,
                        updateMainScreenViewType = updateMainScreenViewType,
                        initHomeScreen = initHomeScreen,
                        initFavoriteScreen = initFavoriteScreen
                    )
                }
            },
            floatingActionButton = {},
            containerColor = getColor().surface,
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) {
            Column(
                modifier = Modifier
                    .imePadding()
                    .padding(bottom = if (isImeKeyboardShowing) 0.dp else it.calculateBottomPadding())
            ) {
                when (viewType) {
                    MainScreenViewType.Home -> {
                        HomeScreen(
                            moveToNotificationScreen = moveToNotificationScreen,
                            moveToCategoryContentScreen = moveToCategoryContentScreen,
                            moveToRestaurantListScreen = moveToRestaurantListScreen,
                            moveToNatureListScreen = moveToNatureListScreen,
                            moveToFestivalListScreen = moveToFestivalListScreen,
                            moveToMarketListScreen = moveToMarketListScreen,
                            moveToActivityListScreen = moveToActivityListScreen,
                            moveToArtListScreen = moveToArtListScreen,
                            moveToSignInScreen = moveToSignInScreen,
                            updateHomeScreenViewType = onHomeScreenViewType
                        )
                    }
                    MainScreenViewType.Favorite -> {
                        Spacer(Modifier.height(SYSTEM_STATUS_BAR_HEIGHT.dp))
                        FavoriteScreen(
                            prevViewType = prevViewType,
                            updateMainScreenViewType = updateMainScreenViewType,
                            moveToCategoryContentScreen = moveToCategoryContentScreen,
                            moveToSignInScreen = moveToSignInScreen,
                        )
                    }
                    MainScreenViewType.NanaPick -> {
                        Spacer(Modifier.height(SYSTEM_STATUS_BAR_HEIGHT.dp))
                        NanaPickListScreen(
                            moveToNanaPickContentScreen = { contentId ->
                                moveToCategoryContentScreen(contentId, "NANA", false) },
                            moveToMainScreen = {},
                            moveToNanaPickAllListScreen = moveToNanaPickAllListScreen
                        )
                    }
                    MainScreenViewType.MyPage -> {
                        Spacer(Modifier.height(SYSTEM_STATUS_BAR_HEIGHT.dp))
                        ProfileScreen(
                            onBackButtonClicked = null,
                            moveToSettingsScreen = moveToSettingsScreen,
                            moveToProfileModificationScreen = moveToProfileModificationScreen,
                            moveToSignInScreen = moveToSignInScreen,
                            moveToTypeTestScreen = moveToTypeTestScreen,
                            moveToTypeTestResultScreen = moveToTypeTestResultScreen,
                            moveToReviewWriteScreen = moveToReviewWriteScreen,
                            moveToProfileNoticeListScreen = moveToProfileNoticeListScreen,
                            moveToProfileReviewListScreen = moveToProfileReviewListScreen,
                            moveToReviewEditScreen = moveToReviewEditScreen,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainNavigationBar(
    viewType: MainScreenViewType,
    navigationItemContentList: List<MainViewModel.NavigationItemContent>,
    updateMainScreenViewType: (MainScreenViewType) -> Unit,
    initHomeScreen: () -> Unit,
    initFavoriteScreen: () -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .height(TOP_BAR_HEIGHT.dp)
            .shadowBottomNav(),
        containerColor = Color(0xFFFFFFFF),
        windowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        navigationItemContentList.forEachIndexed { _, navItem ->
            NavigationBarItem(
                modifier = Modifier.fillMaxHeight(),
                selected = viewType == navItem.viewType,
                onClick = {
                    updateMainScreenViewType(navItem.viewType)
                    if (navItem.viewType != MainScreenViewType.Home) {
                        initHomeScreen()
                    }
                    if (navItem.viewType != MainScreenViewType.Favorite) {
                        initFavoriteScreen()
                    }
                },
                icon = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(id = when (viewType == navItem.viewType) {
                                true -> navItem.iconSelected
                                false -> navItem.iconUnselected
                            }),
                            contentDescription = null,
                        )
                        Text(
                            text = navItem.label,
                            style = caption02SemiBold
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = getColor().black,
                    selectedTextColor = getColor().black,
                    indicatorColor = Color(0x00FFFFFF),
                    unselectedIconColor = getColor().black25,
                    unselectedTextColor = getColor().black25
                ),
                interactionSource = NoRippleInteractionSource(),
                alwaysShowLabel = true
            )
        }
    }
}

class NoRippleInteractionSource : MutableInteractionSource {
    override val interactions = emptyFlow<Interaction>()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = false
}

@Preview
@Composable
private fun MainScreenPreview() {
    val navigationItemContentList = listOf(
        MainViewModel.NavigationItemContent(
            viewType = MainScreenViewType.Home,
            iconSelected = R.drawable.ic_home_filled,
            iconUnselected = R.drawable.ic_home_outlined,
            label = getString(R.string.common_홈)
        ),
        MainViewModel.NavigationItemContent(
            viewType = MainScreenViewType.Favorite,
            iconSelected = R.drawable.ic_heart_filled,
            iconUnselected = R.drawable.ic_heart_outlined,
            label = getString(R.string.common_찜)
        ),
        MainViewModel.NavigationItemContent(
            viewType = MainScreenViewType.NanaPick,
            iconSelected = R.drawable.ic_logo_gray,
            iconUnselected = R.drawable.ic_logo_gray,
            label = getString(R.string.common_나나s_Pick)
        ),
        MainViewModel.NavigationItemContent(
            viewType = MainScreenViewType.MyPage,
            iconSelected = R.drawable.ic_person_filled,
            iconUnselected = R.drawable.ic_person_outlined,
            label = getString(R.string.common_나의_나나)
        )
    )
    NanaLandTheme {
//        MainNavigationBar(
//            viewType = MainScreenViewType.MyNana,
//            navigationItemContentList = navigationItemContentList,
//            updateMainScreenViewType = {},
//            initHomeScreen = {}
//        )
    }
}