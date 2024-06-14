package com.jeju.nanaland.ui.languageinitialization

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.languageinitialization.LanguageInitializationScreenLanguageBox
import com.jeju.nanaland.ui.component.languageinitialization.LanguageInitializationScreenText1
import com.jeju.nanaland.ui.component.languageinitialization.LanguageInitializationScreenText2
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview

@Composable
fun LanguageInitializationScreen(
    moveToSignInScreen: () -> Unit,
    viewModel: LanguageInitializationViewModel = hiltViewModel()
) {
    LanguageInitializationScreen(
        selectLanguage = viewModel::selectLanguage,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun LanguageInitializationScreen(
    selectLanguage: (String) -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(80.dp))

        LanguageInitializationScreenText1()

        Spacer(Modifier.height(4.dp))

        LanguageInitializationScreenText2()

        Spacer(Modifier.height(120.dp))

        LanguageInitializationScreenLanguageBox(
            text = getString(R.string.common_영어),
            onClick = {
                selectLanguage("en")
                moveToSignInScreen()
            }
        )

        Spacer(Modifier.height(32.dp))

        LanguageInitializationScreenLanguageBox(
            text = getString(R.string.common_중국어),
            onClick = {
                selectLanguage("zh")
                moveToSignInScreen()
            }
        )

        Spacer(Modifier.height(32.dp))

        LanguageInitializationScreenLanguageBox(
            text = getString(R.string.common_말레이시아어),
            onClick = {
                selectLanguage("ms")
                moveToSignInScreen()
            }
        )

        Spacer(Modifier.height(32.dp))

        LanguageInitializationScreenLanguageBox(
            text = getString(R.string.common_한국어),
            onClick = {
                selectLanguage("ko")
                moveToSignInScreen()
            }
        )
    }
}

@ScreenPreview
@Composable
private fun LanguageInitializationScreenPreview() {
}