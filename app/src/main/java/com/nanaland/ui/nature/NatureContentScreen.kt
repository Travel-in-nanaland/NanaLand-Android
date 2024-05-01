package com.nanaland.ui.nature

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.util.ui.CustomPreview

@Composable
fun NatureContentScreen(
    viewModel: NatureContentViewModel = hiltViewModel()
) {
    NatureContentScreen(
        isContent = true
    )
}

@Composable
private fun NatureContentScreen(
    isContent: Boolean
) {

}

@CustomPreview
@Composable
private fun NatureContentScreenPreview() {

}