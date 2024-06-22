package com.jeju.nanaland.util.string

fun String?.useNonBreakingSpace() = this.orEmpty()
    .replace(
        ' ',
        '\u00A0'
    )