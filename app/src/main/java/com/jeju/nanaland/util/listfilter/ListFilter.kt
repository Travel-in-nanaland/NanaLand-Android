package com.jeju.nanaland.util.listfilter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListFilter(
    var filter: String? = null
): Parcelable
