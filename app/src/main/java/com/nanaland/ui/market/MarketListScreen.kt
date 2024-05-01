package com.nanaland.ui.market

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.util.ui.CustomPreview

@Composable
fun MarketListScreen(
    moveToMarketContentScreen: () -> Unit,
    viewModel: MarketListViewModel = hiltViewModel()
) {
    MarketListScreen(
        isContent = true
    )
}

@Composable
private fun MarketListScreen(
    isContent: Boolean
) {

}

@CustomPreview
private fun MarketListScreenPreview() {

}