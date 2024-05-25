package com.jeju.nanaland.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.TOP_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.type.HomeScreenViewType
import com.jeju.nanaland.globalvalue.type.MainScreenViewType
import com.jeju.nanaland.globalvalue.type.SearchCategoryType
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.main.favorite.FavoriteScreen
import com.jeju.nanaland.ui.main.favorite.FavoriteViewModel
import com.jeju.nanaland.ui.main.home.HomeScreen
import com.jeju.nanaland.ui.main.home.HomeViewModel
import com.jeju.nanaland.ui.main.home.search.SearchViewModel
import com.jeju.nanaland.ui.main.jejustory.JejuStoryScreen
import com.jeju.nanaland.ui.main.mypage.MyPageScreen
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.caption02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.drawColoredShadow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun MainScreen(
    moveToCategoryContentScreen: (Long, String?, Boolean) -> Unit,
    moveToNanaPickListScreen: () -> Unit,
    moveToNatureListScreen: () -> Unit,
    moveToFestivalListScreen: () -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceListScreen: () -> Unit,
    moveToProfileModificationScreen: () -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val viewType = viewModel.viewType.collectAsState().value
    val navigationItemContentList = viewModel.getNavigationItemContentList()
    MainScreen(
        viewType = viewType,
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
        moveToCategoryContentScreen = moveToCategoryContentScreen,
        moveToNanaPickListScreen = moveToNanaPickListScreen,
        moveToNatureListScreen = moveToNatureListScreen,
        moveToFestivalListScreen = moveToFestivalListScreen,
        moveToMarketListScreen = moveToMarketListScreen,
        moveToExperienceListScreen = moveToExperienceListScreen,
        moveToProfileModificationScreen = moveToProfileModificationScreen,
        isContent = true
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MainScreen(
    viewType: MainScreenViewType,
    navigationItemContentList: List<MainViewModel.NavigationItemContent>,
    updateMainScreenViewType: (MainScreenViewType) -> Unit,
    initHomeScreen: () -> Unit,
    initFavoriteScreen: () -> Unit,
    moveToCategoryContentScreen: (Long, String?, Boolean) -> Unit,
    moveToNanaPickListScreen: () -> Unit,
    moveToNatureListScreen: () -> Unit,
    moveToFestivalListScreen: () -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceListScreen: () -> Unit,
    moveToProfileModificationScreen: () -> Unit,
    isContent: Boolean
) {
    CustomSurface { isImeKeyboardShowing ->
        Scaffold(
            topBar = {},
            bottomBar = {
                MainNavigationBar(
                    viewType,
                    navigationItemContentList,
                    updateMainScreenViewType = updateMainScreenViewType,
                    initHomeScreen = initHomeScreen,
                    initFavoriteScreen = initFavoriteScreen
                )
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
                            moveToCategoryContentScreen = moveToCategoryContentScreen,
                            moveToNanaPickListScreen = moveToNanaPickListScreen,
                            moveToNatureListScreen = moveToNatureListScreen,
                            moveToFestivalListScreen = moveToFestivalListScreen,
                            moveToMarketListScreen = moveToMarketListScreen,
                            moveToExperienceListScreen = moveToExperienceListScreen,
                        )
                    }
                    MainScreenViewType.Favorite -> {
                        FavoriteScreen(
                            moveToCategoryContentScreen = moveToCategoryContentScreen,
                        )
                    }
                    MainScreenViewType.JejuStory -> {
                        JejuStoryScreen()
                    }
                    MainScreenViewType.MyPage -> {
                        MyPageScreen(
                            moveToProfileModificationScreen = moveToProfileModificationScreen
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
            .drawColoredShadow(
                color = getColor().black,
                alpha = 0.1f,
                shadowRadius = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp
            )
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
            },
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
                            style = caption02
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
            label = "홈"
        ),
        MainViewModel.NavigationItemContent(
            viewType = MainScreenViewType.Favorite,
            iconSelected = R.drawable.ic_heart_filled,
            iconUnselected = R.drawable.ic_heart_outlined,
            label = "찜"
        ),
        MainViewModel.NavigationItemContent(
            viewType = MainScreenViewType.JejuStory,
            iconSelected = R.drawable.ic_group_filled,
            iconUnselected = R.drawable.ic_group_outlined,
            label = "제주 이야기"
        ),
        MainViewModel.NavigationItemContent(
            viewType = MainScreenViewType.MyPage,
            iconSelected = R.drawable.ic_person_filled,
            iconUnselected = R.drawable.ic_person_outlined,
            label = "나의 나나"
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