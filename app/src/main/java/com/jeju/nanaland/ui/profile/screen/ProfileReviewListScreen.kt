package com.jeju.nanaland.ui.profile.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.ui.profile.ProfileViewModel
import com.jeju.nanaland.ui.theme.largeTitle01

@Composable
fun ProfileReviewListScreen(
    id: Int?,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Text(text = "ProfileReviewListScreen \n ID: $id",  color = Color.Black, style = largeTitle01)
}