package com.jeju.nanaland.ui.component.main.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.main.home.parts.HomeScreenCategoryButton
import com.jeju.nanaland.util.resource.getString

@Composable
fun HomeScreenCategoryButtons(
    moveToNatureListScreen: (String?) -> Unit,
    moveToFestivalListScreen: (String?) -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToActivityListScreen: () -> Unit,
    moveToArtListScreen: () -> Unit,
    moveToRestaurantListScreen: () -> Unit,
) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Spacer(Modifier)
        HomeScreenCategoryButton(
            resId = R.drawable.img_category_nature,
            text = getString(R.string.common_자연),
            onClick = { moveToNatureListScreen(null) }
        )
        HomeScreenCategoryButton(
            resId = R.drawable.img_category_festival,
            text = getString(R.string.common_축제),
            onClick = { moveToFestivalListScreen(null) }
        )
        HomeScreenCategoryButton(
            resId = R.drawable.img_category_market,
            text = getString(R.string.common_전통시장),
            onClick = moveToMarketListScreen
        )
        HomeScreenCategoryButton(
            resId = R.drawable.img_category_activity,
            text = getString(R.string.common_액티비티),
            onClick = moveToActivityListScreen
        )
        HomeScreenCategoryButton(
            resId = R.drawable.img_category_art,
            text = getString(R.string.common_문화예술),
            onClick = moveToArtListScreen
        )
        HomeScreenCategoryButton(
            resId = R.drawable.img_category_restaurant,
            text = getString(R.string.common_제주_맛집),
            onClick = moveToRestaurantListScreen
        )
        Spacer(Modifier)
    }
}
