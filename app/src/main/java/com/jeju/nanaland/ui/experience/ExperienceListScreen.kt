package com.jeju.nanaland.ui.experience

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.util.ui.ScreenPreview

@Composable
fun ExperienceListScreen(
    moveToExperienceContentScreen: () -> Unit,
    viewModel: ExperienceListViewModel = hiltViewModel()
) {
    ExperienceListScreen(
        isContent = true
    )
}

@Composable
private fun ExperienceListScreen(
    isContent: Boolean
) {

}

@ScreenPreview
@Composable
private fun ExperienceListScreenPreview() {

}