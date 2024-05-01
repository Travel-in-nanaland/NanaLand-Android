package com.nanaland.util.resource

import androidx.annotation.StringRes
import com.nanaland.NanaLandApplication

fun getString(@StringRes id: Int) = NanaLandApplication.applicationContext().
createConfigurationContext(NanaLandApplication.applicationContext()
    .resources.configuration).getString(id)