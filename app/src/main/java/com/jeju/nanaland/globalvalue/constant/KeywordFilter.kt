package com.jeju.nanaland.globalvalue.constant

import androidx.compose.runtime.mutableStateListOf
import com.jeju.nanaland.R
import com.jeju.nanaland.util.resource.getString

fun getActivityKeywordIdx(keyword: String?) = when (keyword) {
    "지상레저" -> 0
    "수상레저" -> 1
    "항공레저" -> 2
    "해양체험" -> 3
    "농촌체험" -> 4
    else -> 5
}

fun getActivityKeywordList() = listOf(
    getString(R.string.activity_지상레저),
    getString(R.string.activity_수상레저),
    getString(R.string.activity_항공레저),
    getString(R.string.activity_해양체험),
    getString(R.string.activity_농촌체험),
    getString(R.string.activity_힐링테라피),
)

fun getActivityKeywordSelectionList() = mutableStateListOf(false, false, false, false, false, false)

fun getCultureArtKeywordIdx(keyword: String?) = when (keyword) {
    "역사" -> 0
    "전시회" -> 1
    "공방" -> 2
    "미술관" -> 3
    "박물관" -> 4
    "공원" -> 5
    "공연" -> 6
    "종교시설" -> 7
    else -> 8
}

fun getCultureArtKeywordList() = listOf(
    getString(R.string.activity_역사),
    getString(R.string.activity_전시회),
    getString(R.string.activity_공방),
    getString(R.string.activity_미술관),
    getString(R.string.activity_박물관),
    getString(R.string.activity_공원),
    getString(R.string.activity_공연),
    getString(R.string.activity_종교시설),
    getString(R.string.activity_테마파크),
)

fun getCultureArtKeywordSelectionList() = mutableStateListOf(false, false, false, false, false, false, false, false, false)

fun getRestaurantKeywordFilterList() = listOf(
    "한식",
    "중식",
    "일식",
    "양식",
    "분식",
    "남미 음식",
    "동남아 음식",
    "비건푸드",
    "할랄푸드",
    "육류/흑돼지",
    "해산물",
    "치킨/버거",
    "카페/디저트",
    "펍/요리주점"
)

fun getRestaurantKeywordList() = listOf(
    getString(R.string.restaurant_한식),
    getString(R.string.restaurant_중식),
    getString(R.string.restaurant_일식),
    getString(R.string.restaurant_양식),
    getString(R.string.restaurant_분식),
    getString(R.string.restaurant_남미_음식),
    getString(R.string.restaurant_동남아_음식),
    getString(R.string.restaurant_비건_푸드),
    getString(R.string.restaurant_할랄_푸드),
    getString(R.string.restaurant_육류_흑돼지),
    getString(R.string.restaurant_해산물),
    getString(R.string.restaurant_치킨_버거),
    getString(R.string.restaurant_카페_디저트),
    getString(R.string.restaurant_펍_요리주점)
)

fun getRestaurantKeywordSelectionList() = mutableStateListOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false)