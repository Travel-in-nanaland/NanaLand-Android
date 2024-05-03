package com.nanaland.ui.experience

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.util.ui.ScreenPreview

@Composable
fun ExperienceContentScreen(
    viewModel: ExperienceContentViewModel = hiltViewModel()
) {
    ExperienceContentScreen(
        isContent = true
    )
}

@Composable
private fun ExperienceContentScreen(
    isContent: Boolean
) {

}

@ScreenPreview
@Composable
private fun ExperienceContentScreenPreview() {

}