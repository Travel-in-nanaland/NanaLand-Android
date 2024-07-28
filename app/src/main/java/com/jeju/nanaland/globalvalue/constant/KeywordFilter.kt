package com.jeju.nanaland.globalvalue.constant

import androidx.compose.runtime.mutableStateListOf

fun getKeywordIdx(keyword: String?) = when (keyword) {
    "지상레저" -> 0
    "수상레저" -> 1
    "항공레저" -> 2
    "해양체험" -> 3
    "농촌체험" -> 4
    else -> 5
}

fun getKeywordList() = listOf(
    "지상레저",
    "수상레저",
    "항공레저",
    "해양체험",
    "농촌체험",
    "힐링테라피"
)

fun getKeywordSelectionList() = mutableStateListOf(false, false, false, false, false, false)