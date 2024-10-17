package com.jeju.nanaland.util.language

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jeju.nanaland.NanaLandApplication
import com.jeju.nanaland.globalvalue.type.LanguageType


var customContext by mutableStateOf(NanaLandApplication.applicationContext())

fun getLanguage(): LanguageType = LanguageType.codeToLanguage(customContext.resources.configuration.locales[0].language)