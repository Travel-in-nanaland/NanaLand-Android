package com.nanaland.ui.languageselection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.ui.theme.getColor
import com.nanaland.util.resource.getString
import com.nanaland.util.ui.CustomPreview
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun LanguageSelectionScreen(
    moveToSignInScreen: () -> Unit,
) {
    LanguageSelectionScreen(
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun LanguageSelectionScreen(
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = getString(R.string.select_language_please),
            color = getColor().black
        )
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(50.dp)
                .background(
                    color = getColor().main,
                    shape = RoundedCornerShape(50)
                )
                .clickableNoEffect {  }
        ) {
            Text(
                text = "English",
                color = getColor().main
            )
        }
    }
}

@CustomPreview
@Composable
private fun LanguageSelectionScreenPreview() {
}