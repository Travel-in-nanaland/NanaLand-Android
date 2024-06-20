package com.jeju.nanaland.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.SplashCheckingState
import com.jeju.nanaland.util.intent.DeepLinkData
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    deepLinkData: DeepLinkData,
    moveToMainScreen: () -> Unit,
    moveToLanguageInitScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val checkingState = viewModel.checkingState.collectAsState().value
    val isNetworkConnected = viewModel.isNetworkConnected.collectAsState().value
    SplashScreen(
        checkingState = checkingState,
        isNetworkConnected = isNetworkConnected,
        checkNetworkState = viewModel::checkNetworkState,
        checkLanguageState = {
            viewModel.checkLanguageState(
                deepLinkData,
                it
            )
        },
        checkSignInState = viewModel::checkSignInState,
        moveToMainScreen = moveToMainScreen,
        moveToLanguageInitScreen = moveToLanguageInitScreen,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun SplashScreen(
    checkingState: SplashCheckingState,
    isNetworkConnected: Boolean,
    checkNetworkState: () -> Unit,
    checkLanguageState: (() -> Unit) -> Unit,
    checkSignInState: (() -> Unit, () -> Unit) -> Unit,
    moveToMainScreen: () -> Unit,
    moveToLanguageInitScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(checkingState) {
        when (checkingState) {
            SplashCheckingState.Network -> {
                systemUiController.setStatusBarColor(
                    color = Color(0x00000000),
                    darkIcons = false
                )
                systemUiController.setNavigationBarColor(
                    color = Color(0x00000000),
                    darkIcons = false
                )

                delay(3500)

                systemUiController.setStatusBarColor(
                    color = Color(0x00000000),
                    darkIcons = true
                )
                systemUiController.setNavigationBarColor(
                    color = Color(0x00000000),
                    darkIcons = true
                )
                checkNetworkState()
//                moveToMainScreen()
            }
            SplashCheckingState.Language -> {
                checkLanguageState(moveToLanguageInitScreen)
            }
            SplashCheckingState.Authorization -> {
                checkSignInState(moveToMainScreen, moveToSignInScreen)
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
//        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash1))
//        LottieAnimation(composition)
        GlideImage(
            modifier = Modifier.fillMaxSize(),
            imageModel = { R.raw.splash }
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {

}