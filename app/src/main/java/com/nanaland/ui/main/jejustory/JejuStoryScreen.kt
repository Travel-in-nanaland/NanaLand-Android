package com.nanaland.ui.main.jejustory

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun JejuStoryScreen() {
    JejuStoryScreen(
        isContent = true
    )
}

@Composable
private fun JejuStoryScreen(
    isContent: Boolean
) {
    Text(
        text = "JejuStoryScreen"
    )
}

@Preview
@Composable
private fun JejuStoryScreenPreview() {

}