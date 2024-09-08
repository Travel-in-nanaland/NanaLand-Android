package com.jeju.nanaland.util.string

import com.jeju.nanaland.R
import com.jeju.nanaland.util.resource.getString
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

// 05.01 (수)
fun getYearMonthDateSeperated(calendar: Calendar): String {
//    val dateFormat = SimpleDateFormat("yy.MM.dd (E)", Locale.KOREAN)
    val dateFormat = SimpleDateFormat("yy.MM.dd", Locale.KOREAN)
    return dateFormat.format(calendar.time)
}

// yyyyMMdd
fun getYearMonthDate(calendar: Calendar): String {
    val dateFormat = SimpleDateFormat("yyyyMMdd")
    return dateFormat.format(calendar.time)
}

// yyyy.MM.dd
fun Date.toDotYearMonthDate(): String {
    val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREAN)
    return dateFormat.format(time)
}
// yyyy.MM.dd
fun Calendar.toDotYearMonthDate(): String {
    val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREAN)
    return dateFormat.format(time)
}

// 1시간 이내 (59분까지) - 방금 전  // 23시간까지만, 시간으로 보여주고 // 이후는 날짜로 표기
fun Date.toFormatString(): String {
    val now = Date()
    val diffInHours = TimeUnit.MILLISECONDS.toHours(now.time - time)

    return when {
        diffInHours < 1 -> getString(R.string.common_방금전)
        diffInHours < 24 -> getString(R.string.common_시간전, diffInHours)
        else -> toDotYearMonthDate()
    }
}

// 가격 등에 컴마 넣는거
private val numberFormat = mutableMapOf<Locale, NumberFormat>()
fun Number.toWithComma(locale: Locale): String = numberFormat.getOrPut(locale) {
    NumberFormat.getNumberInstance(locale)
}.format(this)
