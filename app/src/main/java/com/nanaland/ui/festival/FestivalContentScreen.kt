package com.nanaland.ui.festival

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.util.ui.CustomPreview

@Composable
fun FestivalContentScreen(
    viewModel: FestivalContentViewModel = hiltViewModel()
) {
    FestivalContentScreen(
        isContent = true
    )
}

@Composable
private fun FestivalContentScreen(
    isContent: Boolean
) {

}

@CustomPreview
@Composable
private fun FestivalContentScreenPreview() {

}