package com.jeju.nanaland.globalvalue.constant

import androidx.compose.runtime.mutableStateListOf
import com.jeju.nanaland.R
import com.jeju.nanaland.util.resource.getString

fun getLocationIdx(location: String?) = when (location) {
    "제주시" -> 0
    "애월" -> 1
    "조천" -> 2
    "한경" -> 3
    "구좌" -> 4
    "한림" -> 5
    "우도" -> 6
    "추자" -> 7
    "서귀포시" -> 8
    "대정" -> 9
    "안덕" -> 10
    "남원" -> 11
    "표선" -> 12
    else -> 13
}

fun getLocationFilterList() = listOf(
    "JEJU",
    "AEWOL",
    "JOCHEON",
    "HANGYEONG",
    "GUJWA",
    "HALLIM",
    "UDO",
    "CHUJA",
    "SEOGWIPO",
    "DAEJEONG",
    "ANDEOK",
    "NAMWON",
    "PYOSEON",
    "SEONGSAN"
)

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