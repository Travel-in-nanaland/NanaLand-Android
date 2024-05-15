package com.jeju.nanaland.ui.component.main.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.main.home.parts.HomeScreenCategoryButton

@Composable
fun HomeScreenCategoryButtons(
    moveToNatureListScreen: () -> Unit,
    moveToFestivalListScreen: () -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceScreen: () -> Unit,
    moveToNanaPickListScreen: () -> Unit,
) {
    Row() {
        HomeScreenCategoryButton(
            resId = R.drawable.img_nature,
            text = "7대 자연",
            onClick = moveToNatureListScreen
        )
        Spacer(Modifier.weight(1f))
        HomeScreenCategoryButton(
            resId = R.drawable.img_festival,
            text = "축제",
            onClick = moveToFestivalListScreen
        )
        Spacer(Modifier.weight(1f))
        HomeScreenCategoryButton(
            resId = R.drawable.img_market,
            text = "전통시장",
            onClick = moveToMarketListScreen
        )
        Spacer(Modifier.weight(1f))
        HomeScreenCategoryButton(
            resId = R.drawable.img_activity,
            text = "이색 체험",
            onClick = moveToExperienceScreen
        )
        Spacer(Modifier.weight(1f))
        HomeScreenCategoryButton(
            resId = R.drawable.img_nanapick,
            text = "나나's Pick",
            onClick = moveToNanaPickListScreen
        )
    }
}