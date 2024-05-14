package com.nanaland.util.resource

import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import com.nanaland.NanaLandApplication
import com.nanaland.NanaLandApplication.Companion.applicationContext
import com.nanaland.util.language.customContext
import javax.inject.Inject

//fun getString(@StringRes id: Int) = NanaLandApplication.applicationContext().
//createConfigurationContext(NanaLandApplication.applicationContext()
//    .resources.configuration).getString(id)


fun getString(@StringRes id: Int) = customContext.getString(id)