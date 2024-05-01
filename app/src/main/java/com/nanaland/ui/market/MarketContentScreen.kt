package com.nanaland.ui.market

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.util.ui.CustomPreview

@Composable
fun MarketContentScreen(
    viewModel: MarketContentViewModel = hiltViewModel()
) {
    MarketContentScreen(
        isContent = true
    )
}

@Composable
private fun MarketContentScreen(
    isContent: Boolean
) {

}

@CustomPreview
@Composable
private fun MarketContentScreenPreview() {

}