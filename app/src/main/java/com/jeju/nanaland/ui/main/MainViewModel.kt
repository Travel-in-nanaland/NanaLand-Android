package com.jeju.nanaland.ui.main

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.MainScreenViewType
import com.jeju.nanaland.util.resource.getString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _viewType = MutableStateFlow(MainScreenViewType.Home)
    val viewType = _viewType.asStateFlow()
    private val _prevViewType = MutableStateFlow(MainScreenViewType.Home)
    val prevViewType = _prevViewType.asStateFlow()

    fun getNavigationItemContentList(): List<NavigationItemContent> {
        return listOf(
            NavigationItemContent(
                viewType = MainScreenViewType.Home,
                iconSelected = R.drawable.ic_home_filled,
                iconUnselected = R.drawable.ic_home_outlined,
                label = getString(R.string.common_홈)
            ),
            NavigationItemContent(
                viewType = MainScreenViewType.Favorite,
                iconSelected = R.drawable.ic_heart_filled,
                iconUnselected = R.drawable.ic_heart_outlined,
                label = getString(R.string.common_찜)
            ),
            NavigationItemContent(
                viewType = MainScreenViewType.NanaPick,
                iconSelected = R.drawable.ic_logo_gray,
                iconUnselected = R.drawable.ic_logo_gray,
                label = getString(R.string.common_나나s_Pick)
            ),
            NavigationItemContent(
                viewType = MainScreenViewType.MyPage,
                iconSelected = R.drawable.ic_person_filled,
                iconUnselected = R.drawable.ic_person_outlined,
                label = getString(R.string.common_나의_나나)
            )
        )
    }

    fun updateViewType(viewType: MainScreenViewType) {
        _prevViewType.update { _viewType.value }
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