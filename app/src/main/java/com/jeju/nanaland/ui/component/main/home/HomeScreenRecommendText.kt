package com.jeju.nanaland.ui.component.main.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.resource.getString

@Composable
fun HomeScreenRecommendText(text: String) {
    Text(
        text = getString(R.string.home_screen_recommend2, text),
        color = Color(0xFF1A1A1A),
        style = title02Bold
    )
}