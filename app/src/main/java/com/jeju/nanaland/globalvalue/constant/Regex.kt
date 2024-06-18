package com.jeju.nanaland.globalvalue.constant

//val nicknameRegex = Regex("^[가-힇一-龥a-zA-Z0-9/s]+\$")
val nicknameRegex = Regex("^[a-zA-Z0-9\\uAC00-\\uD7AF\\u4E00-\\u9FFF\\u00C0-\\u024F\\u1E00-\\u1EFF][a-zA-Z0-9\\uAC00-\\uD7AF\\u4E00-\\u9FFF\\u00C0-\\u024F\\u1E00-\\u1EFF ]{0,10}[a-zA-Z0-9\\uAC00-\\uD7AF\\u4E00-\\u9FFF\\u00C0-\\u024F\\u1E00-\\u1EFF]\$")
val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")