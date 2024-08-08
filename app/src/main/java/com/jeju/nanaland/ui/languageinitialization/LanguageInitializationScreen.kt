package com.jeju.nanaland.ui.languageinitialization

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.BOTTOM_NAVIGATION_BAR_HEIGHT
import com.jeju.nanaland.ui.component.languageinitialization.LanguageInitializationScreenLanguageBox
import com.jeju.nanaland.ui.component.languageinitialization.LanguageInitializationScreenText1
import com.jeju.nanaland.ui.component.languageinitialization.LanguageInitializationScreenText2
import com.jeju.nanaland.ui.theme.getColor
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
    val systemUiController = rememberSystemUiController()
    DisposableEffect(Unit) {
        systemUiController.setSystemBarsColor(
            color = Color(0x00000000),
            darkIcons = false
        )
        onDispose {
            systemUiController.setSystemBarsColor(
                color = Color(0x00000000),
                darkIcons = true
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getColor().main)
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Spacer(Modifier.height(80.dp))

        Column(Modifier.padding(start = 8.dp)) {
            LanguageInitializationScreenText1()

            Spacer(Modifier.height(4.dp))

            LanguageInitializationScreenText2()
        }

        Spacer(Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            item {
                LanguageInitializationScreenLanguageBox(
                    text1 = "Hello!\nNice to meet you \uD83D\uDC4B",
                    text2 = getString(R.string.common_영어),
                    onClick = {
                        selectLanguage("en")
                        moveToSignInScreen()
                    }
                )
            }

            item {
                LanguageInitializationScreenLanguageBox(
                    text1 = "你好!\n很高兴见到你 \uD83D\uDC4B",
                    text2 = getString(R.string.common_중국어),
                    onClick = {
                        selectLanguage("zh")
                        moveToSignInScreen()
                    }
                )
            }

            item {
                LanguageInitializationScreenLanguageBox(
                    text1 = "Hai!\nGembira jumpa \uD83D\uDC4B",
                    text2 = getString(R.string.common_말레이시아어),
                    onClick = {
                        selectLanguage("ms")
                        moveToSignInScreen()
                    }
                )
            }

            item {
                LanguageInitializationScreenLanguageBox(
                    text1 = "안녕하세요!\n반갑습니다 \uD83D\uDC4B",
                    text2 = getString(R.string.common_한국어),
                    onClick = {
                        selectLanguage("ko")
                        moveToSignInScreen()
                    }
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(64.dp),
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = null,
            colorFilter = ColorFilter.tint(getColor().white)
        )

        Spacer(Modifier.height((32 + BOTTOM_NAVIGATION_BAR_HEIGHT).dp))
    }
}

@ScreenPreview
@Composable
private fun LanguageInitializationScreenPreview() {
}