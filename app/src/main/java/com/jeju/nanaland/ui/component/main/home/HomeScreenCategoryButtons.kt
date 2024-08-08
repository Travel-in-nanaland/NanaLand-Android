package com.jeju.nanaland.ui.component.main.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.main.home.parts.HomeScreenCategoryButton
import com.jeju.nanaland.util.listfilter.ListFilter
import com.jeju.nanaland.util.resource.getString

@Composable
fun HomeScreenCategoryButtons(
    moveToNatureListScreen: (ListFilter) -> Unit,
    moveToFestivalListScreen: (ListFilter) -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceListScreen: () -> Unit,
    moveToRestaurantListScreen: () -> Unit,
) {
    Row() {
        HomeScreenCategoryButton(
            resId = R.drawable.img_nature,
            text = getString(R.string.common_7대_자연),
            onClick = { moveToNatureListScreen(ListFilter()) }
        )
        Spacer(Modifier.weight(1f))
        HomeScreenCategoryButton(
            resId = R.drawable.img_festival,
            text = getString(R.string.common_축제),
            onClick = { moveToFestivalListScreen(ListFilter()) }
        )
        Spacer(Modifier.weight(1f))
        HomeScreenCategoryButton(
            resId = R.drawable.img_market,
            text = getString(R.string.common_전통시장),
            onClick = moveToMarketListScreen
        )
        Spacer(Modifier.weight(1f))
        HomeScreenCategoryButton(
            resId = R.drawable.img_activity,
            text = getString(R.string.common_이색_체험),
            onClick = moveToExperienceListScreen
        )
        Spacer(Modifier.weight(1f))
        HomeScreenCategoryButton(
            resId = R.drawable.img_restaurant,
            text = "제주 맛집",
            onClick = moveToRestaurantListScreen
        )
    }
}