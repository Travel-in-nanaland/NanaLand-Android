package com.jeju.nanaland.ui.splash

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.SplashCheckingState
import com.jeju.nanaland.ui.splash.component.NetworkConnectionDialog
import com.jeju.nanaland.util.intent.DeepLinkData

@Composable
fun SplashScreen(
    deepLinkData: DeepLinkData,
    moveToMainScreen: () -> Unit,
    moveToLanguageInitScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val checkingState = viewModel.checkingState.collectAsState().value
    var isNetworkConnectionDialogShowing by remember { mutableStateOf(false) }

    LaunchedEffect(checkingState) {
        when (checkingState) {
            SplashCheckingState.Init -> viewModel.checkProcess(deepLinkData)
            SplashCheckingState.Network -> isNetworkConnectionDialogShowing = true
            SplashCheckingState.Language -> moveToLanguageInitScreen()
            SplashCheckingState.Authorization -> moveToSignInScreen()
            SplashCheckingState.Success -> moveToMainScreen()
        }
    }

    SplashScreen(
        isNetworkConnectionDialogShowing = isNetworkConnectionDialogShowing,
        retry = {
            isNetworkConnectionDialogShowing = false
            viewModel.checkProcess(deepLinkData)
        }
    )

}

@Composable
private fun SplashScreen(
    isNetworkConnectionDialogShowing: Boolean,
    retry: () -> Unit,
) {
    val context = LocalContext.current

    SetStatusBar()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.splash)
        )
        val progress by animateLottieCompositionAsState(composition)
        LottieAnimation(
            modifier = Modifier.fillMaxSize(),
            composition = composition,
            progress = { progress },
            contentScale = ContentScale.Crop
        )
    }
    if (isNetworkConnectionDialogShowing) {
        NetworkConnectionDialog(
            exit = { (context as Activity).finish() },
            retry = {
                retry()
            }
        )
    }
}

@Composable
private fun SetStatusBar() {
    rememberSystemUiController().apply {
        setStatusBarColor(
            color = Color(0x00000000),
            darkIcons = false
        )
        setNavigationBarColor(
            color = Color(0x00000000),
            darkIcons = false
        )
    }
}