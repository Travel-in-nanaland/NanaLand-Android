package com.nanaland.ui.component.home.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.nanaland.ui.theme.title02Bold

@Composable
fun HomeScreenRecommendText(text: String) {
    Text(
        text = "$text 님을 위한 도민 추천 \uD83C\uDF4A",
        color = Color(0xFF1A1A1A),
        style = title02Bold
    )
}