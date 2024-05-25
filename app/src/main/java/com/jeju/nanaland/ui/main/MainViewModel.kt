package com.jeju.nanaland.ui.main

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.MainScreenViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _viewType = MutableStateFlow(MainScreenViewType.Home)
    val viewType = _viewType.asStateFlow()
    private val navigationItemContentList = listOf(
        NavigationItemContent(
            viewType = MainScreenViewType.Home,
            iconSelected = R.drawable.ic_home_filled,
            iconUnselected = R.drawable.ic_home_outlined,
            label = "홈"
        ),
        NavigationItemContent(
            viewType = MainScreenViewType.Favorite,
            iconSelected = R.drawable.ic_heart_filled,
            iconUnselected = R.drawable.ic_heart_outlined,
            label = "찜"
        ),
        NavigationItemContent(
            viewType = MainScreenViewType.JejuStory,
            iconSelected = R.drawable.ic_group_filled,
            iconUnselected = R.drawable.ic_group_outlined,
            label = "제주 이야기"
        ),
        NavigationItemContent(
            viewType = MainScreenViewType.MyPage,
            iconSelected = R.drawable.ic_person_filled,
            iconUnselected = R.drawable.ic_person_outlined,
            label = "나의 나나"
        )
    )

    fun getNavigationItemContentList(): List<NavigationItemContent> {
        return navigationItemContentList
    }

    fun updateViewType(viewType: MainScreenViewType) {
        _viewType.update { viewType }
    }

    data class NavigationItemContent(
        val viewType: MainScreenViewType,
        @DrawableRes
        val iconSelected: Int,
        @DrawableRes
        val iconUnselected: Int,
        val label: String = ""
    )
}