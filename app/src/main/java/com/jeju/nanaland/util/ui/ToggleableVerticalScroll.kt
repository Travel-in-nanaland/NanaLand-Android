package com.jeju.nanaland.util.ui

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.toggleableVerticalScroll(isAvailable: Boolean): Modifier {
    return if (isAvailable) {
        this.verticalScroll(rememberScrollState())
    } else {
        this
    }
}