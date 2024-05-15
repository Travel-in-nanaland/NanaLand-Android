package com.jeju.nanaland.util.language

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jeju.nanaland.NanaLandApplication


var customContext by mutableStateOf(NanaLandApplication.applicationContext())