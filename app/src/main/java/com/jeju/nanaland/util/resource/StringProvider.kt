package com.jeju.nanaland.util.resource

import androidx.annotation.StringRes
import com.jeju.nanaland.util.language.customContext

//fun getString(@StringRes id: Int) = NanaLandApplication.applicationContext().
//createConfigurationContext(NanaLandApplication.applicationContext()
//    .resources.configuration).getString(id)


fun getString(@StringRes id: Int, vararg formatArgs: Any) = customContext.getString(id, *formatArgs)