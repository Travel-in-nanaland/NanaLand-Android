package com.jeju.nanaland.globalvalue.constant

import androidx.compose.runtime.mutableStateListOf

fun getActivityKeywordIdx(keyword: String?) = when (keyword) {
    "지상레저" -> 0
    "수상레저" -> 1
    "항공레저" -> 2
    "해양체험" -> 3
    "농촌체험" -> 4
    else -> 5
}

fun getActivityKeywordList() = listOf(
    "지상레저",
    "수상레저",
    "항공레저",
    "해양체험",
    "농촌체험",
    "힐링테라피"
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
    "역사",
    "전시회",
    "공방",
    "미술관",
    "박물관",
    "공원",
    "공연",
    "종교시설",
    "테마파크"
)

fun getCultureArtKeywordSelectionList() = mutableStateListOf(false, false, false, false, false, false, false, false, false)

fun getRestaurantKeywordList() = listOf(
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

fun getRestaurantKeywordSelectionList() = mutableStateListOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false)