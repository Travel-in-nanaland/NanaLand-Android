package com.jeju.nanaland.ui.languagechange

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.component.languagechange.LanguageChangeScreenDialog
import com.jeju.nanaland.ui.component.languagechange.LanguageChangeScreenGuideText
import com.jeju.nanaland.ui.component.languagechange.LanguageChangeScreenItem
import com.jeju.nanaland.util.resource.getString

@Composable
fun LanguageChangeScreen(
    moveToBackScreen: () -> Unit,
    viewModel: LanguageChangeViewModel = hiltViewModel()
) {
    val currLanguage = viewModel.currLanguage.collectAsState().value
    LanguageChangeScreen(
        currLanguage = currLanguage,
        updateLanguage = viewModel::updateLanguage,
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun LanguageChangeScreen(
    currLanguage: String,
    updateLanguage: (String) -> Unit,
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    val languageList = remember { mutableStateListOf("ko", "en", "zh", "ms", "vi") }.apply {
        sortBy { it != currLanguage }
    }
    val selectedLanguage = remember { mutableStateOf(currLanguage) }
    val isConfirmDialogShowing = remember { mutableStateOf(false) }
    CustomSurface {
        CustomTopBar(
            title = getString(R.string.settings_screen_언어_설정),
            onBackButtonClicked = { moveToBackScreen() }
        )

        Spacer(Modifier.height(32.dp))

        Box(Modifier.padding(start = 16.dp, end = 16.dp)) {
            LanguageChangeScreenGuideText()
        }

        Spacer(Modifier.height(24.dp))

        languageList.forEach {
            LanguageChangeScreenItem(
                text = when(it) {
                    "ko" -> getString(R.string.common_한국어)
                    "en" -> getString(R.string.common_영어)
                    "zh" -> getString(R.string.common_중국어)
                    "ms" -> getString(R.string.common_말레이시아어)
                    else -> getString(R.string.common_베트남어)
                },
                isSelected = currLanguage == it,
                onClick = {
                    selectedLanguage.value = it
                    isConfirmDialogShowing.value = true
                }
            )
        }
    }

    if (isConfirmDialogShowing.value) {
        LanguageChangeScreenDialog(
            onConfirm = {
                updateLanguage(when (selectedLanguage.value) {
                    "ko" -> "KOREAN"
                    "en" -> "ENGLISH"
                    "zh" -> "CHINESE"
                    else -> "MALAYSIA"
                })
            },
            onCancel = { isConfirmDialogShowing.value = false }
        )
    }
}