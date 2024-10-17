package com.jeju.nanaland.ui.languagechange

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.LanguageType
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
    currLanguage: LanguageType,
    updateLanguage: (LanguageType) -> Unit,
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    val languageList = remember { LanguageType.entries.toMutableStateList() }.apply {
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
                text = getString(it.stringIndex),
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
                updateLanguage(selectedLanguage.value)
            },
            onCancel = { isConfirmDialogShowing.value = false }
        )
    }
}