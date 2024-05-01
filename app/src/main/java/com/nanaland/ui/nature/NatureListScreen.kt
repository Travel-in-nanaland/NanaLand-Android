package com.nanaland.ui.nature

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.util.ui.CustomPreview

@Composable
fun NatureListScreen(
    moveToNatureContentScreen: () -> Unit,
    viewModel: NatureListViewModel = hiltViewModel()
) {
    NatureListScreen(
        isContent = true
    )
}

@Composable
private fun NatureListScreen(
    isContent: Boolean
) {

}

@CustomPreview
@Composable
private fun NatureListScreenPreview() {

}