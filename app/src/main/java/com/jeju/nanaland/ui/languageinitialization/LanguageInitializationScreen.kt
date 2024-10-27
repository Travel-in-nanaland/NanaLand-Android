package com.jeju.nanaland.ui.languageinitialization

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.BOTTOM_NAVIGATION_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.type.LanguageType
import com.jeju.nanaland.ui.component.languageinitialization.LanguageInitializationScreenText1
import com.jeju.nanaland.ui.component.languageinitialization.LanguageInitializationScreenText2
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import java.util.Locale

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
    selectLanguage: (LanguageType) -> Unit,
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
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(80.dp))

        Column() {
            LanguageInitializationScreenText1()

            Spacer(Modifier.height(4.dp))

            LanguageInitializationScreenText2()
        }

        Spacer(Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            listOf(
                LanguageType.English,
                LanguageType.Chinese,
                LanguageType.Malaysia,
                LanguageType.Vietnam,
                LanguageType.Korean
            ).forEach {
                item {
                    LanguageInitializationScreenLanguageBox(
                        languageType = it,
                        onClick = {
                            selectLanguage(it)
                            moveToSignInScreen()
                        }
                    )
                }
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

@Composable
private fun LanguageInitializationScreenLanguageBox(
    languageType: LanguageType,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val selectText = remember(languageType) {
        context.createConfigurationContext(
            Configuration(context.resources.configuration).apply {
                setLocale(Locale(languageType.code))
            }
        ).resources.getString(R.string.profile_initialization_screen_select_text)
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(115.dp)
            .background(
                color = getColor().white,
                shape = RoundedCornerShape(12.dp)
            )
            .clickableNoEffect(onClick)
            .padding(horizontal = 12.dp)
            .padding(top = 12.dp, bottom = 8.dp)
    ) {
        Text(
            text = selectText,
            color = getColor().black,
            style = body02Bold
        )

        Spacer(Modifier.weight(1f))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.weight(1f))
            Text(
                text = getString(languageType.stringIndex),
                color = getColor().black,
                style = caption01
            )

            Spacer(Modifier.width(4.dp))

            Image(
                modifier = Modifier.width(16.dp),
                painter = painterResource(R.drawable.ic_right_arrow_2),
                contentDescription = null
            )
        }
    }
}