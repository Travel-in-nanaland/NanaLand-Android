package com.jeju.nanaland.ui.component.common.layoutSet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun ListEmptyByFilter(
    onFilterReset: ()-> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = getString(R.string.list_screen_common_empty_list_by_filter_head),
            color = getColor().gray01,
            style = body01,
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = getString(R.string.list_screen_common_empty_list_by_filter_sub),
            color = Color(0xFFC4C4C4),
            style = body02,
        )
        Spacer(Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .clickableNoEffect(onFilterReset)
                .background(getColor().gray02, RoundedCornerShape(50))
                .padding(horizontal = 28.dp, vertical = 9.dp),
            text = getString(R.string.list_screen_common_reset_filter),
            color = getColor().gray01,
            style = body02SemiBold,
        )
    }
}