package com.nanaland.util.type

import com.nanaland.globalvalue.type.CategoryType

fun getCategoryType(type: String?): CategoryType {
    return when (type) {
        "NATURE" -> CategoryType.Nature
        "FESTIVAL" -> CategoryType.Festival
        "MARKET" -> CategoryType.Market
        "EXPERIENCE" -> CategoryType.Experience
        else  -> CategoryType.Nana
    }
}