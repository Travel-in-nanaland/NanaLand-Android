package com.jeju.nanaland.globalvalue.constant

import androidx.compose.runtime.mutableStateListOf
import com.jeju.nanaland.R
import com.jeju.nanaland.util.resource.getString

fun getLocationList() = listOf(
    getString(R.string.location_common_제주시),
    getString(R.string.location_common_애월),
    getString(R.string.location_common_조천),
    getString(R.string.location_common_한경),
    getString(R.string.location_common_구좌),
    getString(R.string.location_common_한림),
    getString(R.string.location_common_우도),
    getString(R.string.location_common_추자),
    getString(R.string.location_common_서귀포시),
    getString(R.string.location_common_대정),
    getString(R.string.location_common_안덕),
    getString(R.string.location_common_남원),
    getString(R.string.location_common_표선),
    getString(R.string.location_common_성산)
)
fun getLocationSelectionList() = mutableStateListOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false)