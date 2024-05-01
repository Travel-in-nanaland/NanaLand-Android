package com.nanaland.ui.experience

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.util.ui.CustomPreview

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

@CustomPreview
@Composable
private fun ExperienceListScreenPreview() {

}