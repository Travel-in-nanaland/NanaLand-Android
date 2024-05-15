package com.jeju.nanaland.ui.languageselection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.languageselection.LanguageSelectionScreenLanguageBox
import com.jeju.nanaland.ui.component.languageselection.LanguageSelectionScreenText1
import com.jeju.nanaland.ui.component.languageselection.LanguageSelectionScreenText2
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview

@Composable
fun LanguageSelectionScreen(
    moveToSignInScreen: () -> Unit,
    viewModel: LanguageSelectionViewModel = hiltViewModel()
) {
    LanguageSelectionScreen(
        selectLanguage = viewModel::selectLanguage,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun LanguageSelectionScreen(
    selectLanguage: (String) -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(80.dp))

        LanguageSelectionScreenText1()

        Spacer(Modifier.height(4.dp))

        LanguageSelectionScreenText2()

        Spacer(Modifier.height(76.dp))

        LanguageSelectionScreenLanguageBox(
            text = "English",
            onClick = {
                selectLanguage("en")
                moveToSignInScreen()
            }
        )

        Spacer(Modifier.height(32.dp))

        LanguageSelectionScreenLanguageBox(
            text = "중국어",
            onClick = {
                selectLanguage("zh")
                moveToSignInScreen()
            }
        )

        Spacer(Modifier.height(32.dp))

        LanguageSelectionScreenLanguageBox(
            text = "말레이시아어",
            onClick = {
                selectLanguage("ms")
                moveToSignInScreen()
            }
        )

        Spacer(Modifier.height(32.dp))

        LanguageSelectionScreenLanguageBox(
            text = "한국어",
            onClick = {
                selectLanguage("ko")
                moveToSignInScreen()
            }
        )
    }
}

@ScreenPreview
@Composable
private fun LanguageSelectionScreenPreview() {
}