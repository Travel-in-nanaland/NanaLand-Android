package com.jeju.nanaland.globalvalue.type

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.R

enum class LanguageType(
    val code: String,
    val stringIndex: Int
) {
    @SerializedName("KOREAN")
    Korean("ko", R.string.common_한국어),

    @SerializedName("ENGLISH")
    English("en", R.string.common_영어),

    @SerializedName("CHINESE")
    Chinese("zh", R.string.common_중국어),

    @SerializedName("MALAYSIA")
    Malaysia("ms", R.string.common_말레이시아어),

    @SerializedName("VIETNAMESE")
    Vietnam("vi", R.string.common_베트남어);

    companion object {
        fun codeToLanguage(languageCode: String): LanguageType = when(languageCode) {
            "ko" -> Korean
            "zh" -> Chinese
            "ms" -> Malaysia
            "vi" -> Vietnam
            else -> English
        }
    }
}