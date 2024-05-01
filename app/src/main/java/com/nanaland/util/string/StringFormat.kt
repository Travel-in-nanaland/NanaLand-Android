package com.nanaland.util.string

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// 05.01 (수)
fun getMonthDateDayOfWeek(calendar: Calendar): String {
    val dateFormat = SimpleDateFormat("MM.dd (E)", Locale.KOREAN)
    return dateFormat.format(calendar.time)
}