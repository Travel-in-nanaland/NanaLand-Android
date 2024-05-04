package com.nanaland.util.string

import java.text.SimpleDateFormat
import java.util.Calendar
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