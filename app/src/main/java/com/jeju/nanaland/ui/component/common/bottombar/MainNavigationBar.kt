package com.jeju.nanaland.ui.component.common.bottombar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.TOP_BAR_HEIGHT
import com.jeju.nanaland.ui.main.NoRippleInteractionSource
import com.jeju.nanaland.ui.theme.caption02SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.shadowBottomNav
import com.jeju.nanaland.util.resource.getString


@Composable
fun MainNavigationBar(
    toHome: () -> Unit,
    toFavorite: () -> Unit,
    toNana: () -> Unit,
    toProfile: () -> Unit,
) {
    val items = remember {
        listOf(
            Triple(R.drawable.ic_home_outlined, getString(R.string.common_홈), toHome),
            Triple(R.drawable.ic_heart_outlined, getString(R.string.common_찜), toFavorite),
            Triple(R.drawable.ic_logo_gray, getString(R.string.common_나나s_Pick), toNana),
            Triple(R.drawable.ic_person_outlined, getString(R.string.common_나의_나나), toProfile),
        )
    }
    NavigationBar(
        modifier = Modifier
            .height(TOP_BAR_HEIGHT.dp)
            .shadowBottomNav(),
        containerColor = getColor().white,
        windowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        items.forEach {
            NavigationBarItem(
                modifier = Modifier.fillMaxHeight(),
                selected = false,
                onClick = it.third,
                icon = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(it.first),
                            contentDescription = null,
                        )
                        Text(
                            text = it.second,
                            style = caption02SemiBold
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = getColor().black,
                    selectedTextColor = getColor().black,
                    indicatorColor = getColor().white,
                    unselectedIconColor = getColor().black25,
                    unselectedTextColor = getColor().black25
                ),
                interactionSource = NoRippleInteractionSource(),
                alwaysShowLabel = true
            )
        }
    }
}