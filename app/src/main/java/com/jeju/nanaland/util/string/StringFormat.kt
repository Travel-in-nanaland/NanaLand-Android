package com.jeju.nanaland.util.string

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

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
fun getDotYearMonthDate(calendar: Date): String {
    val dateFormat = SimpleDateFormat("yyyy.MM.dd")
    return dateFormat.format(calendar.time)
}
// yyyy.MM.dd
fun getDotYearMonthDate(calendar: Calendar): String {
    val dateFormat = SimpleDateFormat("yyyy.MM.dd")
    return dateFormat.format(calendar.time)
}