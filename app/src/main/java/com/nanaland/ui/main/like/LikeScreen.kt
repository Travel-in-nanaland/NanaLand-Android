package com.nanaland.ui.main.like

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LikeScreen() {
    LikeScreen(
        isContent = true
    )
}

@Composable
private fun LikeScreen(
    isContent: Boolean
) {
    Text(text = "LikeScreen")
}

@Preview
@Composable
private fun LikeScreenPreview() {

}