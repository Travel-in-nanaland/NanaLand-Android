package com.jeju.nanaland.util.type

import com.jeju.nanaland.globalvalue.type.CategoryType

fun getCategoryType(type: String?): CategoryType {
    return when (type) {
        "NATURE" -> CategoryType.Nature
        "FESTIVAL" -> CategoryType.Festival
        "MARKET" -> CategoryType.Market
        "EXPERIENCE" -> CategoryType.Experience
        "RESTAURANT" -> CategoryType.Restaurant
        else  -> CategoryType.Nana
    }
}