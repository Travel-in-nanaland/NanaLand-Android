package com.jeju.nanaland.ui.component.listscreen.filter.parts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ComponentPreview
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun LocationFilterBox(
    modifier: Modifier,
    locationList: List<String>,
    openLocationFilterDialog: () -> Unit,
    selectedLocationList: SnapshotStateList<Boolean>,
) {
    val borderColor = getColor().gray02
    Row(
        modifier = modifier
            .height(32.dp)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = borderColor
                ),
                shape = RoundedCornerShape(50)
            )
            .clickableNoEffect(openLocationFilterDialog),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.width(12.dp))

        Text(
            modifier = Modifier.weight(1f, false),
            text = (when (selectedLocationList.count { it }) {
                0 -> getString(R.string.list_screen_common_전_지역)
                1 -> locationList[selectedLocationList.indexOfFirst { it }]
                else -> getString(R.string.common_외, locationList[selectedLocationList.indexOfFirst { it }], selectedLocationList.count { it } - 1)
            }).replace('\n',' '),
            color = getColor().gray01,
            style = caption01,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.width(4.dp))

        Image(
            modifier = Modifier.size(16.dp),
            painter = painterResource(R.drawable.ic_arrow_down),
            contentDescription = null,
            colorFilter = ColorFilter.tint(getColor().gray01)
        )

        Spacer(Modifier.width(12.dp))
    }
}

@ComponentPreview
@Composable
private fun LocationFilterBoxPreview() {
    val locationList = listOf("전체", "제주시", "애월", "서귀포시", "성산", "한림", "조천", "구좌", "한경", "대정", "안덕", "남원", "표선", "우도")
    val selectedLocationList = remember { mutableStateListOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false) }
    NanaLandTheme {
        LocationFilterBox(
            modifier = Modifier,
            locationList = locationList,
            openLocationFilterDialog = {},
            selectedLocationList = selectedLocationList
        )
    }
}