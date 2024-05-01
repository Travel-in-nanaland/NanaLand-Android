package com.nanaland.ui.main.mynana

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyNanaScreen() {
    MyNanaScreen(
        isContent = true
    )
}

@Composable
private fun MyNanaScreen(
    isContent: Boolean
) {
    Text(
        text = "MyNanaScreen"
    )
}

@Preview
@Composable
private fun MyNanaScreenPreview() {

}